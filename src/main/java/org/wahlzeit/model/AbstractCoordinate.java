package org.wahlzeit.model;

import org.wahlzeit.DesignPattern;

@DesignPattern(
        name = "template method",
        participants = {"AbstractClass"}
)
public abstract class AbstractCoordinate implements Coordinate{
    static final double eps = 1e-7;

    //template method: calls abstract method asCartesianCoordinate()
    private double getDistance (CartesianCoordinate otherCoordinate) {

        CartesianCoordinate thisAsCartesian = this.asCartesianCoordinate();

        double distance = Math.sqrt(Math.pow(thisAsCartesian.getX() - otherCoordinate.getX(), 2)+
                Math.pow(thisAsCartesian.getY() - otherCoordinate.getY(), 2) +
                Math.pow(thisAsCartesian.getZ() - otherCoordinate.getZ(), 2));

        return distance;
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        assertClassInvariants();
        if (coordinate == null){
            throw new IllegalArgumentException("coordinate is null");
        }
        double cartesianDistance = getDistance(coordinate.asCartesianCoordinate());

        assertClassInvariants();
        if (Double.isNaN(cartesianDistance)){
            throw new IllegalStateException("cartesianDistance is not a number");
        }
        return cartesianDistance;

    }


    @Override
    public double getCentralAngle(Coordinate coordinate) {
        assertClassInvariants();
        if(coordinate == null){
            throw new IllegalArgumentException("coordinate is null");
        }

        SphericCoordinate otherAsSpheric = coordinate.asSphericCoordinate();
        SphericCoordinate thisAsSpheric = this.asSphericCoordinate();

        double deltaLambda = Math.abs(thisAsSpheric.getPhi() - otherAsSpheric.getPhi());
        double phiDash1 = Math.PI / 2 - thisAsSpheric.getTheta();
        double phiDash2 = Math.PI / 2 - otherAsSpheric.getTheta();
        double centralAngle = Math.acos((Math.sin(phiDash1)*Math.sin(phiDash2))+
                (Math.cos(phiDash1)*Math.cos(phiDash2)*Math.cos(deltaLambda)));

        assertClassInvariants();
        if(centralAngle<0 || centralAngle>=Math.PI*2){
            throw new IllegalStateException("central angle is not in range [0;2PI)");
        }


        return centralAngle;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        assertClassInvariants();
        if(coordinate == null){
            throw new IllegalArgumentException("coordinate is null");
        }


        boolean coordinatesEqual = getCartesianDistance(coordinate.asCartesianCoordinate()) <= eps;

        assertClassInvariants();

        return coordinatesEqual;


    }

    public abstract void assertClassInvariants();


}
