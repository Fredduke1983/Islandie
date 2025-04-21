package org.example.config;

import org.example.modules.Organism;
import org.example.modules.herbivores.*;
import org.example.modules.plants.Grass;
import org.example.modules.predators.Eagle;
import org.example.modules.predators.Fox;
import org.example.modules.predators.Snake;
import org.example.modules.predators.Wolf;
import org.example.utils.AnimalInfoUtils;

import java.util.function.Function;

public enum OrganismType {

    RABBIT(Rabbit.class, Rabbit::new),
    DEER(Deer.class, Deer::new),
    HORSE(Horse.class, Horse::new),
    MOUSE(Mouse.class, Mouse::new),
    BEAR(Bear.class, Bear::new),
    WOLF(Wolf.class, Wolf::new),
    FOX(Fox.class, Fox::new),
    SNAKE(Snake.class, Snake::new),
    EAGLE(Eagle.class, Eagle::new),
    GRASS(Grass.class, Grass::new);

    private static int globalId = 0;

    private final Function<String, Organism> creator;
    private final Class<? extends Organism> organismClass;

    OrganismType(Class<? extends Organism> organismClass, Function<String, Organism> creator) {
        this.creator = creator;
        this.organismClass = organismClass;
    }

    public Organism createInstance() {
        globalId++;
        String name = this.name().charAt(0) + this.name().substring(1).toLowerCase() + "-" + globalId;
        return creator.apply(name);
    }

    public Double getMaxCountPerCell() {
        OrganismInfo info = AnimalInfoUtils.getByName(this.name());
        return info != null ? info.getCharacteristics().getMaxOrganismsAtCell() : 0;
    }

    public Class<? extends Organism> getOrganismClass() {
        return organismClass;
    }
}
