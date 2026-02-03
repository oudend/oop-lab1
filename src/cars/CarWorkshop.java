package cars;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CarWorkshop<T extends Car> implements Positionable, CarStorage<T> {
    private final int maxCars;
    private final Point2D.Double position;

    private final ArrayList<T> cars = new ArrayList<>();

    public CarWorkshop(int maxCars) {
        this.maxCars = maxCars;
        this.position = new Point2D.Double(0, 0);
    }

    public int getMaxCars() {
        return maxCars;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public void load(T car) {
        if(cars.size() >= maxCars)
            throw new RuntimeException("CarWorkshop full");

        car.attach(this);
        cars.add(car);
    }

    public T unload(int index) {
        T unloadedCar = cars.remove(index);
        unloadedCar.detach();
        return unloadedCar;
    }

    @Override
    public T unload() {
        return unload(0);
    }
}
