package org.example.services;

import org.example.config.OrganismType;
import org.example.location.Cell;
import org.example.modules.Organism;

public class GrowthGrassService {

    public void addGrass(Cell cell, int column) {
        Organism newGrass = OrganismType.GRASS.createInstance();
        cell.addOrganism(newGrass, column);
    }
}
