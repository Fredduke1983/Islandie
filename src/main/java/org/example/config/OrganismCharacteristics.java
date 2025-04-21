package org.example.config;

public class OrganismCharacteristics {
    private final Double maxOrganismsAtCell;
    private final Double needFood;
    private final Double weight;
    private final Double defaultHealth;
    private final Double speed;

    public OrganismCharacteristics(Double maxOrganismsAtCell, Double needFood, Double weight, Double defaultHealth, Double speed) {
        this.maxOrganismsAtCell = maxOrganismsAtCell;
        this.needFood = needFood;
        this.weight = weight;
        this.defaultHealth = defaultHealth;
        this.speed = speed;
    }

    public Double getMaxOrganismsAtCell() {
        return maxOrganismsAtCell;
    }

    public Double getNeedFood() {
        return needFood;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getDefaultHealth() {
        return defaultHealth;
    }

    public Double getSpeed(){
        return speed;
    }
}
