package base_destroyer.soldier;

import base_destroyer.Point;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.TriangleMesh;

public class SoldierView extends Parent {
    private NewSoldier s;
    private Polygon triangle;
    private Point spot;
    public SoldierView(NewSoldier soldier) {
        this.s = soldier;
        spot = s.getPoint();
        triangle = new Polygon();
        triangle.getPoints().setAll(
                (spot.getX()), (spot.getY() + 5.0),
                (spot.getX() - 2.5), (spot.getY()),
                (spot.getX() + 2.5), (spot.getY())
        );
        triangle.setFill(Color.GREEN);
        triangle.setStroke(Color.BLACK);
        getChildren().add(triangle);
    }
    public void update() {
        triangle.setTranslateX(s.getPoint().getX());
        triangle.setTranslateY(s.getPoint().getY());
    }
    public String toString() {
        return "Nose?: " + s.getPoint().getX() + ", " + (s.getPoint().getY() + 5) + "; Test: " + s.getPoint().getY();
    }
}
