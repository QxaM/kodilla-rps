package com.kodilla.rps.gamemechanics;

import com.kodilla.rps.user.User;
import com.kodilla.rps.user.UserChoice;

public final class WinnerChecker {

    private final BeatRules beatRules = new BeatRules();

    public User checkForWinner(User player, User computer) {
        UserChoice playerChoice = player.getUserChoice();
        UserChoice computerChoice = computer.getUserChoice();

        boolean playerBeatsComputer = beatRules.getBeatingMap().get(playerChoice).contains(computerChoice);
        boolean computerBeatsPlayer = beatRules.getBeatingMap().get(computerChoice).contains(playerChoice);

        if(playerBeatsComputer && !computerBeatsPlayer) {
            return player;
        } else if (computerBeatsPlayer && !playerBeatsComputer) {
            return computer;
        } else {
            return null;
        }
    }
}
