package org.wahlzeit.model;

public interface Coordinate {


    /**
     * Converts this coordinate to a cartesian coordinate.
     * @return conversion result, not null
     */
    public CartesianCoordinate asCartesianCoordinate();

    /**
     * Calculates the cartesian distance between two coordinates
     * @param coordinate other coordinate, not null
     * @return the distance as a double, valid number
     */
    public double getCartesianDistance (Coordinate coordinate);

    /**
     * Transforms Coordinate into SphericCoordinate
     * @return SphericCoordinate, not null
     */
    public SphericCoordinate asSphericCoordinate();

    /**
     * Calculates the central angle between two coordinates
     * @param coordinate other coordinate, not null
     * @return the angle as a double, valid number, between 0 & 2*PI
     */
    public double getCentralAngle(Coordinate coordinate);

    /**
     * Checks if two coordinates represent the same point
     * @param coordinate other coordinate, not null
     * @return equality yes/no
     */
    public boolean isEqual (Coordinate coordinate);
}
