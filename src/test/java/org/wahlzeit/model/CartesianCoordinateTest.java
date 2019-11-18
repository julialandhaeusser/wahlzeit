package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianCoordinateTest {

	private static final double EPS = 1e-7;
	
	@Test
	public void getDistanceTest () {
		CartesianCoordinate first = new CartesianCoordinate(1,1,1);
		CartesianCoordinate second = new CartesianCoordinate(0,0,0);
		
		double distance = first.getDistance(second);
		assertEquals(Math.sqrt(3), distance, EPS);
	}

	@Test
	public void isEqualTest (){
		CartesianCoordinate first = new CartesianCoordinate(1,1,1);
		CartesianCoordinate second = new CartesianCoordinate(0,0,0);
		CartesianCoordinate third = new CartesianCoordinate(1, 1, 1 + EPS/10.0);

		assertFalse(first.isEqual(second));
		assertTrue(first.isEqual(third));
	}

	@Test
	public void equalsTest (){
		CartesianCoordinate first = new CartesianCoordinate(1, 1, 1);
		CartesianCoordinate second = new CartesianCoordinate(1,1,1);
		CartesianCoordinate third = new CartesianCoordinate(0,1,0);
		Object fourth = new Object();


		assertTrue(first.equals(first));
		assertTrue(first.equals(second));
		assertFalse(first.equals(third));
		assertFalse(first.equals(fourth));
	}
	@Test
	public void asSphericCoordinateTest(){
		CartesianCoordinate first = new CartesianCoordinate(1.5, 1.5, 2.121320344);
		CartesianCoordinate second = new CartesianCoordinate(-3.75, 2.165063509, 2.5);
		CartesianCoordinate third = new CartesianCoordinate(0, -5, 0);


		assertEquals(new SphericCoordinate(Math.PI/4,Math.PI/4, 3 ), first.asSphericCoordinate());
		assertEquals(new SphericCoordinate(Math.PI/3,Math.PI*5/6, 5), second.asSphericCoordinate());
		assertEquals(new SphericCoordinate(2.5*Math.PI,2.5*Math.PI, -5), third.asSphericCoordinate());

	}
}