package com.davidhjones.example;

import java.util.Set;


public class Application {

	public static void main(String[] args) {


		//		ZipCodeRange zipCodeRange = new ZipCodeRange("99998", "99999");
		//		System.out.println(zipCodeRange);
		//
		//
		//		ZipCodeRangeSet zips = new ZipCodeRangeSet();
		//
		//		System.out.println("Test set 1");
		//		zips.add(new ZipCodeRange("94133", "94133"))
		//			 .add(new ZipCodeRange("94200", "94299"))
		//			 .add(new ZipCodeRange("94600", "94699"));
		//
		//
		//		System.out.println(zips.mergeRanges().toArray());
		//
		//		System.out.println("Merged ranges");
		//		printRanges(zips.mergeRanges());
		//
		//
		//		System.out.println("Test set 2");
		//		zips = new ZipCodeRangeSet();
		//		zips.add(new ZipCodeRange("94133", "94133"))
		//			 .add(new ZipCodeRange("94200", "94299"))
		//			 .add(new ZipCodeRange("94226", "94399"));
		//
		//
		//		System.out.println("Merged ranges");
		//		printRanges(zips.mergeRanges());
		//
		//
		//		System.out.println("Test set 3");
		//		zips = new ZipCodeRangeSet();
		//		zips.add("00001", "12343")
		//			 .add("12345", "67890")
		//			 .add("12345", "67890")
		//			 .add("12345", "67890")
		//			 .add("12345", "67890")
		//			 .add("12345", "67890")
		//			 .add("12345", "67890")
		//			 .add("67891", "67891")
		//			 .add("12345", "67890");
		//
		//		System.out.println("Merged ranges");
		//		printRanges(zips.mergeRanges());

		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();

		String rawRanges = "[94133,94133] [94200,94299] [94600,94699]";
		Set<ZipCodeRange> mergedRanges = merger.mergeRanges(rawRanges);

		System.out.println(mergedRanges);


		rawRanges = "[94133,94133] [94200,94299] [94226,94699]";
		mergedRanges = merger.mergeRanges(rawRanges);

		System.out.println(mergedRanges);


	}


}
