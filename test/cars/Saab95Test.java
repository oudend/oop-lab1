package cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {
    private Saab95 saab95;

    @BeforeEach
    void init() {
        saab95 = new Saab95();
    }

    @Test
    void testNrDoors() {
        assertEquals(2, saab95.getNrDoors());
    }

    @Test
    void testEnginePower() {
        assertEquals(125, saab95.getEnginePower());
    }

    @Test
    void testInitialColor() {
        assertEquals(Color.red, saab95.getColor());
    }

    @Test
    void testModelName() {
        assertEquals("Saab95", saab95.getModelName());
    }

    @Test
    void testTurbo() {
        saab95.gas(1);
        double noTurbo = saab95.getCurrentSpeed();
        saab95.stopEngine();
        saab95.setTurboOn();
        saab95.gas(1);
        double withTurbo = saab95.getCurrentSpeed();
        assertTrue(noTurbo < withTurbo);

        saab95.stopEngine();
        saab95.setTurboOff();
        saab95.gas(1);
        assertEquals(noTurbo, saab95.getCurrentSpeed());

        saab95.stopEngine();
        saab95.setTurboOn();
        saab95.gas(1);
        assertEquals(withTurbo, saab95.getCurrentSpeed());
    }


    @Test
    void testSpeed() {
        for (int i = 0; i < 100; i++) saab95.gas(1);
        assertEquals(saab95.getEnginePower(), saab95.getCurrentSpeed());

        for (int i = 0; i < 100; i++) saab95.brake(.5);
        assertEquals(saab95.getEnginePower() / 2, saab95.getCurrentSpeed());

        for (int i = 0; i < 100; i++) saab95.brake(.5);
        assertEquals(0, saab95.getCurrentSpeed());
    }
}