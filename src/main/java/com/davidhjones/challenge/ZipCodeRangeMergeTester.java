package com.davidhjones.challenge;


/**
 * A simple tester class to demonstrate the execution of the ZipCodeRangeMerger.
 * <p>
 * See unit tests for comprehensive tests.
 */
@SuppressWarnings("WeakerAccess")
public class ZipCodeRangeMergeTester {

	public static void main(String[] args) {

		// Challenge exmpale 1: all ranges distinct, remains three ranges
		testRange("[94133,94133] [94200,94299] [94600,94699]");

		// Challenge example 2: one range overlaps, merges down to two ranges
		testRange("[94133,94133] [94200,94299] [94226,94699]");

		// three adjacent ranges, merges down to one range
		testRange("[12000,12999] [13000,13999] [14000,14999]");

		// Three ranges, distinct but not in order, and one range where the zips are not in order.
		testRange("[98700,96000] [56000,45000] [25000,35000]");

	}


	public static void testRange(String input) {
		String mergedRanges = new ZipCodeRangeMerger().mergeRanges(input);
		System.out.printf("Result for: %s\n", input);
		System.out.println(mergedRanges);
		System.out.println();
	}


}
