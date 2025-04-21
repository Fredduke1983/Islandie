package org.example;

import org.example.location.Cell;
import org.example.location.GameField;
import org.example.modules.Animal;
import org.example.modules.Organism;
import org.example.services.CLI;
import org.example.simulation.Simulation;
import org.example.utils.ShowLife;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        GameField gameField = GameField.getInstance();
        gameField.init();
        Simulation simulation = new Simulation(gameField);
        ShowLife showLife = new ShowLife(gameField);
        CLI cli = new CLI(0);

        int lifeTime = cli.getLifeTime();

        long startTime = System.currentTimeMillis();

        showLife.current();
        while (System.currentTimeMillis() - startTime < lifeTime) {
            simulation.oneCycle();
            if (isIslandLive(gameField)) break;
        }
        showLife.current();
        System.out.println("=====================================");
    }

    static boolean isIslandLive(GameField gameField) {
        Cell[][] cells = gameField.getCells();
        for (Cell[] rowCells : cells) {
            for (Cell cell : rowCells) {
                for (ArrayList<Organism> organisms : cell.getOrganisms()) {
                    for (Organism organism : organisms) {
                        if (organism instanceof Animal) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}