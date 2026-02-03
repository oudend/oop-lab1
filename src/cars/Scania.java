package cars;

import java.awt.*;

public class Scania extends Car {
    /**
     * Affects the car's maximum bed angle
     * @see #lowerBed(double) 
     * @see #raiseBed(double)  
     */
    public final static double MAX_BED_ANGLE = 70f;

    /**
     * Affects the car's bed rotation speed
     * @see #lowerBed(double)
     * @see #raiseBed(double)
     */
    public final static double BED_ROTATION_SPEED = 1f;

    /**
     * The angle of the bed
     */
    private double bedAngle = 0.0f;

    public Scania(){
        super("Scania", Color.red, 250, 2, Math.PI / 8, 100, 300);
    }

    public double getBedAngle() {
        return bedAngle;
    }

    private void modifyBed(double amount) {
        if(getCurrentSpeed() > 0f)
            return;

        bedAngle += Math.clamp(amount, -1f, 1f) * BED_ROTATION_SPEED;
        bedAngle = Math.clamp(bedAngle, 0f, MAX_BED_ANGLE);
    }

    public void lowerBed(double amount) {
        modifyBed(-Math.clamp(amount, 0f, 1f));
    }

    public void raiseBed(double amount) {
        modifyBed(Math.clamp(amount, 0f, 1f));
    }

    @Override
    public void gas(double amount) {
        if(getBedAngle() > 0f)
            throw new RuntimeException("can't gas with non-zero bed angle");

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
