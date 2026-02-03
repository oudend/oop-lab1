package cars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTransportTest {
    private CarTransport carTransport;

    @BeforeEach
    void init() {
        carTransport = new CarTransport();
    }

    @Test
    void testBedMode() {
        carTransport.raiseBed();
        carTransport.lowerBed();
        assertEquals(CarTransport.BedMode.DOWN, carTransport.getBedMode());
        carTransport.raiseBed();
        assertEquals(CarTransport.BedMode.UP, carTransport.getBedMode());
    }

    @Test
    void testLoadExceedingBedDepth() {
        Car bigCar = new Volvo240() {
            @Override
            public double getDepth() {
                return carTransport.getDepth() + 1; // make it too big
            }
        };

        RuntimeException exception = assertThrows(RuntimeException.class, () -> carTransport.load(bigCar));
        assertEquals("Car does not fit on bed", exception.getMessage());
    }

    @Test
    void testMultipleCarsExceedingDepth() {
        Car car1 = new Volvo240() {
            @Override
            public double getDepth() {
                return carTransport.getDepth() / 2.0;
            }
        };
        Car car2 = new Volvo240() {
            @Override
            public double getDepth() {
                return carTransport.getDepth() / 2.0 + 1; // will exceed when combined
            }
        };

        carTransport.load(car1); // should succeed
        RuntimeException exception = assertThrows(RuntimeException.class, () -> carTransport.load(car2));
        assertEquals("Car does not fit on bed", exception.getMessage());
    }

    @Test
    void testLoadUnload() {
        Car testCar = new Volvo240();
        carTransport.load(testCar);

        carTransport.raiseBed();

        carTransport.gas(1);
        carTransport.move();

        assertEquals(carTransport.getPosition(), testCar.getPosition());

        carTransport.move();
        carTransport.brake(1);

        carTransport.lowerBed();

        assertEquals(testCar, carTransport.unload());

        assertNotEquals(carTransport.getPosition(), testCar.getPosition());
    }

    @AfterEach
    void checkBed() {
        assertTrue(carTransport.getCurrentSpeed() <= 0 || carTransport.getBedMode() == CarTransport.BedMode.UP,
                "If speed > 0, bedMode should be UP");
    }
}
