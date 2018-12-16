package com.davidhjones.example;

import java.util.Set;

/**
 * Hello world!
 */
public class Application {

	public static void main(String[] args) {


		ZipCodeRangeCollection zips = new ZipCodeRangeCollection();

		// test set 2
		zips.add(new ZipCodeRange("94133", "94133"))
			 .add(new ZipCodeRange("94200", "94299"))
			 .add(new ZipCodeRange("94600", "94699"));

		System.out.println("Source ranges:");
		printRanges(zips.getRanges());
		System.out.println("Merged ranges");
		printRanges(zips.merge());


		// test set 2
		zips = new ZipCodeRangeCollection();
		zips.add(new ZipCodeRange("94133", "94133"))
			 .add(new ZipCodeRange("94200", "94299"))
			 .add(new ZipCodeRange("94226", "94399"));


		System.out.println("Source ranges:");
		printRanges(zips.getRanges());
		System.out.println("Merged ranges");
		printRanges(zips.merge());


		//		zips.add(new ZipCodeRange("94200", "94299"))
		//			 .add(new ZipCodeRange("94200", "94299"))
		//			 .add(new ZipCodeRange("94200", "94299"))
		//			 .add(new ZipCodeRange("94200", "94299"))
		//			 .add(new ZipCodeRange("94200", "94299"));
		//
		//		out = zips.getAll();
		//
		//		System.out.println(out);


	}


	static void printRanges(Set<ZipCodeRange> ranges) {
		for (ZipCodeRange range : ranges) {
			System.out.println(range);
		}
	}

}
