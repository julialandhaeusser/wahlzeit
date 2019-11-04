package org.wahlzeit.services.mailing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.services.EmailAddress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AbstractEmailServiceTest {
    EmailService emailService = null;
    EmailAddress validAddress = null;

    @Before
    public void setup() throws Exception {
        emailService = new MockEmailService();
        validAddress = EmailAddress.getFromString("test@test.de");

    }

    @Test
    public void testSendInvalidEmail() {
        try {
            assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "lol", "hi"));
            assertFalse(emailService.sendEmailIgnoreException(null, validAddress, null, "body"));
            assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "hi", "       "));
        } catch (Exception ex) {
            Assert.fail("Silent mode does not allow exceptions");
        }
    }

    @Test
    public void testSendValidEmail() {
        try {
            assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, "hi", "test"));
        } catch (Exception ex) {
            Assert.fail("Silent mode does not allow exceptions");
        }

    }

    @Test
    public void testSendValidEmailIgnoreException (){

        try {
            assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, validAddress, "hi", "test"));
        } catch (Exception ex) {
            Assert.fail("Silent mode does not allow exceptions");
        }
    }

    @Test
    public void testSendInvalidEmailIgnoreException (){
        try {
            assertFalse(emailService.sendEmailIgnoreException(validAddress, null, validAddress, "lol", "hi"));
            assertFalse(emailService.sendEmailIgnoreException(null, validAddress, null, null,  "body"));
            assertFalse(emailService.sendEmailIgnoreException(validAddress, null, null, "hi", "       "));
        } catch (Exception ex) {
            Assert.fail("Silent mode does not allow exceptions");
        }
    }






}
