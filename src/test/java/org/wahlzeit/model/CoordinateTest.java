package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {

	private static final double EPS = 1e7;
	
	@Test
	public void getDistanceTest () {
		Coordinate first = new Coordinate(1,1,1);
		Coordinate second = new Coordinate (0,0,0);
		
		double distance = first.getDistance(second);
		assertEquals(Math.sqrt(3), distance, EPS);
	}
}
