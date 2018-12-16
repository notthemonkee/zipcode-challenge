package com.davidhjones.example;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;


// TODO: dave 2018-12-16 find/replace collection with set

/**
 * Unit test suite for the ZipCodeRangeCollection class.
 */
public class ZipCodeRangeCollectionTest {

	/**
	 * Verifies that a new ZipCodeRangeCollection can be added from two String parameters.
	 */
	@Test
	public void add_byStringBounds() {
		ZipCodeRangeCollection rangeSet = new ZipCodeRangeCollection();

		assertEquals("Expected new set to have zero items", 0, rangeSet.getAllRanges().size());

		rangeSet.add("12569", "25698");
		assertEquals("Expected set to have one item after adding one range from String params", 1, rangeSet.getAllRanges().size());
	}


	/**
	 * Verifies that a new ZipCodeRangeCollection can be added from a single {@link ZipCodeRange} parameter.
	 */
	@Test
	public void add_byRanges() {
		ZipCodeRangeCollection rangeSet = new ZipCodeRangeCollection();

		assertEquals("Expected new set to have zero items", 0, rangeSet.getAllRanges().size());

		rangeSet.add(new ZipCodeRange("58965", "45878"));
		assertEquals("Expected set to have one item after adding one range", 1, rangeSet.getAllRanges().size());
	}


	/**
	 * Verifies that a newly constructed ZipCodeRangeCollection contains no items.
	 */
	@Test
	public void getAllRanges_emptySet() {
		ZipCodeRangeCollection rangeSet = new ZipCodeRangeCollection();
		assertEquals("Expected new set to have zero items", 0, rangeSet.getAllRanges().size());
	}


	/**
	 * Verifies that we can get back the {@link ZipCodeRange} items from a populated ZipCodeRangeCollection.
	 */
	@Test
	public void getAllRanges_populatedCollection() {
		ZipCodeRangeCollection collection = new ZipCodeRangeCollection();
		collection.add("65659", "76767")
			 .add("95630", "98865");

		assertEquals("Expected collection to have two items after adding two ranges", 2, collection.getAllRanges().size());
		ZipCodeRange[] ranges = collection.getAllRanges().toArray(new ZipCodeRange[collection.getAllRanges().size()]);

		assertEquals("Expected first range lower bound to be 65659", ranges[0].getLowerBound(), "65659");
		assertEquals("Expected first range upper bound to be 76767", ranges[0].getUpperBound(), "76767");

		assertEquals("Expected second range lower bound to be 95630", ranges[1].getLowerBound(), "95630");
		assertEquals("Expected second range upper bound to be 98865", ranges[1].getUpperBound(), "98865");
	}


	/**
	 * Verifies that the result of merging zero {@link ZipCodeRange} items is an empty set.
	 */
	@Test
	public void mergeRanges_whenNoRanges() {
		ZipCodeRangeCollection collection = new ZipCodeRangeCollection();
		Set<ZipCodeRange> ranges = collection.mergeRanges();
		assertTrue("Expected range to be empty when none have been added.", ranges.isEmpty());
	}


