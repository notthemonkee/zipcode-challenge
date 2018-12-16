package com.davidhjones.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class ZipCodeRangeTest {


	// TODO: dave 2018-12-16 flesh this out with parameterized tests

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void constructor_zip1Null() {
		ZipCodeRange range = new ZipCodeRange(null, "89565");
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void constructor_zip2Null() {
		ZipCodeRange range = new ZipCodeRange("45589", null);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void constructor_zip1NotValidZip() {
		ZipCodeRange range = new ZipCodeRange("090", "45896");
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void constructor_zip2NotValidZip() {
		ZipCodeRange range = new ZipCodeRange("48796", "4568");
	}


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
	public void isSingleZipRange_whenSame() {
		ZipCodeRange range = new ZipCodeRange("12312", "12312");
		assertTrue("Expected instance to be single zip range when upper and lower are the same", range.isSingleZipRange());
	}

	@Test
	public void isSingleZipRange_whenDifferent() {
		ZipCodeRange range = new ZipCodeRange("12312", "12311");
		assertFalse("Expected instance not to be single zip range when upper and lower are different", range.isSingleZipRange());
	}

	@Test
	public void isLessThan_whenLess() {
		ZipCodeRange range1 = new ZipCodeRange("11111", "22222");
		ZipCodeRange range2 = new ZipCodeRange("22223", "33333");
		assertTrue("Expected " + range1 + " to be less than " + range2, range1.isLessThan(range2));
	}

	@Test
	public void isLessThan_whenSame() {
		ZipCodeRange range1 = new ZipCodeRange("21212", "31313");
		ZipCodeRange range2 = new ZipCodeRange("31313", "46464");
		assertFalse("Expected " + range1 + " not to be less than " + range2, range1.isLessThan(range2));
	}

	@Test
	public void isLessThan_whenGreater() {
		ZipCodeRange range1 = new ZipCodeRange("65656", "76768");
		ZipCodeRange range2 = new ZipCodeRange("76767", "88778");
		assertFalse("Expected " + range1 + " not to be less than " + range2, range1.isLessThan(range2));
	}

	@Test
	public void isLessThan_whenNull() {
		ZipCodeRange range1 = new ZipCodeRange("23456", "24567");
		assertFalse("Expected " + range1 + " not to be less than null", range1.isLessThan(null));
	}


	@Test
	public void isAdjacentLower_whenAdjacentLower() {
		ZipCodeRange range1 = new ZipCodeRange("65656", "76768");
		ZipCodeRange range2 = new ZipCodeRange("76769", "88778");
		assertTrue("Expected " + range1 + " to be adjacent to lower " + range2, range1.isAdjacentToLower(range2));
	}


	@Test
	public void isAdjacentLower_whenAdjacentHigher() {
		ZipCodeRange range1 = new ZipCodeRange("87564", "87889");
		ZipCodeRange range2 = new ZipCodeRange("87550", "87563");
		assertFalse("Expected " + range1 + " not to be adjacent to lower " + range2, range1.isAdjacentToLower(range2));
	}

	@Test
	public void isAdjacentLower_whenLower() {
		ZipCodeRange range1 = new ZipCodeRange("12345", "12355");
		ZipCodeRange range2 = new ZipCodeRange("12357", "65482");
		assertFalse("Expected " + range1 + " not to be adjacent to lower " + range2, range1.isAdjacentToLower(range2));
	}

	@Test
	public void isAdjacentLower_whenSame() {
		ZipCodeRange range1 = new ZipCodeRange("23556", "56698");
		ZipCodeRange range2 = new ZipCodeRange("23556", "56698");
		assertFalse("Expected " + range1 + " not to be adjacent to lower " + range2, range1.isAdjacentToLower(range2));
	}

	@Test
	public void isAdjacentLower_whenContains() {
		ZipCodeRange range1 = new ZipCodeRange("10567", "10679");
		ZipCodeRange range2 = new ZipCodeRange("10600", "10650");
		assertFalse("Expected " + range1 + " not to be adjacent to lower " + range2, range1.isAdjacentToLower(range2));
	}

	@Test
	public void isAdjacentLower_whenNull() {
		ZipCodeRange range1 = new ZipCodeRange("65987", "78451");
		assertFalse("Expected " + range1 + " not to be adjacent to lower null", range1.isAdjacentToLower(null));
	}


	@Test
	public void overlapsLowBound_whenOverlaps() {
		ZipCodeRange range1 = new ZipCodeRange("34500", "34601");
		ZipCodeRange range2 = new ZipCodeRange("34600", "36522");
		assertTrue("Expected " + range1 + " to overlap the lower bound of " + range2, range1.overlapsLowBound(range2));
	}

	@Test
	public void overlapsLowBound_whenSame() {
		ZipCodeRange range1 = new ZipCodeRange("16522", "45889");
		ZipCodeRange range2 = new ZipCodeRange("45889", "56999");
		assertFalse("Expected " + range1 + " to not overlap the lower bound of " + range2, range1.overlapsLowBound(range2));
	}

	@Test
	public void overlapsLowBound_whenContains() {
		ZipCodeRange range1 = new ZipCodeRange("33569", "35669");
		ZipCodeRange range2 = new ZipCodeRange("35600", "35660");
		assertFalse("Expected " + range1 + " to not overlap the lower bound of " + range2, range1.overlapsLowBound(range2));
	}

	@Test
	public void overlapsLowBound_whenLessThan() {
		ZipCodeRange range1 = new ZipCodeRange("22569", "27880");
		ZipCodeRange range2 = new ZipCodeRange("27881", "32333");
		assertFalse("Expected " + range1 + " to not overlap the lower bound of " + range2, range1.overlapsLowBound(range2));
	}

	@Test
	public void overlapsLowBound_whenGreaterThan() {
		ZipCodeRange range1 = new ZipCodeRange("42569", "56698");
		ZipCodeRange range2 = new ZipCodeRange("30230", "30456");
		assertFalse("Expected " + range1 + " to not overlap the lower bound of " + range2, range1.overlapsLowBound(range2));
	}

	@Test
	public void overlapsLowBound_whenNull() {
		ZipCodeRange range1 = new ZipCodeRange("42569", "56698");
		assertFalse("Expected " + range1 + " to not overlap null", range1.overlapsLowBound(null));
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
	public void equals_noMatchWhenRangeReversed() {
		ZipCodeRange range1 = new ZipCodeRange("24589", "75864");
		ZipCodeRange range2 = new ZipCodeRange("75864", "24589");
		assertTrue("Expected instances to be equal when range values are mirrors of each other.", range1.equals(range2));
	}

	@Test
	public void equals_noMatchDifferentRange() {
		ZipCodeRange range1 = new ZipCodeRange("77485", "46952");
		ZipCodeRange range2 = new ZipCodeRange("14562", "75684");
		assertFalse("Expected instances to be not equal when range value are different.", range1.equals(range2));
	}

	@Test
	public void equals_noMatchNull() {
		ZipCodeRange range = new ZipCodeRange("98655", "99854");
		assertFalse("Expected instances to be not equal when comparee is null", range.equals(null));
	}

}