package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass(index=true)
public class FoodPhoto extends Photo {

    public FoodPhoto (PhotoId id) {
        super(id);

    }

    public FoodPhoto (){
        super();
    }

    @Override
    public boolean equals (Object o){
        if(!(o instanceof FoodPhoto)){
            return false;
        }

        return ((FoodPhoto) o).getId().equals(this.id);

    }



}
