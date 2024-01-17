package base_destroyer.base;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class BaseView extends Parent {
    private Base base;
    private Circle circle;
    private Label garrison;
    private Text text;
    public BaseView(Base base) {
        this.base = base;
        circle = new Circle();
        circle.setFill(base.getAllegiance().getColor());
        circle.setStroke(Color.BLACK);
        circle.setRadius(base.getRadius());
        circle.setCenterX(base.getPoint().getX());
        circle.setCenterY(base.getPoint().getY());
//        garrison = new Label();
//        garrison.setText(Integer.toString(base.getGarrison()));
//        garrison.setLayoutX(base.getPoint().getX());
//        garrison.setLayoutY(base.getPoint().getY());
        getChildren().add(circle);
//        getChildren().add(garrison);
        text = new Text(Integer.toString(base.getGarrison()));
        //I want the garrison text to be centered!!!
        text.setX(base.getPoint().getX() - base.getRadius() / 2);
        text.setY(base.getPoint().getY());
        text.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(text);
    }
    public void update() {
        circle.setFill(base.getAllegiance().getColor());
        text.setText(Integer.toString(base.getGarrison()));
    }
}
