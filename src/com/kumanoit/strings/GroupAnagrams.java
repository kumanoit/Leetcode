package com.kumanoit.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/group-anagrams/
 * 
 * @author kumanoit
 * 22-Dec-2019 9:01:58 AM
 */
public class GroupAnagrams {

	public static void main(String[] args) {
		groupAnagrams2(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }).forEach(item -> {
			item.forEach(x -> System.out.print(x + ", "));
			System.out.println();
		});
	}

	/**
	 * Approach: Put all the strings in hashmap. 
	 * Key: will be sorted representation of string, and 
	 * value: will be list of string having same key
	 * The solution will be set of all list in this hashmap
	 * @param strs
	 * @return
	 * 
	 * Time: O(nklogk) : k is length of string, n is total number of string
	 * Space: O(nk): size of map
	 * 
	 */
	public static List<List<String>> groupAnagrams1(String[] strs) {
		Map<String, List<String>> wordMap = new HashMap<String, List<String>>();
		for (int i = 0; i < strs.length; i++) {
			String sortedKey = getSortedKey(strs[i]);
			if (!wordMap.containsKey(sortedKey)) {
				wordMap.put(sortedKey, new ArrayList<>());
			}
			wordMap.get(sortedKey).add(strs[i]);
		}
		final List<List<String>> solution = new ArrayList<List<String>>();
		wordMap.forEach((key, value) -> solution.add(value));
		return solution;
	}

	private static String getSortedKey(String key) {
		char[] input = key.toCharArray();
		Arrays.sort(input);
		return String.valueOf(input);
	}

	/**
	 * Approach: Put all the strings in hashmap. 
	 * Key: will be count of all characters in string separated by $
	 * value: will be list of string having same count
	 * The solution will be set of all list in this hashmap
	 * @param strs
	 * @return
	 * 
	 * Time: O(nk): n is total string, and since each string is iterated
	 *  to generate key, total complexity will be O(nk)
	 * Space: O(nk): nk = Size of map, count array will be constant 26 size int array
	 * 
	 */
	public static List<List<String>> groupAnagrams2(String[] strs) {
		Map<String, List<String>> wordMap = new HashMap<String, List<String>>();
		for (int i = 0; i < strs.length; i++) {
			String countKey = getCountKey(strs[i]);
			if (!wordMap.containsKey(countKey)) {
				wordMap.put(countKey, new ArrayList<>());
			}
			wordMap.get(countKey).add(strs[i]);
		}
		final List<List<String>> solution = new ArrayList<List<String>>();
		wordMap.forEach((key, value) -> solution.add(value));
		return solution;
	}

	private static String getCountKey(String input) {
		int[] count = new int[26];
		StringBuilder countKey = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			count[input.charAt(i) - 'a']++;
		}
		for (int i = 0; i < count.length; i++) {
			countKey.append(count[i]).append("$");
		}
		return countKey.toString();
	}
}
