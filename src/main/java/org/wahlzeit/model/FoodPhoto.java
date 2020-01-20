package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.DesignPattern;

import javax.swing.text.html.Option;
import java.util.Optional;

@DesignPattern(
        name = "Abstract Factory",
        participants = {"Concrete Product"}
)
@Subclass(index=true)
public class FoodPhoto extends Photo {

    private Food food;

    public FoodPhoto (PhotoId id) {
        super(id);

        if (id==null){
            throw new IllegalArgumentException("Id is null");
        }
    }

    public FoodPhoto (){
        super();
    }

    public Optional<Food> getFood (){
        return Optional.ofNullable(food);
    }

    public void setFood (Food newFood){
        food = newFood;
    }

    @Override
    public boolean equals (Object o){

        if(!(o instanceof FoodPhoto)){
            return false;
        }

        return ((FoodPhoto) o).getId().equals(this.id);

    }



}
