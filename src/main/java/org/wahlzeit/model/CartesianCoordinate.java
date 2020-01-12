package org.wahlzeit.model;


import org.wahlzeit.DesignPattern;

import java.util.HashMap;
import java.util.HashSet;
@DesignPattern(
		name = "template method",
		participants = {"ConcreteClass"}
)
public class CartesianCoordinate extends AbstractCoordinate{
	private double x;
	private double y; 
	private double z;

	static HashMap <String, CartesianCoordinate> allCartesianCoordinates = new HashMap<>();

	
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		assertClassInvariants();
	}

	private static String generateHashKey(double x, double y, double z) {
		//Avoid negative zero
		if(Math.abs(x) < eps) {
			x = 0.0;
		}
		if(Math.abs(y) < eps) {
			y = 0.0;
		}
		if(Math.abs(z) < eps) {
			z = 0.0;
		}
		return String.format("%.5f", x) + ";" + String.format("%.5f", y) + ";" + String.format("%.5f", z);
	}

	public static CartesianCoordinate createCartesianCoordinate(double x, double y, double z){

		String key = generateHashKey(x,y,z);
		CartesianCoordinate value = allCartesianCoordinates.get(key);
		if(value == null) {
			value = new CartesianCoordinate(x,y,z);
			allCartesianCoordinates.put(key, value);
		}
		return value;
	}



	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}


	@Override
	public boolean equals (Object otherCoordinate) {

		return otherCoordinate == this;

	}

	@Override
	public int hashCode (){
		return super.hashCode();
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		assertClassInvariants();
		double radius = Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
		double theta = Math.atan2(this.y, this.x);
		double phi = Math.acos(this.z/radius);
		SphericCoordinate sphericCoordinateNew = SphericCoordinate.createSphericCoordinate(phi,theta,radius);
		assertClassInvariants();
		return sphericCoordinateNew;

	}


	@Override
	public void assertClassInvariants () {
		if (Double.isNaN(x)) {
			throw new IllegalStateException ("x is not a number");
		}
		if (Double.isNaN(y)) {
			throw new IllegalStateException ("y is not a number");
		}

		if (Double.isNaN(z)) {
			throw new IllegalStateException ("z is not a number");
		}
	}
}
