package com.davidhjones.example;


import java.util.Set;
import java.util.TreeSet;

public class ZipCodeRangeCollection {


	private final Set<ZipCodeRange> zipRanges = new TreeSet<>();


	public ZipCodeRangeCollection add(ZipCodeRange zipCodeRange) {
		if (zipCodeRange != null) {
			zipRanges.add(zipCodeRange);
		}
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


			// TODO: dave 2018-12-15 need to check for nulls in here
			// does treeset allow nulls

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


				if (lastMerged.contains(rangeToCheck)) {
					// The zip range we're checking is completely within the last merge range so we can skip it and move on.
					continue;
				}
				else if (lastMerged.isLessThan(rangeToCheck)) {
					// The low bound of the range we're checking is above the upper bound of the
					// last merge range so we just append this range to the end of the merged ranges.
					mergedRanges.add(rangeToCheck);
				}
				else if (lastMerged.overlapsLowBound(rangeToCheck)) {
					// The low bound of the range we're checking is straddled by the last merged range,
					// but is not fully contained within. In this case, we update the last merged range's
					// upper bound to the upper bound of the range we're checking.
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

