package org.example.services;

import org.example.modules.Animal;
import org.example.modules.Organism;
import org.example.location.Cell;
import org.example.location.GameField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovingService {
    GameField gameField;

    public MovingService(GameField gameField) {
        this.gameField = gameField;
    }

    private int[] getRandomDirection(int x, int y, int maxX, int maxY) {
        List<int[]> directions = new ArrayList<>();
        if (x > 0) directions.add(new int[]{x - 1, y});       // UP
        if (x < maxX - 1) directions.add(new int[]{x + 1, y}); // DOWN
        if (y > 0) directions.add(new int[]{x, y - 1});       // LEFT
        if (y < maxY - 1) directions.add(new int[]{x, y + 1}); // RIGHT

        return directions.get(new Random().nextInt(directions.size()));
    }

    public void moving(Cell cell, int coordX, int coordY) {
        for (int k = 0; k < cell.getOrganisms().size(); k++) {
            List<Organism> organisms = cell.getOrganisms().get(k);

            List<Organism> copyOrganisms = new ArrayList<>(organisms);
            for (Organism organism : copyOrganisms) {
                if (organism instanceof Animal animal) {

                    int animalSpeed = (int) (double) animal.getSpeed();
                    int newX = coordX;
                    int newY = coordY;

                    for (int l = 0; l < animalSpeed; l++) {
                        int[] newCoords = getRandomDirection(coordX, coordY, gameField.getCells().length, gameField.getCells()[0].length);
                        newX = newCoords[0];
                        newY = newCoords[1];
                    }
                    Cell toCell = gameField.getCells()[newX][newY];

                    Cell firstLock = cell.hashCode() < toCell.hashCode() ? cell : toCell;
                    Cell secondLock = cell == firstLock ? toCell : cell;
                    synchronized (firstLock) {
                        synchronized (secondLock) {
                            moveAnimal(animal, cell, toCell, k);
                        }
                    }
                }
            }
        }
    }

    public void moveAnimal(Animal animal, Cell fromCell, Cell toCell, int i) {
        fromCell.removeOrganism(animal, i);
        toCell.addOrganism(animal, i);
//        System.out.println(animal + " has moved");
    }
}
