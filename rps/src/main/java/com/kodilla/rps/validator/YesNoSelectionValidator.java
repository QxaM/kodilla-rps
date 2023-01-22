package com.kodilla.rps.validator;

public final class YesNoSelectionValidator implements Validator {

    public static boolean didConfirm(String userEntry) {
        return userEntry.equals("y");
    }

    @Override
    public boolean validate(String userEntry) {
        return switch (userEntry) {
            case "y", "n" -> true;
            default -> false;
        };
    }
}
