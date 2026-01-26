import java.awt.*;

public abstract class Car implements Movable {
    public static final class Position {
        public double x = 0, y = 0;
    }

    private final int nrDoors;
    private final String modelName;
    private final Position position;
    private final double enginePower;

    private double currentSpeed;
    private double angle;
    private Color color;

    public Car(String modelName, Color color, double enginePower, int nrDoors) {
        this.modelName = modelName;
        this.color = color;
        this.enginePower = enginePower;
        this.nrDoors = nrDoors;
        this.position = new Position();
        stopEngine();
    }

    public void move() {
        position.x += currentSpeed * Math.cos(angle);
        position.y += currentSpeed * Math.sin(angle);
    }

    private double normalizeAngle(double angle) {
        while (angle >= 2 * Math.PI) angle -= 2 * Math.PI;
        while (angle < 0) angle += 2 * Math.PI;
        if (angle >= 2 * Math.PI - 0.01) angle = 0;
        return angle;
    }

    public void turnLeft() {
        angle += Math.PI / 8;
        angle = normalizeAngle(angle);
    }

    public void turnRight() {
        angle -= Math.PI / 8;
        angle = normalizeAngle(angle);
    }

    public double getAngle() {
        return angle;
    }

    public Position getPosition() {
        return position;
    }

    public void gas(double amount) {
        double old_speed = currentSpeed;
        incrementSpeed(Math.clamp(amount, 0, 1));
        currentSpeed = Math.clamp(currentSpeed, old_speed, enginePower);
    }

    public void brake(double amount) {
        double old_speed = currentSpeed;
        decrementSpeed(Math.clamp(amount, 0, 1));
        currentSpeed = Math.clamp(currentSpeed, 0, old_speed);
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public String getModelName() {
        return modelName;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    protected void setCurrentSpeed(double speed) {
        currentSpeed = speed;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    protected abstract void incrementSpeed(double amount);
    protected abstract void decrementSpeed(double amount);
}