package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.DesignPattern;

@DesignPattern(
        name = "Abstract Factory",
        participants = {"Concrete Product"}
)
@Subclass(index=true)
public class FoodPhoto extends Photo {

    public FoodPhoto (PhotoId id) {
        super(id);

        if (id==null){
            throw new IllegalArgumentException("Id is null");
        }
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