	@Test
	public void mergeRanges_whenOneRange() {
		// multiple ranges but all the same lower and upper bounds, merged is a collection of one, and bounds same
		ZipCodeRangeCollection collection = new ZipCodeRangeCollection();
		collection.add("62539", "70056");

		Set<ZipCodeRange> mergedRanges = collection.mergeRanges();

		assertEquals("Expected merged ranges to have one element", 1, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected merged range lower bound to be 62539", ranges[0].getLowerBound(), "62539");
		assertEquals("Expected merged range upper bound to be 70056", ranges[0].getUpperBound(), "70056");
	}


	@Test
	public void mergeRanges_whenOutOfOrder() {
		fail("Not implemented");
	}


	@Test
	public void mergeRanges_whenAllSame() {
		// multiple ranges but all the same lower and upper bounds, merged is a collection of one, and bounds same
		ZipCodeRangeCollection collection = new ZipCodeRangeCollection();
		collection.add("12569", "25698")
			 .add("12569", "25698")
			 .add("12569", "25698")
			 .add("12569", "25698");

		Set<ZipCodeRange> mergedRanges = collection.mergeRanges();

		assertEquals("Expected merged ranges to have one element", 1, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected merged range lower bound to be 12569", ranges[0].getLowerBound(), "12569");
		assertEquals("Expected merged range upper bound to be 25698", ranges[0].getUpperBound(), "25698");
	}


	@Test
	public void mergeRanges_whenAllAdjacent() {
		// multiple ranges but they all stack up, merged is a collection of one with lower and upper = min/max of inputs
		ZipCodeRangeCollection collection = new ZipCodeRangeCollection();
		collection.add("10001", "10009")
			 .add("10040", "10049")
			 .add("10020", "10029")
			 .add("10030", "10039")
			 .add("10010", "10019");

		Set<ZipCodeRange> mergedRanges = collection.mergeRanges();

		assertEquals("Expected merged ranges to have one element", 1, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected merged range lower bound to be 10001", ranges[0].getLowerBound(), "10001");
		assertEquals("Expected merged range upper bound to be 10049", ranges[0].getUpperBound(), "10049");
	}


	@Test
	public void mergeRanges_whenNoneAdjacent() {
		// multiple ranges, all distinct, merged is a collection of x with each range in the collection
		ZipCodeRangeCollection collection = new ZipCodeRangeCollection();
		collection.add("26002", "27000")
			 .add("25002", "26000")
			 .add("27002", "28000")
			 .add("24000", "25000");

		Set<ZipCodeRange> mergedRanges = collection.mergeRanges();

		assertEquals("Expected merged ranges to have one element", 4, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected first merged range lower bound to be 24000", ranges[0].getLowerBound(), "24000");
		assertEquals("Expected first merged range upper bound to be 25000", ranges[0].getUpperBound(), "25000");

		assertEquals("Expected second merged range lower bound to be 25002", ranges[1].getLowerBound(), "25002");
		assertEquals("Expected second merged range upper bound to be 26000", ranges[1].getUpperBound(), "26000");

		assertEquals("Expected third merged range lower bound to be 26002", ranges[2].getLowerBound(), "26002");
		assertEquals("Expected third merged range upper bound to be 27000", ranges[2].getUpperBound(), "27000");

		assertEquals("Expected fourth merged range lower bound to be 27002", ranges[3].getLowerBound(), "27002");
		assertEquals("Expected fourth merged range upper bound to be 28000", ranges[3].getUpperBound(), "28000");
	}


	@Test
	public void mergeRanges_whenHasOverlaps() {
		ZipCodeRangeCollection collection = new ZipCodeRangeCollection();
		collection.add("32185", "34000")
			 .add("33999", "36890")
			 .add("37000", "37510")
			 .add("37500", "45000");

		Set<ZipCodeRange> mergedRanges = collection.mergeRanges();

		assertEquals("Expected merged ranges to have one element", 2, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected first merged range lower bound to be 32185", ranges[0].getLowerBound(), "32185");
		assertEquals("Expected first merged range upper bound to be 36890", ranges[0].getUpperBound(), "36890");

		assertEquals("Expected second merged range lower bound to be 37000", ranges[1].getLowerBound(), "37000");
		assertEquals("Expected second merged range upper bound to be 45000", ranges[1].getUpperBound(), "45000");
	}


	/**
	 * Tests that any ranges whose bounds are completely within the bounds of another range
	 * are "swallowed" and not included in the resulting merged ranges.
	 */
	@Test
	public void mergeRanges_whenSomeContained() {

		// multiple ranges, some of which are fully contained within other ranges
		ZipCodeRangeCollection collection = new ZipCodeRangeCollection();
		collection.add("56000", "57000")
			 .add("56001", "56999")
			 .add("82757", "90000")
			 .add("35662", "48562");

		Set<ZipCodeRange> mergedRanges = collection.mergeRanges();

		assertEquals("Expected merged ranges to have one element", 3, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);

		assertEquals("Expected first merged range lower bound to be 35662", ranges[0].getLowerBound(), "35662");
		assertEquals("Expected first merged range upper bound to be 48562", ranges[0].getUpperBound(), "48562");

		assertEquals("Expected second merged range lower bound to be 56000", ranges[1].getLowerBound(), "56000");
		assertEquals("Expected second merged range upper bound to be 57000", ranges[1].getUpperBound(), "57000");

		assertEquals("Expected third merged range lower bound to be 82757", ranges[2].getLowerBound(), "82757");
		assertEquals("Expected third merged range upper bound to be 90000", ranges[2].getUpperBound(), "90000");
	}


	@Test
	public void mergeRanges_challengeExample1() {
		ZipCodeRangeCollection collection = new ZipCodeRangeCollection();
		collection.add("94113", "94113")
			 .add("94200", "94299")
			 .add("94600", "94699");

		Set<ZipCodeRange> mergedRanges = collection.mergeRanges();

		assertEquals("Expected merged ranges to have three elements", 3, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected first merged range lower bound to be 94113", ranges[0].getLowerBound(), "94113");
		assertEquals("Expected first merged range upper bound to be 94113", ranges[0].getUpperBound(), "94113");

		assertEquals("Expected second merged range lower bound to be 94200", ranges[1].getLowerBound(), "94200");
		assertEquals("Expected second merged range upper bound to be 94299", ranges[1].getUpperBound(), "94299");

		assertEquals("Expected third merged range lower bound to be 94600", ranges[2].getLowerBound(), "94600");
		assertEquals("Expected third merged range upper bound to be 94699", ranges[2].getUpperBound(), "94699");
	}


	@Test
	public void mergeRanges_challengeExample2() {
		ZipCodeRangeCollection collection = new ZipCodeRangeCollection();
		collection.add("94113", "94113")
			 .add("94200", "94299")
			 .add("94226", "94399");

		Set<ZipCodeRange> mergedRanges = collection.mergeRanges();

		assertEquals("Expected merged ranges to have three elements", 2, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected first merged range lower bound to be 94113", ranges[0].getLowerBound(), "94113");
		assertEquals("Expected first merged range upper bound to be 94113", ranges[0].getUpperBound(), "94113");

		assertEquals("Expected second merged range lower bound to be 94200", ranges[1].getLowerBound(), "94200");
		assertEquals("Expected second merged range upper bound to be 94299", ranges[1].getUpperBound(), "94399");
	}

}