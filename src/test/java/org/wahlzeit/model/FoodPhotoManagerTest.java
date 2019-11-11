package org.wahlzeit.model;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;
import org.wahlzeit.services.OfyService;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.Assert.*;

public class FoodPhotoManagerTest {

    @Before
    public void initializeFactory() throws Exception {
        Field field = PhotoFactory.class.getDeclaredField ("instance");
        field.setAccessible(true);
        field.set(null, null);
        FoodPhotoFactory.initialize();
    }

    @Test
    public void initializaTest (){
        FoodPhotoManager.initialize();
        PhotoManager test1 = PhotoManager.getInstance();
        assertEquals((test1 instanceof FoodPhotoManager),true);
    }

    @Test
    public void loadPhotosTest (){

        //Setup local test google datestore environment
        LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
        helper.setUp();
        Closeable closeable = ObjectifyService.begin();

        //Set correct PhotoFactory instance
        ImageStorage.setInstance(new DatastoreAdapter());
        FoodPhotoManager.initialize();

        //Create and save photo with id '10'
        PhotoId testId = new PhotoId(10);
        FoodPhoto foodPhoto = new FoodPhoto(testId);

        //Create and save photo with id '15'
        PhotoId testId2 = new PhotoId(15);
        FoodPhoto foodPhoto2 = new FoodPhoto(testId2);
        OfyService.ofy().save().entities(foodPhoto, foodPhoto2).now();

        //load photos
        FoodPhotoManager.instance.loadPhotos();

        //tests
        assertTrue(PhotoManager.getInstance().photoCache.containsKey(testId));
        assertTrue(PhotoManager.getInstance().photoCache.containsValue(foodPhoto));
        assertTrue(PhotoManager.getInstance().photoCache.containsKey(testId2));
        assertTrue(PhotoManager.getInstance().photoCache.containsValue(foodPhoto2));


        //Shutdown test environment
        closeable.close();
        helper.tearDown();
    }

    @Test
    public void findPhotosByOwnerTest(){
        //Setup local test google datestore environment
        LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
        helper.setUp();
        Closeable closeable = ObjectifyService.begin();

        //Set correct PhotoFactory instance
        ImageStorage.setInstance(new DatastoreAdapter());


        FoodPhotoManager.initialize();

        FoodPhoto foodPhoto1 = new FoodPhoto();
        foodPhoto1.setOwnerId("owner1");
        FoodPhoto foodPhoto2 = new FoodPhoto();
        foodPhoto2.setOwnerId("owner2");
        Photo photo = new Photo();
        photo.setOwnerId("owner1");

        OfyService.ofy().save().entities(foodPhoto1, foodPhoto2, photo);

        Set<Photo> result1 = FoodPhotoManager.instance.findPhotosByOwner("owner1");
        Set<Photo> result2 = FoodPhotoManager.instance.findPhotosByOwner("owner2");

        assertEquals(1, result1.size() );
        assertEquals(result1.iterator().next(), foodPhoto1);

        assertEquals(1, result2.size() );
        assertEquals(result2.iterator().next(), foodPhoto2);

        //Shutdown test environment
        closeable.close();
        helper.tearDown();
    }

    @Test
    public void getPhotoFromIdTest (){
        //Setup local test google datestore environment
        LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
        helper.setUp();
        Closeable closeable = ObjectifyService.begin();

        //Set correct PhotoFactory instance
        ImageStorage.setInstance(new DatastoreAdapter());

        //FoodPhotoFactory.initialize();
        FoodPhotoManager.initialize();

        PhotoId nullId = null;
        PhotoId foodPhotoId = new PhotoId(1);
        PhotoId photoId = new PhotoId(2);

        FoodPhoto foodPhoto = new FoodPhoto();
        Photo photo = new Photo();

        FoodPhotoManager.instance.photoCache.put(foodPhotoId, foodPhoto);
        PhotoManager.instance.photoCache.put(photoId, photo);

        assertEquals(FoodPhotoManager.instance.getPhotoFromId(nullId), null);
        assertEquals(FoodPhotoManager.instance.getPhotoFromId(foodPhotoId), foodPhoto);
        assertEquals(FoodPhotoManager.instance.getPhotoFromId(photoId), null);

        //Shutdown test environment
        closeable.close();
        helper.tearDown();
    }

   @Test
    public void addPhotoTest () throws IOException {
       //Setup local test google datestore environment
       LocalServiceTestHelper helper =
               new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
       helper.setUp();
       Closeable closeable = ObjectifyService.begin();

       //Set correct PhotoFactory instance
       ImageStorage.setInstance(new DatastoreAdapter());
       FoodPhotoManager.initialize();
        Photo photo = new Photo();
        FoodPhoto foodPhoto = new FoodPhoto();

       FoodPhotoManager.instance.addPhoto(foodPhoto);

        assertThrows(IllegalArgumentException.class, () -> FoodPhotoManager.instance.addPhoto(photo));
        assertTrue(PhotoManager.getInstance().photoCache.containsValue(foodPhoto));

        assertThrows(IllegalStateException.class, () -> FoodPhotoManager.instance.addPhoto(foodPhoto));


       //Shutdown test environment
       closeable.close();
       helper.tearDown();

    }

}

