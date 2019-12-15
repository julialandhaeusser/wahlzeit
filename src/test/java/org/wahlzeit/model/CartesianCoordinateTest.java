package org.wahlzeit.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CartesianCoordinateTest {

	private static final double EPS = 1e-7;

	@Test
	public void equalsTest (){
		CartesianCoordinate first = CartesianCoordinate.createCartesianCoordinate(1, 1, 1);
		CartesianCoordinate second = CartesianCoordinate.createCartesianCoordinate(1,1,1);
		CartesianCoordinate third = CartesianCoordinate.createCartesianCoordinate(0,1,0);
		Object fourth = new Object();


		assertTrue(first.equals(first));
		assertTrue(first.equals(second));
		assertFalse(first.equals(third));
		assertFalse(first.equals(fourth));
	}
	@Test
	public void asSphericCoordinateTest(){
		CartesianCoordinate first = CartesianCoordinate.createCartesianCoordinate(1.5, 1.5, 2.121320344);
		CartesianCoordinate second = CartesianCoordinate.createCartesianCoordinate(-3.75, 2.165063509, 2.5);
		CartesianCoordinate third = CartesianCoordinate.createCartesianCoordinate(0, -5, 0);


		assertEquals(SphericCoordinate.createSphericCoordinate(Math.PI/4,Math.PI/4, 3 ), first.asSphericCoordinate());
		assertEquals(SphericCoordinate.createSphericCoordinate(Math.PI/3,Math.PI*5/6, 5), second.asSphericCoordinate());
		assertEquals(SphericCoordinate.createSphericCoordinate(2.5*Math.PI,2.5*Math.PI, -5), third.asSphericCoordinate());

	}

	@Test(expected = IllegalStateException.class)
	public void classInvarianceTest (){
		CartesianCoordinate first = CartesianCoordinate.createCartesianCoordinate(Double.NaN, Double.NaN, Double.NaN);


	}
}