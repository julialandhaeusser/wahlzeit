package org.wahlzeit.model;


public class Coordinate {
	static final double eps = 1e-7;
	private double x;
	private double y; 
	private double z;
	
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}


	public double getDistance (Coordinate otherCoordinate) {
	
		double distance = Math.sqrt(Math.pow(this.x - otherCoordinate.x, 2)+
				Math.pow(this.y - otherCoordinate.y, 2)+Math.pow(this.z - otherCoordinate.z, 2));
		
		return distance;
	}
	
	
	public boolean isEqual (Coordinate otherCoordinate) {
	 
		
		if (Math.abs(getDistance(otherCoordinate)) <= eps) {
			return true;
		}else {
			return false; 
		}
		
	}
	
	@Override 
	
	public boolean equals (Object otherCoordinate) {
		
		if(otherCoordinate == this) {
			return true;
		}	
		if(otherCoordinate instanceof Coordinate) {
			
			return isEqual((Coordinate) otherCoordinate);
		}else {
			return false; 
		}
		
		
		
		
	}
	
}
