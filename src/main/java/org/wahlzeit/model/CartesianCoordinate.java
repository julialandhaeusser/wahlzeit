package org.wahlzeit.model;


public class CartesianCoordinate extends AbstractCoordinate{
	private double x;
	private double y; 
	private double z;

	
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		assertClassInvariants();
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
		
		if(otherCoordinate == this) {
			return true;
		}

		return otherCoordinate instanceof CartesianCoordinate && isEqual((CartesianCoordinate) otherCoordinate);

	}

	@Override
	public int hashCode (){
		final long primeNumber = 23;
		long result = 1;

		result = primeNumber * result + Double.doubleToLongBits(x);
		result = primeNumber * result + Double.doubleToLongBits(y);
		result = primeNumber * result + Double.doubleToLongBits(z);

		return (int) result;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		assertClassInvariants();
		double radius = Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
		double theta = Math.atan2(this.y, this.x);
		double phi = Math.acos(this.z/radius);
		SphericCoordinate sphericCoordinateNew = new SphericCoordinate(phi, theta, radius);
		assertClassInvariants();
		return sphericCoordinateNew;

	}


	@Override
	public void assertClassInvariants() {
		assert !Double.isNaN(x);
		assert !Double.isNaN(y);
		assert !Double.isNaN(z);
	}
}
