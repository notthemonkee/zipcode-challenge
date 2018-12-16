package com.davidhjones.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZipCodeRangeMerger {

	Set<ZipCodeRange> mergeRanges(String rawRanges) {

		List<ZipCodeRange> parsedRanges = parseRanges(rawRanges);
		ZipCodeRangeSet zipCodeRangeSet = new ZipCodeRangeSet();

		for (ZipCodeRange range : parsedRanges) {
			zipCodeRangeSet.add(range);
		}

		return zipCodeRangeSet.mergeRanges();

	}


	private List<ZipCodeRange> parseRanges(String input) {
		String rangeRegEx = "\\[\\s?\\d\\d\\d\\d\\d\\s?,\\s?\\d\\d\\d\\d\\d\\s?\\]";

		List<ZipCodeRange> ranges = new ArrayList<>();

		Matcher matcher = Pattern.compile(rangeRegEx).matcher(input);

		while (matcher.find()) {
			String[] zips = matcher.group().replaceAll("[\\[\\]]", "").split(",");
			ranges.add(new ZipCodeRange(zips[0], zips[1]));
		}

		return ranges;
	}
}
