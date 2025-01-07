package com.example.basedestroyers;

import com.example.basedestroyers.Core.Base;
import javafx.geometry.Point2D;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Layout {
    /*
    I want this class to be used for creating an initial layout
    Either a preset can be requested or a number of friendly/enemy/neutral bases and accompanying garrisons
     */
    private ArrayList<Base> bases = new ArrayList<>();
    TotalGame game = TotalGame.getGame();
    private double width;
    private double height;
    private ArrayList<Integer> map_list = new ArrayList<>();
    public Layout(double width, double height) {
        this.width = width;
        this.height = height;
    }
    private boolean checkBaseCollision(Base base) {
        for (Base b: bases) {
            if (base.getPoint().distance(b.getPoint()) < b.getSize() + (b.getSize() * 0.6) + base.getSize() && base != b) {
//                System.out.println("Base collision");
                return true;
            }
        }
//        System.out.println("No base collision");
        return false;
    }
    private void spawnBase(Point2D point, Faction faction, int garrison) throws FileNotFoundException {
        Base b = new Base(point, faction, garrison);
        while (checkBaseCollision(b)) {
            b = new Base(makeRandomPoint(50), faction, garrison);
        }
        bases.add(b);
//        gamePane.getChildren().add(b);
    }
    private Point2D makeRandomPoint(int radius) {
        java.util.Random random = new java.util.Random();
        int xValue = random.nextInt(radius, (int) (width - radius));
        int yValue = random.nextInt(radius, (int) (height - radius));
        return new Point2D(xValue, yValue);
    }
    /*
    I want to input a number, and this method will generate the appropriate layout.
    Maybe instead of an integer, an enum would be better?
     */
    public void spawnPreset(Level level) throws FileNotFoundException {
        level.setBaseList();
         game.setBases(level.getBaseList());
    }
    public void customRandomLayout(int numFriendlyBases, int numNeutralBases, int numEnemyBases, int garrison, int neutralGarrison) throws FileNotFoundException {
        int radius = 20;
        for (int i = 0; i < numFriendlyBases; i++) {
            spawnBase(makeRandomPoint(radius), Faction.PLAYER, garrison);
        }
        for (int i = 0; i < numEnemyBases; i++) {
            spawnBase(makeRandomPoint(radius), Faction.RED, garrison);
        }
        for (int i = 0; i < numEnemyBases; i++) {
            spawnBase(makeRandomPoint(radius), Faction.GREEN, garrison);
        }
        for (int i = 0; i < numEnemyBases; i++) {
            spawnBase(makeRandomPoint(radius), Faction.YELLOW, garrison);
        }
        for (int i = 0; i < numNeutralBases; i++) {
            spawnBase(makeRandomPoint(radius), Faction.NEUTRAL, neutralGarrison);
        }
        //This complicated Triple/master list is no longer necessary.

        game.setBases(bases);
    }
}
