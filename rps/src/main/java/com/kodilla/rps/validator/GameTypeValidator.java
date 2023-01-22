package com.kodilla.rps.validator;

public final class GameTypeValidator implements Validator {

    @Override
    public boolean validate(String userEntry) {
        return switch (userEntry) {
            case "b", "e" -> true;
            default -> false;
        };
    }
}