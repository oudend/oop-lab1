import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {
    private Saab95 saab95;

    @BeforeEach
    void init() {
        saab95 = new Saab95();
    }

    @Nested
    class InitialValueTests {
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
        void testInitialSpeed() {
            assertEquals(0, saab95.getCurrentSpeed());
        }

        @Test
        void testModelName() {
            assertEquals("Saab95", saab95.getModelName());
        }
    }

    @ParameterizedTest
    @CsvSource({"255, 0, 0", "0, 255, 0", "9, 205, 218"})
    void testSetColor(int r, int g, int b) {
        Color color = new Color(r, g, b);
        saab95.setColor(color);
        assertEquals(color, saab95.getColor());
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
    void testEngine() {
        saab95.startEngine();
        assertEquals(0.1, saab95.getCurrentSpeed());
        saab95.gas(10);
        assertNotEquals(0, saab95.getCurrentSpeed());
        saab95.stopEngine();
        assertEquals(0, saab95.getCurrentSpeed());
    }

    @Test
    void testSpeed() {
        saab95.gas(100);
        assertEquals(saab95.getEnginePower(), saab95.getCurrentSpeed());
        
        saab95.brake(50);
        assertEquals(saab95.getEnginePower() / 2, saab95.getCurrentSpeed());

        saab95.brake(50);
        assertEquals(0, saab95.getCurrentSpeed());
    }
}