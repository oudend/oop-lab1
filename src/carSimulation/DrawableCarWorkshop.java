package carSimulation;

import cars.CarWorkshop;

import java.awt.*;
import java.awt.geom.Point2D;

public class DrawableCarWorkshop implements DrawablePositionable {
    private final CarWorkshop<?> carWorkshop;
    private final Image image;

    DrawableCarWorkshop(CarWorkshop<?> workshop, Image image) {
        this.carWorkshop = workshop;
        this.image = image;
    }

    CarWorkshop<?> getCarWorkshop() {
        return carWorkshop;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public Point2D getPosition() {
        return carWorkshop.getPosition();
    }
}
