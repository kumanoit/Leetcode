package com.kumanoit.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-i/
 * 
 * 496. Next Greater Element I
 * 
 * Approach: 1. Brute force: Iterate over first array and look for its greater
 * element in second array
 * 
 * Complexity:
 * Time: O(n^2)
 * Space: O(1)
 *
 * Approach 2. Iterate over second array from end and push element on stack. For
 * the last element there is no number greater than the last number on its
 * right. For any number check stack. Pop element from stack until there is a
 * number greater than the current number on stack. In case, stack gets over,
 * there is no greater number on right. Also, create a map to keep solution of
 * each number. This will be helpful for the second phase when you will iterate
 * over first array and ask from map to get its solution.
 * 
 * Complexity: 
 * Time: O(n): The array has been traversed only once and each
 * number got on stack only once. 
 * Space: O(n) for map and stack.
 * 
 * @author kumanoit May 24, 2020 1:21:54 AM
 *
 */
public class NextGreaterElement1 {

	public static void main(String[] args) {
		test(new int[] {}, new int[] {});
		test(new int[] { 2, 4 }, new int[] { 1, 2, 3, 4 });
		test(new int[] { 1, 2, 3, 4 }, new int[] { 1, 2, 3, 4 });
		test(new int[] { 1, 2, 3, 4 }, new int[] { 4, 3, 2, 1 });
		test(new int[] { 4, 1, 2, 5 }, new int[] { 1, 3, 4, 2 });
	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}

	private static void test(final int[] nums1, final int[] nums2) {
		printArray(nextGreaterElementBruteForce(nums1, nums2));
		printArray(nextGreaterElement(nums1, nums2));
	}

	private static int[] nextGreaterElementBruteForce(final int[] nums1, final int[] nums2) {
		int[] result = new int[nums1.length];
		for (int i = 0; i < nums1.length; i++) {
			result[i] = -1;
			boolean found = false;
			for (int j = 0; j < nums2.length; j++) {
				if (!found) {
					found = nums2[j] == nums1[i];
				} else {
					if (nums2[j] > nums1[i]) {
						result[i] = nums2[j];
						break;
					}
				}
			}
		}
		return result;
	}

	private static int[] nextGreaterElement(int[] nums1, int[] nums2) {
		if (nums1.length == 0 || nums2.length == 0) {
			return new int[] {};
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Stack<Integer> stack = new Stack<>();
		map.put(nums2[nums2.length - 1], -1);
		stack.push(nums2[nums2.length - 1]);
		for (int i = nums2.length - 2; i >= 0; i--) {
			while (!stack.isEmpty() && stack.peek() < nums2[i]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				map.put(nums2[i], -1);
			} else {
				map.put(nums2[i], stack.peek());
			}
			stack.push(nums2[i]);
		}
		int[] result = new int[nums1.length];
		for (int i = 0; i < nums1.length; i++) {
			result[i] = map.getOrDefault(nums1[i], -1);
		}
		return result;
	}
}
