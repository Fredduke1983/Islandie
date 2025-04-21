package org.example.services;

import java.util.Scanner;

public class CLI {

    private int lifeTime;

    public CLI(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public int getLifeTime() {
        while (lifeTime <= 0) {
            try {
                System.out.print("Уведіть ціле число життя острова в секундах: ");
                Scanner scanner = new Scanner(System.in);
                lifeTime = Integer.parseInt(scanner.nextLine()) * 1000;
            } catch (NumberFormatException e) {
                System.out.println("Ви ввели не число, або число відємне. Уведіть повторно.");
            }
        }
        return lifeTime;
    }
}
