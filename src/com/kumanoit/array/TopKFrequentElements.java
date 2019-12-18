package com.kumanoit.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/top-k-frequent-words/solution/
 * 
 * @author kumanoit 18-Dec-2019 10:40:18 PM
 */
public class TopKFrequentElements {

	public static void main(String[] args) {
		test(new int[] { 1, 1, 1, 2, 2, 2, 3 }, 1);
		test(new int[] { 1, 1, 1, 2, 2, 2, 3 }, 2);
		test(new int[] { 1, 1, 1, 2, 2, 2, 3 }, 3);
		test(new int[] { 4, 1, -1, 2, -1, 2, 3 }, 2);
	}

	private static void test(int[] words, int k) {
		System.out.println(topKFrequentElement(words, k));
		System.out.println(topKFrequentElement(words, k));
	}

	public static List<Integer> topKFrequentElement(int[] nums, int k) {
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		for (int num : nums) {
			countMap.put(num, countMap.getOrDefault(num, 0) + 1);
		}
		List<Integer> solution = new ArrayList<Integer>(countMap.keySet());
		Collections.sort(solution, 
				(num1, num2) -> 
					countMap.get(num1).equals(countMap.get(num2)) ? 
						num1.compareTo(num2) : 
						countMap.get(num2) - countMap.get(num1)
					);
		solution = solution.subList(0, k);
		List<Integer> result = new ArrayList<Integer>();
		for (Integer number : solution) {
			result.add(Integer.valueOf(number));
		}
		return result;
	}
}
