package org.wahlzeit.model;

import org.wahlzeit.DesignPattern;
import org.wahlzeit.services.OfyService;

import java.util.Iterator;

@DesignPattern(
        name = "Abstract Factory",
        participants = {"ConcreteFactory"}
)
public class FoodPhotoFactory extends PhotoFactory {

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {

        PhotoFactory.setInstance(new FoodPhotoFactory());
    }

    @Override
    public Photo createPhoto() {

        return new FoodPhoto();
    }

    /**
     * Creates a new photo with the specified id
     */
    @Override
    public Photo createPhoto(PhotoId id) {
        return new FoodPhoto(id);
    }

    /**
     * Loads a photo. The Java object is loaded from the Google Datastore, the Images in all sizes are loaded from the
     * Google Cloud storage.
     */
    @Override
    public Photo loadPhoto(PhotoId id) {
        if (id ==null){
            throw new IllegalArgumentException("id is null");
        }

        Iterator<FoodPhoto> iterator = OfyService.ofy().load().type(FoodPhoto.class).iterator();
        Photo result = null;
        while (iterator.hasNext()) {
            Photo next = iterator.next();
            if (next.getId().equals(id)) {
                result = next;
                break;
            }
        }

        return result;
    }

}
