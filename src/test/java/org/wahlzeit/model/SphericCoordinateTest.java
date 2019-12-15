package org.wahlzeit.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class SphericCoordinateTest {

    @Test
    public void asCartesianCoordinateTest() {
        SphericCoordinate first = SphericCoordinate.createSphericCoordinate(Math.PI / 4, Math.PI / 4, 3);
        SphericCoordinate second = SphericCoordinate.createSphericCoordinate(Math.PI / 3, Math.PI * 5 / 6, 5);
        SphericCoordinate third = SphericCoordinate.createSphericCoordinate(2.5 * Math.PI, 2.5 * Math.PI, -5);

        assertEquals(CartesianCoordinate.createCartesianCoordinate(1.5, 1.5, 2.121320344), first.asCartesianCoordinate());
        assertEquals(CartesianCoordinate.createCartesianCoordinate(-3.75, 2.165063509, 2.5), second.asCartesianCoordinate());
        assertEquals(CartesianCoordinate.createCartesianCoordinate(0, -5, 0), third.asCartesianCoordinate());
    }

    @Test
    public void equalsTest() {
        SphericCoordinate first = SphericCoordinate.createSphericCoordinate(Math.PI / 3, Math.PI / 4, 10);
        SphericCoordinate second = SphericCoordinate.createSphericCoordinate(Math.PI / 3, Math.PI / 4, 10);
        CartesianCoordinate cc = first.asCartesianCoordinate();


        assertTrue(first.equals(first));
        assertTrue(first.equals(second));
        assertTrue(first == second);
        assertFalse(first.equals(cc));
    }


    @Test(expected = IllegalStateException.class)
    public void classInvarianceTest (){
        SphericCoordinate first = SphericCoordinate.createSphericCoordinate(Double.NaN, Double.NaN, Double.NaN);
    }
}
