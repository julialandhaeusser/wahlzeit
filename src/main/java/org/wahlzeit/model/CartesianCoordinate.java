package org.wahlzeit.model;


public class CartesianCoordinate implements Coordinate {
	static final double eps = 1e-7;
	private double x;
	private double y; 
	private double z;

	
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}


	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}


	public double getDistance (CartesianCoordinate otherCoordinate) {
	
		double distance = Math.sqrt(Math.pow(this.x - otherCoordinate.x, 2)+
				Math.pow(this.y - otherCoordinate.y, 2)+Math.pow(this.z - otherCoordinate.z, 2));
		
		return distance;
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
			return getDistance(coordinate.asCartesianCoordinate());

	}


	public boolean isEqual (CartesianCoordinate otherCoordinate) {
	 
		
		if (getDistance(otherCoordinate) <= eps) {
			return true;
		}else {
			return false; 
		}
	}

	@Override
	public boolean isEqual(Coordinate coordinate) {
		return isEqual(coordinate.asCartesianCoordinate());
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

	@Override
	public double getCentralAngle(Coordinate coordinate) {
		return this.asSphericCoordinate().getCentralAngle(coordinate);
	}


}
