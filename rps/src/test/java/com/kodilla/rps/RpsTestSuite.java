package com.kodilla.rps;

import com.kodilla.rps.computerAI.ComputerType;
import com.kodilla.rps.gamemechanics.GameMechanics;
import com.kodilla.rps.gamemechanics.GameType;
import com.kodilla.rps.gamemechanics.WinnerChecker;
import com.kodilla.rps.user.User;
import com.kodilla.rps.user.UserChoice;
import com.kodilla.rps.validator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RpsTestSuite {

    @Nested
    public class UserTestSuite {

        @Test
        void testAddWin() {
            //Given
            User user = new User("User");

            //When
            user.addWin();

            //Then
            assertEquals(1, user.getWinCount());
        }
    }

    @Nested
    public class ValidatorTestSuite {

        @Test
        void testValidateWinCount() {
            //Given
            UserValidator userValidator = new UserValidator();
            WinCountValidator winCountValidator = new WinCountValidator();

            //When
            boolean notNumberValidation = userValidator.validateUserSelection("a", winCountValidator);
            boolean zeroValidation = userValidator.validateUserSelection("0", winCountValidator);
            boolean biggerThanZeroValidation = userValidator.validateUserSelection("3", winCountValidator);

            //Then
            assertAll(() -> assertFalse(notNumberValidation),
                    () -> assertFalse(zeroValidation),
                    () -> assertTrue(biggerThanZeroValidation));
        }

        @Test
        void testValidateRpsSelection() {
            //Given
            UserValidator userValidator = new UserValidator();
            RpsSelectionValidator rpsSelectionValidator = new RpsSelectionValidator();

            //When
            boolean rockValidation = userValidator.validateUserSelection("1", rpsSelectionValidator);
            boolean paperValidation = userValidator.validateUserSelection("2", rpsSelectionValidator);
            boolean scissorsValidation = userValidator.validateUserSelection("3", rpsSelectionValidator);
            boolean endValidation = userValidator.validateUserSelection("x", rpsSelectionValidator);
            boolean newGameValidation = userValidator.validateUserSelection("n", rpsSelectionValidator);
            boolean otherValidation = userValidator.validateUserSelection("m", rpsSelectionValidator);

            //Then
            assertAll(() -> assertTrue(rockValidation),
                    () -> assertTrue(paperValidation),
                    () -> assertTrue(scissorsValidation),
                    () -> assertTrue(endValidation),
                    () -> assertTrue(newGameValidation),
                    () -> assertFalse(otherValidation));
        }

        @Test
        void testValidateErpsSelection() {
            //Given
            UserValidator userValidator = new UserValidator();
            ErpsSelectionValidator erpsSelectionValidator = new ErpsSelectionValidator();

            //When
            boolean rockValidation = userValidator.validateUserSelection("1", erpsSelectionValidator);
            boolean paperValidation = userValidator.validateUserSelection("2", erpsSelectionValidator);
            boolean scissorsValidation = userValidator.validateUserSelection("3", erpsSelectionValidator);
            boolean lizardValidation = userValidator.validateUserSelection("4", erpsSelectionValidator);
            boolean spockValidation = userValidator.validateUserSelection("5", erpsSelectionValidator);
            boolean endValidation = userValidator.validateUserSelection("x", erpsSelectionValidator);
            boolean newGameValidation = userValidator.validateUserSelection("n", erpsSelectionValidator);
            boolean otherValidation = userValidator.validateUserSelection("m", erpsSelectionValidator);

            //Then
            assertAll(() -> assertTrue(rockValidation),
                    () -> assertTrue(paperValidation),
                    () -> assertTrue(scissorsValidation),
                    () -> assertTrue(lizardValidation),
                    () -> assertTrue(spockValidation),
                    () -> assertTrue(endValidation),
                    () -> assertTrue(newGameValidation),
                    () -> assertFalse(otherValidation));
        }

        @Test
        void testYesNoValidator() {
            //Given
            UserValidator userValidator = new UserValidator();
            YesNoSelectionValidator yesNoSelectionValidator = new YesNoSelectionValidator();

            //When
            boolean yesValidation = userValidator.validateUserSelection("y", yesNoSelectionValidator);
            boolean noValidation = userValidator.validateUserSelection("n", yesNoSelectionValidator);
            boolean otherValidation = userValidator.validateUserSelection("x", yesNoSelectionValidator);

            //Then
            assertAll(() -> assertTrue(yesValidation),
                    () -> assertTrue(noValidation),
                    () -> assertFalse(otherValidation));
        }

        @Test
        void testDidConfirm() {
            //Given

            //When
            boolean confirmed = YesNoSelectionValidator.didConfirm("y");
            boolean didNotConfirmed = YesNoSelectionValidator.didConfirm("n");

            //Then
            assertAll(() -> assertFalse(didNotConfirmed),
                    () -> assertTrue(confirmed));

        }

        @Test
        void testDifficultyLevelValidator() {
            //Given
            UserValidator userValidator = new UserValidator();
            DifficultyLevelValidator difficultyLevelValidator = new DifficultyLevelValidator();

            //When
            boolean easyValidation = userValidator.validateUserSelection("e", difficultyLevelValidator);
            boolean hardValidation = userValidator.validateUserSelection("h", difficultyLevelValidator);
            boolean otherValidation = userValidator.validateUserSelection("m", difficultyLevelValidator);

            //Then
            assertAll(() -> assertTrue(easyValidation),
                    () -> assertTrue(hardValidation),
                    () -> assertFalse(otherValidation));

        }

        @Test
        void testGameTypeValidation() {
            //Given
            UserValidator userValidator = new UserValidator();
            GameTypeValidator gameTypeValidator = new GameTypeValidator();

            //When
            boolean basicValidation = userValidator.validateUserSelection("b", gameTypeValidator);
            boolean extendedValidation = userValidator.validateUserSelection("e", gameTypeValidator);
            boolean otherValidation = userValidator.validateUserSelection("m", gameTypeValidator);

            //Then
            assertAll(() -> assertTrue(basicValidation),
                    () -> assertTrue(extendedValidation),
                    () -> assertFalse(otherValidation));

        }
    }

    @Nested
    public class GameMechanicsTestSuite {

        private static GameMechanics gameMechanics;

        @BeforeEach
        public void initializeGameMechanics() {
            gameMechanics = new GameMechanics.GameMechanicsBuilder()
                    .player("Player1")
                    .computer()
                    .roundCount("3")
                    .gameType("b")
                    .computerAi("e")
                    .build();
        }

        @Test
        void testGameMechanicsBuilder() {
            //Given

            //When
            String playerName = gameMechanics.getPlayer().getName();
            String computerName = gameMechanics.getComputer().getName();
            int roundCount = gameMechanics.getRoundCount();
            GameType gameType = gameMechanics.getGameType();
            ComputerType computerType = gameMechanics.getComputerAi().getComputerType();

            //Then
            assertAll(() -> assertEquals("Player1", playerName),
                    () -> assertEquals("Computer", computerName),
                    () -> assertEquals(3, roundCount),
                    () -> assertEquals(GameType.BASIC, gameType),
                    () -> assertEquals(ComputerType.EASY, computerType));
        }

        @Test
        void testSetUserSelection() {
            //Given

            //When
            gameMechanics.setPlayerSelection("1");
            UserChoice rockChoice = gameMechanics.getPlayer().getUserChoice();
            gameMechanics.setPlayerSelection("2");
            UserChoice paperChoice = gameMechanics.getPlayer().getUserChoice();
            gameMechanics.setPlayerSelection("3");
            UserChoice scissorsChoice = gameMechanics.getPlayer().getUserChoice();
            gameMechanics.setPlayerSelection("x");
            UserChoice endChoice = gameMechanics.getPlayer().getUserChoice();
            gameMechanics.setPlayerSelection("n");
            UserChoice newGameChoice = gameMechanics.getPlayer().getUserChoice();

            //Then
            assertAll(() -> assertEquals(UserChoice.ROCK, rockChoice),
                    () -> assertEquals(UserChoice.PAPER, paperChoice),
                    () -> assertEquals(UserChoice.SCISSORS, scissorsChoice),
                    () -> assertEquals(UserChoice.END_GAME, endChoice),
                    () -> assertEquals(UserChoice.NEW_GAME, newGameChoice));
        }

        @Test
        void testPlayerSelectedEnd(){
            //Given

            //When
            gameMechanics.setPlayerSelection("x");
            boolean playerSelectedEnd = gameMechanics.playerSelectedEnd();

            //Then
            assertTrue(playerSelectedEnd);
        }

        @Test
        void testPlayerSelectedNewGame(){
            //Given

            //When
            gameMechanics.setPlayerSelection("n");
            boolean playerSelectedNewGame = gameMechanics.playerSelectedNewGame();

            //Then
            assertTrue(playerSelectedNewGame);
        }

        @Test
        void testAddWinPlayer() {
            //Given
            User user = gameMechanics.getPlayer();

            //When
            gameMechanics.addWin(user);
            int playerWins = gameMechanics.getPlayer().getWinCount();
            int computerWins = gameMechanics.getComputer().getWinCount();

            //Then
            assertAll(() -> assertEquals(1, playerWins),
                    () -> assertEquals(0, computerWins));
        }

        @Test
        void testAddWinComputer() {
            //Given
            User user = gameMechanics.getComputer();

            //When
            gameMechanics.addWin(user);
            int playerWins = gameMechanics.getPlayer().getWinCount();
            int computerWins = gameMechanics.getComputer().getWinCount();

            //Then
            assertAll(() -> assertEquals(0, playerWins),
                    () -> assertEquals(1, computerWins));

        }

        @Test
        void testAddWinNull() {
            //Given
            User user = null;

            //When
            gameMechanics.addWin(user);
            int playerWins = gameMechanics.getPlayer().getWinCount();
            int computerWins = gameMechanics.getComputer().getWinCount();

            //Then
            assertAll(() -> assertEquals(0, playerWins),
                    () -> assertEquals(0, computerWins));

        }

        @Test
        void testGameFinishedPlayerWins() {
            //Given
            User winner = gameMechanics.getPlayer();
            gameMechanics.addWin(winner);
            gameMechanics.addWin(winner);
            gameMechanics.addWin(winner);

            //When
            boolean isFinished = gameMechanics.gameFinished();

            //Then
            assertTrue(isFinished);
        }

        @Test
        void testGameFinishedComputerWins() {
            //Given
            User winner = gameMechanics.getComputer();
            gameMechanics.addWin(winner);
            gameMechanics.addWin(winner);
            gameMechanics.addWin(winner);

            //When
            boolean isFinished = gameMechanics.gameFinished();

            //Then
            assertTrue(isFinished);
        }

        @Test
        void testGameFinishedNoneWins() {
            //Given
            User winner = gameMechanics.getComputer();
            gameMechanics.addWin(winner);
            gameMechanics.addWin(winner);

            //When
            boolean isFinished = gameMechanics.gameFinished();

            //Then
            assertFalse(isFinished);
        }

        @Test
        void testFindWinnerPlayer() {
            //Given
            gameMechanics.getPlayer().setUserChoice(UserChoice.ROCK);
            gameMechanics.getComputer().setUserChoice(UserChoice.SCISSORS);

            //When
            User winner = gameMechanics.findWinner();

            //Then
            assertEquals(gameMechanics.getPlayer(), winner);
        }

        @Test
        void testFindWinnerComputer() {
            //Given
            gameMechanics.getPlayer().setUserChoice(UserChoice.ROCK);
            gameMechanics.getComputer().setUserChoice(UserChoice.PAPER);

            //When
            User winner = gameMechanics.findWinner();

            //Then
            assertEquals(gameMechanics.getComputer(), winner);
        }
    }

    @Nested
    public class WinnerCheckerTestSuite {

        @Test
        void testCheckForWinnerUser1Beats2() {
            //Given
            WinnerChecker winnerChecker = new WinnerChecker();
            User user1 = new User("User1");
            user1.setUserChoice(UserChoice.ROCK);
            User user2 = new User("User2");
            user2.setUserChoice(UserChoice.SCISSORS);

            //When
            User winner = winnerChecker.checkForWinner(user1, user2);

            //Then
            assertEquals(user1, winner);
        }

        @Test
        void testCheckForWinnerUser2Beats1() {
            //Given
            WinnerChecker winnerChecker = new WinnerChecker();
            User user1 = new User("User1");
            user1.setUserChoice(UserChoice.ROCK);
            User user2 = new User("User2");
            user2.setUserChoice(UserChoice.PAPER);

            //When
            User winner = winnerChecker.checkForWinner(user1, user2);

            //Then
            assertEquals(user2, winner);
        }

        @Test
        void testCheckForWinnerDraw() {
            //Given
            WinnerChecker winnerChecker = new WinnerChecker();
            User user1 = new User("User1");
            user1.setUserChoice(UserChoice.ROCK);
            User user2 = new User("User2");
            user2.setUserChoice(UserChoice.ROCK);

            //When
            User winner = winnerChecker.checkForWinner(user1, user2);

            //Then
            assertNull(winner);
        }
    }
}
