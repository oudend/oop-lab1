package cars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest {
    private Scania scania;

    @BeforeEach
    void init() {
        scania = new Scania();
    }

    @Test
    void testNrDoors() {
        assertEquals(2, scania.getNrDoors());
    }

    @Test
    void testEnginePower() {
        assertEquals(250, scania.getEnginePower());
    }

    @Test
    void testInitialColor() {
        assertEquals(Color.red, scania.getColor());
    }

    @Test
    void testModelName() {
        assertEquals("Scania", scania.getModelName());
    }

    @Test
    void testBed() {
        assertEquals(0, scania.getBedAngle());

        scania.raiseBed(1f);
        scania.lowerBed(1f);

        assertEquals(0, scania.getBedAngle(), 0.01);

        scania.raiseBed(0.1);
        scania.raiseBed(10);
        assertEquals(Scania.BED_ROTATION_SPEED * 1.1, scania.getBedAngle(), 0.01);
        scania.lowerBed(10);
        assertEquals(Scania.BED_ROTATION_SPEED * 0.1, scania.getBedAngle(), 0.01);
        scania.lowerBed(0.1);
        assertEquals(0, scania.getBedAngle(), 0.01);

        double angle = 0;

        for (int i = 0; i < Scania.MAX_BED_ANGLE / Scania.BED_ROTATION_SPEED; i++) {
            scania.raiseBed(1f);
            angle += Scania.BED_ROTATION_SPEED;
            assertEquals(angle, scania.getBedAngle(), 0.01);
        }
        scania.raiseBed(1f);

        assertEquals(Scania.MAX_BED_ANGLE, scania.getBedAngle(), 0.01);

        angle = Scania.MAX_BED_ANGLE;

        for (int i = 0; i < Scania.MAX_BED_ANGLE / Scania.BED_ROTATION_SPEED; i++) {
            scania.lowerBed(1f);
            angle -= Scania.BED_ROTATION_SPEED;
            assertEquals(angle, scania.getBedAngle(), 0.01);
        }

        scania.lowerBed(1f);

        assertEquals(0, scania.getBedAngle(), 0.01);
    }

    @Test
    void testGas() {
        assertEquals(0, scania.getBedAngle());

        scania.gas(1f);
        scania.brake(1f);

        scania.raiseBed(1);

        assertThrows(RuntimeException.class, () -> {
            scania.gas(1f);
        });

        scania.lowerBed(1);

        assertDoesNotThrow(() -> {
            scania.gas(1f);
        });
    }

    @AfterEach
    void checkBed() {
        assertTrue(scania.getBedAngle() >= 0);
        assertTrue(scania.getBedAngle() <= Scania.MAX_BED_ANGLE);
    }
}
