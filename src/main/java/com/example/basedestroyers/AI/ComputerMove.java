package com.example.basedestroyers.AI;

import com.example.basedestroyers.Core.Base;
import com.example.basedestroyers.Core.Timer;
import com.example.basedestroyers.Duple;
import com.example.basedestroyers.Faction;

import java.util.ArrayList;
import java.util.Random;
/*
Standard AI:
in order to decide what to do / what to attack will be based on a few factors
    - distance, allegiance (strength level), and base strength?
    - Maybe look at reinforcements coming in, etc. that can come later
    To accomplish this, each enemy base will need a score. Scores will be >0 and <1. and all scores will
    add up to 1
    - Arraylist of duples<Base (enemy bases), integer (score)
    - function to calculate score of a base.
    - function to update scorelist
    - function to convert all scores to a percentage.
    - makemove will be changed to pick a double bewtween 0 and 1. Then, it will pick the corresponding move.
    That is just for attacking. When picking the attacking base, it should just pick the most powerful and
    closest friendly base. Perhaps multiple bases could be used if necessary


scorelist = arraylist<duple<base, double>>
for base in enemybases:
    calculate score(base)
    duple = new duple(base, score)
    scorelist.add duple

for score in scorelist:
    sum += score
for score in scorelist:
    part = score / sum:
    scorelist.getsecond = part
 */

public class ComputerMove {
    Faction faction;
    Timer timer;

    public ComputerMove(Timer timer, Faction faction) {
        this.timer = timer;
        this.faction = faction;
    }
    private Base getWeakestBase(Faction a) {
        Base weakest = null;
        for (Base b : a.getBaseList()) {
            if (weakest == null || b.getGarrison() < weakest.getGarrison()) {
                weakest = b;
            }
        }
        return weakest;
    }
    private Base getStrongestBase(Faction a) {
        Base strongest = null;
        for (Base b: a.getBaseList()) {
            if (strongest == null || b.getGarrison() > strongest.getGarrison()) {
                strongest = b;
            }
        }
        return strongest;
    }
    //get the closest base to base b that is of allegiance a
    private Base getClosestBase(Base b, Faction a) {
        Base closest;
        closest = a.getBaseList().get(0);
        double closest_distance = b.getPoint().distance(closest.getPoint());
        for (Base fb : a.getBaseList()) {
            double temp_distance = fb.getPoint().distance(closest.getPoint());
            if (temp_distance < closest_distance) {
                closest_distance = temp_distance;
                closest = fb;
            }
        }
        return closest;
    }
    //same as get closest base, except only returns a base that has sufficient garrison
    private Base getPotentialAttacker(Base target, Faction a) {
        Base closest;
        closest = a.getBaseList().get(0);
        double closest_distance = target.getPoint().distance(closest.getPoint());
        for (Base fb : a.getBaseList()) {
            double temp_distance = fb.getPoint().distance(closest.getPoint());
            if (temp_distance < closest_distance && target.getGarrison() < fb.getGarrison()) {
                closest_distance = temp_distance;
                closest = fb;
            }
        }
        if (closest.getGarrison() < target.getGarrison()) {
            return null;
        }
        return closest;
    }

