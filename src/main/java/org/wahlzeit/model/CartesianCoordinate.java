package org.wahlzeit.model;


import java.util.HashMap;
import java.util.HashSet;

public class CartesianCoordinate extends AbstractCoordinate{
	private double x;
	private double y; 
	private double z;

	static HashSet <CartesianCoordinate> allCartesianCoordinates = new HashSet<>();

	
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		assertClassInvariants();
	}

	public static CartesianCoordinate createCartesianCoordinate(double x, double y, double z){
		for (CartesianCoordinate coordinate : allCartesianCoordinates){
			if(Math.abs(coordinate.getX() - x) < AbstractCoordinate.eps
					&& Math.abs(coordinate.getY() - y) < AbstractCoordinate.eps
					&& Math.abs(coordinate.getZ() - z) < AbstractCoordinate.eps ){
				return coordinate;
			}
		}

		CartesianCoordinate newCoordinate = new CartesianCoordinate(x,y,z);
		allCartesianCoordinates.add(newCoordinate);
		return newCoordinate;
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
