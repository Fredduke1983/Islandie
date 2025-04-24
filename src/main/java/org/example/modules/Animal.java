package org.example.modules;

public abstract class Animal extends Organism {

    public Animal(String baseName) {
        super(baseName);
    }

    private boolean ateThisTurn;

    public boolean hasEatenThisTurn() {
        return ateThisTurn;
    }

    public void setAteThisTurn(boolean ateThisTurn) {
        this.ateThisTurn = ateThisTurn;
    }

    public void resetEatingFlag() {
        this.ateThisTurn = false;
    }

    public abstract Double getNeedFood();

    public abstract Double getWeight();

    public abstract Double getHealth();

    public abstract Double getSpeed();

    public abstract void setHealth(Double health);

}
