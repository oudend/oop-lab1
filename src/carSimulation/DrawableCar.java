package carSimulation;

import cars.Car;

import java.awt.*;
import java.awt.geom.Point2D;

public class DrawableCar implements DrawablePositionable {
    private final Car car;
    private final Image image;
    DrawableCar(Car car, Image image) {
        this.car = car;
        this.image = image;
    }

    Car getCar() {
        return car;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public Point2D getPosition() {
        return car.getPosition();
    }
}
