package com.kumanoit.strings;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * 
 * @author kumanoit
 * 19-Dec-2019 12:43:41 AM
 */
public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		test("a");
		test("abc");
		test("abababa");
		test("ccccabba");
		test("");
		test("cbbd");
	}

	private static void test(String input) {
		System.out.println(longestPalindromeDP(input));
	}

	public static String longestPalindromeDP(String text) {
		if (text.length() < 2) {
			return text;
		}
		boolean[][] isPalindrome = new boolean[text.length()][text.length()];
		int maxLength = 1;
		int start = 0;
		for (int i = 0; i < text.length(); i++) {
			isPalindrome[i][i] = true;
		}
		for (int i = 0; i < text.length() - 1; i++) {
			isPalindrome[i][i + 1] = text.charAt(i) == text.charAt(i + 1);
			if (isPalindrome[i][i + 1] && maxLength < 2) {
				start = i;
				maxLength = 2;
			}
		}
		for (int length = 3; length <= text.length(); length++) {
			for (int i = 0; i < text.length() - length + 1; i++) {
				int j = i + length - 1;
				isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && text.charAt(i) == text.charAt(j);
				if (isPalindrome[i][j] && maxLength < length) {
					maxLength = length;
					start = i;
				}
			}
		}
		return text.substring(start, start + maxLength);
	}
}
