package base_destroyer.base;

import base_destroyer.Allegiance;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.awt.geom.Point2D;

public class Base extends Parent {
    private final Point2D point;
    private final int radius = 20;
    private int garrison;
    private Allegiance allegiance;
    private int garrisonUpdateRate = 1;
    private Circle circle;
    private Text text;
    public Base(Allegiance allegiance, Point2D point, int garrison) {
        this.allegiance = allegiance;
        this.point = point;
        this.garrison = garrison;
        circle = new Circle();
        circle.setFill(allegiance.getColor());
        circle.setStroke(Color.BLACK);
        circle.setRadius(radius);
        circle.setCenterX(point.getX());
        circle.setCenterY(point.getY());
        getChildren().add(circle);
        text = new Text(Integer.toString(garrison));
        text.setX(point.getX() - radius / 2);
        text.setY(point.getY());
        text.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(text);

    }
    public void update() {
        circle.setFill(allegiance.getColor());
        text.setText(Integer.toString(garrison));
    }
    public Point2D getPoint() {
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
