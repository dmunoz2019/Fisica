package com.example.physicsenginev_0_1;

import static java.lang.Math.*;

public class Collision {
    static double kCol = 1000;

    static double calcularPuntoMedio(double dc, double minVal, double maxVal) {
        return max(minVal, min(maxVal, dc));
    }

    static Vector checkCircleCollision(Body alphaCircle, Body omegaCircle) {
        double fxExt = 0;
        double fyExt = 0;

        double drx = omegaCircle.xPos - alphaCircle.xPos;
        double dry = omegaCircle.yPos - alphaCircle.yPos;
        double dr = sqrt(pow(drx, 2) + pow(dry, 2));
        double d = alphaCircle.radius + omegaCircle.radius - dr;

        if (d > 0) {
            fxExt = -1 * (drx / dr) * kCol * d;
            fyExt = -1 * (dry / dr) * kCol * d;

            return new Vector(fxExt, fyExt);
        }
        return new Vector(fxExt, fyExt);
    }

    static Vector checkCircleRectangleCollision(Body circle, Body rectangle) {
        double nfx = 0;
        double nfy = 0;
        Vector cCentro = new Vector(circle.xPos, circle.yPos);
        Vector rCentro = new Vector(rectangle.xPos, rectangle.yPos);
        Vector dc = new Vector(cCentro.x - rCentro.x, cCentro.y - rCentro.y);
        Vector mitadAlturaAnchura = new Vector(rectangle.width / 2, rectangle.height / 2);
        Vector medio = new Vector(calcularPuntoMedio(dc.x, -mitadAlturaAnchura.x, mitadAlturaAnchura.x), calcularPuntoMedio(dc.y, -mitadAlturaAnchura.y, mitadAlturaAnchura.y));
        Vector closest = new Vector(rCentro.x + medio.x, rCentro.y + medio.y);
        Vector ndc = new Vector(closest.x - cCentro.x, closest.y - cCentro.y);
        double dr = sqrt(pow(ndc.x, 2) + pow(ndc.y, 2));
        double d = circle.radius + min(mitadAlturaAnchura.x, mitadAlturaAnchura.y) - dr;
        if (d > 0) {
            nfx = -1 * (ndc.x / dr) * kCol * d;
            nfy = -1 * (ndc.y / dr) * kCol * d;
            return new Vector(nfx, nfy);
        }
        return new Vector(nfx, nfy);
    }
}
