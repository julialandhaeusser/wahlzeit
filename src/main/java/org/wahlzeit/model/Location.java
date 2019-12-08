package org.wahlzeit.model;

public class Location {
 private Coordinate coordinateLocation;

 public Location(Coordinate coordinateLocation) {
  this.coordinateLocation = coordinateLocation;
 }

 
 public void setCoordinateLocation (Coordinate location){
  if (location == null){
   throw new IllegalArgumentException("Null is not a valid coordinate");
  }
  coordinateLocation = location;
 }

 public Coordinate getCoordinateLocation (){
  return coordinateLocation;
 }
}
