package com.davidhjones.challenge;

import com.davidhjones.challenge.ZipCodeRange;
import com.davidhjones.challenge.ZipCodeRangeSet;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Unit test suite for the ZipCodeRangeSet class.
 */
public class ZipCodeRangeSetTest {

	/**
	 * Verifies that a new ZipCodeRangeSet can be added from two String parameters.
	 */
	@Test
	public void add_byStringBounds() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();

		assertEquals("Expected new set to have zero items", 0, rangeSet.getAllRanges().size());

		rangeSet.add("12569", "25698");
		assertEquals("Expected set to have one item after adding one range from String params", 1, rangeSet.getAllRanges().size());
	}


	/**
	 * Verifies that a new ZipCodeRangeSet can be added from a single {@link ZipCodeRange} parameter.
	 */
	@Test
	public void add_byRanges() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();

		assertEquals("Expected new set to have zero items", 0, rangeSet.getAllRanges().size());

		rangeSet.add(new ZipCodeRange("58965", "45878"));
		assertEquals("Expected set to have one item after adding one range", 1, rangeSet.getAllRanges().size());
	}


	/**
	 * Verifies that a newly constructed ZipCodeRangeSet contains no items.
	 */
	@Test
	public void getAllRanges_emptySet() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		assertEquals("Expected new set to have zero items", 0, rangeSet.getAllRanges().size());
	}


	/**
	 * Verifies that we can get back the {@link ZipCodeRange} items from a populated ZipCodeRangeSet.
	 */
	@Test
	public void getAllRanges_populatedSet() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("65659", "76767")
			 .add("95630", "98865");

		assertEquals("Expected set to have two items after adding two ranges", 2, rangeSet.getAllRanges().size());
		ZipCodeRange[] ranges = rangeSet.getAllRanges().toArray(new ZipCodeRange[rangeSet.getAllRanges().size()]);

		assertEquals("Expected first range lower bound to be 65659", ranges[0].getLowerBound(), "65659");
		assertEquals("Expected first range upper bound to be 76767", ranges[0].getUpperBound(), "76767");

		assertEquals("Expected second range lower bound to be 95630", ranges[1].getLowerBound(), "95630");
		assertEquals("Expected second range upper bound to be 98865", ranges[1].getUpperBound(), "98865");
	}


	@Test
	public void clear_emptiesSet() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("65659", "76767")
			 .add("95630", "98865");

		assertEquals("Expected set to have two items after adding two ranges", 2, rangeSet.getAllRanges().size());

		rangeSet.clear();

		assertEquals("Expected set to have zero items after clearing", 0, rangeSet.getAllRanges().size());
	}


	/**
	 * Verifies that the result of merging zero {@link ZipCodeRange} items is an empty set.
	 */
	@Test
	public void mergeRanges_whenNoRanges() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		Set<ZipCodeRange> ranges = rangeSet.mergeRanges();
		assertTrue("Expected set to be empty when none have been added.", ranges.isEmpty());
	}


	/**
	 * Verifies that the result of merging one {@link ZipCodeRange} items is a set with that one item.
	 */
	@Test
	public void mergeRanges_whenOneRange() {
		// multiple ranges but all the same lower and upper bounds, merged is a set of one, and bounds same
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("62539", "70056");

		Set<ZipCodeRange> mergedRanges = rangeSet.mergeRanges();

		assertEquals("Expected set to have one element", 1, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected merged range lower bound to be 62539", ranges[0].getLowerBound(), "62539");
		assertEquals("Expected merged range upper bound to be 70056", ranges[0].getUpperBound(), "70056");
	}


	/**
	 * Verifies that when {@link ZipCodeRange} items are added "out of order",
	 * the merged set returns them in correct order.
	 */
	@Test
	public void mergeRanges_whenOutOfOrder() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("35689", "45521")
			 .add("21656", "28996")
			 .add("10001", "10900");

		Set<ZipCodeRange> mergedRanges = rangeSet.mergeRanges();

		assertEquals("Expected set to have three elements", 3, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected first merged range lower bound to be 10001", ranges[0].getLowerBound(), "10001");
		assertEquals("Expected first merged range upper bound to be 10900", ranges[0].getUpperBound(), "10900");

		assertEquals("Expected second merged range lower bound to be 21656", ranges[1].getLowerBound(), "21656");
		assertEquals("Expected second merged range upper bound to be 28996", ranges[1].getUpperBound(), "28996");

		assertEquals("Expected third merged range lower bound to be 35689", ranges[2].getLowerBound(), "35689");
		assertEquals("Expected third merged range upper bound to be 45521", ranges[2].getUpperBound(), "45521");
	}


	/**
	 * Verifies that a set with a single range is returned when merging a set of ranges
	 * where all ranges have the same lower and upper bound.
	 */
	@Test
	public void mergeRanges_whenAllSame() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("12569", "25698")
			 .add("12569", "25698")
			 .add("12569", "25698")
			 .add("12569", "25698");

		Set<ZipCodeRange> mergedRanges = rangeSet.mergeRanges();

		assertEquals("Expected set to have one element", 1, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected merged range lower bound to be 12569", ranges[0].getLowerBound(), "12569");
		assertEquals("Expected merged range upper bound to be 25698", ranges[0].getUpperBound(), "25698");
	}


	/**
	 * Verifies that a set where all ranges are adjacent is collapsed down into one range
	 * encompassing the overall lower and upper bounds of all ranges.
	 */
	@Test
	public void mergeRanges_whenAllAdjacent() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("10001", "10009")
			 .add("10040", "10049")
			 .add("10020", "10029")
			 .add("10030", "10039")
			 .add("10010", "10019");

		Set<ZipCodeRange> mergedRanges = rangeSet.mergeRanges();

		assertEquals("Expected set to have one element", 1, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected merged range lower bound to be 10001", ranges[0].getLowerBound(), "10001");
		assertEquals("Expected merged range upper bound to be 10049", ranges[0].getUpperBound(), "10049");
	}


	/**
	 * Verifies that a set where all ranges are distinct, with none adjacent and no overlap,
	 * the result is a set containing all the ranges in the correct order
	 */
	@Test
	public void mergeRanges_whenNoneAdjacent() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("26002", "27000")
			 .add("25002", "26000")
			 .add("27002", "28000")
			 .add("24000", "25000");

		Set<ZipCodeRange> mergedRanges = rangeSet.mergeRanges();

		assertEquals("Expected set to have four elements", 4, mergedRanges.size());

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


	/**
	 * Verifies that a set that has some overlapping ranges is collapsed down to the minimum number of ranges needed.
	 */
	@Test
	public void mergeRanges_whenHasOverlaps() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("32185", "34000")
			 .add("33999", "36890")
			 .add("37000", "37510")
			 .add("37500", "45000");

		Set<ZipCodeRange> mergedRanges = rangeSet.mergeRanges();

		assertEquals("Expected set to have two elements", 2, mergedRanges.size());

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
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("56000", "57000")
			 .add("56001", "56999")
			 .add("82757", "90000")
			 .add("35662", "48562");

		Set<ZipCodeRange> mergedRanges = rangeSet.mergeRanges();

		assertEquals("Expected set to have three elements", 3, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);

		assertEquals("Expected first merged range lower bound to be 35662", ranges[0].getLowerBound(), "35662");
		assertEquals("Expected first merged range upper bound to be 48562", ranges[0].getUpperBound(), "48562");

		assertEquals("Expected second merged range lower bound to be 56000", ranges[1].getLowerBound(), "56000");
		assertEquals("Expected second merged range upper bound to be 57000", ranges[1].getUpperBound(), "57000");

		assertEquals("Expected third merged range lower bound to be 82757", ranges[2].getLowerBound(), "82757");
		assertEquals("Expected third merged range upper bound to be 90000", ranges[2].getUpperBound(), "90000");
	}


	/**
	 * Verifies the result of the first example given in the Zip Code Challenge specifications.
	 */
	@Test
	public void mergeRanges_challengeExample1() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("94113", "94113")
			 .add("94200", "94299")
			 .add("94600", "94699");

		Set<ZipCodeRange> mergedRanges = rangeSet.mergeRanges();

		assertEquals("Expected set to have three elements", 3, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected first merged range lower bound to be 94113", ranges[0].getLowerBound(), "94113");
		assertEquals("Expected first merged range upper bound to be 94113", ranges[0].getUpperBound(), "94113");

		assertEquals("Expected second merged range lower bound to be 94200", ranges[1].getLowerBound(), "94200");
		assertEquals("Expected second merged range upper bound to be 94299", ranges[1].getUpperBound(), "94299");

		assertEquals("Expected third merged range lower bound to be 94600", ranges[2].getLowerBound(), "94600");
		assertEquals("Expected third merged range upper bound to be 94699", ranges[2].getUpperBound(), "94699");
	}


	/**
	 * Verifies the result of the second example given in the Zip Code Challenge specifications.
	 */
	@Test
	public void mergeRanges_challengeExample2() {
		ZipCodeRangeSet rangeSet = new ZipCodeRangeSet();
		rangeSet.add("94113", "94113")
			 .add("94200", "94299")
			 .add("94226", "94399");

		Set<ZipCodeRange> mergedRanges = rangeSet.mergeRanges();

		assertEquals("Expected set to have two elements", 2, mergedRanges.size());

		ZipCodeRange[] ranges = mergedRanges.toArray(new ZipCodeRange[mergedRanges.size()]);
		assertEquals("Expected first merged range lower bound to be 94113", ranges[0].getLowerBound(), "94113");
		assertEquals("Expected first merged range upper bound to be 94113", ranges[0].getUpperBound(), "94113");

		assertEquals("Expected second merged range lower bound to be 94200", ranges[1].getLowerBound(), "94200");
		assertEquals("Expected second merged range upper bound to be 94299", ranges[1].getUpperBound(), "94399");
	}

}