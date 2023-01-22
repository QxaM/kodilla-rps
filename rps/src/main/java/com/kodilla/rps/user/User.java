package com.kodilla.rps.user;

import java.util.Objects;

public final class User {

    private final String name;
    private UserChoice userChoice;
    private int winCount = 0;

    public User(String name) {
        this.name = name;
    }

    public void addWin() {
        winCount++;
    }

    public String getName() {
        return name;
    }

    public UserChoice getUserChoice() {
        return userChoice;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setUserChoice(UserChoice userChoice) {
        this.userChoice = userChoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
