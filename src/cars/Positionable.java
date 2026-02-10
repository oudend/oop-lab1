package cars;

import java.awt.geom.Point2D;

/**
 * Interface for positionable objects that have a position in 2d space
 */
public interface Positionable {
    /**
     * Gets position.
     *
     * @return the position
     */
    Point2D getPosition();
}
