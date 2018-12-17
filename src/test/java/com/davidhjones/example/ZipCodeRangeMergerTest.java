package com.davidhjones.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZipCodeRangeMergerTest {


	@Test
	public void mergeRanges_whenNull() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		String mergedRanges = merger.mergeRanges(null);
		assertEquals("Expected result to be empty string when null passed", "", mergedRanges);
	}


	@Test
	public void mergeRanges_whenEmptyString() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		String mergedRanges = merger.mergeRanges("");
		assertEquals("Expected result to be empty string when no ranges passed", "", mergedRanges);
	}


	@Test
	public void mergeRanges_withOneRange() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		String mergedRanges = merger.mergeRanges("[12345,67890]");
		assertEquals("[12345,67890]", mergedRanges);
	}


	@Test
	public void mergeRanges_withMultipleRanges() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		String mergedRanges = merger.mergeRanges("[32659,45887] [12544,25666]");
		assertEquals("[12544,25666] [32659,45887]", mergedRanges);
	}


	@Test
	public void mergeRanges_withStringHavingWhitespace() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		String mergedRanges = merger.mergeRanges("[  56698  ,  69854  ]");
		assertEquals("[56698,69854]", mergedRanges);
	}


	@Test
	public void mergeRanges_withStringHavingOtherCharacters() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		String mergedRanges = merger.mergeRanges("apple [78874,89981] banana [32256,36000] foo");
		assertEquals("[32256,36000] [78874,89981]", mergedRanges);
	}


	@Test
	public void mergeRanges_challengeExample1() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		String mergedRanges = merger.mergeRanges("[94133,94133] [94200,94299] [94600,94699]");
		assertEquals("[94133,94133] [94200,94299] [94600,94699]", mergedRanges);
	}


	@Test
	public void mergeRanges_challengeExample2() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		String mergedRanges = merger.mergeRanges("[94133,94133] [94200,94299] [94226,94699]");
		assertEquals("[94133,94133] [94200,94699]", mergedRanges);
	}

}