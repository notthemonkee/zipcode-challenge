package com.davidhjones.example;


import java.util.Set;
import java.util.TreeSet;

public class ZipCodeRangeCollection {


	private final Set<ZipCodeRange> zipRanges = new TreeSet<>();


	public ZipCodeRangeCollection add(ZipCodeRange zipCodeRange) {
		zipRanges.add(zipCodeRange);
		return this;
	}

	public Set<ZipCodeRange> getRanges() {

		return new TreeSet<>(zipRanges);
	}

	public Set<ZipCodeRange> merge() {

		if (zipRanges.isEmpty()) {
			return zipRanges;
		}

		TreeSet<ZipCodeRange> mergedRanges = new TreeSet<>();

		for (ZipCodeRange rangeToCheck : zipRanges) {

			// If the merged list is empty, just add the range to check
			if (mergedRanges.isEmpty()) {
				mergedRanges.add(rangeToCheck);
			}
			else {

				ZipCodeRange lastMerged = mergedRanges.last();


				// TODO: dave 2018-12-15 not sure if I need this case yet
				//				if (rangeToCheck.getLowerBound().equals(rangeToCheck.getUpperBound())) {
				//					// TODO: dave 2018-12-15 fill in this case
				//
				//				}
				//				else {


				// test range is completely within this last range so skip it
				if (lastMerged.contains(rangeToCheck)) {
					continue;
				}

				// low bound of test range is greater than upper bound of merged list, just append test range.
				else if (lastMerged.isLessThan(rangeToCheck)) {
					mergedRanges.add(rangeToCheck);
				}


				// low bound of test range is within last range in merge list, update the last range in merged
				// list to have upper bound of test range
				else if (lastMerged.overlapsLowBound(rangeToCheck)) {
					// TODO: dave 2018-12-15 maybe change so we're just setting the upper bound of the last one instead of
					// creating a new one.
					mergedRanges.add(new ZipCodeRange(lastMerged.getLowerBound(), rangeToCheck.getUpperBound()));
					mergedRanges.remove(lastMerged);
				}

				//}

			}
		}


		return mergedRanges;

	}


}

