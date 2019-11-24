package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SphericCoordinateTest {
    @Test
    public void asCartesianCoordinateTest(){
        SphericCoordinate first = new SphericCoordinate(Math.PI/4,Math.PI/4, 3 );
        SphericCoordinate second = new SphericCoordinate(Math.PI/3,Math.PI*5/6, 5 );
        SphericCoordinate third = new SphericCoordinate(2.5*Math.PI,2.5*Math.PI, -5 );

        assertEquals(new CartesianCoordinate(1.5, 1.5, 2.121320344), first.asCartesianCoordinate());
        assertEquals(new CartesianCoordinate(-3.75, 2.165063509, 2.5), second.asCartesianCoordinate());
        assertEquals(new CartesianCoordinate(0, -5, 0), third.asCartesianCoordinate());
    }

    @Test
    public void equalsTest (){
        SphericCoordinate first = new SphericCoordinate(Math.PI/3, Math.PI/4, 10);
        SphericCoordinate second = new SphericCoordinate(Math.PI/3, Math.PI/4, 10);
        CartesianCoordinate cc = first.asCartesianCoordinate();


        assertTrue(first.equals(first));
        assertTrue(first.equals(second));
        assertFalse(first.equals(cc));
    }
}
