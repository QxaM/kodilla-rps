package com.kodilla.rps.validator;

public final class FinishGameValidator implements Validator {

    @Override
    public boolean validate(String userEntry) {
        return switch (userEntry) {
            case "x", "n" -> true;
            default -> false;
        };
    }
}
