package com.kumanoit.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-common-characters/
 * 
 * 
 * 
 * @author kumanoit 24-Dec-2019 11:07:47 AM
 */
public class FindCommonCharacters {

	public static void main(String[] args) {
		test(new String[] { "bella", "label", "roller" });
		test(new String[] { "bella", "label" });
		test(new String[] { "bella" });
	}

	private static void test(final String[] A) {
		commonChars(A).forEach(item -> {
			System.out.print(item + ", ");
		});
		System.out.println();
		commonChars1(A).forEach(item -> {
			System.out.print(item + ", ");
		});
		System.out.println();
	}

	/**
	 * Approach: For each string maintain count of character in n * 26 size matrix.
	 * At the end see the minimum count in each column and add that many character
	 * in output string. 
	 * n = total string in array and k is average string length
	 * TimeComplexity: O(nk) 
	 * SpaceComplexity: O(n*26)
	 * 
	 * @param A
	 * @return
	 */
	public static List<String> commonChars(final String[] A) {
		if (A.length == 0) {
			return null;
		}
		int[][] count = new int[A.length][26];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length(); j++) {
				count[i][A[i].charAt(j) - 'a']++;
			}
		}
		List<String> answer = new ArrayList<>();
		for (int j = 0; j < 26; j++) {
			int min = count[0][j];
			for (int i = 1; i < count.length; i++) {
				min = Math.min(min, count[i][j]);
			}
			while (min > 0) {
				answer.add((char) (j + 'a') + "");
				min--;
			}
		}
		return answer;
	}

	/**
	 * Approach: Initialize a minCount array with maxInteger value. This will have
	 * minimum count for each characters for all string. For each string count the
	 * frequency of characters. Every time after finding frequency, update minCount
	 * array which will have minimum value for character now. After looping over all
	 * strings, find the min character count from minCount array and use it
	 * construct solution.
	 * 
	 * Time Complexity: O(nk) + O(n * 26)
	 * Space: O(26)
	 * 
	 * @param A
	 * @return
	 */
	public static List<String> commonChars1(final String[] A) {
		int[] minCount = new int[26];
		Arrays.fill(minCount, Integer.MAX_VALUE);
		for (int i = 0; i < A.length; i++) {
			int[] min = new int[26];
			for (int j = 0; j < A[i].length(); j++) {
				min[A[i].charAt(j) - 'a']++;
			}
			for (int k = 0; k < minCount.length; k++) {
				minCount[k] = Math.min(minCount[k], min[k]);
			}
		}
		List<String> solution = new ArrayList<String>();
		for (int i = 0; i < minCount.length; i++) {
			if (minCount[i] < Integer.MAX_VALUE) {
				while (minCount[i] > 0) {
					solution.add((char) (i + 'a') + "");
					minCount[i]--;
				}
			}
		}
		return solution;
	}
}
