package com.kumanoit.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets
 * 
 * Approach: Create a list with adding an element and one without adding element
 * in recursive manner.
 * 
 * @author kumanoit 08-Jan-2020 12:25:24 AM
 */
public class SubsetWithUniqueNumber {

	public static void main(String[] args) {
		test(new int[] {});
		test(new int[] { 1 });
		test(new int[] { 1, 2 });
	}

	private static void test(int[] nums) {
		subsets(nums).forEach(item -> {
			item.forEach(element -> System.out.print(element + ", "));
			System.out.println();
		});
		;
	}

	private static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		generate(nums, new ArrayList<Integer>(), solution, 0);
		return solution;
	}

	private static void generate(int[] nums, List<Integer> subList,
			List<List<Integer>> solution, int index) {
		if (index == nums.length) {
			solution.add(new ArrayList<Integer>(subList));
			return;
		}
		generate(nums, subList, solution, index + 1);
		subList.add(nums[index]);
		generate(nums, subList, solution, index + 1);
		subList.remove(subList.size() - 1);
	}
}
