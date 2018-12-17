package com.davidhjones.example;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ZipCodeRangeMergerTest {

	@Test
	public void mergeRanges_whenEmptyString() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		Set<ZipCodeRange> rangeSet = merger.mergeRanges("");
		assertTrue("Expected set to be empty when no ranges passed", rangeSet.isEmpty());
	}


	@Test
	public void mergeRanges_withOneRange() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		Set<ZipCodeRange> rangeSet = merger.mergeRanges("[12345,67890]");
		ZipCodeRange[] ranges = rangeSet.toArray(new ZipCodeRange[rangeSet.size()]);
		assertEquals("Expected set to have one item when passed one range", 1, rangeSet.size());
		assertEquals("Expected range lower bound to match passed lower bound", "12345", ranges[0].getLowerBound());
		assertEquals("Expected range upper bound to match passed upper bound", "67890", ranges[0].getUpperBound());
	}


	@Test
	public void mergeRanges_withMultipleRanges() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		Set<ZipCodeRange> rangeSet = merger.mergeRanges("[32659,45887] [12544,25666]");
		ZipCodeRange[] ranges = rangeSet.toArray(new ZipCodeRange[rangeSet.size()]);
		assertEquals("Expected set to have two items when passed two ranges", 2, rangeSet.size());
		assertEquals("Expected first range lower bound to match passed lower bound", "12544", ranges[0].getLowerBound());
		assertEquals("Expected first range upper bound to match passed upper bound", "25666", ranges[0].getUpperBound());
		assertEquals("Expected second range lower bound to match passed lower bound", "32659", ranges[1].getLowerBound());
		assertEquals("Expected second range upper bound to match passed upper bound", "45887", ranges[1].getUpperBound());
	}


	@Test
	public void mergeRanges_withStringHavingWhitespace() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		Set<ZipCodeRange> rangeSet = merger.mergeRanges("[  56698  ,  69854  ]");
		ZipCodeRange[] ranges = rangeSet.toArray(new ZipCodeRange[rangeSet.size()]);
		assertEquals("Expected set to have one item when passed one range", 1, rangeSet.size());
		assertEquals("Expected range lower bound to match passed lower bound", "56698", ranges[0].getLowerBound());
		assertEquals("Expected range upper bound to match passed upper bound", "69854", ranges[0].getUpperBound());
	}


	@Test
	public void mergeRanges_withStringHavingOtherCharacters() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		Set<ZipCodeRange> rangeSet = merger.mergeRanges("apple [78874,89981] bananna [32256,36000] foo");
		ZipCodeRange[] ranges = rangeSet.toArray(new ZipCodeRange[rangeSet.size()]);
		assertEquals("Expected set to have two items when passed two ranges", 2, rangeSet.size());
		assertEquals("Expected first range lower bound to match passed lower bound", "32256", ranges[0].getLowerBound());
		assertEquals("Expected first range upper bound to match passed upper bound", "36000", ranges[0].getUpperBound());
		assertEquals("Expected second range lower bound to match passed lower bound", "78874", ranges[1].getLowerBound());
		assertEquals("Expected second range upper bound to match passed upper bound", "89981", ranges[1].getUpperBound());
	}


	@Test
	public void mergeRanges_challengeExample1() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		Set<ZipCodeRange> rangeSet = merger.mergeRanges("[94133,94133] [94200,94299] [94600,94699]");
		ZipCodeRange[] ranges = rangeSet.toArray(new ZipCodeRange[rangeSet.size()]);
		assertEquals("Expected set to have three items when passed challenge example 1", 3, rangeSet.size());
		assertEquals("Expected first range lower bound to match passed lower bound", "94133", ranges[0].getLowerBound());
		assertEquals("Expected first range upper bound to match passed upper bound", "94133", ranges[0].getUpperBound());

		assertEquals("Expected second range lower bound to match passed lower bound", "94200", ranges[1].getLowerBound());
		assertEquals("Expected second range upper bound to match passed upper bound", "94299", ranges[1].getUpperBound());

		assertEquals("Expected third range lower bound to match passed lower bound", "94600", ranges[2].getLowerBound());
		assertEquals("Expected third range upper bound to match passed upper bound", "94699", ranges[2].getUpperBound());
	}


	@Test
	public void mergeRanges_challengeExample2() {
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		Set<ZipCodeRange> rangeSet = merger.mergeRanges("[94133,94133] [94200,94299] [94226,94699]");
		ZipCodeRange[] ranges = rangeSet.toArray(new ZipCodeRange[rangeSet.size()]);
		assertEquals("Expected set to have two items when passed challenge example 2", 2, rangeSet.size());
		assertEquals("Expected first range lower bound to match passed lower bound", "94133", ranges[0].getLowerBound());
		assertEquals("Expected first range upper bound to match passed upper bound", "94133", ranges[0].getUpperBound());

		assertEquals("Expected second range lower bound to match passed lower bound", "94200", ranges[1].getLowerBound());
		assertEquals("Expected second range upper bound to match passed upper bound", "94699", ranges[1].getUpperBound());
	}

}