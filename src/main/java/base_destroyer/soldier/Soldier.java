package base_destroyer.soldier;

import base_destroyer.Allegiance;
import base_destroyer.Point;

public class Soldier {
    //point is the tip
    private Point tip;
    private final int length = 10;
    private final int width = 5;
    private double speed = 1;
    private Allegiance allegiance;
    private Point target;
    //change all references to bases to indexes for the base_list. or maybe just have the target be a point on a map?
    public Soldier(Point tip, Allegiance allegiance, Point target) {
        this.tip = tip;
        this.allegiance = allegiance;
        this.target = target;
    }
    public double getSpeed() {
        return speed;
    }
    public Allegiance getAllegiance() {
        return allegiance;
    }
    public Point getTip() {
        return tip;
    }
    public void setTip(Point point) {
        tip.setX(point.getX());
        tip.setY(point.getY());
    }
    public Point getBottomLeft() {
        return new Point(tip.getX() - width / 2, tip.getY() - length);
    }
    public Point getBottomRight() {
        return new Point(tip.getX() + width / 2, tip.getY() - length);
    }
    public void move() {
        if (target.getX() > tip.getX()) {
            tip.setX(tip.getX() + 1);
        }
        else if (target.getX() < tip.getX()) {
            tip.setX(tip.getX() - 1);
        }
        if (target.getY() > tip.getY()) {
            tip.setY(tip.getY() + 1);
        }
        else if (target.getY() < tip.getY()) {
            tip.setY(tip.getY() - 1);
        }
    }
    public String toString() {
        return "Soldier Allegiance: " + allegiance + ", Soldier Location: " + getTip();
    }
}
