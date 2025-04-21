package org.example.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.OrganismType;
import org.example.modules.Organism;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameField {

    private static int countX;
    private static int countY;
    static ObjectMapper mapper = new ObjectMapper();

    static {
        gameConfig();
    }

    private static void gameConfig() {
        InputStream inputStream = GameField.class.getClassLoader().getResourceAsStream("game-field-config.json");
        try {
            Map<String, Object> configData = mapper.readValue(inputStream, Map.class);
            configData.forEach((key, value) -> {
                if (value instanceof Map) {
                    Map<String, Object> innerMap = (Map<String, Object>) value;
                    if (key.equals("field")) {
                        countX = (int) innerMap.get("width");
                        countY = (int) innerMap.get("height");
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }

    private static final List<OrganismType> organismTypes =
            Arrays.asList(OrganismType.values());

    private static GameField instance;
    private final Cell[][] cells = new Cell[countX][countY];

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

    public void init() {
        for (int i = 0; i < countX; i++) {
            for (int j = 0; j < countY; j++) {
                Cell cell = new Cell();
                for (OrganismType organismType : organismTypes) {
                    cell.addOrganisms(listOrganisms(organismType));
                }
                cells[i][j] = cell;
            }
        }
    }

    public ArrayList<Organism> listOrganisms(OrganismType type) {
        ArrayList<Organism> listOfOrganisms = new ArrayList<>();
        Double maxCount = type.getMaxCountPerCell();
        int randomCount = (int) ThreadLocalRandom.current().nextDouble(maxCount + 1);
        for (int i = 0; i < randomCount; i++) {
            listOfOrganisms.add(type.createInstance());
        }
        return listOfOrganisms;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }
}


