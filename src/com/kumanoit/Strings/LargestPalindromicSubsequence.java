package com.kumanoit.Strings;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/submissions/
 * 
 * Approach: Find the length of longest common subsequence between current
 * string and its reverse.
 * 
 * @author kumanoit 03-Jan-2020 12:40:52 AM
 */
public class LargestPalindromicSubsequence {

	public static void main(String[] args) {
		test("a"); // 1
		test("bbbab"); // 4
		test("mdeaflghiyjaklmnlam"); // 7
		test("kbdfdanaouophyk"); // 5
		test("cbbd"); // 2
	}

	private static void test(final String input) {
		System.out.println(longestPalindromeSubseq(input));
	}

	public static int longestPalindromeSubseq(String s) {
		return longestCommonSubsequence(s, reverse(s));
	}

	private static String reverse(String s) {
		char[] reversedString = new char[s.length()];
		for (int i = 0, j = s.length() - 1; i < s.length(); i++, j--) {
			reversedString[i] = s.charAt(j);
		}
		return new String(reversedString);
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