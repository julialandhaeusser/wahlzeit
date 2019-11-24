package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{
    static final double eps = 1e-7;

    private double getDistance (CartesianCoordinate otherCoordinate) {
        CartesianCoordinate thisAsCartesian = this.asCartesianCoordinate();

        double distance = Math.sqrt(Math.pow(thisAsCartesian.getX() - otherCoordinate.getX(), 2)+
                Math.pow(thisAsCartesian.getY() - otherCoordinate.getY(), 2) +
                Math.pow(thisAsCartesian.getZ() - otherCoordinate.getZ(), 2));

        return distance;
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return getDistance(coordinate.asCartesianCoordinate());

    }


    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate otherAsSpheric = coordinate.asSphericCoordinate();
        SphericCoordinate thisAsSpheric = this.asSphericCoordinate();

        double deltaLambda = Math.abs(thisAsSpheric.getPhi() - otherAsSpheric.getPhi());
        double phiDash1 = Math.PI / 2 - thisAsSpheric.getTheta();
        double phiDash2 = Math.PI / 2 - otherAsSpheric.getTheta();
        double centralAngle = Math.acos((Math.sin(phiDash1)*Math.sin(phiDash2))+
                (Math.cos(phiDash1)*Math.cos(phiDash2)*Math.cos(deltaLambda)));
        return centralAngle;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        return getCartesianDistance(coordinate.asCartesianCoordinate()) <= eps;


    }


}
