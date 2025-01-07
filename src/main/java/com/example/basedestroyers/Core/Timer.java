package com.example.basedestroyers.Core;

public class Timer {
    private double time;
    private double initialTime;
    public Timer() {
        time = 0.0;
        initialTime = 0.0;
    }
    public double getTime() {
        return time / 100;
    }
    public double getInitialTime() {
        return initialTime;
    }
    public void setTime(double time) {
        this.time = time;
    }
    public void setInitialTime(double initialTime) {
        this.initialTime = initialTime;
    }
    public boolean checkTime(double initialTime, double goalTime) {
        if (time / 100 - initialTime < goalTime) {
            return false;
        }
        return true;
    }
    public void update() {
        time += 1;
    }
}
