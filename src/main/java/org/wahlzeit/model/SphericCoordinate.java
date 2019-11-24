package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate{
    static final double eps = 1e-7;
    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate (double phi, double theta, double radius){
        //Convention: (phi,theta,-radius) = (phi,theta+180deg,radius) see Wikipedia:Spheric Coordinates
        if(radius < 0) {
            radius = -radius;
            theta = theta + Math.PI;
        }
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    public double getPhi() {
        return phi;
    }

    public double getTheta() {
        return theta;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = this.radius * Math.cos(this.theta)*Math.sin(this.phi);
        double y = this.radius * Math.sin(this.theta)*Math.sin(this.phi);
        double z = this.radius* Math.cos(this.phi);
        return new CartesianCoordinate(x, y, z);
    }


    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public boolean equals (Object otherCoordinate) {

        if(otherCoordinate == this) {
            return true;
        }
        if(otherCoordinate instanceof SphericCoordinate) {

            return isEqual((SphericCoordinate) otherCoordinate);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode (){
        final long primeNumber = 29;
        long result = 1;

        result = primeNumber * result + Double.doubleToLongBits(phi);
        result = primeNumber * result + Double.doubleToLongBits(theta);
        result = primeNumber * result + Double.doubleToLongBits(radius);

        return (int) result;
    }
    @Override
    public String toString(){
        return "SphericCoordinate(Phi: "+phi +" Theta: " + theta +" Radius: " +radius +")";
    }
}
