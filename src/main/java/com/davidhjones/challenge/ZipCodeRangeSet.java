package com.davidhjones.challenge;


import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Represents a sorted set of {@link ZipCodeRange} items.
 * <p>
 * Allows ranges to be added to the set in any order and can return a new set of ranges,
 * merged down to the minimum number of ZipCodeRange items to still represent all the ranges.
 */
class ZipCodeRangeSet {

	/**
	 * Contains all the distinct {@link ZipCodeRange}, in order, that have been added to this set.
	 */
	private final SortedSet<ZipCodeRange> zipRanges = new TreeSet<>();


	/**
	 * Add a new {@link ZipCodeRange}to this set.
	 * <p>
	 * Duplicate ranges will be skipped.
	 * Calls to this method can be chained together.
	 *
	 * @param zipCodeRange one bound of the ZIP code range
	 * @return a reference to this ZIpCodeRangeSet
	 */
	ZipCodeRangeSet add(ZipCodeRange zipCodeRange) {
		if (zipCodeRange != null) {
			zipRanges.add(zipCodeRange);
		}
		return this;
	}


	/**
	 * Provides access to a copy of this set.
	 *
	 * @return a new TreeSet which is an independent copy of the internal zipRanges set.
	 */
	Set<ZipCodeRange> getAllRanges() {
		return new TreeSet<>(zipRanges);
	}


	/**
	 * Removes all items from this set's zipRanges.
	 */
	void clear() {
		zipRanges.clear();
	}


	/**
	 * Creates a single, ordered set of all {@link ZipCodeRange}, merging and collapsing down to the minimum
	 * number of ranges required to encompass all cases.
	 * <p>
	 * Basic rules:
	 * <ul>
	 * <li>Any range that is completely contained within another range will be discarded</li>
	 * <li>Any two ranges that are adjacent will be collapsed to one range with the lowest and highest bounds of the two</li>
	 * <li>Any two ranges that overlap will be collapsed to one range with the lowest and highest bounds of the two</li>
	 * <li>Any range that has nothing in common with another range will be included as is</li>
	 * </ul>
	 *
	 * @return A new {@code TreeSet<ZipCodeRange>} with the merged ranges in order.
	 */
	Set<ZipCodeRange> mergeRanges() {

		if (zipRanges.isEmpty()) {
			return zipRanges;
		}

		SortedSet<ZipCodeRange> mergedRanges = new TreeSet<>();

		// Build the new merged set by iterating over our overall zipRange set
		// Since the base zipRange set is ordered (based on how ZipCodeRange defines ordering), we know we're already
		// adding them in order. Knowing they are in order also simplifies the logic needed to determine if one range
		// contains the next, is adjacent to the next or overlaps the next range
		for (ZipCodeRange rangeToCheck : zipRanges) {

			// Nothing in our merge list yet, add the first range.
			if (mergedRanges.isEmpty()) {
				mergedRanges.add(rangeToCheck);
			}
			else {

				ZipCodeRange lastMerged = mergedRanges.last();

				// We really only have two cases to deal with here, we're either replacing the last merged range
				// with a new one that covers a span from two ranges, or we're adding a new, independent range.
				// If the range we're testing is the same as, or contained by the last merged range, then we can
				// just skip it and move on.
				if (lastMerged.isAdjacentToLower(rangeToCheck) || lastMerged.overlapsLowBound(rangeToCheck)) {
					mergedRanges.remove(lastMerged);
					// TODO: dave 2018-12-17 throws
					mergedRanges.add(new ZipCodeRange(lastMerged.getLowerBound(), rangeToCheck.getUpperBound()));
				}
				else if (lastMerged.isLessThan(rangeToCheck)) {
					mergedRanges.add(rangeToCheck);
				}

			}
		}

		return new TreeSet<>(mergedRanges);

	}

}

