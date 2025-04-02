package org.example.location;


import org.example.entities.Organism;
import org.example.entities.herbivores.Deer;
import org.example.entities.herbivores.Rabbit;
import org.example.entities.predators.Fox;
import org.example.entities.predators.Wolf;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameField {

    private static GameField instance;
    private Cell[][] cells = new Cell[10][10];

    private GameField() {
    }

    public static GameField getInstance() {
        if (instance == null) {
            instance = new GameField();
        }
        return instance;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        if (this.cells == null) {
            this.cells = cells;
        }
    }

    public boolean init() throws InstantiationException, IllegalAccessException {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell();

                ArrayList<Organism> rabbits = listOrganisms(Rabbit.class);
                ArrayList<Organism> deers = listOrganisms(Deer.class);
                ArrayList<Organism> wolfs = listOrganisms(Wolf.class);
                ArrayList<Organism> foxes = listOrganisms(Fox.class);

                cell.addOrganism(rabbits);
                cell.addOrganism(deers);
                cell.addOrganism(wolfs);
                cell.addOrganism(foxes);

                cells[i][j] = cell;
//                System.out.println(cell.getOrganisms());
            }
        }
        return true;
    }

    ArrayList<Organism> listOrganisms(Class<?> thisClass) throws InstantiationException, IllegalAccessException {
        ArrayList<Organism> listOfOrganisms = new ArrayList<>();

        int threadLocalRandom = ThreadLocalRandom.current().nextInt(4);

        for (int i = 0; i < threadLocalRandom; i++) {
            listOfOrganisms.add((Organism) thisClass.newInstance());
        }
        return listOfOrganisms;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

}
