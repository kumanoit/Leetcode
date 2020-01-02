package com.kumanoit.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-and-replace-pattern/
 * 
 * Approach: Create a map of mapping from characters from word to pattern.
 * 
 * @author kumanoit 02-Jan-2020 11:49:56 PM
 */
public class FindAndReplacePattern {

	public static void main(String[] args) {
		test(new String[] { "abc", "deq", "mee", "aqq", "dkd", "ccc" }, "abb");// mee, aqq,
		test(new String[] { "abb" }, "abc"); // no solution
		test(new String[] { "abc" }, "abb"); // no solution
		test(new String[] { "a", "b", "c", "d", "e", "f", "g" }, "a"); // a, b, c, d, e, f, g
	}

	private static void test(final String[] words, final String pattern) {
		System.out.println();
		findAndReplacePattern(words, pattern).forEach(solution -> System.out.print(solution + ", "));
	}

	public static List<String> findAndReplacePattern(String[] words, String pattern) {
		List<String> solution = new ArrayList<String>();
		for (String word : words) {
			if (isMatchPattern(word, pattern)) {
				solution.add(word);
			}
		}
		return solution;
	}

	private static boolean isMatchPattern(String word, String pattern) {
		if (word.length() != pattern.length()) {
			return false;
		}
		Set<Character> used = new HashSet<Character>();
		Map<Character, Character> map = new HashMap<>();
		for (int i = 0; i < word.length(); i++) {
			if (map.keySet().contains(word.charAt(i))) {
				if (map.get(word.charAt(i)) != pattern.charAt(i)) {
					return false;
				}
			} else if (used.contains(pattern.charAt(i))) {
				return false;
			} else {
				map.put(word.charAt(i), pattern.charAt(i));
				used.add(pattern.charAt(i));
			}
		}
		return true;
	}
}
