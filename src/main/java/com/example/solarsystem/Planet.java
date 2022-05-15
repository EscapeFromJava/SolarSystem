package com.example.solarsystem;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Planet {

    Label label;
    private double radius;
    private double distanceToSun;
    private double speed;
    private Color color;

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius / 2;
    }

    public double getDistanceToSun() {
        return distanceToSun;
    }

    public void setDistanceToSun(double distanceToSun) {
        this.distanceToSun = distanceToSun;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Planet(Label label, double radius, double distanceToSun, double speed, Color color) {
        this.label = label;
        this.radius = radius;
        this.distanceToSun = distanceToSun;
        this.speed = speed;
        this.color = color;
    }

    public void changeSpeed(){
        setSpeed(getSpeed() * 1.0002);
    }

    @Override
    public String toString() {
        return label + " " + radius + " " + distanceToSun + " " + speed + " " + color;
    }
}
