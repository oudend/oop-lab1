package main;

import carSimulation.App;
import carSimulation.DrawableCarFactory;
import carSimulation.DrawableCarWorkshopFactory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;

public class Main {
    static void main(String[] args) throws IOException {
        App app = new App();

        app.addCar(DrawableCarFactory.createVolvo240(new Point2D.Double(0, 0)));
        app.addCar(DrawableCarFactory.createSaab95(new Point2D.Double(0, 100)));
        app.addCar(DrawableCarFactory.createScania(new Point2D.Double(0, 200)));

        app.addCarWorkshop(DrawableCarWorkshopFactory.createVolvo240Workshop(10, new Point2D.Double(300, 0)));

        app.run();
    }
}
