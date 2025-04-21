package org.example.utils;

import org.example.config.OrganismType;
import org.example.location.Cell;
import org.example.location.GameField;
import org.example.modules.Organism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowLife {

    GameField gameField;
    public ShowLife(GameField gameField){
        this.gameField = gameField;
    }

    public void current() {
        for (int i = 0; i < gameField.getCells().length; i++) {
            for (int j = 0; j < gameField.getCells()[i].length; j++) {
                Cell cell = gameField.getCells()[i][j];

                List<ArrayList<Organism>> organismGroups = cell.getOrganisms();

                List<Organism> allOrganisms = new ArrayList<>();
                for (ArrayList<Organism> group : organismGroups) {
                    allOrganisms.addAll(group);
                }

                Map<OrganismType, Integer> countMap = new HashMap<>();
                for (OrganismType type : OrganismType.values()) {
                    countMap.put(type, 0);
                }

                for (Organism organism : allOrganisms) {
                    for (OrganismType type : OrganismType.values()) {
                        if (type.getOrganismClass().isInstance(organism)) {
                            countMap.put(type, countMap.get(type) + 1);
                        }
                    }
                }
                areaInfo(i, j, countMap);
            }
        }
    }

    private static void areaInfo(int i, int j, Map<OrganismType, Integer> countMap) {
        System.out.print("Cell[" + i + "][" + j + "]: ");
        for (Map.Entry<OrganismType, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.print(entry.getKey().name() + "=" + entry.getValue() + " ");
            }
        }
        System.out.println();
    }
}
