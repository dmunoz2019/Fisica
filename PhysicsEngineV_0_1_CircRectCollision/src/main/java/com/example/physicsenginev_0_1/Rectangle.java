package com.example.physicsenginev_0_1;

import javafx.scene.paint.Color;

public class Rectangle extends Body {
    public Rectangle(double xPos, double yPos, double xVel, double yVel, double k, boolean inMotion, double mass, Color color, double radius, double width, double height, boolean isVoid, boolean exists, boolean taco) {
        super(xPos, yPos, xVel, yVel, k, inMotion, mass, color, radius, width, height, isVoid, exists, taco);
    }

    @Override
    void draw() {
        World.getInstance().drawRectangle(xPos, yPos, width, height, color);
    }
}
