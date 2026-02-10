package cars;

import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Car implements CarStorage<Car> {

    /**
     * The enum Bed mode.
     */
    public enum BedMode {
        UP,
        DOWN
    }

    private BedMode bedMode = BedMode.DOWN;
    private double bedCarDepth = 0;
    ArrayList<Car> cars = new ArrayList<>();

    /**
     * Creates a Car of model <em>CarTransport</em>, of color <em>Color.red</em>, with <em>250</em> engine-power, and <em>2</em> doors
     */
    public CarTransport() {
        super("CarTransport", Color.red, 250, 2, Math.PI / 10, 100, 300);
    }

    /**
     * loads a given car as long as it's close enough and not too wide.
     * @param car The car to load
     */
    public void load(Car car) {
        if(car.getWidth() >= getWidth() || bedCarDepth + car.getDepth() >= getDepth())
            throw new RuntimeException("Car does not fit on bed");

        if(car.getPosition().distance(getPosition()) > Math.max(car.getWidth(), car.getDepth()) * 1.5)
            throw new RuntimeException("Car is too far away to be loaded");

        if(bedMode == BedMode.UP)
            throw new RuntimeException("Can't load while the bed is up");

        bedCarDepth += car.getDepth();
        car.attach(this);
        cars.add(car);
    }

    /**
     * unloads the first stored car.
     * @return Car the car that was unloaded
     */
    public Car unload() {
        if(bedMode == BedMode.UP)
            throw new RuntimeException("Can't unload while the bed is up");

        Car unloadedCar = cars.removeFirst();
        bedCarDepth -= unloadedCar.getDepth();
        unloadedCar.detach();

        return unloadedCar;
    }

    /**
     * Gets bed mode.
     *
     * @return the bed mode
     */
    public BedMode getBedMode() {
        return bedMode;
    }

    /**
     * Lower bed.
     */
    public void lowerBed() {
        if(getCurrentSpeed() != 0)
            throw new RuntimeException("Can't lower the bed while the car is non stationary");

        bedMode = BedMode.DOWN;
    }

    /**
     * Raise bed.
     */
    public void raiseBed() {
        bedMode = BedMode.UP;
    }

    @Override
    public void gas(double amount) {
        if(bedMode == BedMode.DOWN)
            throw new RuntimeException("can't gas while the bed is down");

        super.gas(amount);
    }

    /**
     * Calculates the car's speed-factor using its engine-power and trim-factor
     * @see #getEnginePower()
     * @return calculated speed-factor
     */
    private double speedFactor(){
        return getEnginePower() * 0.01;
    }

    /**
     * Linearly increments the speed of the car relative to {@link #speedFactor()}
     * @param amount amount to increase the speed by, in the range [0, 1]
     */
    @Override
    protected void incrementSpeed(double amount){
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    /**
     * Linearly decrements the speed of the car relative to {@link #speedFactor()}
     * @param amount amount to decrease the speed by, in the range [0, 1]
     */
    @Override
    protected void decrementSpeed(double amount){
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }
}
