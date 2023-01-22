package com.kodilla.rps.validator;

public final class RpsSelectionValidator implements Validator {

    @Override
    public boolean validate(String userEntry) {
        return switch (userEntry) {
            case "1", "2", "3", "x", "n" -> true;
            default -> false;
        };
    }
}
