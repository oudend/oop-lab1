package cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CarWorkshopTest {
    private CarWorkshop<Car> workshop;

    @BeforeEach
    void init() {
        workshop = new CarWorkshop<>(2);
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 10}) // different maxCars values to test
    void testGetMaxCars(int maxCars) {
        CarWorkshop<Car> carWorkshop = new CarWorkshop<>(maxCars);
        assertEquals(maxCars, carWorkshop.getMaxCars());
    }

    @Test
    void testLoadCar() {
        Car car = new Volvo240();

        assertDoesNotThrow(() -> workshop.load(car));
    }

    @Test
    void testUnloadCar() {
        Car car = new Volvo240();
        workshop.load(car);

        Car unloaded = workshop.unload();

        assertEquals(car, unloaded);
    }

    @Test
    void testMaxCapacityReached() {
        Car car1 = new Volvo240();
        Car car2 = new Volvo240();
        Car car3 = new Volvo240();

        workshop.load(car1);
        workshop.load(car2);

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> workshop.load(car3));

        assertEquals("CarWorkshop full", exception.getMessage());
    }

    @Test
    void testUnloadByIndex() {
        Car car1 = new Volvo240();
        Car car2 = new Volvo240();

        workshop.load(car1);
        workshop.load(car2);

        Car unloaded = workshop.unload(1);

        assertEquals(car2, unloaded);
    }
}
