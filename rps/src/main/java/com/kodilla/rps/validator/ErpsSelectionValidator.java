package com.kodilla.rps.validator;

public final class ErpsSelectionValidator implements Validator {

    @Override
    public boolean validate(String userEntry) {
        return switch (userEntry) {
            case "1", "2", "3", "4", "5", "x", "n" -> true;
            default -> false;
        };
    }
}
