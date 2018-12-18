package com.davidhjones.challenge;

/**
 * An immutable class that defines what is a valid ZIP code range in our system.
 * <p>
 * A ZIP code range has lower and upper bounds, each of which must be a valid US ZIP code: five sequential digits.
 * A ZipCodeRange knows how to compare itself with other ranges as well as how to be sorted with other ranges.
 */
class ZipCodeRange implements Comparable<ZipCodeRange> {

	/**
	 * Contains the lower of the two ZIP codes in the range.
	 */
	private final String lowerBound;

	/**
	 * Contains the upper of the two ZIP codes in the range.
	 */
	private final String upperBound;


	/**
	 * Constructor
	 * <p>
	 * A ZipCodeRange must be passed two valid US ZIP codes. Each ZIP code parameter is validated and an
	 * {@link IllegalArgumentException} will be thrown if either parameter is not valid.
	 * <p>
	 * The ZIP code parameters can be passed in either order and may be the same value. When the instance
	 * is constructed, the lower and upper bounds will be set based on the values of the two parameters.
	 *
	 * @param zip1 a valid US ZIP code
	 * @param zip2 a valid US ZIP code
	 * @throws IllegalArgumentException if either ZIP code argument is not a valid US ZIP code
	 */
	ZipCodeRange(String zip1, String zip2) throws IllegalArgumentException {

		// Both ZIP parameters must be valid upon construction of a ZipCodeRange.
		// The upper and lower instance variables are final. We validate the both ZIP parameters so that we know
		// once we've set them, we are safe to assume they are valid ZIPs of exactly five digits.
		if (!ZipCodeValidator.isValidZip(zip1)) {
			throw new IllegalArgumentException("Parameter [zip1] must be a valid US ZIP code.");
		}

		if (!ZipCodeValidator.isValidZip(zip2)) {
			throw new IllegalArgumentException("Parameter [zip2] must be a valid US ZIP code.");
		}

		// Establish ZIP range lower/upper bounds allowing caller to pass them in either order.
		if (zip1.compareTo(zip2) < 0) {
			lowerBound = zip1;
			upperBound = zip2;
		}
		else {
			lowerBound = zip2;
			upperBound = zip1;
		}

	}


	/**
	 * Get the lower bound of the ZIP range.
	 *
	 * @return the lower bound of the ZIP range
	 */
	String getLowerBound() {
		return lowerBound;
	}


	/**
	 * Get the upper bound of the ZIP range.
	 *
	 * @return the upper bound of the ZIP range
	 */
	String getUpperBound() {
		return upperBound;
	}


	/**
	 * Determines if the two ZIP codes in the range are the same.
	 *
	 * @return true if they are the same, otherwise false
	 */
	boolean isSingleZipRange() {
		return getLowerBound().equals(getUpperBound());
	}


	/**
	 * Determines if this ZipCodeRange is less than another range when the ranges are sorted by their natural order.
	 * <p>
	 * This range is considered less than another if its upper bound is less than the other range's lower bound.
	 *
	 * @param range the ZipCodeRange to compare
	 * @return true if the range to compare is not null and this range is less than the passed range, otherwise false
	 */
	boolean isLessThan(ZipCodeRange range) {
		if (range == null) {
			return false;
		}
		return getUpperBound().compareTo(range.getLowerBound()) < 0;
	}


	/**
	 * Determines if this ZipCodeRange is the next range lower than another range when the ranges are sorted
	 * by their natural order.
	 * <p>
	 * This range is considered to be the next lower range if its upper bound is numerically one less
	 * than the other range's lower bound.
	 *
	 * @param range the ZipCodeRange to compare
	 * @return true if the range to compare is not null and this range is one less, otherwise false
	 */
	boolean isAdjacentToLower(ZipCodeRange range) {
		if (range == null) {
			return false;
		}
		int myUpperVal = Integer.parseInt(getUpperBound());
		int rangeLowerVal = Integer.parseInt(range.getLowerBound());
		return rangeLowerVal == myUpperVal + 1;
	}


	/**
	 * Determines if this ZipCodeRange overlaps the lower bound of another range
	 * when the ranges are sorted by their natural order.
	 * <p>
	 * This range is considered to be overlapping another when its upper bound is greater than the other range's lower
	 * bound but less than the other ranges upper bound.
	 *
	 * @param range the ZipCodeRange to compare
	 * @return true if the range to compare is not null and this range overlaps, otherwise false
	 */
	boolean overlapsLowBound(ZipCodeRange range) {
		if (range == null) {
			return false;
		}
		return (getUpperBound().compareTo(range.getLowerBound()) > 0)
			 && (getUpperBound().compareTo(range.getUpperBound()) < 0);
	}


	/**
	 * Returns a consistent String token, based on the lower and upper bounds of this range
	 * that can be used to compare one range with another.
	 *
	 * @return a consistent String token, based on the lower and upper bounds of this range
	 */
	private String getCompareToken() {
		return getLowerBound() + "-" + getUpperBound();
	}


	/**
	 * Determines if this ZipCodeRange is equal to another.
	 * <p>
	 * Two range instances are considered equal if the lower and upper bounds are the same.
	 *
	 * @param obj the ZipCodeRange to compare
	 * @return true if the two ranges have the same lower and upper bounds, otherwise false
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		else if (obj == null) {
			return false;
		}
		else if (obj.getClass() == this.getClass()) {
			return getCompareToken().equals(((ZipCodeRange) obj).getCompareToken());
		}
		else {
			return false;
		}
	}


	/**
	 * A hash code for ZipCodeRange instances.
	 * <p>
	 * Based on the lower and upper bounds
	 *
	 * @return hash code for this ZipCodeRange
	 */
	@Override
	public int hashCode() {
		return getLowerBound().hashCode() + getUpperBound().hashCode() + 6;
	}


	@Override
	public String toString() {
		return String.format("[%s,%s]", lowerBound, upperBound);
	}


	/**
	 * Compares this ZipCodeRange to another for equality and sorting.
	 *
	 * @param range the ZipCodeRange to compare to
	 * @return 0 if both ranges are the same, -1 if this range is less than the range to compare to, 1 if greater.
	 */
	@Override
	public int compareTo(ZipCodeRange range) {
		return getCompareToken().compareTo(range.getCompareToken());
	}


}
