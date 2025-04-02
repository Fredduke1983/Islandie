package org.example.location;

import org.example.entities.Organism;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private List<ArrayList<Organism>> organisms;

    public Cell() {
        organisms = new ArrayList<>();
    }

    public void addOrganism(ArrayList<Organism> organism) {
        organisms.add(organism);
    }

    public List<ArrayList<Organism>> getOrganisms() {
        return organisms;
    }
}
