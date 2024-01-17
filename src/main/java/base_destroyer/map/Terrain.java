package base_destroyer.map;

public enum Terrain {
    GRASS{
        @Override
        public int getTerrainDifficulty() {
            return 2;
        }

        @Override
        public double getDefensiveModifier() {
            return 1.0;
        }
    },
    WOODS{
        @Override
        public int getTerrainDifficulty() {
            return 3;
        }

        @Override
        public double getDefensiveModifier() {
            return 1.25;
        }
    },
    RIVER{
        @Override
        public int getTerrainDifficulty() {
            return 4;
        }

        @Override
        public double getDefensiveModifier() {
            return 0.5;
        }
    },
    HILL{
        @Override
        public int getTerrainDifficulty() {
            return 4;
        }

        @Override
        public double getDefensiveModifier() {
            return 1.25;
        }
    },
    MOUNTAIN{
        @Override
        public int getTerrainDifficulty() {
            return 6;
        }

        @Override
        public double getDefensiveModifier() {
            return 2.0;
        }
    },
    WATER{
        @Override
        public int getTerrainDifficulty() {
            return 100;
        }

        @Override
        public double getDefensiveModifier() {
            return 1.0;
        }
    },
    ROAD{
        @Override
        public int getTerrainDifficulty() {
            return 1;
        }

        @Override
        public double getDefensiveModifier() {
            return 1.0;
        }
    };
    public abstract int getTerrainDifficulty();
    public abstract double getDefensiveModifier();
//    public abstract int visibility();

}
