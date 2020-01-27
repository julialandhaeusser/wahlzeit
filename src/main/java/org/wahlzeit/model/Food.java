package org.wahlzeit.model;


import org.wahlzeit.DesignPattern;

import java.util.ArrayList;
import java.util.List;

@DesignPattern(
        name = "Type Object",
        participants = {"DomainClass"}
)
public class Food {

    private List<FoodPhoto> foodPhotoList = new ArrayList<FoodPhoto>();

    final FoodType newFoodType ;

    private static int instanceCounter = 0;

    private int id;

    Food (FoodType foodType){
        newFoodType = foodType;
        id = instanceCounter;
        instanceCounter++;
    }

    public void addFoodPhoto (FoodPhoto foodPhoto){
        foodPhotoList.add(foodPhoto);
    }

    public void removeFoodPhoto (FoodPhoto foodPhoto){
        foodPhotoList.remove(foodPhoto);
    }

    public int getId() {
        return id;
    }



}
