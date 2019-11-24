package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractCoordinateTest {

    private static final double EPS = 1e-7;

    @Test
    public void getCartesianDistanceTest () {
        CartesianCoordinate cartesianFirst = new CartesianCoordinate(1,1,1);
        CartesianCoordinate cartesianSecond = new CartesianCoordinate(0,0,0);
        SphericCoordinate sphericFirst = new SphericCoordinate(2.5*Math.PI,2.5*Math.PI, -5 );
        SphericCoordinate sphericSecond = new SphericCoordinate(0,0,0);

        double distance = cartesianFirst.getCartesianDistance(cartesianSecond);
        assertEquals(Math.sqrt(3), distance, EPS);

        double distance2 = sphericFirst.getCartesianDistance(cartesianFirst);
        assertEquals(Math.sqrt(38), distance2, EPS);

        double distance3 = cartesianSecond.getCartesianDistance(sphericFirst);
        assertEquals(5, distance3, EPS);

        double distance4 = sphericFirst.getCartesianDistance(sphericSecond);
        assertEquals(5, distance4, EPS);
    }

    @Test
    public void getCentralAngleTest() {
        SphericCoordinate sphericFirst = new SphericCoordinate(Math.PI/2, 0, 1);
        SphericCoordinate sphericSecond = new SphericCoordinate(Math.PI/2, Math.PI/2, 1);
        CartesianCoordinate cartesianFirst = new CartesianCoordinate(1,0,0);
        CartesianCoordinate cartesianSecond = new CartesianCoordinate(0,1,0);


        assertEquals(Math.PI/2, sphericFirst.getCentralAngle(sphericSecond), EPS);
        assertEquals(Math.PI/2, cartesianFirst.getCentralAngle(cartesianSecond), EPS);
        assertEquals(Math.PI/2, sphericFirst.getCentralAngle(cartesianSecond), EPS);
        assertEquals(Math.PI/2, cartesianFirst.getCentralAngle(sphericSecond), EPS);


    }

    @Test
    public void isEquaSphericlTest (){
        SphericCoordinate first = new SphericCoordinate(Math.PI/3, Math.PI/4, 10);
        SphericCoordinate second = new SphericCoordinate(Math.PI/3, Math.PI/4, 12);
        SphericCoordinate third = new SphericCoordinate(Math.PI/3, 5*Math.PI/4, -10);
        SphericCoordinate fourth = new SphericCoordinate(7*Math.PI/3, Math.PI/4, 10);
        SphericCoordinate fifth = new SphericCoordinate(1,2,10);

        assertFalse(first.isEqual(second));
        assertTrue(first.isEqual(third));
        assertTrue(first.isEqual(fourth));
        assertFalse(first.isEqual(fifth));
    }

    @Test
    public void isEqualCartesianTest (){
        CartesianCoordinate first = new CartesianCoordinate(1,1,1);
        CartesianCoordinate second = new CartesianCoordinate(0,0,0);
        CartesianCoordinate third = new CartesianCoordinate(1, 1, 1 + EPS/10.0);

        assertFalse(first.isEqual(second));
        assertTrue(first.isEqual(third));
    }

}
