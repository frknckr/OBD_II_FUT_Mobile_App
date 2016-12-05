package com.andapp.futcorp.obdfut;

import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private float acceleration, coolant, intake, speed, load, throttle, boost, totalKm;

    public Car(float acceleration, float coolant, float intake, float speed, float load, float throttle, float boost, float totalKm) {
        this.acceleration = acceleration;
        this.coolant = coolant;
        this.intake = intake;
        this.speed = speed;
        this.load = load;
        this.throttle = throttle;
        this.boost = boost;
        this.totalKm = totalKm;
    }

    public float getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(float totalKm) {
        this.totalKm = totalKm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getCoolant() {
        return coolant;
    }

    public void setCoolant(float coolant) {
        this.coolant = coolant;
    }

    public float getIntake() {
        return intake;
    }

    public void setIntake(float intake) {
        this.intake = intake;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getLoad() {
        return load;
    }

    public void setLoad(float load) {
        this.load = load;
    }

    public float getThrottle() {
        return throttle;
    }

    public void setThrottle(float throttle) {
        this.throttle = throttle;
    }

    public float getBoost() {
        return boost;
    }

    public void setBoost(float boost) {
        this.boost = boost;
    }
}
