package org.example;

import org.example.location.GameField;
import org.example.entities.Organism;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        GameField gameField = GameField.getInstance();
        gameField.init();

        System.out.println(gameField.getCell(2, 4).getOrganisms());
        List<ArrayList<Organism>> organisms = gameField.getCell(2, 4).getOrganisms();
        System.out.println(organisms.get(0).size());
        System.out.println(organisms.get(0).remove(0));
        System.out.println(organisms.get(0).size());


    }
}