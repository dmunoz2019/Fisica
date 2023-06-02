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
        double r = 0.2;
        double w = 1.6;
        double h = 0.4;
        double AalturaAnchuraMinima = 1.5;
        double AalturaAnchuraMaxima = 6;
        double balturaAnchuraMinima = 1.5;
        double balturaAnchuraMaxima = 9;
        Color COLORPARED = Color.BLUE;
        Color COLORPELOTA = Color.WHITE;
        Color COLORAGUJERO = Color.GREEN;

        double trianguloX = 9;
        double trianguloY = -1;
        double posicionInicial = trianguloY;
        double counter = 0;

        //Circulos rebotando
        /*bodies.add(new com.example.physicsenginev_0_1.Circle(-3, 2, 0, 10, 0, true, 1, Color.RED, r, w, h));
        bodies.add(new com.example.physicsenginev_0_1.Circle(-3, 0, 0, 0, 0, false, 1, Color.GREEN, r, w, h));
        bodies.add(new com.example.physicsenginev_0_1.Circle(0, 2, 0, 10, 0.2, true, 1, Color.BLUE, r, w, h));
        bodies.add(new com.example.physicsenginev_0_1.Circle(0, 0, 0, 0, 0, false, 1, Color.ORANGE, r, w, h));

        bodies.add(new com.example.physicsenginev_0_1.Circle(0, 2, 0, -5, 1, true, 1, Color.PINK, r, w, h));
        bodies.add(new Rectangle(0, 0, 0, 0, 0, false, 1, Color.PURPLE, r, w, h));
        */

        //Caja con circulo dentro (AP15)
        /*bodies.add(new Rectangle(0, 0, 0, 0, 0, false, 1, colorPared, 0, alturaAnchuraMinima, alturaAnchuraMaxima, false, true, false));
        bodies.add(new Rectangle(8, 0, 0, 0, 0, false, 1, colorPared, 0, alturaAnchuraMinima, alturaAnchuraMaxima, false, true, false));
        bodies.add(new Rectangle(4, 4, 0, 0, 0, false, 1, colorPared, 0, alturaAnchuraMaxima, alturaAnchuraMinima, false, true, false));
        bodies.add(new Rectangle(4, -4, 0, 0, 0, false, 1, colorPared, 0, alturaAnchuraMaxima, alturaAnchuraMinima, false, true, false));
        bodies.add(new Circle(3.5, 1.5, 3, 2, 0, true, 1, Color.BLUE, r, 0, 0, false, true, false));*/

        //Walls
        bodies.add(new Rectangle(-2.5, 0, 0, 0, 0, false, 1, COLORPARED, 0, AalturaAnchuraMinima, AalturaAnchuraMaxima, false, true, false));
        bodies.add(new Rectangle(12.5, 0, 0, 0, 0, false, 1, COLORPARED, 0, AalturaAnchuraMinima, AalturaAnchuraMaxima, false, true, false));
        bodies.add(new Rectangle(5, 4.5, 0, 0, 0, false, 1, COLORPARED, 0, balturaAnchuraMaxima, balturaAnchuraMinima, false, true, false));
        bodies.add(new Rectangle(5, -4.5, 0, 0, 0, false, 1, COLORPARED, 0, balturaAnchuraMaxima, balturaAnchuraMinima, false, true, false));

        //Holes
        bodies.add(new Circle(11, 3, 0, 0, 0, false, 1, null, 0.4, 0, 0, true, true, false));
        bodies.add(new Circle(11, -3, 0, 0, 0, false, 1, null, 0.4, 0, 0, true, true, false));
        bodies.add(new Circle(-1, 3, 0, 0, 0, false, 1, null, 0.4, 0, 0, true, true, false));
        bodies.add(new Circle(-1, -3, 0, 0, 0, false, 1, null, 0.4, 0, 0, true, true, false));

        //Cue ball
        bodies.add(new Circle(2, 0, 10, 0, 0.2, true, 1, Color.BLACK, r, 0, 0, false, true, false));

        //Balls
        for (int i = 3; i > 0; i--) {
            counter = counter + (r);
            trianguloY = trianguloY + counter;
            for (int j = 0; j < i; j++) {
                bodies.add(new Circle(trianguloX, trianguloY, 0, 0, 0.2, true, 1, COLORPELOTA, r, 0, 0, false, true, false));
                trianguloY = trianguloY + (r * 2);
            }
            trianguloY = posicionInicial;
            trianguloX = trianguloX - (r * 2);
        }

        //Cue
        //bodies.add(new Rectangle(-8, 0, 20, 0, 0, true, 1, Color.BLACK, 0, 2, 0.1, false, true, true));
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
                        Vector col = Collision.colisionCirculos(bodies.get(i), bodies.get(j));
                        bodies.get(i).extForces(col);
                    }

                    if (bodies.get(i) instanceof Circle && bodies.get(j) instanceof Rectangle) {
                        Vector col = Collision.colisionCirculoRectangulo(bodies.get(i), bodies.get(j));
                        bodies.get(i).extForces(col);
                    }
                }
            }
        }

        bodies.forEach(n -> n.sim(deltaT));
        //bodies.forEach(n -> n.sim(nuevoDT));

        for (int i = 0; i < bodies.size(); i++) {
            if (bodies.get(i).exists == true) {
                bodies.get(i).draw();
            }
            else {
                bodies.remove(i);
            }
        }
    }
}
