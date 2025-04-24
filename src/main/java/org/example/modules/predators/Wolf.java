package org.example.modules.predators;

import org.example.config.OrganismInfo;
import org.example.modules.Predator;

public class Wolf extends Predator {


    private Double health;
    private final Double speed;
    private final OrganismInfo info = OrganismInfo.WOLF;

    public Wolf(String baseName) {
        super("Wolf");
        this.speed = info.getCharacteristics().getSpeed();
        this.health = info.getCharacteristics().getDefaultHealth();
    }

    @Override
    public String toString() {
        return "WOLF";
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
