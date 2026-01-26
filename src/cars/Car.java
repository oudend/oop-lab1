package cars;
import java.awt.*;

/**
 * Abstract representation of a Car that provides basic car descriptors and basic movement logic
 */
public abstract class Car implements Movable {
    /**
     * A 2D-position represented by a set of x and y coordinates
     */
    public static final class Position {
        /**
         * Position-coordinate along the x-axis
         */
        public double x;

        /**
         * Position-coordinate along the y-axis
         */
        public double y;

        /**
         * Creates a new position-object with the coordinates (0, 0)
         */
        public Position() {
            x = 0;
            y = 0;
        }
    }

    private final int nrDoors;
    private final String modelName;
    private final Position position;
    private final double enginePower;

    private double currentSpeed;
    private double angle;
    private Color color;

    /**
     * Creates a Car of model <em>modelName</em>, of color <em>color</em>, with <em>enginePower</em> engine-power, and <em>nrDoors</em> doors
     * @param modelName Name of the car-model
     * @param color Color of the car
     * @param enginePower Power of the engine
     * @param nrDoors Number of doors on the car
     */
    public Car(String modelName, Color color, double enginePower, int nrDoors) {
        this.modelName = modelName;
        this.color = color;
        this.enginePower = enginePower;
        this.nrDoors = nrDoors;
        this.position = new Position();
        stopEngine();
    }

    /**
     * Moves the car linearly relative to its speed, in the direction that it is pointing
     * @see Car#getCurrentSpeed
     * @see Car#getAngle
     */
    public void move() {
        position.x += currentSpeed * Math.cos(angle);
        position.y += currentSpeed * Math.sin(angle);
    }

    /**
     * Normalizes an angle in radians to the range [0, 2PI] with a modulation precision of 0.01
     * @param angle angle to normalize
     * @return normalized angle
     */
    private double normalizeAngle(double angle) {
        while (angle >= 2 * Math.PI) angle -= 2 * Math.PI;
        while (angle < 0) angle += 2 * Math.PI;
        if (angle >= 2 * Math.PI - 0.01) angle = 0;
        return angle;
    }

    /**
     * Turns the car PI/8 radians to the left
     */
    public void turnLeft() {
        angle += Math.PI / 8;
        angle = normalizeAngle(angle);
    }

    /**
     * Turns the car PI/8 radians to the right
     */
    public void turnRight() {
        angle -= Math.PI / 8;
        angle = normalizeAngle(angle);
    }

    /**
     * Gets the current angle of the car in radians
     * @return angle in radians
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Gets the current position of the car
     * @return position of the car
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Accelerates the car using {@link #incrementSpeed(double)}
     *
     * The updated speed is in the range [current speed, engine power]
     *
     * @param amount amount to gas in the range [0, 1]
     */
    public void gas(double amount) {
        double old_speed = currentSpeed;
        incrementSpeed(Math.clamp(amount, 0, 1));
        currentSpeed = Math.clamp(currentSpeed, old_speed, enginePower);
    }

    /**
     * Brakes the car using {@link #decrementSpeed(double)}
     *
     * The updated speed is in the range [current speed, engine power]
     *
     * @param amount amount to brake in the range [0, 1]
     */
    public void brake(double amount) {
        double old_speed = currentSpeed;
        decrementSpeed(Math.clamp(amount, 0, 1));
        currentSpeed = Math.clamp(currentSpeed, 0, old_speed);
    }

    /**
     * Gets the number of doors on the car
     * @return number of doors on the car
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * Gets the power of the car's engine
     * @return engine power of the car
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * Gets the car's current speed
     * @return current speed of the car
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * Gets the name of the car's model
     * @return name of the car-model
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Gets the color of the car
     * @return color of the car
     */
    public Color getColor(){
        return color;
    }

    /**
     * Sets the color of the car
     * @param color color to apply to the car
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * Sets the speed of the car
     * @param speed new speed
     */
    protected void setCurrentSpeed(double speed) {
        currentSpeed = speed;
    }

    /**
     * Starts the car by setting the speed to 0.1
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Sets the car's speed to 0, stopping it
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Increments the speed of the car
     * @param amount amount to increase the speed by, in the range [0, 1]
     */
    protected abstract void incrementSpeed(double amount);
    /**
     * Decrements the speed of the car
     * @param amount amount to decrease the speed by, in the range [0, 1]
     */
    protected abstract void decrementSpeed(double amount);
}