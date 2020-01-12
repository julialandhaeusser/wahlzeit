package org.wahlzeit.model;

import org.wahlzeit.DesignPattern;

import java.util.HashMap;

@DesignPattern(
        name = "template method",
        participants = {"ConcreteClass"}
)
public class SphericCoordinate extends AbstractCoordinate {
    static final double eps = 1e-7;
    static HashMap<String, SphericCoordinate> allSphericCoordinates = new HashMap<>();
    private double phi;
    private double theta;
    private double radius;

    private SphericCoordinate(double phi, double theta, double radius) {


        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        assertClassInvariants();
    }

    private static String generateHashKey(double phi, double theta, double radius) {
        return String.format("%.5f", phi) + ";" + String.format("%.5f", theta) + ";" + String.format("%.5f", radius);
    }

    public static SphericCoordinate createSphericCoordinate(double phi, double theta, double radius) {
        //Convention: (phi,theta,-radius) = (phi,theta+180deg,radius) see Wikipedia:Spheric Coordinates
        if (radius < 0) {
            radius = -radius;
            theta = theta + Math.PI;
        }

        if (phi >= Math.PI * 2) {
            phi = phi % (Math.PI * 2);
        } else if (phi < 0) {
            phi = phi % (Math.PI * 2);
            phi = phi + Math.PI * 2;
        }

        if (theta >= Math.PI * 2) {
            theta = theta % (Math.PI * 2);
        } else if (theta < 0) {
            theta = theta % (Math.PI * 2);
            theta = theta + Math.PI * 2;
        }

        String key = generateHashKey(phi, theta, radius);
        SphericCoordinate value = allSphericCoordinates.get(key);
        if (value == null) {
            value = new SphericCoordinate(phi, theta, radius);
            allSphericCoordinates.put(key, value);
        }
        return value;

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
        double x = this.radius * Math.cos(this.theta) * Math.sin(this.phi);
        double y = this.radius * Math.sin(this.theta) * Math.sin(this.phi);
        double z = this.radius * Math.cos(this.phi);


        CartesianCoordinate cartesianCoordinateNew = CartesianCoordinate.createCartesianCoordinate(x, y, z);

        assertClassInvariants();
        return cartesianCoordinateNew;
    }


    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public boolean equals(Object otherCoordinate) {

        return otherCoordinate == this;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "SphericCoordinate(Phi: " + phi + " Theta: " + theta + " Radius: " + radius + ")";
    }

    @Override
    public void assertClassInvariants() {
        if (phi < 0 || phi >= Math.PI * 2 || Double.isNaN(phi)) {
            throw new IllegalStateException("phi is not in range [0,2PI)");
        }
        if (theta < 0 || theta >= Math.PI * 2 || Double.isNaN(theta)) {
            throw new IllegalStateException("theta is not in range [0;2PI)");
        }
        if (radius < 0 || Double.isNaN(radius)) {
            throw new IllegalStateException("radius is smaller than 0");
        }
    }
}
