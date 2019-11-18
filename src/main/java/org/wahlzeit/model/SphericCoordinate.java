package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {
    static final double eps = 1e-7;
    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate (double phi, double theta, double radius){
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = this.radius * Math.cos(this.theta)*Math.sin(this.phi);
        double y = this.radius * Math.sin(this.theta)*Math.sin(this.phi);
        double z = this.radius* Math.cos(this.phi);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return this.asCartesianCoordinate().getCartesianDistance(coordinate);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate coordinateAsSepheric = coordinate.asSphericCoordinate();
        //Convention: (phi,theta,-radius) = (phi,theta+180deg,radius) see Wikipedia:Spheric Coordinates
        if(Math.signum(coordinateAsSepheric.radius) != Math.signum(this.radius)) {
            coordinateAsSepheric = new SphericCoordinate(coordinateAsSepheric.phi,
                    coordinateAsSepheric.theta+Math.PI, -coordinateAsSepheric.radius);
        }
        double deltaLambda = Math.abs(this.phi - coordinateAsSepheric.phi);
        double phiDash1 = Math.PI / 2 - this.theta;
        double phiDash2 = Math.PI / 2 - coordinateAsSepheric.theta;
        double centralAngle = Math.acos((Math.sin(phiDash1)*Math.sin(phiDash2))+
                (Math.cos(phiDash1)*Math.cos(phiDash2)*Math.cos(deltaLambda)));
        return centralAngle;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        //Same radius and same central angle --> same point
        if (getCentralAngle(coordinate) <= eps
                && Math.abs(coordinate.asSphericCoordinate().radius) - Math.abs(this.radius) <= eps) {
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
