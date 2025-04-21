package org.example.location;

import org.example.modules.Organism;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private List<ArrayList<Organism>> organisms;

    public Cell() {
        organisms = new ArrayList<>();
    }

    public void addOrganisms(ArrayList<Organism> organism) {
        organisms.add(organism);
    }

    public void addOrganism(Organism organism, int i) {
        organisms.get(i).add(organism);

    }

    public List<ArrayList<Organism>> getOrganisms() {
        return organisms;
    }

    public void removeOrganism(Organism organism, int i) {
        organisms.get(i).remove(organism);
    }

}
