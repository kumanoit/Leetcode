package com.kumanoit.Strings;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 * 
 * @author kumanoit 26-Dec-2019 11:57:24 PM
 */
public class ReverseVowelsInString {

	public static void main(String[] args) {
		test("hello");
		test("leetcode");
		test("a");
		test("aeiou");
		test("");
	}

	private static void test(String input) {
		System.out.println("Original string: " + input);
		System.out.println("After reversing vowels in string: " + reverseVowels(input));
		System.out.println();
	}

	public static String reverseVowels(String s) {
		char[] characters = s.toCharArray();
		int start = 0;
		int end = s.length() - 1;
		while (start < end) {
			if (!isVowel(characters[start])) {
				start++;
			} else if (!isVowel(characters[end])) {
				end--;
			} else {
				char temp = characters[start];
				characters[start] = characters[end];
				characters[end] = temp;
				start++;
				end--;
			}
		}
		return new String(characters);
	}

	private static boolean isVowel(char ch) {
		switch (ch) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
		case 'A':
		case 'E':
		case 'I':
		case 'O':
		case 'U':
			return true;
		default:
			return false;
		}
	}
}
