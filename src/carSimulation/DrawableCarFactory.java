package carSimulation;

import cars.Saab95;
import cars.Scania;
import cars.Volvo240;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.io.IOException;

public class DrawableCarFactory {
    public static DrawableCar createVolvo240(Point2D point) throws IOException {
        return new DrawableCar(new Volvo240(point.getX(), point.getY()), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
    }

    public static DrawableCar createSaab95(Point2D point) throws IOException {
        return new DrawableCar(new Saab95(point.getX(), point.getY()), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
    }

    public static DrawableCar createScania(Point2D point) throws IOException {
        return new DrawableCar(new Scania(point.getX(), point.getY()), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
    }
}
