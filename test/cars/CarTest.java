package cars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    static final class TestCar extends Car {

        public TestCar() {
            super("TestCar", Color.cyan, 10, 0, Math.PI / 5, 40, 40);
        }

        @Override
        protected void incrementSpeed(double amount) {
            setCurrentSpeed(getCurrentSpeed() + amount);
        }

        @Override
        protected void decrementSpeed(double amount) {
            setCurrentSpeed(getCurrentSpeed() - amount);
        }
    }

    TestCar car;

    @BeforeEach
    void init() {
        car = new TestCar();
    }

    @Test
    void testModelName() {
        assertEquals("TestCar", car.getModelName());
    }

    @Test
    void testNrDoors() {
        assertEquals(0, car.getNrDoors());
    }

    @Test
    void testEnginePower() {
        assertEquals(10, car.getEnginePower());
    }

    @Test
    void testColor() {
        assertEquals(Color.cyan, car.getColor());
    }

    @ParameterizedTest
    @CsvSource({"255, 0, 0", "0, 255, 0", "9, 205, 218"})
    void testSetColor(int r, int g, int b) {
        Color color = new Color(r, g, b);
        car.setColor(color);
        assertEquals(color, car.getColor());
    }

    @Test
    void testIsStopped() {
        assertEquals(0, car.getCurrentSpeed());
    }

    @Test
    void testEngine() {
        car.startEngine();
        assertEquals(0.1, car.getCurrentSpeed());
        car.stopEngine();
        assertEquals(0, car.getCurrentSpeed());
    }

    @Test
    void testGasAndBrake() {
        car.gas(1);
        assertEquals(1, car.getCurrentSpeed());
        car.brake(1);
        assertEquals(0, car.getCurrentSpeed());

        for (int i = 0; i < 10; i++) car.gas(1);
        assertEquals(10, car.getCurrentSpeed());

        for (int i = 0; i < 5; i++) car.brake(1);
        assertEquals(5, car.getCurrentSpeed());
        for (int i = 0; i < 5; i++) car.brake(1);
        assertEquals(0, car.getCurrentSpeed());
    }

    @Test
    void testTurning() {
        assertEquals(0, car.getAngle(), 0.01);
        for (int i = 0; i < 1000; i++) {
            car.turnLeft();
            assertEquals(Car.normalizeAngle(car.getRotationSpeed() * (i+1)), car.getAngle(), 0.01);
        }

        for (int i = 0; i < 2000; i++) {
            car.turnRight();
            assertEquals(Car.normalizeAngle(car.getRotationSpeed() * (1000 - i - 1)), car.getAngle(), 0.01);
        }
    }

    @Test
    void testMovement() {
        assertEquals(0, car.getPosition().x, 0.01);
        assertEquals(0, car.getPosition().y, 0.01);

        car.gas(1);
        car.move();
        assertEquals(1, car.getPosition().x, 0.01);
        assertEquals(0, car.getPosition().y, 0.01);

        for (int i = 0; i < 2; i++) car.turnLeft();
        car.move();
        assertEquals(1.31, car.getPosition().x, 0.01);
        assertEquals(0.95, car.getPosition().y, 0.01);

        for (int i = 0; i < 4; i++) car.turnRight();
        car.gas(1);
        car.move();
        assertEquals(1.93, car.getPosition().x, 0.01);
        assertEquals(-0.95, car.getPosition().y, 0.01);
    }

    @Test
    void testGasBrakeRange() {
        car.gas(10);
        assertEquals(1, car.getCurrentSpeed());

        car.brake(10);
        assertEquals(0, car.getCurrentSpeed());

        car.gas(-1);
        assertEquals(0, car.getCurrentSpeed());

        car.brake(-1);
        assertEquals(0, car.getCurrentSpeed());
    }

    @Test
    void goFast() {
        for (int i = 0; i < 1000; i++) car.gas(1);
    }

    @Test
    void goBack() {
        for (int i = 0; i < 1000; i++) car.brake(1);
    }

    @AfterEach
    void checkSpeed() {
        assertTrue(car.getCurrentSpeed() >= 0);
        assertTrue(car.getCurrentSpeed() <= car.getEnginePower());
    }
}