package cars;

import java.awt.geom.Point2D;

/**
 * Interface for movable objects that can move and turn
 */
public interface Movable extends Positionable {
    /**
     * Moves the object
     */
    void move();

    /**
     * Turns the object to the left
     */
    void turnLeft();

    /**
     * Turns the object to the right
     */
    void turnRight();
}