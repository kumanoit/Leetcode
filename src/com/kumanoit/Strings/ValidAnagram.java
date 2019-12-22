package com.kumanoit.Strings;

/**
 * https://leetcode.com/problems/valid-anagram/
 * 
 * @author kumanoit
 * 22-Dec-2019 9:19:45 AM
 */
public class ValidAnagram {

	public static void main(String[] args) {
		test("acb", "abc");
		test("cab", "cabb");
		test("cabb", "cab");
		test("abc", "abd");
	}

	private static void test(String s, String t) {
		System.out.println(isAnagram(s, t));
	}

	/**
	 * Approach: If character count in both string differs then return fasle;
	 * else return true;
	 * @param s
	 * @param t
	 * @return
	 * 
	 * Time Complexity: O(n)
	 * Space: Constant space of size of array
	 * 
	 */
	public static boolean isAnagram(String s, String t) {
		if (s.length() < t.length()) {
			return false;
		}
		int[] count = new int[26];
		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			count[t.charAt(i) - 'a']--;
		}
		for (int i = 0; i < count.length; i++) {
			if (count[i] < 0) {
				return false;
			}
		}
		return true;
	}
}
