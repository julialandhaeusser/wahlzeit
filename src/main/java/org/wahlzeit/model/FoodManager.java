package org.wahlzeit.model;
import org.wahlzeit.DesignPattern;

import java.util.HashMap;
import java.util.Map;


public class FoodManager {

    private static FoodManager instance;

    Map <String, FoodType> foodTypeMap = new HashMap<>();

    Map <Integer, Food> foodMap = new HashMap<>();

    private FoodManager() {}

    public static FoodManager getInstance() {
        if(instance == null) {
            instance = new FoodManager();
        }
        return instance;
    }

    public static void initialize() {
        getInstance();
    }

    public void clear() {
        foodTypeMap.clear();
        foodMap.clear();
    }

    private void assertIsValidFoodTypeName (String typeName){
       if (typeName.length()<=1){
           throw new IllegalArgumentException("String is too short!");
        }
    }

    public FoodType getFoodType(String typeName){
        assertIsValidFoodTypeName(typeName);
        FoodType foodType = foodTypeMap.get(typeName);
        if (foodType == null){
            foodType = new FoodType(typeName);
            foodTypeMap.put(typeName, foodType);
        }
        return foodType;
    }

    public Food createFood (String typeName){
        assertIsValidFoodTypeName(typeName);
        FoodType foodType = getFoodType(typeName);
        Food result = foodType.createInstance();
        foodMap.put(result.getId(), result);
        return result;
    }

    public boolean setAsSubtype(String superTypeName, String subTypeName) {
        FoodType superType = getFoodType(superTypeName);
        FoodType subType = getFoodType(subTypeName);

        FoodType testSuperType = superType;

        while(testSuperType != null) {
            if(testSuperType.equals(subType)) {
                return false;
            }
            testSuperType = testSuperType.superType;
        }

        subType.setSuperType(superType);
        return true;

    }


}
