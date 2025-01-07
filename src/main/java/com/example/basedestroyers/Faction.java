package com.example.basedestroyers;

import com.example.basedestroyers.Core.Base;
import com.example.basedestroyers.Core.Soldier;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
maybe a list of bases for each faction?
I think that would make a lot more sense.
could also calculate faction strength here.
 */
public enum Faction {
    PLAYER{
        private ArrayList<Base> playerBases = new ArrayList<>();
        private ArrayList<Soldier> player_soldier_lst = new ArrayList<>();
        private int roundsWon = 0;
        @Override
        public Color getColor() {
            return Color.DODGERBLUE;
        }

        @Override
        public Image getBaseImage() throws FileNotFoundException {
            FileInputStream inputstream = new FileInputStream("C:/Users/colte/IdeaProjects/demo/src/main/resources/com/example/demo/Basic_Castle_Blue.png");
            Image image = new Image(inputstream);
            return image;
        }

        @Override
        public Image getSoldierImage(boolean right) throws FileNotFoundException {
            FileInputStream inputstream;
            if (right) {
                inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Knight_Walking_Right_Blue.png");
            }
            else {
                inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Knight_Walking_Left_Blue.png");
            }
                Image image = new Image(inputstream);
                return image;
        }

        @Override
        public int calculateStrength() {
            int strength = 0;
            for (Base b: getBaseList()) {
                strength += b.getGarrison();
            }
            return strength + getSoldierList().size();
        }

        @Override
        public void setBaseList(ArrayList<Base> lst) {
            playerBases = lst;
        }

        public ArrayList<Base> getBaseList() {
            return playerBases;
        }

        @Override
        public void removeBase(Base b) {
            if (playerBases.contains(b)) {
                playerBases.remove(b);
            }
        }

        @Override
        public void addBase(Base b) {
            if (!playerBases.contains(b)) {
                playerBases.add(b);
            }
        }

        @Override
        public int getRoundsWon() {
            return roundsWon;
        }

        @Override
        public void incrementRoundsWon() {
            roundsWon += 1;
        }

        @Override
        public ArrayList<Soldier> getSoldierList() {
            return player_soldier_lst;
        }
    },
    RED{
        private ArrayList<Base> base_list = new ArrayList<>();
        private ArrayList<Soldier> red_soldier_lst = new ArrayList<>();
        private int roundsWon = 0;
        @Override
        public Color getColor() {
            return Color.RED;
        }

        @Override
        public Image getBaseImage() throws FileNotFoundException {
            FileInputStream inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Basic_Castle_Red.png");
            Image image = new Image(inputstream);
            return image;
        }

        @Override
        public Image getSoldierImage(boolean right) throws FileNotFoundException {
            FileInputStream inputstream;
            if (right) {
                inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Knight_Walking_Right_Red.png");
            }
            else {
                inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Knight_Walking_Left_Red.png");
            }
            Image image = new Image(inputstream);
            return image;
        }

        @Override
        public int calculateStrength() {
            int strength = 0;
            for (Base b: getBaseList()) {
                strength += b.getGarrison();
            }
            return strength + getSoldierList().size();
        }

        @Override
        public void setBaseList(ArrayList<Base> lst) {
            base_list = lst;
        }

        @Override
        public ArrayList<Base> getBaseList() {
            return base_list;
        }

        @Override
        public void removeBase(Base b) {
            if (base_list.contains(b)) {
                base_list.remove(b);
            }
        }

        @Override
        public void addBase(Base b) {
            if (!base_list.contains(b)) {
                base_list.add(b);
            }
        }

        @Override
        public int getRoundsWon() {
            return roundsWon;
        }

        @Override
        public void incrementRoundsWon() {
            roundsWon += 1;
        }

        @Override
        public ArrayList<Soldier> getSoldierList() {
            return red_soldier_lst;
        }
    },
    GREEN{
        private ArrayList<Base> base_list = new ArrayList<>();
        private ArrayList<Soldier> green_soldier_lst = new ArrayList<>();
        private int roundsWon = 0;
        @Override
        public Color getColor() {
            return Color.GREEN;
        }

        @Override
        public Image getBaseImage() throws FileNotFoundException {
            FileInputStream inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Basic_Castle_Green.png");
            Image image = new Image(inputstream);
            return image;
        }

        @Override
        public Image getSoldierImage(boolean right) throws FileNotFoundException {
            FileInputStream inputstream;
            if (right) {
                inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Knight_Walking_Right_Green.png");
            }
            else {
                inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Knight_Walking_Left_Green.png");
            }
            Image image = new Image(inputstream);
            return image;
        }

        @Override
        public int calculateStrength() {
            int strength = 0;
            for (Base b: getBaseList()) {
                strength += b.getGarrison();
            }
            return strength + getSoldierList().size();
        }

        @Override
        public void setBaseList(ArrayList<Base> lst) {
            base_list = lst;
        }

        @Override
        public ArrayList<Base> getBaseList() {
            return base_list;
        }

        @Override
        public void removeBase(Base b) {
            if (base_list.contains(b)) {
                base_list.remove(b);
            }
        }

        @Override
        public void addBase(Base b) {
            if (!base_list.contains(b)) {
                base_list.add(b);
            }
        }

        @Override
        public int getRoundsWon() {
            return roundsWon;
        }

        @Override
        public void incrementRoundsWon() {
            roundsWon += 1;
        }

        @Override
        public ArrayList<Soldier> getSoldierList() {
            return green_soldier_lst;
        }
    },
    YELLOW {
        private ArrayList<Base> base_lst = new ArrayList<>();
        private ArrayList<Soldier> yellow_soldier_lst = new ArrayList<>();
        private int roundsWon = 0;
        @Override
        public Color getColor() {
            return Color.YELLOW;
        }

        @Override
        public Image getBaseImage() throws FileNotFoundException {
            FileInputStream inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Basic_Castle_Yellow.png");
            Image image = new Image(inputstream);
            return image;
        }

        @Override
        public Image getSoldierImage(boolean right) throws FileNotFoundException {
            FileInputStream inputstream;
            if (right) {
                inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Knight_Walking_Right_Yellow.png");
            }
            else {
                inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Knight_Walking_Left_Yellow.png");
            }
            Image image = new Image(inputstream);
            return image;
        }

        @Override
        public int calculateStrength() {
            int strength = 0;
            for (Base b: getBaseList()) {
                strength += b.getGarrison();
            }
            return strength + getSoldierList().size();
        }

        @Override
        public void setBaseList(ArrayList<Base> lst) {
            this.base_lst = lst;
        }

        @Override
        public ArrayList<Base> getBaseList() {
            return base_lst;
        }

        @Override
        public void removeBase(Base b) {
            base_lst.remove(b);
        }

        @Override
        public void addBase(Base b) {
            if (!base_lst.contains(b)) {
                base_lst.add(b);
            }
        }

        @Override
        public int getRoundsWon() {
            return roundsWon;
        }

        @Override
        public void incrementRoundsWon() {
            roundsWon += 1;
        }

        @Override
        public ArrayList<Soldier> getSoldierList() {
            return yellow_soldier_lst;
        }
    },

