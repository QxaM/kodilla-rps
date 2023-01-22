package com.kodilla.rps.gamemechanics;

import com.kodilla.rps.user.UserChoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BeatRules {

    private final Map<UserChoice, List<UserChoice>> beatingMap = new HashMap<>();

    public BeatRules() {
        beatingMap.put(UserChoice.ROCK, new ArrayList<>());
        beatingMap.get(UserChoice.ROCK).add(UserChoice.SCISSORS);
        beatingMap.get(UserChoice.ROCK).add(UserChoice.LIZARD);
        beatingMap.put(UserChoice.PAPER, new ArrayList<>());
        beatingMap.get(UserChoice.PAPER).add(UserChoice.ROCK);
        beatingMap.get(UserChoice.PAPER).add(UserChoice.SPOCK);
        beatingMap.put(UserChoice.SCISSORS, new ArrayList<>());
        beatingMap.get(UserChoice.SCISSORS).add(UserChoice.PAPER);
        beatingMap.get(UserChoice.SCISSORS).add(UserChoice.LIZARD);
        beatingMap.put(UserChoice.LIZARD, new ArrayList<>());
        beatingMap.get(UserChoice.LIZARD).add(UserChoice.SPOCK);
        beatingMap.get(UserChoice.LIZARD).add(UserChoice.PAPER);
        beatingMap.put(UserChoice.SPOCK, new ArrayList<>());
        beatingMap.get(UserChoice.SPOCK).add(UserChoice.SCISSORS);
        beatingMap.get(UserChoice.SPOCK).add(UserChoice.ROCK);
    }

    public Map<UserChoice, List<UserChoice>> getBeatingMap() {
        return beatingMap;
    }
}
