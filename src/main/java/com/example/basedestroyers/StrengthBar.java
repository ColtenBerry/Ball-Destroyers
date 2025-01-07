package com.example.basedestroyers;

import com.example.basedestroyers.Core.Base;
import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class StrengthBar extends Parent {
    private double player_percent;
    private double red_percent;
    private double green_percent;
    private double yellow_percent;
    private ArrayList<Faction> allFactions = new ArrayList<>();
    private double totalStrength = 0;
    Rectangle playerRect;
    Rectangle redRect;
    Rectangle greenRect;
    Rectangle yellowRect;
    private double minX;
    private double minY;
    private double height;
    private double length;
    TotalGame game = TotalGame.getGame();
    public StrengthBar(double minX, double minY, double height, double length) {
        this.minX = minX;
        this.minY = minY;
        this.height = height;
        this.length = length;
        playerRect = new Rectangle(minX, minY, player_percent * length, height);
        playerRect.setFill(Faction.PLAYER.getColor());
        redRect = new Rectangle(playerRect.getX() + playerRect.getWidth(), minY, red_percent * length, height);
        redRect.setFill(Faction.RED.getColor());
        greenRect = new Rectangle(redRect.getX() + redRect.getWidth(), minY, green_percent * length, height);
        greenRect.setFill(Faction.GREEN.getColor());
        yellowRect = new Rectangle(greenRect.getX() + greenRect.getWidth(), minY, yellow_percent * length, height);
        yellowRect.setFill(Faction.YELLOW.getColor());
        getChildren().add(playerRect);
        getChildren().add(redRect);
        getChildren().add(greenRect);
        getChildren().add(yellowRect);
    }
    public void update() {
        updateFactionList();
        updateStrengths();
        updateRectangles();
    }
    private void updateStrengths() {
        totalStrength = 0;
        for (Faction f: allFactions) {
            totalStrength += f.calculateStrength();
        }
        totalStrength -= Faction.NEUTRAL.calculateStrength();
        for (Faction f: allFactions) {
            if (f == Faction.PLAYER) {
                player_percent = f.calculateStrength() / totalStrength;
            }
            else if (f == Faction.RED) {
                red_percent = f.calculateStrength() / totalStrength;
            }
            else if (f == Faction.GREEN) {
                green_percent = f.calculateStrength() / totalStrength;
            }
            else if (f == Faction.YELLOW) {
                yellow_percent = f.calculateStrength() / totalStrength;
            }
        }
    }
    private void updateRectangles() {
        playerRect = new Rectangle(minX, minY, player_percent * length, height);
        playerRect.setFill(Faction.PLAYER.getColor());
        redRect = new Rectangle(playerRect.getX() + playerRect.getWidth(), minY, red_percent * length, height);
        redRect.setFill(Faction.RED.getColor());
        greenRect = new Rectangle(redRect.getX() + redRect.getWidth(), minY, green_percent * length, height);
        greenRect.setFill(Faction.GREEN.getColor());
        yellowRect = new Rectangle(greenRect.getX() + greenRect.getWidth(), minY, yellow_percent * length, height);
        yellowRect.setFill(Faction.YELLOW.getColor());
        getChildren().add(playerRect);
        getChildren().add(redRect);
        getChildren().add(greenRect);
        getChildren().add(yellowRect);
    }
    private void updateFactionList() {
        for (Base b: game.getBases()) {
            if (!allFactions.contains(b.getFaction())) {
                allFactions.add(b.getFaction());
            }
        }
    }

}
