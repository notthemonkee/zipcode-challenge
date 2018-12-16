package com.davidhjones.example;


import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ZipCodeRangeCollection {

	private final SortedSet<ZipCodeRange> zipRanges = new TreeSet<>();

	ZipCodeRangeCollection add(String lowerBound, String upperBound) {
		zipRanges.add(new ZipCodeRange(lowerBound, upperBound));
		return this;
	}

	ZipCodeRangeCollection add(ZipCodeRange zipCodeRange) {
		if (zipCodeRange != null) {
			zipRanges.add(zipCodeRange);
		}
		return this;
	}

	Set<ZipCodeRange> getAllRanges() {
		return new TreeSet<>(zipRanges);
	}


	Set<ZipCodeRange> mergeRanges() {

		if (zipRanges.isEmpty()) {
			return zipRanges;
		}

		SortedSet<ZipCodeRange> mergedRanges = new TreeSet<>();

		for (ZipCodeRange rangeToCheck : zipRanges) {

			// Nothing in our merge list yet, add the first range.
			if (mergedRanges.isEmpty()) {
				mergedRanges.add(rangeToCheck);
			}
			else {

				ZipCodeRange lastMerged = mergedRanges.last();

				if (lastMerged.isAdjacentToLower(rangeToCheck) || lastMerged.overlapsLowBound(rangeToCheck)) {
					mergedRanges.remove(lastMerged);
					mergedRanges.add(new ZipCodeRange(lastMerged.getLowerBound(), rangeToCheck.getUpperBound()));
				}
				else if (lastMerged.isLessThan(rangeToCheck)) {
					// The low bound of the range we're checking is above the upper bound of the
					// last merge range so we just append this range to the end of the merged ranges.
					mergedRanges.add(rangeToCheck);
				}

			}
		}

		return new TreeSet<>(mergedRanges);

	}

}

