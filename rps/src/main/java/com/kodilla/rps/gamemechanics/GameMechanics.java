package com.kodilla.rps.gamemechanics;

import com.kodilla.rps.computerAI.ComputerAi;
import com.kodilla.rps.user.User;
import com.kodilla.rps.user.UserChoice;

public final class GameMechanics {

    private final User player;
    private final User computer;
    private final int roundCount;
    private final ComputerAi computerAi;
    private final WinnerChecker winnerChecker = new WinnerChecker();
    private final GameType gameType;

    private GameMechanics(User player, User computer, int roundCount, GameType gameType, ComputerAi computerAi) {
        this.player = player;
        this.computer = computer;
        this.roundCount = roundCount;
        this.gameType = gameType;
        this.computerAi = computerAi;
    }

    public void setPlayerSelection(String userEntry) {
        switch (userEntry) {
            case "1" -> player.setUserChoice(UserChoice.ROCK);
            case "2" -> player.setUserChoice(UserChoice.PAPER);
            case "3" -> player.setUserChoice(UserChoice.SCISSORS);
            case "4" -> player.setUserChoice(UserChoice.LIZARD);
            case "5" -> player.setUserChoice(UserChoice.SPOCK);
            case "x" -> player.setUserChoice(UserChoice.END_GAME);
            case "n" -> player.setUserChoice(UserChoice.NEW_GAME);
        }
    }

    public void setComputerSelection() {
        UserChoice computerChoice = computerAi.randomizeComputer(gameType, player.getUserChoice());
        computer.setUserChoice(computerChoice);
    }

    public boolean playerSelectedEnd() {
        return player.getUserChoice() == UserChoice.END_GAME;
    }

    public boolean playerSelectedNewGame() {
        return player.getUserChoice() == UserChoice.NEW_GAME;
    }

    public User findWinner() {
        return winnerChecker.checkForWinner(player, computer);
    }

    public void addWin(User winner) {
        if(player.equals(winner)) {
            player.addWin();
        }
        if(computer.equals(winner)) {
            computer.addWin();
        }
    }

    public boolean gameFinished() {
        return player.getWinCount() >= roundCount || computer.getWinCount() >= roundCount;
    }

    public User getGameWinner() {
        if(player.getWinCount() >= roundCount) {
            return player;
        }
        if(computer.getWinCount() >= roundCount) {
            return computer;
        }
        return null;
    }

    public static class GameMechanicsBuilder {

        private User player;
        private User computer;
        private int roundCount;
        private ComputerAi computerAi;
        private GameType gameType;

        public GameMechanicsBuilder player(String name) {
            this.player = new User(name);
            return this;
        }

        public GameMechanicsBuilder computer() {
            this.computer = new User("Computer");
            return this;
        }

        public GameMechanicsBuilder roundCount(String roundCount) {
            this.roundCount = Integer.parseInt(roundCount);
            return this;
        }

        public GameMechanicsBuilder computerAi(String aiSelection) {
            this.computerAi = new ComputerAi(aiSelection);
            return this;
        }

        public GameMechanicsBuilder gameType(String gameType) {
            if (gameType.equals("e")) {
                this.gameType = GameType.EXTENDED;
            } else {
                this.gameType = GameType.BASIC;
            }
            return this;
        }

        public GameMechanics build() {
            return new GameMechanics(this.player, this.computer, this.roundCount, this.gameType, this.computerAi);
        }
    }
    public User getPlayer() {
        return player;
    }

    public User getComputer() {
        return computer;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public GameType getGameType() {
        return gameType;
    }

    public ComputerAi getComputerAi() {
        return computerAi;
    }
}
