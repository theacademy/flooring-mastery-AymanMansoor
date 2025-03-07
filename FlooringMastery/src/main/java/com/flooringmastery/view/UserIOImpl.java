package com.flooringmastery.view;
import java.util.Scanner;

public class UserIOImpl extends UserIO {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return result;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        double result = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return result;
    }
}
