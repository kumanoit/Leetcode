package com.kumanoit.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * 
 * Approach: First find all palindromes in given string by below rule
 * String[x,y] is palindrome only if String[x+1,y-1] is palindrome and 
 * String[x] = String[y]
 * 
 * The trick is to get all substring which are palindromes. For this do a
 * recursive call keeping a note of current palindrome string.
 * 
 * @author kumanoit 25-Feb-2020 10:04:53 PM
 */
public class PalindromicSubstring {

	private static List<List<String>> solution = new ArrayList<List<String>>();

	public static void main(String[] args) {
		test("aaba");
		solution.forEach(subSolution -> {
			subSolution.forEach(item -> System.out.print(item + ", "));
			System.out.println();
		});
	}

	private static void test(String input) {
		partition(input);
	}

	public static void partition(String s) {
		int size = s.length();
		boolean isPalindrome[][] = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			isPalindrome[i][i] = true;
		}
		for (int i = 0; i < size - 1; i++) {
			isPalindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
		}
		for (int length = 3; length <= size; length++) {
			for (int start = 0; start < size - length + 1; start++) {
				int end = start + length - 1;
				if (isPalindrome[start + 1][end - 1]) {
					isPalindrome[start][end] = s.charAt(start) == s.charAt(end);
				}
			}
		}
		getAllPalindrome(s, isPalindrome, 0, size - 1, new ArrayList<>());
	}

	private static void getAllPalindrome(String s, boolean[][] isPalindrome, int start, int end,
			List<String> sol) {
		if (start > end) {
			solution.add(new ArrayList<>(sol));
			return;
		}
		for (int i = start; i <= end; i++) {
			if (isPalindrome[start][i]) {
				sol.add(s.substring(start, i + 1));
				getAllPalindrome(s, isPalindrome, i + 1, end, sol);
				sol.remove(sol.size() - 1);
			}
		}
	}
}