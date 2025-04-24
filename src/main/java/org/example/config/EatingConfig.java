package org.example.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.modules.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EatingConfig {

    private static final Map<OrganismType, Map<OrganismType, Integer>> probabilityMap = new HashMap<>();

    static {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = EatingConfig.class.getClassLoader().getResourceAsStream("eating-config.json")) {
            Map<String, Map<String, Integer>> animalsData = mapper.readValue(inputStream, new TypeReference<>() {});
            for (var entry : animalsData.entrySet()) {
                OrganismType hunter = OrganismType.valueOf(entry.getKey());
                Map<OrganismType, Integer> foodMap = new HashMap<>();
                for (var foodEntry : entry.getValue().entrySet()) {
                    foodMap.put(OrganismType.valueOf(foodEntry.getKey()), foodEntry.getValue());
                }
                probabilityMap.put(hunter, foodMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int getProbability(Class<? extends Animal> hunter, Class<? extends Organism> food) {
        return probabilityMap
                .getOrDefault(hunter, Map.of())
                .getOrDefault(food, 0);
    }
}


