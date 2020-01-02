package com.kumanoit.Strings;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * 
 * @author kumanoit 03-Jan-2020 12:07:48 AM
 */
public class LongestCommonSubsequence {

	public static void main(String[] args) {
		test("abcde", "ace"); // 3
		test("a", "a"); // 1
		test("abc", "abc"); // 3
		test("abcdef", "fedcba"); // 1
	}

	private static void test(final String text1, final String text2) {
		System.out.println(longestCommonSubsequence(text1, text2));
	}

	public static int longestCommonSubsequence(String text1, String text2) {
		int[][] memo = new int[text1.length() + 1][text2.length() + 1];
		for (int i = 1; i <= text1.length(); i++) {
			for (int j = 1; j <= text2.length(); j++) {
				if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					memo[i][j] = memo[i - 1][j - 1] + 1;
				} else {
					memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
				}
			}
		}
		return memo[text1.length()][text2.length()];
	}
}
