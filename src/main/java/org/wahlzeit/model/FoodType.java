package org.wahlzeit.model;

public class FoodType {
    protected FoodType superType;

    String foodType;

    public FoodType (String newFoodType){
        foodType = newFoodType;
    }

    public FoodType getSuperType() {
        return superType;
    }

    public boolean isSubtype (FoodType other){
        if(other == null) {
            return false;
        } else if (other.equals(this)){
            return true;
        } else if(this.superType == null) {
            return false;
        } else {
            return this.superType.isSubtype(other);
        }
    }

    public Food createInstance (){
        return new Food(this);
    }

    public void setSuperType(FoodType newSuperType) {
        superType = newSuperType;
    }
}
