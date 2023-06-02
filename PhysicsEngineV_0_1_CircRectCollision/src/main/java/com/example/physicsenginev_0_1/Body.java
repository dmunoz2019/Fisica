package com.example.physicsenginev_0_1;

import javafx.scene.paint.Color;

public abstract class Body {
    double xPos;
    double yPos;
    double xVel;
    double yVel;
    double k;
    double xNetForce;
    double yNetForce;
    boolean isMovable;
    double mass;
    Color color;
    double radius;
    double width;
    double height;
    boolean isVoid;
    boolean exists;
    boolean taco;

    public Body(double xPos, double yPos, double xVel, double yVel, double k, boolean isMovable, double mass, Color color, double radius, double width, double height, boolean isVoid, boolean exists, boolean taco) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.k = k;
        this.isMovable = isMovable;
        this.mass = mass;
        this.color = color;
        this.radius = radius;
        this.width = width;
        this.height = height;
        this.isVoid = isVoid;
        this.exists = exists;
        this.taco = taco;
    }

    public void calcForces(double g) {
        double fxG = 0;
        double fyG = mass * g;

        double fxR = k * xVel;
        double fyR = k * yVel;

        xNetForce = fxG - fxR;
        yNetForce = fyG - fyR;
    }

    public void sim(double deltaT) {
        if (isMovable == true) {
            //accl
            double xAccl = xNetForce / mass;
            double yAccl = yNetForce / mass;

            //vel
            xVel = xVel + xAccl * deltaT;
            yVel = yVel + yAccl * deltaT;

            //pos
            xPos = xPos + xVel * deltaT;
            yPos = yPos + yVel * deltaT;
        }
    }

    void extForces(Vector vector2D) {
        xNetForce = xNetForce + vector2D.x;
        yNetForce = yNetForce + vector2D.y;
    }

    abstract void draw();
}
