package com.example.solarsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.*;

public class HelloController {
    @FXML
    AnchorPane pane;
    Map<Planet, Circle> map = new HashMap<>();
    ArrayList<Planet> listPlanet = new ArrayList<>();
    double CENTER_X;
    double CENTER_Y;
    Timer timer = new Timer();
    TimerTask timerTask;
    long speed = 30;

    public void initialize() {

        CENTER_X = pane.getPrefWidth() / 2;
        CENTER_Y = pane.getPrefHeight() / 2;

        initListPlanet();
        drawSolarSystem();
        startAnimation();
        
    }

    private void drawSolarSystem() {
        for (Planet planet : listPlanet) {
            addOrbita(planet, pane);
            addPlanet(planet, pane, map);
        }
    }

    private void startAnimation() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                for (Circle currentCircle : map.values()) {
                    Planet currentPlanet = map.entrySet().stream().filter(el -> currentCircle.equals(el.getValue())).map(Map.Entry::getKey).findFirst().get();
                    currentCircle.setCenterX(CENTER_X + currentPlanet.getDistanceToSun() * Math.cos(currentPlanet.getSpeed()));
                    currentCircle.setCenterY(CENTER_Y + currentPlanet.getDistanceToSun() * Math.sin(currentPlanet.getSpeed()));
                    currentPlanet.label.setLayoutX(CENTER_X - (currentPlanet.getRadius()) + currentPlanet.getDistanceToSun() * Math.cos(currentPlanet.getSpeed()));
                    currentPlanet.label.setLayoutY(CENTER_Y + currentPlanet.getRadius() + currentPlanet.getDistanceToSun() * Math.sin(currentPlanet.getSpeed()));
                    currentPlanet.changeSpeed();
                }
            }
        };
        timer.schedule(timerTask, 0, speed);
    }

    private void addOrbita(Planet p, AnchorPane map) {
        Circle orbita = new Circle(CENTER_X, CENTER_Y, p.getDistanceToSun());
        orbita.setStroke(Paint.valueOf("ffffff30"));
        orbita.setFill(Paint.valueOf("#ffffff00"));
        map.getChildren().add(orbita);
    }

    private void addPlanet(Planet p, AnchorPane pane, Map<Planet, Circle> map) {
        Circle planet = new Circle(CENTER_X + p.getDistanceToSun(), CENTER_Y, p.getRadius());
        planet.setStroke(Color.BLACK);
        planet.setFill(p.getColor());
        p.label.setTextFill(Color.WHITE);
        p.label.setLayoutX(CENTER_X + p.getDistanceToSun());
        p.label.setLayoutY(CENTER_Y);
        pane.getChildren().addAll(planet, p.label);
        map.put(p, planet);
    }

    private void initListPlanet() {
        listPlanet.add(new Planet(new Label("Солнце"), 15, 0, 0, Color.YELLOW));
        listPlanet.add(new Planet(new Label("Меркурий"), 15, 50, 4.3, Color.GRAY));
        listPlanet.add(new Planet(new Label("Венера"), 15, 100, 10.4, Color.ORANGE));
        listPlanet.add(new Planet(new Label("Земля"), 15, 150, 11.2, Color.BLUE));
        listPlanet.add(new Planet(new Label("Марс"), 15, 200, 5.0, Color.ORANGERED));
        listPlanet.add(new Planet(new Label("Юпитер"), 15, 250, 59.5, Color.DARKORANGE));
        listPlanet.add(new Planet(new Label("Сатурн"), 15, 300, 35.5, Color.SANDYBROWN));
        listPlanet.add(new Planet(new Label("Уран"), 15, 350, 21.3, Color.ALICEBLUE));
        listPlanet.add(new Planet(new Label("Нептун"), 15, 400, 23.5, Color.CADETBLUE));
        listPlanet.add(new Planet(new Label("Плутон"), 15, 450, 1.1, Color.GREENYELLOW));
    }
}