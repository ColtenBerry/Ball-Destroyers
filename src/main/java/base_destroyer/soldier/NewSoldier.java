package base_destroyer.soldier;

import base_destroyer.Allegiance;
import base_destroyer.Point;

public class NewSoldier {
    private int radius = 5;
    private Point point;
    private double speed = 1.0;
    private Allegiance allegiance;
    private Point destination;
    public NewSoldier(Point point, Allegiance allegiance, Point destination) {
        this.point = point;
        this.allegiance = allegiance;
        this.destination = destination;
    }
    public Point getPoint() {
        return point;
    }
    public Point getDestination() {
        return destination;
    }

    public Allegiance getAllegiance() {
        return allegiance;
    }
    public double getSpeed() {
        return speed;
    }
    public void move() {
        if (getDestination().getX() > getPoint().getX()) {
            getPoint().setX(getPoint().getX() + getSpeed());
        }
        else if (getDestination().getX() < getPoint().getX()) {
            getPoint().setX(getPoint().getX() - getSpeed());
        }
        if (getDestination().getY() > getPoint().getY()) {
            getPoint().setY(getPoint().getY() + getSpeed());
        }
        else if (getDestination().getY() < getPoint().getY()) {
            getPoint().setY(getPoint().getY() - getSpeed());
        }
    }
    public String toString() {
        return "Position: " + getPoint() + "; Allegiance: " + getAllegiance();
    }
}
