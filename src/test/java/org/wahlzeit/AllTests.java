package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.HandlersTestSuite;
import org.wahlzeit.handlers.TellFriendTest;
import org.wahlzeit.model.*;
import org.wahlzeit.model.persistence.DatastoreAdapterTest;
import org.wahlzeit.model.persistence.PersistenceTestSuite;
import org.wahlzeit.services.EmailAddressTest;
import org.wahlzeit.services.LogBuilderTest;
import org.wahlzeit.services.ServiceTestSuite;
import org.wahlzeit.services.mailing.EmailServiceTestSuite;
import org.wahlzeit.utils.StringUtilTest;
import org.wahlzeit.utils.UtilsTestSuite;
import org.wahlzeit.utils.VersionTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({HandlersTestSuite.class, PersistenceTestSuite.class, ModelTestSuite.class, ServiceTestSuite.class,
        UtilsTestSuite.class})
public class AllTests {
}
