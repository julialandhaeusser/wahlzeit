package org.wahlzeit.model;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import org.junit.Test;

public class FoodPhotoTest {

    @Test(expected = IllegalArgumentException.class)
    public void FoodPhotoRejecetionTest(){
        //Setup local test google datestore environment
        LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
        helper.setUp();
        Closeable closeable = ObjectifyService.begin();
        new FoodPhoto(null);
        //Shutdown test environment
        closeable.close();
        helper.tearDown();
    }
}
