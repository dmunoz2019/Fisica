package com.example.physicsenginev_0_1;

import javafx.scene.paint.Color;

public class Circle extends Body {
    public Circle(double xPos, double yPos, double xVel, double yVel, double k, boolean inMotion, double mass, Color color, double radius, double width, double height) {
        super(xPos, yPos, xVel, yVel, k, inMotion, mass, color, radius, width, height);
    }

    @Override
    void draw() {
        World.getInstance().drawCircle(xPos, yPos, radius, color);
    }
}
