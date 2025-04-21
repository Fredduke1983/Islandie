package org.example.modules.herbivores;

import org.example.config.OrganismInfo;
import org.example.modules.Herbivore;

public class Deer extends Herbivore {

    private Double health;
    private final Double speed;
    private final OrganismInfo info = OrganismInfo.DEER;

    public Deer(String baseName) {
        super("Deer");
        this.health = info.getCharacteristics().getDefaultHealth();
        this.speed = info.getCharacteristics().getSpeed();
    }

    @Override
    public String toString() {
        return "DEER";
    }

    @Override
    public Double getMaxOrganismsAtCell() {
        return info.getCharacteristics().getMaxOrganismsAtCell();
    }

    @Override
    public Double getNeedFood() {
        return info.getCharacteristics().getNeedFood();
    }

    @Override
    public Double getWeight() {
        return info.getCharacteristics().getWeight();
    }

    @Override
    public Double getHealth() {
        return this.health;
    }

    @Override
    public Double getSpeed() {
        return speed;
    }

    @Override
    public void setHealth(Double health) {
        this.health = health;
    }
}
