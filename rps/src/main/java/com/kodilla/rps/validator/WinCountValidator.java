package com.kodilla.rps.validator;

public final class WinCountValidator implements Validator {

    @Override
    public boolean validate(String userEntry) {
        int winCount;
        try {
            winCount = Integer.parseInt(userEntry);
        } catch (NumberFormatException e) {
            return false;
        }

        return winCount > 0;
    }
}
