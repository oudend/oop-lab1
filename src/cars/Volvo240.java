package cars;
import java.awt.*;

/**
 * A class representing a Volvo 240 car
 */
public class Volvo240 extends  Car{

    /**
     * Affects the car's speed-factor
     * @see #speedFactor()
     */
    private final static double TRIM_FACTOR = 1.25;

    /**
     * Instantiates a new <em>black</em> Volvo240, with <em>four</em> doors and an engine power of 100
     */
    public Volvo240(){
        super("Volvo240", Color.black, 100, 4, Math.PI / 10, 40, 40);
    }

    /**
     * Calculates the car's speed-factor using its engine-power and trim-factor
     * @see #getEnginePower()
     * @see #TRIM_FACTOR
     * @return calculated speed-factor
     */
    private double speedFactor(){
        return getEnginePower() * 0.01 * TRIM_FACTOR;
    }

    /**
     * Linearly increments the speed of the car relative to {@link #speedFactor()}, and limits the speed to not exceed the power of the engine
     * @param amount amount to increase the speed by, in the range [0, 1]
     */
    @Override
    protected void incrementSpeed(double amount){
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower()));
    }

    /**
     * Linearly decrements the speed of the car relative to {@link #speedFactor()}, and limits the speed from going below 0
     * @param amount amount to decrease the speed by, in the range [0, 1]
     */
    @Override
    protected void decrementSpeed(double amount){
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount, 0));
    }
}
