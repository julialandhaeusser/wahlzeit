package org.wahlzeit.model;


public class CartesianCoordinate extends AbstractCoordinate{
	private double x;
	private double y; 
	private double z;

	
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
		if(otherCoordinate instanceof CartesianCoordinate) {
			
			return isEqual((CartesianCoordinate) otherCoordinate);
		}else {
			return false; 
		}
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
		double radius = Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
		double theta = Math.atan2(this.y, this.x);
		double phi = Math.acos(this.z/radius);
		return new SphericCoordinate(phi, theta, radius);

	}


}
