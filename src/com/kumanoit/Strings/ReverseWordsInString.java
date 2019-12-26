package com.kumanoit.Strings;

import java.util.Stack;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/
 * 
 * @author kumanoit 27-Dec-2019 12:14:00 AM
 */
public class ReverseWordsInString {

	public static void main(String[] args) {
		test("the sky is blue");
		test("  hello world!  ");
		test("  a  ");
		test("  hello world!  How are you doing ");
	}

	private static void test(final String input) {
		System.out.println("Input string " + input);
		System.out.println("Reversing_1 : " + reverseWords1(input));
		System.out.println("Reversing_2 : " + reverseWords2(input));
		System.out.println("Reversing_3 : " + reverseWords3(input));
		System.out.println();
	}

	public static String reverseWords3(String s) {
		String[] words = s.split("\\s+");
		for (int start = 0, end = words.length - 1; start < end; start++, end--) {
			String temp = words[start];
			words[start] = words[end];
			words[end] = temp;
		}
		return String.join(" ", words).trim();
	}

	public static String reverseWords2(String s) {
		String[] words = s.split("\\s+");
		for (int start = 0, end = words.length - 1; start < end; start++, end--) {
			String temp = words[start];
			words[start] = words[end];
			words[end] = temp;
		}
		StringBuilder solution = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			solution.append(words[i]).append(" ");
		}
		return solution.toString().trim();
	}

	public static String reverseWords1(String s) {
		String[] words = s.split("\\s+");
		Stack<String> stack = new Stack<String>();
		for (String word : words) {
			stack.push(word);
		}
		StringBuilder solution = new StringBuilder();
		while (!stack.isEmpty()) {
			solution.append(stack.pop()).append(" ");
		}
		return solution.toString().trim();
	}
}
