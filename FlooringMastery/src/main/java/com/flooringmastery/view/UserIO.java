package com.flooringmastery.view;
import java.util.Scanner;

public class UserIO {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    public double readDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine());
    }

    public void print(String message) {
        System.out.println(message);
    }
}
