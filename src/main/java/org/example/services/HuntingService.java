package org.example.services;

import org.example.config.EatingConfig;
import org.example.modules.*;
import org.example.location.Cell;

import java.util.*;

public class HuntingService {

    public void hunt(Animal hunter, Cell cell, int x, int y) {

        Class<?> foodType = (hunter instanceof Predator) ? Herbivore.class : Plant.class;
        List<Organism> foodList = cell.getOrganisms().stream()
                .flatMap(List::stream)
                .filter(foodType::isInstance)
                .toList();

        Double needFood = hunter.getNeedFood();
        List<Organism> toRemove = new ArrayList<>();

        for (Organism food : foodList) {
            if (!canEat(hunter, food)) {
                hunter.setHealth(hunter.getHealth() - 25);
//                System.out.println(food + " runaway from " + hunter.getName() + " and Hunter lost " + 25 + "hp");
                break;
            }

            double foodWeight = (food instanceof Herbivore h) ? h.getWeight() : 1.0;
            needFood -= foodWeight;
            toRemove.add(food);
            hunter.setAteThisTurn(true);
//            System.out.println(hunter.getName() + " ate " + food + " with " + foodWeight + "kg.");

            if (needFood <= 0) {
                hunter.setHealth(100.0);
                break;
            } else if (hunter.getHealth() <= 75) {
                hunter.setHealth(hunter.getHealth() + 25);
                break;
            }
            break;
        }

        if (!hunter.hasEatenThisTurn()) {
            hunter.setHealth(hunter.getHealth() - 25);
//            System.out.println(hunter.getName() + " didn’t eat and lost 25hp. Now: " + hunter.getHealth() + "hp");

            if (hunter.getHealth() <= 0) {
//                System.out.println(hunter.getName() + " died of hunger in [" + x + "][" + y + "]");
                toRemove.add(hunter);
            }
        }
        removeOrganismsFromCell(cell, toRemove);
    }

    private boolean canEat(Animal hunter, Organism food) {
        int probability = EatingConfig.getProbability(hunter.getClass(), food.getClass());
        return new Random().nextInt(100) < probability;
    }

    private void removeOrganismsFromCell(Cell cell, List<Organism> toRemove) {
        for (Organism organism : toRemove) {
            for (List<Organism> list : cell.getOrganisms()) {
                list.removeIf(o -> o == organism);
            }
        }
    }

    public void hunting(Cell cell, int i, int j) {
        HuntingService huntingService = new HuntingService();
        List<Organism> allOrganisms = new ArrayList<>();

        for (List<Organism> organisms : cell.getOrganisms()) {
            allOrganisms.addAll(organisms);
        }

        for (int x = 0; x < allOrganisms.size(); x++) {
            Organism organism = allOrganisms.get(x);

            if (organism instanceof Animal animal) {
                animal.resetEatingFlag();
                huntingService.hunt(animal, cell, i, j);
            }
        }
    }
}
