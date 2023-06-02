package com.example.physicsenginev_0_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class World {
    //Define la región visible en la ventana, definido por las coordenadas de la
    //esquina izquierda superior (xMin/yMax) y un factor de escala
    private final double xMin = -5.0; //metros
    private final double yMax = 8.0; //metros
    private final double scale =  50.0; //pixel/metro

    public static final double g = 0;

    private static World world = null;
    public static World getInstance() {
        if (world == null)
            world = new World();
        return world;
    }

    private ArrayList<Body> bodies = new ArrayList<Body>();
    //Agrega objetos al mundo de simulación
    public void create() {
        double r = 0.8;

        double w = 1.6;

        double h = 0.4;

        double alturaAnchuraMinima = 1;

        double alturaAnchuraMaxima = 5;

        Color colorPared = Color.ORANGE;

        //Circulos rebotando
        /*bodies.add(new com.example.physicsenginev_0_1.Circle(-3, 2, 0, 10, 0, true, 1, Color.RED, r, w, h));
        bodies.add(new com.example.physicsenginev_0_1.Circle(-3, 0, 0, 0, 0, false, 1, Color.GREEN, r, w, h));
        bodies.add(new com.example.physicsenginev_0_1.Circle(0, 2, 0, 10, 0.2, true, 1, Color.BLUE, r, w, h));
        bodies.add(new com.example.physicsenginev_0_1.Circle(0, 0, 0, 0, 0, false, 1, Color.ORANGE, r, w, h));

        bodies.add(new com.example.physicsenginev_0_1.Circle(0, 2, 0, -5, 1, true, 1, Color.PINK, r, w, h));
        bodies.add(new Rectangle(0, 0, 0, 0, 0, false, 1, Color.PURPLE, r, w, h));
        */

        //Paredes de la mesa de billar
        bodies.add(new Rectangle(0, 0, 0, 0, 0, false, 1, colorPared, 0, alturaAnchuraMinima, alturaAnchuraMaxima));
        bodies.add(new Rectangle(8, 0, 0, 0, 0, false, 1, colorPared, 0, alturaAnchuraMinima, alturaAnchuraMaxima));
        bodies.add(new Rectangle(4, 4, 0, 0, 0, false, 1, colorPared, 0, alturaAnchuraMaxima, alturaAnchuraMinima));
        bodies.add(new Rectangle(4, -4, 0, 0, 0, false, 1, colorPared, 0, alturaAnchuraMaxima, alturaAnchuraMinima));
        bodies.add(new Circle(3.5, 1.5, 3, 2, 0, true, 1, Color.BLUE, r, 0, 0));
    }


    private GraphicsContext gc = null;

    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;
    }

    public void drawCircle(double xCenter, double yCenter, double r, Color color) {
        double xPixel = toPixelX(xCenter);
        double yPixel = toPixelY(yCenter);
        double rPixel = scale*r;
        gc.setFill(color);
        gc.fillOval(xPixel-rPixel, yPixel-rPixel, 2*rPixel, 2*rPixel);
    }

    public void drawRectangle(double xCenter, double yCenter, double w, double h, Color color) {
        double xPixel = toPixelX(xCenter);
        double yPixel = toPixelY(yCenter);
        double wPixel = scale * w;
        double hPixel = scale * h;

        gc.setFill(color);
        gc.fillRect(xPixel - wPixel, yPixel - hPixel, 2 * wPixel, 2 * hPixel);
    }

    private double toPixelX(double x) {
        return scale*(x-xMin);
    }
    private double toPixelY(double y) {
        return scale*(yMax-y);
    }

    //simulación de un periodo de tiempo, avanza el tiempo deltaT
    public void run(double t, double deltaT) {
        bodies.forEach((n -> n.calcForces(World.getInstance().g)));

        if (deltaT < 1/60) {
            System.out.println("Lento!");
        }

        final double N = 2;
        final double nuevoDT = deltaT / N;

        for (int i = 0; i < bodies.size(); i++) {
            for (int j = 0; j < bodies.size(); j++) {
                if (bodies.get(i).isMovable == true && i != j) {
                    if (bodies.get(i) instanceof com.example.physicsenginev_0_1.Circle && bodies.get(j) instanceof com.example.physicsenginev_0_1.Circle) {
                        Vector col = Collision.checkCircleCollision(bodies.get(i), bodies.get(j));
                        bodies.get(i).extForces(col);
                    }

                    if (bodies.get(i) instanceof Circle && bodies.get(j) instanceof Rectangle) {
                        Vector col = Collision.checkCircleRectangleCollision(bodies.get(i), bodies.get(j));
                        bodies.get(i).extForces(col);
                    }
                }
            }
        }

        bodies.forEach(n -> n.sim(deltaT));
        //bodies.forEach(n -> n.sim(nuevoDT));
        bodies.forEach((n -> n.draw()));
    }
}
