package org.example.modules.plants;

import org.example.config.OrganismInfo;
import org.example.modules.Plant;

public class Grass extends Plant {

    private final OrganismInfo info = OrganismInfo.GRASS;

    public Grass(String name) {
        super("Grass");
    }

    @Override
    public Double getMaxOrganismsAtCell() {
        return info.getCharacteristics().getMaxOrganismsAtCell();
    }

    @Override
    public String toString() {
        return "Grass";
    }
}
