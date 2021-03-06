package com.davidhjones.challenge;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test suite for {@link ZipCodeValidator}
 */
public class ZipCodeValidatorTest {

	/**
	 * Test that a string of five digits is a valid US ZIP code.
	 */
	@Test
	public void isValid_validZipCode() {

		String validZip = "01234";
		assertTrue("Expected " + validZip + " to be a valid zip code.", ZipCodeValidator.isValidZip(validZip));
	}


	/**
	 * Test that a string of less than five digits is not a valid US ZIP code.
	 */
	@Test
	public void isValid_shortZip() {

		String shortZip = "4567";
		assertFalse("Expected " + shortZip + " to be an invalid zip code.", ZipCodeValidator.isValidZip(shortZip));
	}


	/**
	 * Test that a string of more than five digits is not a valid US ZIP code.
	 */
	@Test
	public void isValid_longZip() {

		String longZip = "987654";
		assertFalse("Expected " + longZip + " to be an invalid zip code.", ZipCodeValidator.isValidZip(longZip));
	}


	/**
	 * Test that a string containing alpha characters is not a valid US ZIP code.
	 */
	@Test
	public void isValid_alphaCharacterZip() {

		String alphaZip = "45X67";
		assertFalse("Expected " + alphaZip + " to be an invalid zip code.", ZipCodeValidator.isValidZip(alphaZip));
	}


	/**
	 * Test that a blank string is not a valid US ZIP codes.
	 */
	@Test
	public void isValid_blankStringZip() {

		assertFalse("Expected blank string to be an invalid zip code.", ZipCodeValidator.isValidZip(""));
	}


	/**
	 * Test that null is not a valid US ZIP code.
	 */
	@Test
	public void isValid_nullZip() {

		assertFalse("Expected null to be an invalid zip code.", ZipCodeValidator.isValidZip(null));
	}

}