package com.davidhjones.challenge;

/**
 * This console application provides a way to exercise the range merger from the command line.
 */
public class Application {

	/**
	 * Entry point to the application.
	 *
	 * @param args the first argument is expected to be the ZIP code range to merge
	 */
	public static void main(String[] args) {

		if (args.length == 0 || args[0] == null) {
			System.out.println("Please pass a ZIP code range as the first argument e.g. \"[94133,94133] [94200,94299] [94600,94699]\"");
			System.exit(-1);
		}


		System.out.println(new ZipCodeRangeMerger().mergeRanges(args[0].trim()));
	}


}
