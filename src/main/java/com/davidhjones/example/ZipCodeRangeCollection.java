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

				// TODO: dave 2018-12-15 not sure if I need this case yet
				//				if (rangeToCheck.getLowerBound().equals(rangeToCheck.getUpperBound())) {
				//					// TODO: dave 2018-12-15 fill in this case
				//
				//				}
				//				else {


				//				if (lastMerged.contains(rangeToCheck)) {
				//
				//					// TODO: dave 2018-12-15 if isLessThan and overlapsLowBound both exclude "contains"
				//					// then we don't have to do the contains check. and therefore can remove the contains method.
				//
				//					// The zip range we're checking is completely within the last merge range so we can skip it and move on.
				//					continue;
				//				}
				//				else
				if (lastMerged.isAdjacentLower(rangeToCheck) || lastMerged.overlapsLowBound(rangeToCheck)) {


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
				//				else if (lastMerged.overlapsLowBound(rangeToCheck)) {
				//					// The low bound of the range we're checking is straddled by the last merged range,
				//					// but is not fully contained within. In this case, we update the last merged range's
				//					// upper bound to the upper bound of the range we're checking.
				//
				//					// TODO: dave 2018-12-15 maybe change so we're just setting the upper bound of the last one instead of
				//					// creating a new one.
				//					mergedRanges.add(new ZipCodeRange(lastMerged.getLowerBound(), rangeToCheck.getUpperBound()));
				//					mergedRanges.remove(lastMerged);
				//				}

				//}

			}
		}


		return Collections.unmodifiableSortedSet(mergedRanges);

	}


}

