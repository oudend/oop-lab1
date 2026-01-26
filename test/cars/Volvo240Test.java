package cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.*;

class Volvo240Test {
    Volvo240 volvo240;

    @BeforeEach
    void init() {
        volvo240 = new Volvo240();
    }

    @Test
    void testNrDoors() {
        assertEquals(4, volvo240.getNrDoors());
    }

    @Test
    void testEnginePower() {
        assertEquals(100, volvo240.getEnginePower());
    }

    @Test
    void testInitialColor() {
        assertEquals(Color.black, volvo240.getColor());
    }

    @Test
    void testModelName() {
        assertEquals("Volvo240", volvo240.getModelName());
    }

    @Test
    void testSpeed() {
        volvo240.gas(1);
        assertEquals(1.25, volvo240.getCurrentSpeed());

        for (int i = 0; i < 9001; i++) volvo240.gas(1);
        assertEquals(volvo240.getEnginePower(), volvo240.getCurrentSpeed());

        for (int i = 0; i < 20; i++) volvo240.brake(1);
        assertEquals(volvo240.getEnginePower() - 25, volvo240.getCurrentSpeed());

        for (int i = 0; i < 9001; i++) volvo240.brake(1);
        assertEquals(0, volvo240.getCurrentSpeed());
    }
}