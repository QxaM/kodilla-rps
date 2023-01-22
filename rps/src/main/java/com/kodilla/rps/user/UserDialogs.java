package com.kodilla.rps.user;

import com.kodilla.rps.gamemechanics.GameType;

public class UserDialogs {

    public static void printHello() {
        System.out.println("Hello there!");
    }

    public static void printAskForName() {
        System.out.println("Who are you?");
        System.out.println("Write your name below: ");
    }

    public static void printEnterWinCount() {
        System.out.println("How many wins do you want?");
    }

    public static void printWrongWinCount() {
        System.out.println("Enter proper win count. Win count should be a number bigger than 0!");
    }

    public static void printChooseGameType() {
        System.out.println("Enter game type");
        System.out.println("b - basic (Rock, Paper, Scissors)\n" +
                            "e - extended (Rock, Paper, Scissors, Lizard, Spock");

    }

    public static void printChooseDifficultyLevel() {
        System.out.println("Enter difficulty level");
        System.out.println("e - easy\n" +
                            "h - hard");
    }

    public static void printHelp(GameType gameType) {
        System.out.println("""
                1 - Rock
                2 - Paper
                3 - Scissors""");
        if (gameType == GameType.EXTENDED) {
            System.out.println("4 - Lizard\n" +
                                "5 - Spock");
        }
        System.out.println("x - Finish game\n" +
                            "n - New game");
    }

    public static void printEnterSelection() {
        System.out.println("Enter your selection below: ");
    }

    public static void printWrongSelection() {
        System.out.println("Enter proper selection!");
    }

    public static void printConfirmFinishGame() {
        System.out.println("Do you want to exit game?");
    }

    public static void printConfirmNewGame() {
        System.out.println("Do you want to start new game?");
    }

    public static void printYesOrNo() {
        System.out.println("y - Yes!\n" +
                            "n - No!");
    }

    public static void printUserSelection(User user) {
        System.out.println(user.getName() + " selected " + user.getUserChoice());
    }

    public static void printWinner(User user) {
        if(user == null) {
            System.out.println("This is a draw!");
        } else {
            System.out.println("The winner is " + user.getName() + "!");
        }
    }

    public static void printGameWinner(User user) {
        if(user != null) {
            System.out.println("Game winner is " + user.getName() + "!!!");
        }
    }

    public static void printStartNewGameOrExit() {
        System.out.println("Do you want to start new game or exit?");
        System.out.println("x - Finish game\n" +
                            "n - New game");
    }

    public static void printScore(User player, User computer) {
        System.out.println(player.getName() + " " + player.getWinCount() + " : " +
                            computer.getWinCount() + " " + computer.getName());
    }

    public static void printGoodbye() {
        System.out.println("Goodbye!");
    }
}
