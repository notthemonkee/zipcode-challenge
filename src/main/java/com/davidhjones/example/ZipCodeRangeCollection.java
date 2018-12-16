package com.davidhjones.example;


import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ZipCodeRangeCollection {


	private final SortedSet<ZipCodeRange> zipRanges = new TreeSet<>();

	public ZipCodeRangeCollection add(String lowerBound, String upperBound) {
		zipRanges.add(new ZipCodeRange(lowerBound, upperBound));
		return this;
	}

	public ZipCodeRangeCollection add(ZipCodeRange zipCodeRange) {
		if (zipCodeRange != null) {
			zipRanges.add(zipCodeRange);
		}
		return this;
	}

	public Set<ZipCodeRange> getRanges() {
		return Collections.unmodifiableSortedSet(zipRanges);
	}

	public Set<ZipCodeRange> mergeRanges() {

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
					// TODO: dave 2018-12-15 maybe change so we're just setting the upper bound of the last one instead of
					// creating a new one.
					mergedRanges.add(new ZipCodeRange(lastMerged.getLowerBound(), rangeToCheck.getUpperBound()));
					mergedRanges.remove(lastMerged);
				}
				else if (lastMerged.isLessThan(rangeToCheck)) {
					// The low bound of the range we're checking is above the upper bound of the
					// last merge range so we just append this range to the end of the merged ranges.
					mergedRanges.add(rangeToCheck);
				}

			}
		}

		return Collections.unmodifiableSortedSet(mergedRanges);

	}


}

