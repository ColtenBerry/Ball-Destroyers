package com.example.basedestroyers.Core;

import com.example.basedestroyers.Faction;
import com.example.basedestroyers.TotalGame;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;


    /*
    I would like to make Base do the spawning itself, not the Move class. In order to accomplish this, I will do the
    following:
        - Base target;
            - initially null
            - will be set with computerMove or Click
            - will return to null when soldiers are done spawning (garrison == 0)
     */

public class Base extends Parent {
    TotalGame game = TotalGame.getGame();
    private final Point2D point;
    private final int size = 50;
    private final double circleRadius = size * 0.6;
    private boolean spawning = false;
    private int garrison;
    int replenishRate = 1;
    private Faction faction;
    private final ImageView imageView;
    private final Circle c;
    private final Text t;
    private double replenishTime = 0.0;
    private double spawningTime = 0.0;
    private Base target;
    private double fontSize = 1;
    private boolean selected = false;
    public Base(Point2D point, Faction faction, int garrison) throws FileNotFoundException {
        this.point = point;
        this.faction = faction;
        this.garrison = garrison;
        imageView = new ImageView(faction.getBaseImage());
        imageView.setX(point.getX() - (size / 2));
        imageView.setY(point.getY() - (size / 2));
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        c = new Circle();
        c.setRadius(circleRadius);
        c.setCenterX(point.getX());
        c.setCenterY(point.getY());
        t = new Text(Integer.toString(garrison));
        t.setX(point.getX() - 5);
        t.setY(point.getY() + (size * 0.8));
//        getChildren().add(c);
        getChildren().add(c);
        getChildren().add(imageView);
        getChildren().add(t);
    }
    public void update() throws FileNotFoundException {
        t.setText(Integer.toString(garrison));
//        t.setFont(new Font(fontSize += 1));
        if(selected) {
            c.setFill(Color.PURPLE);
        }
        else {
            c.setFill(Color.WHITESMOKE);
            imageView.setImage(faction.getBaseImage());
        }
    }
    public Point2D getPoint() {
        return point;
    }
    public Faction getFaction() {
        return faction;
    }
    public void setFaction(Faction faction) {
        this.faction = faction;
    }
    public int getGarrison() {
        return garrison;
    }
    public void setGarrison(int garrison) {
//        System.out.println(this + " New Garrison: " + garrison);
        this.garrison = garrison;
    }
    public double getSize() {
        return circleRadius;
    }
    private void replenishGarrison() {
        garrison += replenishRate;
    }
    public boolean isSpawning() {
        return spawning;
    }
    public void setTarget(Base target) {
        this.target = target;
        spawning = true;
    }
    public void flipSelect() {
        selected = !selected;
    }
    public void replenish(Timer timer) {
        boolean b = timer.checkTime(replenishTime, 0.25);
        if (b) {
//            System.out.println(replenishTime);
            replenishTime = timer.getTime();
            replenishGarrison();
        }
    }
    public boolean checkGarrison() {
        if (garrison > 0) {
            return true;
        }
        else {
            target = null;
            spawning = false;
            return false;
        }
    }
    public Soldier spawnSoldiers(Timer timer) throws FileNotFoundException {
//        System.out.println("Spawn at " + move.getSelected() + ", target at: " + move.getTarget());
        boolean b = timer.checkTime(spawningTime, 0.05);
        if (b) {
                spawning = true;
                Soldier s = new Soldier(this, target, faction);
                spawningTime = timer.getTime();
                return s;
        }
        return null;
    }
    public String toString() {
        return "Location: " + point + " Garrison: " + garrison + ", Allegiance: " + faction + ";";
    }
}