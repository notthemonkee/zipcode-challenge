package com.davidhjones.example;

import java.util.Set;

/**
 * Hello world!
 */
public class Application {

	public static void main(String[] args) {


		ZipCodeRange zipCodeRange = new ZipCodeRange("99998", "99999");
		System.out.println(zipCodeRange);


		ZipCodeRangeCollection zips = new ZipCodeRangeCollection();

		System.out.println("Test set 1");
		zips.add(new ZipCodeRange("94133", "94133"))
			 .add(new ZipCodeRange("94200", "94299"))
			 .add(new ZipCodeRange("94600", "94699"));

		System.out.println("Source ranges:");
		printRanges(zips.getRanges());
		System.out.println("Merged ranges");
		printRanges(zips.mergeRanges());


		System.out.println("Test set 2");
		zips = new ZipCodeRangeCollection();
		zips.add(new ZipCodeRange("94133", "94133"))
			 .add(new ZipCodeRange("94200", "94299"))
			 .add(new ZipCodeRange("94226", "94399"));


		System.out.println("Source ranges:");
		printRanges(zips.getRanges());
		System.out.println("Merged ranges");
		printRanges(zips.mergeRanges());


		System.out.println("Test set 3");
		zips = new ZipCodeRangeCollection();
		zips.add("00001", "12343")
			 .add("12345", "67890")
			 .add("12345", "67890")
			 .add("12345", "67890")
			 .add("12345", "67890")
			 .add("12345", "67890")
			 .add("12345", "67890")
			 .add("67891", "67891")
			 .add("12345", "67890");

		System.out.println("Source ranges:");
		printRanges(zips.getRanges());
		System.out.println("Merged ranges");
		printRanges(zips.mergeRanges());


	}


	static void printRanges(Set<ZipCodeRange> ranges) {
		for (ZipCodeRange range : ranges) {
			System.out.println(range);
		}
	}


}
