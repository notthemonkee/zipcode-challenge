package com.davidhjones.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ZipCodeRangeMerger {


	/**
	 * Creates a single, ordered string of ZIP code ranges from the passed String,
	 * merging and collapsing down to the minimum number of ranges required to encompass all cases.
	 *
	 * @param rawRanges string containing an arbitrary number ZIP code ranges
	 *                  comprised of sets of low and high bounds. e.g. [94133,94133] [94200,94299] [94600,94699]
	 * @return a String of ZIP code ranges condensed down to the smallest number required to encompass all cases
	 */
	Set<ZipCodeRange> mergeRanges(String rawRanges) {

		List<ZipCodeRange> parsedRanges = parseRanges(rawRanges);
		ZipCodeRangeSet zipCodeRangeSet = new ZipCodeRangeSet();

		for (ZipCodeRange range : parsedRanges) {
			zipCodeRangeSet.add(range);
		}

		return zipCodeRangeSet.mergeRanges();

	}


	/**
	 * Parses the supplied String into a List of {@link ZipCodeRange} items for each range found in the String.
	 * <p>
	 * ZIP ranges are expected to be in the format [94133,94133]. This method will extract anything like that,
	 * ignoring whitespace and any other characters in the string.
	 *
	 * @param input String of ZIP code ranges
	 * @return List of {@link ZipCodeRange} items for each range in the string
	 */
	private List<ZipCodeRange> parseRanges(String input) {
		String rangeRegEx = "\\[\\s*\\d\\d\\d\\d\\d\\s*,\\s*\\d\\d\\d\\d\\d\\s*\\]";

		List<ZipCodeRange> ranges = new ArrayList<>();

		Matcher matcher = Pattern.compile(rangeRegEx).matcher(input);

		while (matcher.find()) {
			// Remove brackets and white space from each match to get just zip1,zip2
			String[] zips = matcher.group().replaceAll("[\\[\\]\\s]", "").split(",");
			ranges.add(new ZipCodeRange(zips[0].trim(), zips[1].trim()));
		}

		return ranges;
	}
}
