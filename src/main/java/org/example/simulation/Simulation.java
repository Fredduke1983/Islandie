package org.example.simulation;

import org.example.location.Cell;
import org.example.location.GameField;
import org.example.services.GrowthGrassService;
import org.example.services.HuntingService;
import org.example.services.MovingService;
import org.example.services.ReproductService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {
    GameField gameField;

    public Simulation(GameField gameField) {
        this.gameField = gameField;
    }

    public void oneCycle() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
             final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ) {
            HuntingService huntingService = new HuntingService();
            ReproductService reproductService = new ReproductService();
            MovingService movingService = new MovingService(gameField);
            GrowthGrassService growthGrassService = new GrowthGrassService();

            for (int i = 0; i < gameField.getCells().length; i++) {
                for (int j = 0; j < gameField.getCells()[i].length; j++) {
                    final int coordX = i;
                    final int coordY = j;

                    Cell cell = gameField.getCell(coordX, coordY);
//                    printCellArea(coordX, coordY);

                    executorService.submit(() -> {
                        huntingService.hunting(cell, coordX, coordY);
                        reproductService.reproduce(cell, coordX, coordY);
                        movingService.moving(cell, coordX, coordY);

                        Runnable growthGrassTask = () -> growthGrassService.addGrass(cell, coordX);
                        scheduler.schedule(growthGrassTask, 100, TimeUnit.MILLISECONDS);
                    });
                }
            }
            executorService.shutdown();
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printCellArea(int i, int j) {
        System.out.println("--------------------------");
        System.out.println("AREA: [" + i + "][" + j + "]");
    }
}
