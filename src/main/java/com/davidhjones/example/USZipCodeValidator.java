package com.davidhjones.example;

/**
 * A simple validator for US ZIP codes.
 * <p>
 * <p>
 * This validator is intended for use in a code challenge and so it provides
 * only a single method for doing basic validation of US ZIP codes.
 */
public class USZipCodeValidator {

	/**
	 * Determines if the passed String is a valid US ZIP code.
	 * <p>
	 * This simply checks that the passed ZIP code is five digits, it does not to any checks
	 * to see if the string is actually a valid ZIP code in the US postal system.
	 *
	 * @param zipCode the string to validate
	 * @return true if the string is a valid US ZIP code, otherwise false.
	 */
	public static boolean isValidZip(String zipCode) {

		if (zipCode == null) {
			return false;
		}

		// For a zip code to be valid in our system, it must be exactly 5 digits.
		return zipCode.matches("^\\d\\d\\d\\d\\d$");
		
	}

}
