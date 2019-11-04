/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.services;

import junit.framework.TestCase;
import org.junit.Test;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Test cases for the EmailAddress class.
 */
public class EmailAddressTest extends TestCase {

	/**
	 *
	 */
	public EmailAddressTest(String name) {
		super(name);
	}

	/**
	 *
	 */
	public void testGetEmailAddressFromString() {
		// invalid email addresses are allowed for local testing and online avoided by Google

		assertTrue(createEmailAddressIgnoreException("bingo@bongo"));
		assertTrue(createEmailAddressIgnoreException("bingo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo.bongo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo+bongo@bango"));
	}

	/**
	 *
	 */
	protected boolean createEmailAddressIgnoreException(String ea) {
		try {
			EmailAddress.getFromString(ea);
			return true;
		} catch (IllegalArgumentException ex) {
			// creation failed
			return false;
		}
	}

	/**
	 *
	 */
	public void testEmptyEmailAddress() {
		assertFalse(EmailAddress.EMPTY.isValid());
	}

	public void testAsInternetAddress() throws AddressException {
		EmailAddress addressTest = new EmailAddress("test@testfile.de");
		EmailAddress addressTest2 = new EmailAddress("testfile.de");
		EmailAddress addressTestWrong2 = new EmailAddress("");
		EmailAddress addressTestWrong3 = new EmailAddress("test,testfile");

		assertEquals(new InternetAddress("test@testfile.de"), addressTest.asInternetAddress());
		assertEquals(new InternetAddress("testfile.de"), addressTest2.asInternetAddress());
		assertEquals(null, addressTestWrong2.asInternetAddress());
		assertEquals(null, addressTestWrong3.asInternetAddress());
	}
	public void testIsEqual (){
		EmailAddress addressTest = new EmailAddress("test@testfile.de");
		EmailAddress addressTestWrong = new EmailAddress("test@testfile.de");

		assertTrue(addressTest.isEqual(addressTest));
		assertFalse(addressTest.isEqual(addressTestWrong));
	}

	public void testAsString(){
		String test = "hallo";
		EmailAddress halloTest = new EmailAddress(test);

		assertEquals("hallo", halloTest.asString());
	}

}

