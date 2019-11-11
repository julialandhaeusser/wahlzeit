package org.wahlzeit.model;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wahlzeit.services.OfyService;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class FoodPhotoFactoryTest {

    @Before
    public void initializeFactory() throws Exception {
        Field field = PhotoFactory.class.getDeclaredField ("instance");
        field.setAccessible(true);
        field.set(null, null);
        FoodPhotoFactory.initialize();
    }
    @Test
    public void initializeTest (){

       PhotoFactory test1 = PhotoFactory.getInstance();

       assertEquals((test1 instanceof FoodPhotoFactory), true);

    }

    @Test
    public void loadTest(){

        //Setup local test google datestore environment
        LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
        helper.setUp();
        Closeable closeable = ObjectifyService.begin();

        //Create and save photo with id '10'
        PhotoId testId = new PhotoId(10);
        FoodPhoto foodPhoto = new FoodPhoto(testId);
        OfyService.ofy().save().entity(foodPhoto).now();

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
