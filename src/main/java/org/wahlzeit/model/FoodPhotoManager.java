package org.wahlzeit.model;

import com.google.appengine.api.images.Image;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.OfyService;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class FoodPhotoManager extends PhotoManager {
    //Singleton

    private static final Logger log = Logger.getLogger(FoodPhotoManager.class.getName());

    private FoodPhotoManager() {
        super();
    }

    public static void initialize() {
        instance = new FoodPhotoManager();
    }

    @Override
    public void loadPhotos() {
        Collection<FoodPhoto> existingPhotos = ObjectifyService.run(new Work<Collection<FoodPhoto>>() {
            @Override
            public Collection<FoodPhoto> run() {
                Collection<FoodPhoto> existingPhotos = new ArrayList<FoodPhoto>();
                readObjects(existingPhotos, FoodPhoto.class);
                return existingPhotos;
            }
        });

        for (Photo photo : existingPhotos) {
            if (!doHasPhoto(photo.getId())) {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Load FoodPhoto with ID", photo.getIdAsString()).toString());
                loadScaledImages(photo);
                doAddPhoto(photo);
            } else {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Already loaded FoodPhoto", photo.getIdAsString()).toString());
            }
        }

        log.info(LogBuilder.createSystemMessage().addMessage("All food photos loaded.").toString());
    }

    @Override
    public Set<Photo> findPhotosByOwner(String ownerName) {
        Set<FoodPhoto> result = new HashSet<>();
        result.addAll(OfyService.ofy().load().type(FoodPhoto.class).
                ancestor(applicationRootKey).list());

        Set<Photo> methodResult = new HashSet<>();
        for (Iterator<FoodPhoto> i = result.iterator(); i.hasNext(); ) {
            FoodPhoto photo = i.next();
            if(photo.getOwnerId().equals(ownerName)) {
                doAddPhoto(photo);
                methodResult.add(photo);
            }
        }

        return methodResult;
    }

    @Override
    public Photo getPhotoFromId(PhotoId id) {
        if (id == null) {
            return null;
        }

        Photo result = doGetPhotoFromId(id);
        if (!(result instanceof FoodPhoto)) {
            return null;
        }

        if (result == null) {
            result = FoodPhotoFactory.getInstance().loadPhoto(id);
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    @Override
    public void addPhoto(Photo photo) throws IOException {
        if (!(photo instanceof FoodPhoto)) {
            throw new IllegalArgumentException("Given Photo is not a FoodPhoto");
        }

        PhotoId id = photo.getId();
        assertIsNewPhoto(id);
        doAddPhoto(photo);

        GlobalsManager.getInstance().saveGlobals();
    }
}
