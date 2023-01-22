package com.kodilla.rps.validator;

public class DifficultyLevelValidator implements Validator {

    @Override
    public boolean validate(String userEntry) {
        return switch (userEntry) {
            case "e", "h" -> true;
            default -> false;
        };
    }
}
