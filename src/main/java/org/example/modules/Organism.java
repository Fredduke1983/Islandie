package org.example.modules;

public abstract class Organism {
    public abstract Double getMaxOrganismsAtCell();
    private static int idCounter = 0;
    private final int id;
    private final String name;

    public Organism(String baseName) {
        this.id = ++idCounter;
        this.name = baseName + "-" + id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