    //get the score of a base. Higher score is better
    private double getScore(Base e) {
        double score;
        //get base strength:
        int strength = e.getGarrison();
        Base potential_attacker = getStrongestBase(faction);
        if (strength <= potential_attacker.getGarrison() * 0.1) {
            strength = 100;
        }
        else if (strength <= potential_attacker.getGarrison() * 0.25) {
            strength = 80;
        }
        else if (strength <= potential_attacker.getGarrison() * 0.5) {
            strength = 50;
        }
        else if (strength <= potential_attacker.getGarrison() * 0.75) {
            strength = 20;
        }
        else {
            strength = 0;
        }
        //get distance from attacking base:
        /*
        in order for distance to be correctly calculated, a closer distance should give a higher value
        c = distance from potential target and the closest base of the attacking faction
         */
        //distance between closest home faction base to potential target
        Base closest_attacker = getClosestBase(e, faction);
        double c = closest_attacker.getPoint().distance(e.getPoint());
        double distance;
        //the closer the base, the higher the score
        if (0 <= c && c <= 75) {
            distance = 50;
        }
        else if (75 < c && c <= 150) {
            distance = 30;
        }
        else if (150 < c && c <= 250) {
            distance = 10;
        }
        else {
            distance = 0;
        }
        // get faction strength
        int faction_strength = e.getFaction().calculateStrength();
        if (faction_strength <= faction.calculateStrength() * .5) {
            faction_strength = 0;
        }
        else if (faction_strength <= faction.calculateStrength()) {
            faction_strength = 0;
        }
        else if (faction_strength >= faction.calculateStrength()) {
            faction_strength = 0;
        }
        else if (faction_strength >= faction.calculateStrength() * 0.5) {
            faction_strength = 0;
        }
        score = strength + distance + faction_strength;
        return score;
    }
    //returns a target based on randomness and the scoring algorithm
    private Base getTarget(Faction targetFaction, Faction h) {
        //creates list of all scores for a target faction
        ArrayList<Duple<Base, Double>> score_lst = new ArrayList<>();
        for (Base b: targetFaction.getBaseList()) {
            Base closest_home = getClosestBase(b, h);
            double score = getScore(b);
            System.out.println("Score: " + score);
            Duple<Base, Double> duple= new Duple<>(b, score);
            score_lst.add(duple);
        }
        //gets total
        double total = 0;
        for (Duple<Base, Double> d: score_lst) {
            total += d.getSecond();
        }
        //get percentage and add to new list
        ArrayList<Duple<Base, Double>> percentage_lst = new ArrayList<>();
        for (Duple<Base, Double> d: score_lst) {
            double percentage = d.getSecond() / total;
            Duple<Base, Double> new_duple = new Duple<>(d.getFirst(), percentage);
            percentage_lst.add(new_duple);
            System.out.println("I think this is the percentage: " + percentage);
        }
        double lst_totals = 0;
        ArrayList<Duple<Base, Double>> final_lst = new ArrayList<>();
        for (Duple<Base, Double> duple: percentage_lst) {
            Duple<Base, Double> new_duple = new Duple<>(duple.getFirst(), duple.getSecond() + lst_totals);
            final_lst.add(new_duple);
            lst_totals += duple.getSecond();
        }

        return semiRandomBaseSelector(final_lst);
    }
    private Base semiRandomBaseSelector(ArrayList<Duple<Base, Double>> percentage_lst) {
        Random rand = new Random();
        double result = rand.nextDouble();
        Base possible_base = percentage_lst.get(0).getFirst();
        double possible_percentage = 0;
        for (Duple<Base, Double> duple: percentage_lst) {
            if (result < duple.getSecond()) {
                System.out.println("Target Base: " + possible_base);
                System.out.println("Percentage Chance: " + duple.getSecond());
                System.out.println("Roll: " + result);
                System.out.println("Percentage list: " + percentage_lst);
                return possible_base;
            }
            possible_base = duple.getFirst();
            possible_percentage = duple.getSecond();
        }
        System.out.println("Target Base: " + possible_base);
        System.out.println("Percentage Chance: " + possible_percentage);
        System.out.println("Roll: " + result);
        System.out.println(percentage_lst);
        return possible_base;
        //get last one if this is ever reached
        /*
        this is getting reached because the result is higher than the final percentage.
         */
//        System.out.println("Result: " + result);
//        System.out.println("Last Percentage: " + percentage_lst.get(percentage_lst.size() - 1).getSecond());
//        System.out.println("This probably shouldn't be reached");
//        System.out.println("return first base in list");
//        return percentage_lst.get(0).getFirst();
    }
    private boolean rollDice(double odds) {
        Random rand = new Random();
        double result = rand.nextDouble();
        if (result < odds) {
            return true;
        }
        return false;
    }
    private void attackWeakest(Faction target) {
        Base enemyWeakest = getWeakestBase(target);
        Base friendlyStrongest = getStrongestBase(faction);
        if (enemyWeakest != null && friendlyStrongest != null) {
            if (friendlyStrongest.getGarrison() > enemyWeakest.getGarrison()) {
                //attack target base
                friendlyStrongest.setTarget(enemyWeakest);
//            System.out.println("Attack");
            }
        }
    }
    public void makeMove(Faction targetFaction) {
//        boolean roll = rollDice(0.65);
//        if (roll) {
//            attackWeakest(target, attacker);
            Base target = getTarget(targetFaction, faction);
            Base attacker = getPotentialAttacker(target, faction);
            if (attacker != null) {
                attacker.setTarget(target);
                System.out.println("Attack!! Attacker: " + attacker + "; Target: " + target);
                System.out.println(attacker.getFaction());
//            }
        }
    }
}


/*
New Ideas:
All Out Attack Base:
    an ai that matches its most powerful bases with its enemies most powerful bases.
        - Orders its bases from most to least powerful.
        - depending on its mode, it can either:
            - target the weakest bases on the map
            - or target its nearest neighbors
        - it will list these targets in order of strongest to weakest
        - it will use as many bases as is necessary to attack the strongest neighbor base
        - it will repeat that process to attack the next strongest base. repeat
    Strengths:
        - coordinated base attacks
        - capable of overwhelming very quickly
    Weaknesses:
        - Could very easily overextend
        - If unable to deal a "killing" blow, it would be extremely weak
        - It would be weak against multiple opponents?


Defensive Base:
    - Would defend until it finds a base with < 10 garrison?
 */