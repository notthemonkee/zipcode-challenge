package com.davidhjones.example;

import java.util.Set;


public class Application {

	public static void main(String[] args) {

		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();

		String rawRanges = "[94133,94133] [94200,94299] [94600,94699]";
		Set<ZipCodeRange> mergedRanges = merger.mergeRanges(rawRanges);

		System.out.println(mergedRanges);


		rawRanges = "[94133,94133] [94200,94299] [94226,94699]";
		mergedRanges = merger.mergeRanges(rawRanges);

		System.out.println(mergedRanges);


	}


}
