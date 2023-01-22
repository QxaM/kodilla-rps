package com.kodilla.rps.validator;

import com.kodilla.rps.gamemechanics.GameType;

public final class UserValidator {

    public Validator getRpsValidator(GameType gameType) {
        if(gameType == GameType.EXTENDED) {
            return new ErpsSelectionValidator();
        } else {
            return new RpsSelectionValidator();
        }
    }

    public boolean validateUserSelection(String userSelection, Validator validator) {
        return validator.validate(userSelection);
    }
}
