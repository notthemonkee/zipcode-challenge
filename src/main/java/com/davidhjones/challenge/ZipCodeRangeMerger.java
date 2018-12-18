package com.davidhjones.challenge;

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
	String mergeRanges(String rawRanges) {

		if (rawRanges == null || rawRanges.length() == 0) {
			return "";
		}

		List<ZipCodeRange> parsedRanges = parseRanges(rawRanges);
		ZipCodeRangeSet zipCodeRangeSet = new ZipCodeRangeSet();

		for (ZipCodeRange range : parsedRanges) {
			zipCodeRangeSet.add(range);
		}

		Set<ZipCodeRange> rangeSet = zipCodeRangeSet.mergeRanges();

		return buildRangeString(rangeSet);

	}


	/**
	 * Parses the supplied String into a List of {@link ZipCodeRange} items for each range found in the String.
	 * <p>
	 * ZIP ranges are expected to be in the format [94133,94133]. This method will extract anything like that,
	 * ignoring whitespace and any other characters in the string.
	 * <p>
	 * NOTE: This parsing routine will just skip invalid ranges, i.e. those that aren't a pair of five digit sequences.
	 * We may want to change this to throw an exception so we are aware of bad data.
	 *
	 * @param input String of ZIP code ranges
	 * @return List of {@link ZipCodeRange} items for each range in the string
	 */
	private List<ZipCodeRange> parseRanges(String input) {

		List<ZipCodeRange> ranges = new ArrayList<>();

		if (input == null) {
			return ranges;
		}

		// pattern is [<five digits>,<five digits>] with whitespace allowed between brackets and digits and
		// between digits and comma.
		String rangeRegEx = "\\[\\s*\\d\\d\\d\\d\\d\\s*,\\s*\\d\\d\\d\\d\\d\\s*]";
		Matcher matcher = Pattern.compile(rangeRegEx).matcher(input);

		while (matcher.find()) {

			// Remove brackets and white space from each match to get just zip1,zip2
			String[] zips = matcher.group().replaceAll("[\\[\\]\\s]", "").split(",");

			try {
				ranges.add(new ZipCodeRange(zips[0].trim(), zips[1].trim()));
			} catch (IllegalArgumentException e) {
				/*
					NOTE: Simply writing a message to the console if we get invalid ZIPs when constructing this
					ZipCodeRange. That shouldn't happen here since the regular expression is pulling only valid ZIPs.
					We may want to improve on this error handling.
				 */
				System.out.println("Invalid ZIP code passed: [" + zips[0].trim() + ", " + zips[1].trim() + "]" + e.getMessage());
			}

		}

		return ranges;
	}


	private String buildRangeString(Set<ZipCodeRange> rangeSet) {

		if (rangeSet == null || rangeSet.size() == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder(rangeSet.size());

		for (ZipCodeRange range : rangeSet) {
			sb.append(range).append(" ");
		}

		return sb.toString().trim();

	}
}
