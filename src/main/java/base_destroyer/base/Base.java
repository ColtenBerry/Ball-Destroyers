package base_destroyer.base;

import base_destroyer.Allegiance;
import base_destroyer.Point;

public class Base {
    private final Point point;
    private final int radius = 20;
    private int garrison;
    private Allegiance allegiance;
    private int garrisonUpdateRate = 1;
    public Base(Allegiance allegiance, Point point, int garrison) {
        this.allegiance = allegiance;
        this.point = point;
        this.garrison = garrison;
    }
    public Point getPoint() {
        return point;
    }
    public int getRadius() {
        return radius;
    }
    public int getGarrison() {
        return garrison;
    }
    public void setGarrison(int newGarrison) {
        garrison = newGarrison;
    }
    public int getGarrisonUpdateRate() {
        return garrisonUpdateRate;
    }
    public void setGarrisonUpdateRate(int rate) {
        garrisonUpdateRate = rate;
    }
    public Allegiance getAllegiance() {return allegiance;}
    public void setAllegiance(Allegiance allegiance) {
        this.allegiance = allegiance;
    }
    public void updateGarrison() {
        setGarrison(getGarrison() + getGarrisonUpdateRate());
    }
    public String toString() {
        return "Base Allegiance: " + this.allegiance + ", Base Location: " + this.point;
    }
}
