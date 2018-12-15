package com.davidhjones.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class ZipCodeRangeTest {

	@Test
	public void getLowerBound() {
		ZipCodeRange range = new ZipCodeRange("01234", "56789");
		assertEquals("Expected lower bound to be 01234", "01234", range.getLowerBound());
	}

	@Test
	public void getUpperBound() {
		ZipCodeRange range = new ZipCodeRange("43210", "98765");
		assertEquals("Expected upper bound to be 98765", "98765", range.getUpperBound());
	}

	@Test
	public void equals_matchesSameInstance() {
		ZipCodeRange range = new ZipCodeRange("65656", "47895");
		assertTrue("Expected instance to be equal to itself.", range.equals(range));
	}

	@Test
	public void equals_matchesSameRangeValues() {
		ZipCodeRange range1 = new ZipCodeRange("22356", "95668");
		ZipCodeRange range2 = new ZipCodeRange("22356", "95668");
		assertTrue("Expected instances to be equal when range value are the same.", range1.equals(range2));
	}

	@Test
	public void equals_noMatchDifferentRange() {
		ZipCodeRange range1 = new ZipCodeRange("77485", "46952");
		ZipCodeRange range2 = new ZipCodeRange("14562", "75684");
		assertFalse("Expected instances to be not equal when range value are different.", range1.equals(range2));
	}

	@Test
	public void equals_noMatchWhenRangeReversed() {
		ZipCodeRange range1 = new ZipCodeRange("24589", "75864");
		ZipCodeRange range2 = new ZipCodeRange("75864", "24589");
		assertFalse("Expected instances to be not equal when range values are mirrors of eachother.", range1.equals(range2));
	}

	@Test
	public void equals_noMatchNull() {
		ZipCodeRange range = new ZipCodeRange("98655", "998542");
		assertFalse("Expected instances to be not equal when comparee is null", range.equals(null));
	}

}