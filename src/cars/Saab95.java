package cars;
import java.awt.*;

/**
 * Saab95 car with turbo functionality
 */
public class Saab95 extends Car{

    /**
     * Turbo affects the speed-factor of the car
     * @see #setTurboOn()
     * @see #setTurboOff()
     * @see #speedFactor()
     */
    private boolean turboOn;

    /**
     * Instantiates a new <em>red</em> Saab95 with <em>two</em> doors, an engine power of <em>125</em>, and <em>disabled</em> turbo
     */
    public Saab95(){
        super("Saab95", Color.red, 125, 2, Math.PI / 8, 40, 40);
        turboOn = false;
    }

    /**
     * Enables the turbo
     */
    public void setTurboOn(){
	    turboOn = true;
    }

    /**
     * Disables the turbo
     */
    public void setTurboOff(){
	    turboOn = false;
    }

    /**
     * Calculates the current speed factor of the car using the engine-power and turbo-state
     * @see #getEnginePower()
     * @see #turboOn
     * @return calculated speed-factor
     */
    private double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
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
