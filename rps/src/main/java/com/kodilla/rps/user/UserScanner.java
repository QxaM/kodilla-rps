package com.kodilla.rps.user;


import java.util.Scanner;

public enum UserScanner {

    INSTANCE;

    private final Scanner scanner = new Scanner(System.in);

    public String scanName() {
        return scanner.nextLine();
    }

    public String scanUserEntry() {
        return scanner.nextLine().toLowerCase();
    }

    public void close() {
        scanner.close();
    }

}
