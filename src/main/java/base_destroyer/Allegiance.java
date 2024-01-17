package base_destroyer;


import javafx.scene.paint.Color;

public enum Allegiance {
    PLAYER {
        public Color getColor() {
            return Color.DODGERBLUE;
        }

        public Allegiance endTurn() {
            return Allegiance.ENEMY;
        }
    },
    ENEMY {
        @Override
        public Color getColor() {
            return Color.RED;
        }

        public Allegiance endTurn() {
            return Allegiance.PLAYER;
        }
    },
    NEUTRAL {
        @Override
        public Color getColor() {
            return Color.GRAY;
        }

        @Override
        public Allegiance endTurn() {
            return null;
        }
    };

    abstract public Color getColor();

    abstract public Allegiance endTurn();

}
