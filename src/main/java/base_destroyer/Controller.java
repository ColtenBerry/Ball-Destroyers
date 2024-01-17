package base_destroyer;

import base_destroyer.base.Base;
import base_destroyer.base.BaseView;
import base_destroyer.soldier.NewSoldier;
import base_destroyer.soldier.SoldierView;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class Controller {
    private Movement clock;
    @FXML
    private void startClock() {
        clock.start();
    }
    @FXML
    private void stopClock() {
        clock.stop();
    }
    //what exactly does extends mean?
    private class Movement extends AnimationTimer {
        //why long?
        private long last = 0;
        @Override
        public void handle(long now) {
            long FRAMES_PER_SEC = 100L;
            long INTERVAL = 1000000000L / FRAMES_PER_SEC;
            if (now - last > INTERVAL) {
                updateViews();
                last = now;
            }
        }
    }
    @FXML
    private Pane gamePane;
    private ArrayList<Base> bases = new ArrayList<>();
    private ArrayList<BaseView>baseViews = new ArrayList<>();
    private ArrayList<NewSoldier> soldiers = new ArrayList<>();
    private ArrayList<SoldierView> soldierViews = new ArrayList<>();
    private Point base1Point = new Point(20, 20);
    private Point base2Point = new Point(580, 580);

    public void initialize() {
        spawnBase(base1Point, Allegiance.PLAYER, 200);
        spawnBase(base2Point, Allegiance.ENEMY, 200);
        for (int i = 0; i < 6; i++) {
            spawnBase(randomPoint(), Allegiance.NEUTRAL, 50);
        }
        clock = new Movement();
        clock.start();
    }
    private void updateViews() {
        for (BaseView bs: baseViews) {
            bs.update();
        }
        //problem with soldiers.move?
        for (NewSoldier s: soldiers) {
//            s.move();
        }
        for (SoldierView ss: soldierViews) {
            ss.update();
        }
    }
    private void spawnBase(Point point, Allegiance allegiance, int garrison) {
        Base base = new Base(allegiance, point, garrison);
        bases.add(base);
        BaseView baseView = new BaseView(base);
        baseViews.add(baseView);
        gamePane.getChildren().add(baseView);
    }
    @FXML
    private Button soldierPrint;
    @FXML
    private void buttonPress() {
            System.out.println("Press");
            System.out.println(soldiers);
            System.out.println(soldierViews);
    }
    @FXML
    public void click(MouseEvent event) {
//        Polygon triangle;
//        triangle = new Polygon();
//        triangle.getPoints().setAll(
//                100.0, 100.0,
//                200.0, 200.0,
//                0.0, 200.0
//        );
//        triangle.setFill(Color.GREEN);
//        gamePane.getChildren().add(triangle);
//        Point spot = new Point(event.getX(), event.getY());
//        Soldier s = new Soldier(spot, Allegiance.PLAYER);
//        SoldierView sv = new SoldierView(s);
//        gamePane.getChildren().add(sv);
    }
    private Base selectBase(Point point) {
        for (Base base : bases) {
            if ((base.getPoint().getX() - base.getRadius() < point.getX() && point.getX() < base.getPoint().getX() + base.getRadius())
                    && (base.getPoint().getY() - base.getRadius() < point.getY() && point.getY() < base.getPoint().getY() + base.getRadius())) {
                return base;
            }
        }
        return null;
    }
    private Base selected;
    private Base target;
    @FXML
    public void action(MouseEvent event) {
        Point eventPoint = new Point(event.getX(), event.getY());
        System.out.println(bases);
        //if there is no selected, acquire selected
        if (selected == null) {
            selected = selectBase(eventPoint);
            System.out.println("Selected: " + selected);
        }
        //if there is a selected, acquire target
        else {
            target = selectBase(eventPoint);
            System.out.println("Target: " + target);
            //if no target is clicked, then selected returns to null
        }
        //is there a target and selected?
        if (target != null && selected != null) {
            Move move = new Move(selected, target);
            //attack
            if (target.getAllegiance() != selected.getAllegiance()) {
                System.out.println("Attack");
                move.attack();
                createSoldier(selected.getPoint(), selected.getAllegiance(), target.getPoint());
            }
            //reinforce
            else {
                System.out.println("Reinforce");
                move.reinforce();
                createSoldier(selected.getPoint(), selected.getAllegiance(), target.getPoint());
            }
            selected = null;
            target = null;
        }
    }
    private void createSoldier(Point point, Allegiance allegiance, Point target) {
        NewSoldier s = new NewSoldier(point, allegiance, target);
        SoldierView sv = new SoldierView(s);
        //Problem with soldiers. Removing the following line fixes the problem
        soldiers.add(s);
        soldierViews.add(sv);
        gamePane.getChildren().add(sv);
    }
    private Point randomPoint() {
        int radius = 20;
        java.util.Random random = new java.util.Random();
        int x = random.nextInt(radius, 600 - radius);
        int y = random.nextInt(radius, 600 - radius);
        return new Point(x, y);
    }


}