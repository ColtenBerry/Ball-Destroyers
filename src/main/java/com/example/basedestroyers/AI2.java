package com.example.basedestroyers;

import com.example.basedestroyers.Core.Base;

import java.util.ArrayList;

public class AI2 {
    /*
    This AI will be created based on a "Mode" system
    The modes will be as following
        - Aggressive
            - Victory is within grasp. The faction strength outnumbers all other faction strengths combined
            - Will attack with every base possible. If an enemy base is stronger, then multiple bases will attack
        - Defensive
            - If this faction is considered to be underpowered. Perhaps the least strong faction.
            - Will only take close, poorly defended bases. Will conserve strength at all costs
        - Balanced
            - If balance of power is relatively even.
            - will take close bases that it feels it can take
     */
    private TotalGame game = TotalGame.getGame();
    private Faction faction;
    private Mode mode = Mode.BALANCED;
    private ArrayList<Base> neutralBases = new ArrayList<>();
    private ArrayList<Base> enemyBases = new ArrayList<>();
    private ArrayList<Duple<Base, Integer>> score_list = new ArrayList<>();
    public AI2(Faction faction) {
        this.faction = faction;
        for (Base b: game.getBases()) {
            if (b.getFaction() == Faction.NEUTRAL) {
                neutralBases.add(b);
            }
            else if (b.getFaction() != faction) {
                if (b.getFaction() == Faction.PLAYER) {
                    System.out.println("player");
                }
                enemyBases.add(b);
            }
        }
    }

    private Base getStrongestBase(Faction faction) {
        Base strongest = null;
        for (Base b: faction.getBaseList()) {
            if (strongest == null || b.getGarrison() > strongest.getGarrison()) {
                strongest = b;
            }
        }
        return strongest;
    }
    public void setScores(Base attacker) {
        score_list.clear();
        for (Base b: enemyBases) {
            int score = 0;
            score += b.getGarrison();
            if (attacker != null) {
                score += b.getPoint().distance(attacker.getPoint());
            }
            Duple<Base, Integer> duple = new Duple<>(b, score);
            score_list.add(duple);
        }
    }
    private Base getBestTarget() {
        if (score_list.isEmpty()) {
            return null;
        }
        Duple<Base, Integer> bestTarget = score_list.get(0);
        for (Duple<Base, Integer> duple: score_list) {
            if (duple.getSecond() < bestTarget.getSecond()) {
                bestTarget = duple;
            }
        }
        return bestTarget.getFirst();
    }
    private void updateLists() {
        enemyBases.clear();
        neutralBases.clear();
        for (Base b: game.getBases()) {
            if (b.getFaction() == Faction.NEUTRAL) {
                neutralBases.add(b);
            }
            else if (b.getFaction() != faction) {
                enemyBases.add(b);
            }
        }
    }
    public void attack() {
        updateLists();
        //TODO: BUG- the faction just picks the first base in its list because it does not understand
        // which base is better for attacking
        Base attacker = getStrongestBase(faction);
        setScores(attacker);
//        System.out.println("Attacker" + attacker);
        Base defender = getBestTarget();
//        System.out.println("Defender" + defender);
        if (attacker != null && defender != null) {
            if (attacker.getGarrison() > defender.getGarrison() * 1.3) {
//                System.out.println("Attack");
                attacker.setTarget(defender);
            }
        }
    }
}
