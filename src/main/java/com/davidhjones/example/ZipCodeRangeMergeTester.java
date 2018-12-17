package com.davidhjones.example;


/**
 * A simple tester class to demonstrate the execution of the ZipCodeRangeMerger.
 * <p>
 * See unit tests for comprehensive tests.
 */
@SuppressWarnings("WeakerAccess")
public class ZipCodeRangeMergeTester {

	public static void main(String[] args) {

		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		String input1 = "[94133,94133] [94200,94299] [94600,94699]";
		String mergedRanges = merger.mergeRanges(input1);

		System.out.printf("Result for example 1: %s\n", input1);
		System.out.println(mergedRanges);


		String input2 = "[94133,94133] [94200,94299] [94226,94699]";
		mergedRanges = merger.mergeRanges(input2);
		System.out.printf("Result for example 2: %s\n", input2);
		System.out.println(mergedRanges);


	}


}
