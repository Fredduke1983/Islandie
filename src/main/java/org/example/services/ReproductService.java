package org.example.services;

import org.example.config.OrganismType;
import org.example.modules.Animal;
import org.example.modules.Organism;
import org.example.location.Cell;

import java.util.*;

public class ReproductService {

    public void reproduce(Cell cell, int i, int j) {
        synchronized (cell) {
            List<ArrayList<Organism>> organismsInCell = cell.getOrganisms();

            for (ArrayList<Organism> groupOfOrganisms : organismsInCell) {

                if (groupOfOrganisms.size() < 2 || !(groupOfOrganisms.getFirst() instanceof Animal)) continue;
                Set<Animal> alreadyPaired = new HashSet<>();

                OrganismType typeOfAnimal = Arrays.stream(OrganismType.values())
                        .filter(animal -> !groupOfOrganisms.isEmpty() && animal.getOrganismClass().equals(groupOfOrganisms.getFirst().getClass()))
                        .findFirst()
                        .orElse(null);
                if (typeOfAnimal == null) continue;

                Double maxPerCell = typeOfAnimal.getMaxCountPerCell();

                for (int x = 0; x < groupOfOrganisms.size(); x++) {
                    Animal animal1 = (Animal) groupOfOrganisms.get(x);
                    if (alreadyPaired.contains(animal1)) continue;

                    for (int y = x + 1; y < groupOfOrganisms.size(); y++) {
                        Animal animal2 = (Animal) groupOfOrganisms.get(y);
                        if (alreadyPaired.contains(animal2)) continue;

                        if (groupOfOrganisms.size() < maxPerCell) {
                            if (new Random().nextInt(100) < 50) {
                                Organism newOrganism = typeOfAnimal.createInstance();
                                groupOfOrganisms.add(newOrganism);
                                alreadyPaired.add(animal1);
                                alreadyPaired.add(animal2);

//                            System.out.println(animal1.getName() + " and " + animal2.getName() + " reproduced in cell " + "[" + i + "]" + "[" + j + "]");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
