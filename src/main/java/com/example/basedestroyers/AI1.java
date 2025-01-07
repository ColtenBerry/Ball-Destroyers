package com.example.basedestroyers;

import com.example.basedestroyers.Core.Base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AI1 {
    /*
    Behavior I want:
        - Randomness?
        - I want potential attacking bases to be chosen first.
            - Determine if they are a border base.
                - pick a point 10 px in each direction.
                - determine the closest base to each point(not the original base and not any base already picked)
                - if one of those bases is an enemy base, then this base is classified as a "border" base
                - I don't think this is a good idea now...
            - If not border base
                - reinforce
                - defend
            - If border base
                - attack targets
        - Target Bases chosen second
            - Weak bases should be prioritized
            - Distance considered 2nd
            - Power scales considered 3rd.
     */
    //i think setting the AI to different "modes" would be best
    TotalGame game = TotalGame.getGame();
    private ArrayList<Base> enemyBases = new ArrayList<>();
    private ArrayList<Base> neutralBases = new ArrayList<>();
    private ArrayList<Faction> livingFactions = new ArrayList<>();
    private Faction faction;
    public AI1 (Faction faction) {
        this.faction = faction;
        for (Base b: game.getBases()) {
            if (b.getFaction() != faction && b.getFaction() != Faction.NEUTRAL) {
                enemyBases.add(b);
            }
        }
        for (Base b: game.getBases()) {
            if (b.getFaction() == Faction.NEUTRAL) {
                neutralBases.add(b);
            }
        }
        for (Base b: game.getBases()) {
            if ((!livingFactions.contains(b.getFaction())) && b.getFaction() != Faction.NEUTRAL) {
                livingFactions.add(b.getFaction());
            }
        }
        setScoreList();
        orderedFriendlyBases.addAll(faction.getBaseList());
    }
    //weakest on top?
    Comparator<Base> weakestBaseToTop = new Comparator<Base>() {
        @Override
        public int compare(Base o1, Base o2) {
            if (o1.getGarrison() > o2.getGarrison()) {
                return 1;
            }
            else if (o1.getGarrison() < o2.getGarrison()) {
                return -1;
            }
            else {
                return 0;
            }
        }
    };
    Comparator<Base> strongestBaseToTop = new Comparator<Base>() {
        @Override
        public int compare(Base o1, Base o2) {
            if (o1.getGarrison() > o2.getGarrison()) {
                return -1;
            }
            else if (o1.getGarrison() < o2.getGarrison()) {
                return 1;
            }
            else {
                return 0;
            }
        }
    };
    Comparator<Duple<Base, Integer>> lowScoreTop = new Comparator<Duple<Base, Integer>>() {
        @Override
        public int compare(Duple<Base, Integer> o1, Duple<Base, Integer> o2) {
            if (o1.getSecond() > o2.getSecond()) {
                return 1;
            }
            else if (o1.getSecond() < o2.getSecond()) {
                return -1;
            }
            else {
                return 0;
            }
        }
    };
    PriorityQueue<Base> orderedFriendlyBases = new PriorityQueue<>(strongestBaseToTop);
//    private ArrayList<Base> getBorderBases(Base home, ArrayList<Point2D> points) {
//        ArrayList<Base> border_lst = new ArrayList<>();
//        for (Point2D p: points) {
//            ArrayList<Base> potential_borders = new ArrayList<>(game.getBases());
//            potential_borders.removeAll(border_lst);
//            Base closest = potential_borders.get(0);
//            for (Base b : potential_borders) {
//                if (p.distance(b.getPoint()) < p.distance(closest.getPoint())) {
//                    closest = b;
//                }
//            }
//            if (home.getPoint().getX() < home.getPoint().distance(closest.getPoint()))
//            border_lst.add(closest);
//        }
//        return border_lst;
//    }
    //there is a finite number of bases, so I think I can just call this every time
    public void updateLists(Base changed, Faction former_faction) {
        Faction new_faction = changed.getFaction();
        if (former_faction == faction) {
            orderedFriendlyBases.remove(changed);
            score_list.add(new Duple<>(changed, setScore(changed)));
        }
        else if (new_faction == faction) {
            orderedFriendlyBases.add(changed);
            score_list.remove(changed);
        }
    }
    PriorityQueue<Duple<Base, Integer>> score_list = new PriorityQueue<>(lowScoreTop);
    private void setScoreList() {
        for (Base b: enemyBases) {
            score_list.add(new Duple<>(b, setScore(b)));
        }
        for (Base b: neutralBases) {
            score_list.add(new Duple<>(b, setScore(b)));
        }
    }
    private int setScore(Base b) {
        //I plan to just make the attacking base be the most powerful base. I can make this better later
        /*
        Higher score is worse. Lower score is more ideal for an attack
        Criteria:
            - Garrison size
            - Distance
            - Potential Ideas
                - Faction strength
         */
        int score = 0;
        score += b.getGarrison();
        if (orderedFriendlyBases.peek() != null) {
            score += b.getPoint().distance(orderedFriendlyBases.peek().getPoint());
        }
        return score;
    }
    private boolean shouldAttack() {
        int totalPower = 0;
        for(Faction f: livingFactions) {
            totalPower += f.calculateStrength();
        }
        if (faction.calculateStrength() >= totalPower / livingFactions.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void attack() {
        Base attacker = orderedFriendlyBases.peek();
        Base defender = score_list.peek().getFirst();
//        System.out.println("Other Bases: " + orderedOtherBases);
//        System.out.println("Faction Base list: " + faction.getBaseList());
//        System.out.println("Friendly Bases: " + orderedFriendlyBases);
        if (attacker != null && defender != null && attacker.getGarrison() > defender.getGarrison()) {
            attacker.setTarget(defender);
        }
    }

}