    NEUTRAL{
        private ArrayList<Base> base_list = new ArrayList<>();
        private ArrayList<Soldier> solder_lst = new ArrayList<>();
        @Override
        public Color getColor() {
            return Color.GRAY;
        }

        @Override
        public Image getBaseImage() throws FileNotFoundException {
            FileInputStream inputstream = new FileInputStream("C:\\Users\\colte\\IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\Basic_Castle_Gray.png");
            Image image = new Image(inputstream);
            return image;
        }

        @Override
        public Image getSoldierImage(boolean right) throws FileNotFoundException {
            return null;
        }

        @Override
        public int calculateStrength() {
            int strength = 0;
            for (Base b: getBaseList()) {
                strength += b.getGarrison();
            }
            return strength;
        }

        @Override
        public void setBaseList(ArrayList<Base> lst) {
            base_list = lst;
        }

        @Override
        public ArrayList<Base> getBaseList() {
            return base_list;
        }

        @Override
        public void removeBase(Base b) {
            if (base_list.contains(b)) {
                base_list.remove(b);
            }
        }

        @Override
        public void addBase(Base b) {
            if (!base_list.contains(b)) {
                base_list.add(b);
            }
        }

        @Override
        public int getRoundsWon() {
            return 0;
        }

        @Override
        public void incrementRoundsWon() {

        }

        @Override
        public ArrayList<Soldier> getSoldierList() {
            return solder_lst;
        }
    };
    public abstract Color getColor();
    public abstract Image getBaseImage() throws FileNotFoundException;
    public abstract Image getSoldierImage(boolean right) throws FileNotFoundException;
    public abstract int calculateStrength();
    public abstract void setBaseList(ArrayList<Base> lst);
    public abstract ArrayList<Base> getBaseList();
    public abstract void removeBase(Base b);
    public abstract void addBase(Base b);
    public abstract int getRoundsWon();
    public abstract void incrementRoundsWon();
    public abstract ArrayList<Soldier> getSoldierList();
    public void clearAllBaseLists() {
        PLAYER.getBaseList().clear();
        RED.getBaseList().clear();
        GREEN.getBaseList().clear();
        YELLOW.getBaseList().clear();
    }
}
