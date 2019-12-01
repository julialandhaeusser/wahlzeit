package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AccessRightsTest.class,
        CoordinateTestSuite.class, FlagReasonTest.class, GenderTest.class, GuestTest.class, PhotoFilterTest.class,
        TagsTest.class, UserStatusTest.class, ValueTest.class, FoodPhotoTestSuite.class})
public class ModelTestSuite {
}
