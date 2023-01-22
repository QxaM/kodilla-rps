package com.kodilla.rps.computerAI;

import com.kodilla.rps.gamemechanics.BeatRules;
import com.kodilla.rps.gamemechanics.GameType;
import com.kodilla.rps.user.UserChoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ComputerAi {

    private final ComputerType computerType;

    public ComputerAi(String computerType) {
        if (computerType.equals("h")) {
            this.computerType = ComputerType.HARD;
        } else {
            this.computerType = ComputerType.EASY;
        }
    }

    public UserChoice randomizeComputer(GameType gameType, UserChoice userChoice) {
        if (ComputerType.HARD == computerType) {
            return hardRandomizer(gameType, userChoice);
        } else {
            return easyRandomizer(gameType);
        }
    }

    private UserChoice easyRandomizer(GameType gameType) {
        Random random = new Random();
        int selection;
        if (gameType == GameType.EXTENDED){
            selection = random.nextInt(1, 6);
        } else {
            selection = random.nextInt(1, 4);
        }

        return switch (selection) {
            case 1 -> UserChoice.ROCK;
            case 2 -> UserChoice.PAPER;
            case 3 -> UserChoice.SCISSORS;
            case 4 -> UserChoice.LIZARD;
            case 5 -> UserChoice.SPOCK;
            default -> UserChoice.ROCK;
        };
    }

    private UserChoice hardRandomizer(GameType gameType, UserChoice userChoice) {
        List<UserChoice> listElements = new ArrayList<>();

        List<UserChoice> winningChoice = findWinningChoice(userChoice);
        if (GameType.BASIC == gameType) {
            listElements.add(UserChoice.ROCK);
            listElements.add(UserChoice.PAPER);
            listElements.add(UserChoice.SCISSORS);
            for(UserChoice choice: winningChoice) {
                if(choice != UserChoice.LIZARD && choice != UserChoice.SPOCK) {
                    listElements.add(choice);
                }
            }
        } else {
            for (int i=0; i<2; i++ ){
                listElements.add(UserChoice.ROCK);
                listElements.add(UserChoice.PAPER);
                listElements.add(UserChoice.SCISSORS);
                listElements.add(UserChoice.LIZARD);
                listElements.add(UserChoice.SPOCK);
            }
            listElements.addAll(winningChoice);
        }

        Random random = new Random();
        int selection = random.nextInt(listElements.size());

       return listElements.get(selection);
    }

    private List<UserChoice> findWinningChoice(UserChoice userChoice) {
        BeatRules beatRules = new BeatRules();
        List<UserChoice> winningChoices = new ArrayList<>();

        for (UserChoice choice: beatRules.getBeatingMap().keySet()) {
            if(beatRules.getBeatingMap().get(choice).contains(userChoice)) {
                winningChoices.add(choice);
            }
        }
        return winningChoices;
    }

    public ComputerType getComputerType() {
        return computerType;
    }
}
