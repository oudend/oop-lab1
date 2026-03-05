package carSimulation;

import cars.Car;
import cars.CarWorkshop;
import cars.Volvo240;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.io.IOException;

public class DrawableCarWorkshopFactory {
    public static DrawableCarWorkshop createVolvo240Workshop(int maxCars, Point2D.Double point) throws IOException {
        return new DrawableCarWorkshop(new CarWorkshop<Volvo240>(Volvo240.class, maxCars, point.getX(), point.getY()), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg")));
    }
}
