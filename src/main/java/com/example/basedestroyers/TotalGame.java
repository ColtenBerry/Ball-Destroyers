package com.example.basedestroyers;

import com.example.basedestroyers.Core.Base;
import com.example.basedestroyers.Core.Soldier;

import java.util.ArrayList;

public class TotalGame {
    private boolean story = false;
    private int roundsPlayed = 0;
    private static final TotalGame game = new TotalGame();
    private ArrayList<Base> bases = new ArrayList<>();
    private ArrayList<Soldier> soldiers = new ArrayList<>();
    private double gameHeight;
    private double gameWidth;
    public TotalGame() {
    }
    public static TotalGame getGame() {
        return game;
    }
    public double getGameHeight() {
        return gameHeight;
    }
    public double getGameWidth() {
        return gameWidth;
    }
    public void setGameHeight(double gameHeight) {
        this.gameHeight = gameHeight;
    }
    public void setGameWidth(double gameWidth) {
        this.gameWidth = gameWidth;
    }
    public void setStory(boolean bool) {
        story = bool;
    }
    public int getRoundsPlayed() {
        return roundsPlayed;
    }
    public void incrementRoundsPlayed() {
        roundsPlayed += 1;
    }
    public boolean isGameStory() {
        return story;
    }
    //go through the game and use this instead of using bases as an argument everywhere.
    public void setBases(ArrayList<Base> bases) {
        this.bases = bases;
    }
    public ArrayList<Base> getBases() {
        return bases;
    }
    public void setSoldiers(ArrayList<Soldier> soldiers) {
        this.soldiers = soldiers;
    }
    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }
}