package com.davidhjones.example;

public class ZipCodeRange implements Comparable<ZipCodeRange> {

	private final String lowerBound;
	private final String upperBound;
	private final String boundsToken;

	public ZipCodeRange(String zip1, String zip2) {

		// TODO:validate each

		// TODO: throw if not in order?
		this.lowerBound = zip1;
		this.upperBound = zip2;
		this.boundsToken = zip1 + "-" + zip2;

	}


	public String getLowerBound() {
		return lowerBound;
	}


	public String getUpperBound() {
		return upperBound;
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
