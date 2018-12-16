package com.davidhjones.example;

public class ZipCodeRange implements Comparable<ZipCodeRange> {

	private final String lowerBound;
	private final String upperBound;
	private final String boundsToken;


	// TODO: dave 2018-12-15 need a constructor that takes a string and parses it.

	public ZipCodeRange(String zip1, String zip2) {

		// TODO:validate each, neither can be null

		// TODO: throw if not in order?
		this.lowerBound = zip1;
		this.upperBound = zip2;
		this.boundsToken = zip1 + "-" + zip2;

	}


	String getLowerBound() {
		return lowerBound;
	}


	String getUpperBound() {
		return upperBound;
	}


	// TODO: dave 2018-12-15 need null checks in here.

	boolean isSingleZipRange() {
		return getLowerBound().equals(getUpperBound());
	}

	boolean isLessThan(ZipCodeRange range) {
		return getUpperBound().compareTo(range.getLowerBound()) < 0;
	}

	boolean overlapsLowBound(ZipCodeRange range) {
		return (getUpperBound().compareTo(range.getLowerBound()) > 0)
			 && (getUpperBound().compareTo(range.getUpperBound()) < 0);
	}

	// TODO: dave 2018-12-15 unit test
	boolean isAdjacentLower(ZipCodeRange range) {
		int myUpperVal = Integer.parseInt(getUpperBound());
		int rangeLowerVal = Integer.parseInt(range.getLowerBound());
		return rangeLowerVal == myUpperVal + 1;
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
			return this.boundsToken.equals(((ZipCodeRange) comparee).boundsToken);
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
		return this.boundsToken.compareTo(comparee.boundsToken);
	}


}
