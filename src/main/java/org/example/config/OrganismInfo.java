package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.location.GameField;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public enum OrganismInfo {

    RABBIT, DEER, HORSE, MOUSE, BEAR, WOLF, FOX, SNAKE, EAGLE, GRASS;

    private static final Map<OrganismInfo, OrganismCharacteristics> characteristicsMap = new EnumMap<>(OrganismInfo.class);
    static final ObjectMapper mapper = new ObjectMapper();

    static {
        gameConfig();
    }

    private static void gameConfig() {
        InputStream is = GameField.class.getClassLoader().getResourceAsStream("game-field-config.json");
        try {
            Map<String, Object> configData = mapper.readValue(is, Map.class);

            configData.forEach((key, value) -> {
                if (value instanceof Map) {
                    Map<String, Object> innerMap = (Map<String, Object>) value;
                    if (key.equals("organism-info")) {
                        innerMap.forEach((keyAnimal, valueAnimalCharacts) -> {
                            OrganismInfo info = OrganismInfo.valueOf(keyAnimal);
                            if (valueAnimalCharacts instanceof Map) {

                                Map<String, Double> values = (HashMap<String, Double>) valueAnimalCharacts;

                                OrganismCharacteristics characteristics = new OrganismCharacteristics(
                                        values.get("maxOrganismsAtCell"),
                                        values.get("needFood"),
                                        values.get("weight"),
                                        values.get("defaultHealth"),
                                        values.get("speed")
                                );
                                characteristicsMap.put(info, characteristics);
                            }
                        });
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("e.getMessage() error = " + e.getMessage());
        }
    }

    public OrganismCharacteristics getCharacteristics() {
        return characteristicsMap.get(this);
    }
}

