package com.kodilla.rps;

import com.kodilla.rps.gamemechanics.GameMechanics;
import com.kodilla.rps.user.User;
import com.kodilla.rps.user.UserDialogs;
import com.kodilla.rps.user.UserScanner;
import com.kodilla.rps.validator.*;

public class RpsRunner {
    public static void main(String[] args) {

        UserScanner userScanner = UserScanner.INSTANCE;
        UserValidator userValidator = new UserValidator();

        GameMechanics gameMechanics;

        boolean newGame;
        do {
            newGame = false;
            UserDialogs.printHello();
            UserDialogs.printAskForName();
            String username = userScanner.scanName();

            UserDialogs.printEnterWinCount();
            String winCount = userScanner.scanUserEntry();
            while (!userValidator.validateUserSelection(winCount, new WinCountValidator())) {
                UserDialogs.printWrongWinCount();
                winCount = userScanner.scanUserEntry();
            }

            UserDialogs.printChooseGameType();
            UserDialogs.printEnterSelection();
            String gameType = userScanner.scanUserEntry();
            while (!userValidator.validateUserSelection(gameType, new GameTypeValidator())) {
                UserDialogs.printWrongSelection();
                UserDialogs.printChooseGameType();
                UserDialogs.printEnterSelection();
                gameType = userScanner.scanUserEntry();
            }

            UserDialogs.printChooseDifficultyLevel();
            UserDialogs.printEnterSelection();
            String difficultyLevel = userScanner.scanUserEntry();
            while(!userValidator.validateUserSelection(difficultyLevel, new DifficultyLevelValidator())) {
                UserDialogs.printWrongSelection();
                UserDialogs.printChooseDifficultyLevel();
                UserDialogs.printEnterSelection();
                difficultyLevel = userScanner.scanUserEntry();
            }

            gameMechanics = new GameMechanics.GameMechanicsBuilder()
                    .player(username)
                    .computer()
                    .roundCount(winCount)
                    .computerAi(difficultyLevel)
                    .gameType(gameType)
                    .build();

            boolean end = false;
            do {
                UserDialogs.printHelp(gameMechanics.getGameType());

                Validator rpsValidator = userValidator.getRpsValidator(gameMechanics.getGameType());

                UserDialogs.printEnterSelection();
                String userSelection = userScanner.scanUserEntry();
                while (!userValidator.validateUserSelection(userSelection, rpsValidator)) {
                    UserDialogs.printWrongSelection();
                    UserDialogs.printHelp(gameMechanics.getGameType());
                    UserDialogs.printEnterSelection();
                    userSelection = userScanner.scanUserEntry();
                }

                gameMechanics.setPlayerSelection(userSelection);

                if (gameMechanics.playerSelectedEnd()) {
                    UserDialogs.printConfirmFinishGame();
                    UserDialogs.printYesOrNo();
                    UserDialogs.printEnterSelection();

                    userSelection = userScanner.scanUserEntry();
                    while (!userValidator.validateUserSelection(userSelection, new YesNoSelectionValidator())) {
                        UserDialogs.printWrongSelection();
                        UserDialogs.printYesOrNo();
                        UserDialogs.printEnterSelection();
                        userSelection = userScanner.scanUserEntry();
                    }
                    end = YesNoSelectionValidator.didConfirm(userSelection);
                    continue;
                }

                if (gameMechanics.playerSelectedNewGame()) {
                    UserDialogs.printConfirmNewGame();
                    UserDialogs.printYesOrNo();
                    UserDialogs.printEnterSelection();

                    userSelection = userScanner.scanUserEntry();
                    while (!userValidator.validateUserSelection(userSelection, new YesNoSelectionValidator())) {
                        UserDialogs.printWrongSelection();
                        UserDialogs.printYesOrNo();
                        UserDialogs.printEnterSelection();
                        userSelection = userScanner.scanUserEntry();
                    }
                    newGame = YesNoSelectionValidator.didConfirm(userSelection);
                    continue;
                }


                gameMechanics.setComputerSelection();

                UserDialogs.printUserSelection(gameMechanics.getPlayer());
                UserDialogs.printUserSelection(gameMechanics.getComputer());

                User winner = gameMechanics.findWinner();
                gameMechanics.addWin(winner);
                UserDialogs.printWinner(winner);

                UserDialogs.printScore(gameMechanics.getPlayer(), gameMechanics.getComputer());


                if(gameMechanics.gameFinished()) {
                    UserDialogs.printGameWinner(gameMechanics.getGameWinner());

                    UserDialogs.printStartNewGameOrExit();
                    UserDialogs.printEnterSelection();

                    userSelection = userScanner.scanUserEntry();
                    while(!userValidator.validateUserSelection(userSelection, new FinishGameValidator())) {
                        UserDialogs.printWrongSelection();
                        UserDialogs.printStartNewGameOrExit();
                        UserDialogs.printEnterSelection();
                        userSelection = userScanner.scanUserEntry();
                    }

                    if(userSelection.equals("x")) {
                        end = true;
                    } else if (userSelection.equals("n")) {
                        newGame = true;
                    } else {
                        end = true;
                    }
                }
            } while (!end && !newGame);
        } while (newGame);

        UserDialogs.printGoodbye();
        userScanner.close();
    }
}