package com.example.basedestroyers;

import com.example.basedestroyers.Core.Base;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Screen;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public enum Level {
    INTRO {
        private ArrayList<Node> objects = new ArrayList<>();
        Label instructions = new Label("Welcome to the fight, General. For your first lesson, you need to take " +
                "the red base. To do so, simply click on the blue base to select it and then click on the red base " +
                "to tell your soldiers to attack.");

        @Override
        public int getLevelNumber() {
            return 0;
        }

        @Override
        public Level getNextLevel() {
            return Level.REINFORCE;
        }
        @Override
        public void setBaseList() throws FileNotFoundException {
            Base b1 = new Base(new Point2D(200.0, height * 2/3), Faction.PLAYER, 50);
            Base b2 = new Base(new Point2D(width - 200, height * 2/3), Faction.RED, 20);
            game.getBases().add(b1);
            game.getBases().add(b2);
        }

        @Override
        public void setObjectsList() {
            objects.add(instructions);
            instructions.setLayoutX((width / 2) - 300);
            instructions.setLayoutY(50);
            instructions.setAlignment(Pos.CENTER);
            instructions.setPrefWidth(600);
            instructions.setWrapText(true);
            instructions.setFont(new Font(26));
        }

        @Override
        public ArrayList<Node> getObjectsList() {
            return objects;
        }
    },
    REINFORCE {
        private ArrayList<Node> objects = new ArrayList<>();
        Label instruction = new Label("Fantastic work General! For your next task, you will need to conquer this " +
                "red base. This base is much stronger, so to assist you, you have been given command of an additional " +
                "blue base.");
        @Override
        public int getLevelNumber() {
            return 1;
        }

        @Override
        public Level getNextLevel() {
            return NEUTRAL_ATTACK;
        }

        @Override
        public void setBaseList() throws FileNotFoundException {
            Base b1 = new Base(new Point2D(50, 50), Faction.PLAYER, 20);
            Base b2 = new Base(new Point2D(50, 350), Faction.PLAYER, 20);
            Base b3 = new Base(new Point2D(500, 325), Faction.RED, 40);
            game.getBases().add(b1);
            game.getBases().add(b2);
            game.getBases().add(b3);
        }

        @Override
        public void setObjectsList() {
            objects.add(instruction);
            instruction.setFont(new Font(26));
            instruction.setLayoutX(width / 2 - 200);
            instruction.setLayoutY(50);
            instruction.setWrapText(true);
            instruction.setPrefWidth(600);
        }

        @Override
        public ArrayList<Node> getObjectsList() {
            return objects;
        }
    },
    NEUTRAL_ATTACK{
        ArrayList<Node> objects = new ArrayList<>();
        Label instruction = new Label("Incredible! Command has seen fit to put you in a far more difficult situation. " +
                "You have command of a single blue base and are considerably weaker than your red counterpart. " +
                "In order to win this battle, you must bide your time and take control of the neutral gray base above you. " +
                "Only then will you have the soldier production to defeat the evil red base once and for all!");
        @Override
        public int getLevelNumber() {
            return 2;
        }

        @Override
        public Level getNextLevel() {
            return Level.ENEMIES;
        }

        @Override
        public void setBaseList() throws FileNotFoundException {
            Base b1 = new Base(new Point2D(50, 225), Faction.PLAYER, 20);
            Base b2 = new Base(new Point2D(50, 50), Faction.NEUTRAL, 5);
            Base b3 = new Base(new Point2D(500, 450), Faction.RED, 30);
            game.getBases().add(b1);
            game.getBases().add(b2);
            game.getBases().add(b3);
        }

        @Override
        public void setObjectsList() {
            objects.clear();
            objects.add(instruction);
            instruction.setFont(new Font(26));
            instruction.setLayoutX(width / 2 - 200);
            instruction.setLayoutY(50);
            instruction.setWrapText(true);
            instruction.setPrefWidth(600);
        }

        @Override
        public ArrayList<Node> getObjectsList() {
            return objects;
        }
    },
    ENEMIES {
        ArrayList<Node> objects = new ArrayList<>();
        Label instruction = new Label("General, the treachery of the reds knows no bounds! That evil empire " +
                "has brought the green people into this war!. If we are to survive, you must use every ounce of cunning " +
                "at your disposal");
        @Override
        public int getLevelNumber() {
            return 3;
        }

        @Override
        public Level getNextLevel() {
            return Level.WAR;
        }

        @Override
        public void setBaseList() throws FileNotFoundException {
            Base b1 = new Base(new Point2D(50, 500), Faction.PLAYER, 20);
            Base b2 = new Base(new Point2D(400, 500), Faction.RED, 20);
            Base b3 = new Base(new Point2D(200, 50), Faction.GREEN, 20);
            Base b4 = new Base(new Point2D(200, 225), Faction.NEUTRAL, 5);
            game.getBases().add(b1);
            game.getBases().add(b2);
            game.getBases().add(b3);
            game.getBases().add(b4);
        }

        @Override
        public void setObjectsList() {
            objects.add(instruction);
            instruction.setFont(new Font(26));
            instruction.setLayoutX(width / 2 - 200);
            instruction.setLayoutY(50);
            instruction.setWrapText(true);
            instruction.setPrefWidth(600);
        }

        @Override
        public ArrayList<Node> getObjectsList() {
            return objects;
        }
    },
    WAR{
        @Override
        public int getLevelNumber() {
            return 4;
        }

        @Override
        public Level getNextLevel() {
            return MASSIVE;
        }

        @Override
        public void setBaseList() throws FileNotFoundException {
            Base b1 = new Base(new Point2D(width / 6, height * 3/4), Faction.PLAYER, 20);
            Base b2 = new Base(new Point2D(width / 6, height / 4), Faction.PLAYER, 20);
            Base b3 = new Base(new Point2D(2 * width / 6, height / 2), Faction.NEUTRAL, 5);
            Base b4 = new Base(new Point2D(3 * width / 6, height / 4), Faction.RED, 20);
            Base b5 = new Base(new Point2D(3 * width / 6, height * 3 / 4), Faction.RED, 20);
            Base b6 = new Base(new Point2D(4 * width / 6, height / 2), Faction.NEUTRAL, 5);
            Base b7 = new Base(new Point2D(5 * width / 6, height / 4), Faction.GREEN, 20);
            Base b8 = new Base(new Point2D(5 * width / 6, height * 3 / 4), Faction.GREEN, 20);
            game.getBases().add(b1);
            game.getBases().add(b2);
            game.getBases().add(b3);
            game.getBases().add(b4);
            game.getBases().add(b5);
            game.getBases().add(b6);
            game.getBases().add(b7);
            game.getBases().add(b8);

        }

        @Override
        public void setObjectsList() {

        }

        @Override
        public ArrayList<Node> getObjectsList() {
            return null;
        }
    },
    MASSIVE {
        @Override
        public int getLevelNumber() {
            return 5;
        }

        @Override
        public Level getNextLevel() {
            return null;
        }

        @Override
        public void setBaseList() throws FileNotFoundException {
            for (int w = 1; w <= 8; w++) {
                for (int h = 1; h <= 7; h++) {
                    if (w == 1 && h == 1) {
                        Base b = new Base(new Point2D(w * width / 9, h * height / 9), Faction.PLAYER, 20);
                        game.getBases().add(b);
                    }
                    else if (w == 8 && h == 7) {
                        Base b = new Base(new Point2D(w * width / 9, h * height / 9), Faction.RED, 20);
                        game.getBases().add(b);
                    }
                    else {
                        Base b = new Base(new Point2D(w * width / 9, h * height / 9), Faction.NEUTRAL, 5);
                        game.getBases().add(b);
                    }
                }
            }
        }

        @Override
        public void setObjectsList() {

        }

        @Override
        public ArrayList<Node> getObjectsList() {
            return null;
        }
    },


    END{
        @Override
        public int getLevelNumber() {
            return 0;
        }

        @Override
        public Level getNextLevel() {
            return null;
        }

        @Override
        public void setBaseList() {

        }

        @Override
        public void setObjectsList() {

        }

        @Override
        public ArrayList<Node> getObjectsList() {
            return null;
        }
    },
    RANDOM{
        @Override
        public int getLevelNumber() {
            return -1;
        }

        @Override
        public Level getNextLevel() {
            return null;
        }

        @Override
        public ArrayList<Base> getBaseList() {
            return game.getBases();
        }

        @Override
        public void setBaseList() throws FileNotFoundException {
            customRandomLayout(1, 5, 2, 20, 5);
        }

        @Override
        public void setObjectsList() {
        }

        @Override
        public ArrayList<Node> getObjectsList() {
            return null;
        }
        private boolean checkBaseCollision(Base base) {
            for (Base b: game.getBases()) {
                if (base.getPoint().distance(b.getPoint()) < b.getSize() + (b.getSize() * 0.6) + base.getSize() && base != b) {
//                System.out.println("Base collision");
                    return true;
                }
            }
//        System.out.println("No base collision");
            return false;
        }
        private void spawnBase(Point2D point, Faction faction, int garrison) throws FileNotFoundException {
            Base b = new Base(point, faction, garrison);
            while (checkBaseCollision(b)) {
                b = new Base(makeRandomPoint(50), faction, garrison);
            }
            game.getBases().add(b);
//        gamePane.getChildren().add(b);
        }
        private Point2D makeRandomPoint(int radius) {
            java.util.Random random = new java.util.Random();
            int xValue = random.nextInt(radius, (int) (width - 75));
            int yValue = random.nextInt(radius, (int) (height - 100));
            return new Point2D(xValue, yValue);
        }
        public void customRandomLayout(int numFriendlyBases, int numNeutralBases, int numEnemyBases, int garrison, int neutralGarrison) throws FileNotFoundException {
            int radius = 20;
            for (int i = 0; i < numFriendlyBases; i++) {
                spawnBase(makeRandomPoint(radius), Faction.PLAYER, garrison);
            }
            for (int i = 0; i < numEnemyBases; i++) {
                spawnBase(makeRandomPoint(radius), Faction.RED, garrison);
            }
            for (int i = 0; i < numEnemyBases; i++) {
                spawnBase(makeRandomPoint(radius), Faction.GREEN, garrison);
            }
            for (int i = 0; i < numEnemyBases; i++) {
                spawnBase(makeRandomPoint(radius), Faction.YELLOW, garrison);
            }
            for (int i = 0; i < numNeutralBases; i++) {
                spawnBase(makeRandomPoint(radius), Faction.NEUTRAL, neutralGarrison);
            }
        }
        };
    public abstract int getLevelNumber();
    public abstract Level getNextLevel();
    public abstract void setBaseList() throws FileNotFoundException;
    public abstract void setObjectsList();
    public abstract ArrayList<Node> getObjectsList();
    TotalGame game = TotalGame.getGame();
    public ArrayList<Base> getBaseList() {
        return game.getBases();
    }
    double width = Screen.getPrimary().getBounds().getWidth() - 45;
    double height = Screen.getPrimary().getBounds().getHeight() - 50;

//        game.setBases(bases);

}
