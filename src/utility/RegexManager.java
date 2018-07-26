package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexManager {

	public static String removeMatchingPattern(String content, String regexExp) {
		return content.replaceAll(regexExp, "");
	}

	public static List<String> getMatchingString(String content, String regex) {

		List<String> result = new ArrayList<>();

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);

		while (matcher.find()) {
			result.add(content.substring(matcher.start(), matcher.end()));
		}

		return result;
	}

}
