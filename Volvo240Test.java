import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {
    Volvo240 volvo240;

    @BeforeEach
    void init() {
        volvo240 = new Volvo240();
    }

    @Nested
    class InitialValueTests {
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
        void testInitialSpeed() {
            assertEquals(0, volvo240.getCurrentSpeed());
        }

        @Test
        void testModelName() {
            assertEquals("Volvo240", volvo240.getModelName());
        }
    }

    @ParameterizedTest
    @CsvSource({"255, 0, 0", "0, 255, 0", "9, 205, 218"})
    void testSetColor(int r, int g, int b) {
        Color color = new Color(r, g, b);
        volvo240.setColor(color);
        assertEquals(color, volvo240.getColor());
    }

    @Test
    void testEngine() {
        volvo240.startEngine();
        assertEquals(0.1, volvo240.getCurrentSpeed());
        volvo240.gas(10);
        assertNotEquals(0, volvo240.getCurrentSpeed());
        volvo240.stopEngine();
        assertEquals(0, volvo240.getCurrentSpeed());
    }

    @Test
    void testSpeed() {
        volvo240.gas(1);
        assertEquals(1.25, volvo240.getCurrentSpeed());

        volvo240.gas(9001);
        assertEquals(volvo240.getEnginePower(), volvo240.getCurrentSpeed());

        volvo240.brake(20);
        assertEquals(volvo240.getEnginePower() - 25, volvo240.getCurrentSpeed());

        volvo240.brake(9001);
        assertEquals(0, volvo240.getCurrentSpeed());
    }
}