package base_destroyer.map;

public enum Cell {
    OPEN{
        private Terrain terrain;
        @Override
        public Terrain getTerrain() {
            return terrain;
        }

        @Override
        public void setTerrain(Terrain desired) {
            terrain = desired;
        }


        @Override
        public boolean canEnter() {
            return false;
        }
    },
    CLOSED {
        private Terrain terrain;
        @Override
        public Terrain getTerrain() {
            return terrain;
        }

        @Override
        public void setTerrain(Terrain desired) {
            terrain = desired;
        }

        @Override
        public boolean canEnter() {
            return false;
        }
    };
    public abstract Terrain getTerrain();
    public abstract void setTerrain(Terrain desired);
    public abstract boolean canEnter();
//    public abstract Building getBuilding();
}
