package com.davidhjones.example;

public class ZipCodeRange implements Comparable<ZipCodeRange> {

	private final String lowerBound;
	private final String upperBound;


	ZipCodeRange(String zip1, String zip2) {

		// Both ZIP parameters must be valid upon construction of a ZipCodeRange.
		// The upper and lower instance variables are final. We validate the both ZIP parameters so that we know
		// once we've set them, we are safe to assume they are valid ZIPs of exactly five digits.
		if (!ZipCodeValidator.isValidZip(zip1)) {
			throw new IllegalArgumentException("Parameter [zip1] must be a valid US ZIP code.");
		}

		if (!ZipCodeValidator.isValidZip(zip2)) {
			throw new IllegalArgumentException("Parameter [zip2] must be a valid US ZIP code.");
		}

		// TODO: dave 2018-12-15 unit test this
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


	String getLowerBound() {
		return lowerBound;
	}


	String getUpperBound() {
		return upperBound;
	}


	boolean isSingleZipRange() {
		return getLowerBound().equals(getUpperBound());
	}


	boolean isLessThan(ZipCodeRange range) {
		if (range == null) {
			return false;
		}
		return getUpperBound().compareTo(range.getLowerBound()) < 0;
	}


	boolean isAdjacentToLower(ZipCodeRange range) {
		if (range == null) {
			return false;
		}
		int myUpperVal = Integer.parseInt(getUpperBound());
		int rangeLowerVal = Integer.parseInt(range.getLowerBound());
		return rangeLowerVal == myUpperVal + 1;
	}


	boolean overlapsLowBound(ZipCodeRange range) {
		if (range == null) {
			return false;
		}
		return (getUpperBound().compareTo(range.getLowerBound()) > 0)
			 && (getUpperBound().compareTo(range.getUpperBound()) < 0);
	}


	private String getCompareToken() {
		return getLowerBound() + "-" + getUpperBound();
	}


	@Override
	public boolean equals(Object comparee) {
		if (comparee == this) {
			return true;
		}
		else if (comparee == null) {
			return false;
		}
		else if (comparee.getClass() == this.getClass()) {
			return getCompareToken().equals(((ZipCodeRange) comparee).getCompareToken());
		}
		else {
			return false;
		}
	}


	@Override
	public int hashCode() {
		return getLowerBound().hashCode() + getUpperBound().hashCode() + 6;
	}


	@Override
	public String toString() {
		return String.format("[%s,%s]", lowerBound, upperBound);
	}


	@Override
	public int compareTo(ZipCodeRange comparee) {
		return getCompareToken().compareTo(comparee.getCompareToken());
	}


}
