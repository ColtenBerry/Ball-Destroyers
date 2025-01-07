package com.example.basedestroyers.AI;

import com.example.basedestroyers.Core.Base;
import com.example.basedestroyers.Faction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Timer;

public class RevisedComputerMove {
    Faction faction;
    Timer timer;
    ArrayList<Base> hostile_bases = new ArrayList<>();
    ArrayList<Base> bases = new ArrayList<>();
    public RevisedComputerMove(Faction faction, Timer timer, ArrayList<Base> bases){
        this.faction = faction;
        this.timer = timer;
        this.bases = bases;
        for (Base b: bases) {
            if (b.getFaction() != faction && b.getFaction() != Faction.NEUTRAL) {
                organized_hostile_lst.add(b);
            }
            else if (b.getFaction() == faction) {
                organized_friendly_lst.add(b);
            }
        }
    }
    /*
    I want this to be the standard for computer AI. Thus, it should accomplish the following
        - Attack
            - locate a good target
            - pick an attacking base
            - attack
        - Defend
            - understand when a friendly base is under attack and in danger of being lost
            - Pick an appropriate reinforcing base
            - Reinforce the under attack base
        - Grab Neutral Bases
            - NOTE: In a 1 v 1 scenario, this is really not a viable strategy
            - NOTE: However, in a lobby with 4 players, it is more viable to grab small, weak, neutral bases
            - Identify when it is a good time to grab a neutral base
            - Locate a neutral base that can be acquired
            - Choose an appropriate attacking base
            - Attack
    It is important to note that I believe it still makes sense to incorporate the ideas I had in my previous
    ComputerMove Class concerning randomness. I believe this is the method that will best mimic a human and
    their ability to make intelligent moves, but occasionally make blunders.
     */
    //weakest at top
    PriorityQueue<Base> organized_hostile_lst = new PriorityQueue<>(new Comparator<Base>() {
        @Override
        public int compare(Base o1, Base o2) {
            int curr_garrison = o1.getGarrison();
            int next_garrison = o2.getGarrison();
            if (curr_garrison > next_garrison) {
                return 1;
            }
            else if (curr_garrison < next_garrison) {
                return -1;
            }
            else{
                return 0;
            }
        }
    });
    //strongest at top
    PriorityQueue<Base> organized_friendly_lst = new PriorityQueue<>(new Comparator<Base>() {
        @Override
        public int compare(Base o1, Base o2) {
            int curr_garrison = o1.getGarrison();
            int next_garrison = o2.getGarrison();
            if (curr_garrison > next_garrison) {
                return -1;
            }
            else if (curr_garrison < next_garrison) {
                return 1;
            }
            else {
                return 0;
            }
        }
    });
    private Base getWeakestBase(Faction a) {
        Base weakest = null;
        for (Base b : a.getBaseList()) {
            if (weakest == null || b.getGarrison() < weakest.getGarrison()) {
                weakest = b;
            }
        }
        return weakest;
    }
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
    private Base getWeakestBase() {
        Base weakest = hostile_bases.get(0);
        for (Base b: hostile_bases) {
            if (b.getGarrison() < weakest.getGarrison()) {
                weakest = b;
            }
        }
        return weakest;
    }

    private Base getFriendlyStrongest() {
        Base strongest = faction.getBaseList().get(0);
        for (Base b: faction.getBaseList()) {
            if (b.getGarrison() > strongest.getGarrison()) {
                strongest = b;
            }
        }
        return strongest;
    }

    public void makeMove() {
        System.out.println("Here 1");
        for (Base hostile: organized_hostile_lst) {
            Base target = hostile;
            if (!organized_friendly_lst.isEmpty()) {
                System.out.println(faction + " list is not empty");
                Base attacker = organized_friendly_lst.poll();
                if (attacker.getGarrison() >= target.getGarrison()) {
                    attacker.setTarget(target);
                }
            }
        }
        //updating the bases list is not working correctly
        for (Base b: bases) {
            if (b.getFaction() != faction && b.getFaction() != Faction.NEUTRAL && !organized_hostile_lst.contains(b)) {
                organized_hostile_lst.add(b);
                System.out.println(organized_friendly_lst);
            }
            else if (b.getFaction() == faction && !organized_friendly_lst.contains(b)) {
                organized_friendly_lst.add(b);
            }
        }

    }
}
