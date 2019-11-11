package org.wahlzeit.model;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import org.junit.Test;
import org.wahlzeit.services.OfyService;

import static org.junit.Assert.*;

public class FoodPhotoFactoryTest {

    @Test
    public void initializeTest (){
       FoodPhotoFactory.initialize();
       PhotoFactory test1 = PhotoFactory.getInstance();

       assertEquals((test1 instanceof FoodPhotoFactory), true);

    }

    @Test
    public void loadTest(){
        //Set correct PhotoFactory instance
        FoodPhotoFactory.initialize();
        //Setup local test google datestore environment
        LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
        helper.setUp();
        Closeable closeable = ObjectifyService.begin();

        //Create and save photo with id '10'
        PhotoId testId = new PhotoId(10);
        FoodPhoto foodPhoto = new FoodPhoto(testId);
        OfyService.ofy().save().entity(foodPhoto);

        //Load foto with id '10' and check result
        Photo photo = PhotoFactory.getInstance().loadPhoto(testId);
        assertNotNull(photo);
        assertTrue(photo instanceof FoodPhoto);
        assertEquals(photo.getId(), testId);

        //Shutdown test environment
        closeable.close();
        helper.tearDown();
    }
}
