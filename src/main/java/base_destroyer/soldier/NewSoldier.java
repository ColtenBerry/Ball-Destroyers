package base_destroyer.soldier;

import base_destroyer.Allegiance;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.geom.Point2D;

public class NewSoldier extends Parent {
    private int radius = 5;
    private Point2D point;
    private double speed = 1.0;
    private Allegiance allegiance;
    private Point2D destination;
    private Circle c;
    public NewSoldier(Point2D point, Allegiance allegiance, Point2D destination) {
        this.point = point;
        this.allegiance = allegiance;
        this.destination = destination;
        c = new Circle();
        c.setFill(allegiance.getColor());
        c.setStroke(Color.BLACK);
        c.setRadius(radius);
        getChildren().add(c);
    }
    public void update() {
        c.setTranslateX(point.getX());
        c.setTranslateY(point.getY());
    }
    public Point2D getPoint() {
        return point;
    }
    public Point2D getDestination() {
        return destination;
    }

    public Allegiance getAllegiance() {
        return allegiance;
    }
    public double getSpeed() {
        return speed;
    }
    /*
    My problems stem from the move() method, but I don't understand why
    changing the position of the soldier would have an effect on the
    position of the base. I can alter the radius of the soldier with
    no problems at all, but apparently shifting the position of the
    soldier point alters the position of the base.
    Perhaps the problem is with the point class itself.
    Maybe I should switch my point class with the point2d class from java.awt?
     */
    public void move() {
        //so now whats happening is the base location is being reassigned to the soldier's location
        point.setLocation(point.getX() + speed, point.getY());
//        if (getDestination().getX() > getPoint().getX()) {
//            getPoint().setX(getPoint().getX() + getSpeed());
//        }
//        else if (getDestination().getX() < getPoint().getX()) {
//            getPoint().setX(getPoint().getX() - getSpeed());
//        }
//        if (getDestination().getY() > getPoint().getY()) {
//            getPoint().setY(getPoint().getY() + getSpeed());
//        }
//        else if (getDestination().getY() < getPoint().getY()) {
//            getPoint().setY(getPoint().getY() - getSpeed());
//        }
    }
    public String toString() {
        return "Position: " + getPoint() + "; Allegiance: " + getAllegiance();
    }

}
