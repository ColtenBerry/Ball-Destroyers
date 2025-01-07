package com.example.basedestroyers.Core;

import com.example.basedestroyers.Faction;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public class Soldier extends Parent {
    private final Base home;
    private final Base target;
    private Point2D point;
    private final Faction faction;
//    private final Circle c;
    private final double xRatio;
    private final int size = 25;
    private final ImageView imageView;

    public Soldier (Base home, Base target, Faction faction) throws FileNotFoundException {
        this.point = home.getPoint();
        this.faction = faction;
        this.home = home;
        this.target = target;
        imageView = new ImageView(faction.getSoldierImage(target.getPoint().getX() >= this.getPoint().getX()));
        imageView.setX(point.getX());
        imageView.setY(point.getY());
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
//        c = new Circle();
//        c.setFill(faction.getColor());
//        c.setRadius(5);
//        c.setCenterX(point.getX());
//        c.setCenterY(point.getY());
//        c.setStroke(Color.BLACK);
//        getChildren().add(c);
        getChildren().add(imageView);
        home.setGarrison(home.getGarrison() - 1);
        double xAxis = Math.abs(target.getPoint().getX() - point.getX());
        double yAxis = Math.abs(target.getPoint().getY() - point.getY());
        double total = xAxis + yAxis;
        xRatio = xAxis / total;
    }
    public Point2D getPoint() {
        return point;
    }
    public void setPoint(Point2D newPoint) {
        point = newPoint;
    }
    public Base getHome() {
        return home;
    }
    public Base getTarget() {
        return target;
    }
    public Faction getAllegiance() {return faction;}
    public int getSize() {
        return size;
    }
    public void move() {
        double speed = 3.0;
        double dy = (1 - xRatio) * speed;
        double dx = (xRatio) * speed;
        if (target.getPoint().getX() < point.getX()) {
            point = new Point2D(point.getX() - dx, point.getY());
//            System.out.println("left");
        }
        if (target.getPoint().getX() > point.getX()) {
            point = new Point2D(point.getX() + dx, point.getY());
//            System.out.println("right");
        }
        if (target.getPoint().getY() < point.getY()) {
            point = new Point2D(point.getX(), point.getY() - dy);
//            System.out.println("up");
        }
        if (target.getPoint().getY() > point.getY()) {
            point = new Point2D(point.getX(), point.getY() + dy);
//            System.out.println("down");
        }
//        c.setCenterX(point.getX());
//        c.setCenterY(point.getY());
        imageView.setX(point.getX() - (size / 2));
        imageView.setY(point.getY() - (size / 2));
        if (target.getPoint().distance(point) <= size) {
//            System.out.println("success");
//            System.out.println(this);
//            System.out.println("finisher");
            finisher();
        }
    }
    private boolean completed = false;
    public boolean getCompleted() {
        return completed;
    }
    private boolean collision = false;
    public void setCollision() {
        collision = !collision;
    }
    public boolean getCollision() {return collision;}
    private boolean conquered = false;
    public boolean getConquered() {
        return conquered;
    }
    private void finisher() {
        if (faction == target.getFaction()) {
            target.setGarrison(target.getGarrison() +1);
        }
        else if (faction != target.getFaction()) {
            target.setGarrison(target.getGarrison() - 1);
            if (target.getGarrison() < 0) {
                target.setGarrison(target.getGarrison() * -1);
                conquered = true;
            }
        }
        completed = true;
    }
    public String toString() {
        return "Home: " + getHome() + ", " + "Target: " + getTarget() + ", " + "Position: " + this.getPoint().getX() + ", " + getPoint().getY() + "; ";
    }
}
