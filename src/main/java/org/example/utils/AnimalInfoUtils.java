package org.example.utils;

import org.example.config.OrganismInfo;

public class AnimalInfoUtils {
    public static OrganismInfo getByName(String name) {
        try {
            return OrganismInfo.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

