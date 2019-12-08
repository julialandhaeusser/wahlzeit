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

        if(phi >= Math.PI*2){
            phi = phi % (Math.PI*2);
        } else if (phi < 0){
            phi = phi % (Math.PI*2);
            phi = phi + Math.PI*2;
        }

        if (theta >= Math.PI*2 ){
            theta = theta % (Math.PI*2);
        } else if (theta < 0){
            theta = theta % (Math.PI*2);
            theta = theta + Math.PI*2;
        }

        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        assertClassInvariants();
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
        assertClassInvariants();
        double x = this.radius * Math.cos(this.theta)*Math.sin(this.phi);
        double y = this.radius * Math.sin(this.theta)*Math.sin(this.phi);
        double z = this.radius* Math.cos(this.phi);
        CartesianCoordinate cartesianCoordinateNew = new CartesianCoordinate(x, y, z);

        assertClassInvariants();
        return cartesianCoordinateNew;
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

        try {
            return otherCoordinate instanceof SphericCoordinate && isEqual((SphericCoordinate) otherCoordinate);
        } catch (IllegalStateException | IllegalArgumentException ex){
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

    @Override
    public void assertClassInvariants() {
        if (phi < 0 || phi >= Math.PI*2){
            throw new IllegalStateException("phi is not in range [0,2PI)");
        }
        if (theta < 0 || theta >= Math.PI*2){
            throw new IllegalStateException("theta is not in range [0;2PI)");
        }
        if (radius < 0){
            throw new IllegalStateException("radius is smaller than 0");
        }
    }
}
