package com.example.basedestroyers.AI;

import com.example.basedestroyers.Core.Base;
import com.example.basedestroyers.Core.Timer;
import com.example.basedestroyers.Faction;
import com.example.basedestroyers.TotalGame;

import java.util.ArrayList;

public class SimpleComputerMove {
    TotalGame game = TotalGame.getGame();
    Faction faction;
    Timer timer;
    ArrayList<Base> enemy_bases = new ArrayList<>();
    public SimpleComputerMove(Faction faction, Timer timer) {
        this.faction = faction;
        this.timer = timer;
    }
    private Base getFriendlyStrongest() {
        if (!(faction.getBaseList().size() > 0)) {
            return null;
        }
        Base strongest = faction.getBaseList().get(0);
        for (Base fb: faction.getBaseList()) {
            if (fb.getGarrison() > strongest.getGarrison()) {
                strongest = fb;
            }
        }
        return strongest;
    }
    private Base getEnemyWeakest() {
        if (!(enemy_bases.size() > 0)) {
            return null;
        }
        Base weakest = enemy_bases.get(0);
        for (Base b: enemy_bases) {
            if (b.getGarrison() < weakest.getGarrison()) {
                weakest = b;
            }
        }
        return weakest;
    }

    private void updateBaseLists() {
        enemy_bases.clear();
        for (Base b: game.getBases()) {
            if (!faction.getBaseList().contains(b) && !Faction.NEUTRAL.getBaseList().contains(b)) {
                enemy_bases.add(b);
            }
        }
    }
    public void makeMove() {
        updateBaseLists();
        Base attacker = getFriendlyStrongest();
        Base target = getEnemyWeakest();
        if (attacker != null && target != null && attacker.getGarrison() > target.getGarrison()) {
            attacker.setTarget(target);
        }
    }
}
