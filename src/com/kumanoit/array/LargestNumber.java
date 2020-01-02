package com.kumanoit.array;

/**
 * https://leetcode.com/problems/largest-number/
 * 
 * Approach: Sort the number after comparing on the basis of value after
 * appending one after other. Example: if 2 numbers are 32 & 4, then create two
 * number after appending them. So, two numbers will be 324 and 432 
 * Since, 432 > 324, Hence keep 4 before 32
 * 
 * Complexity: 
 * Time: O(nlgn) for merge sort used here
 * Space: O(n) for creating temporary arrays
 * 
 * @author kumanoit 02-Jan-2020 8:13:01 PM
 */
public class LargestNumber {

	public static void main(String[] args) {
		test(new int[] { 3, 30, 34, 5, 9 }); // 9534330
		test(new int[] { 10, 2 }); // 210
		test(new int[] { 999999998, 999999997, 999999999 }); // 999999999999999998999999997
		test(new int[] { 0, 0, 0, 0, 1 }); // 10000
		test(new int[] { 0, 0, 0, 0 }); // 0
		test(new int[] { 0, 1, 2, 3, 4, 5 }); // 543210
		test(new int[] { 5, 4, 3, 2, 1, 0 }); // 543210

	}

	private static void test(final int[] nums) {
		System.out.println(largestNumber(nums));
	}

	public static String largestNumber(int[] nums) {
		mergeSort(nums, 0, nums.length - 1);
		StringBuilder solution = new StringBuilder();
		int i = 0;
		while (i < nums.length && nums[i] == 0) {
			i++;
		}
		if (i == nums.length) {
			return "0";
		}
		for (i = 0; i < nums.length; i++) {
			solution.append(nums[i]);
		}
		return solution.toString();
	}

	public static void mergeSort(int[] nums, int p, int r) {
		if (p < r) {
			int q = p + (r - p) / 2;
			mergeSort(nums, p, q);
			mergeSort(nums, q + 1, r);
			merge(nums, p, q, r);
		}
	}

	public static void merge(int[] nums, int p, int q, int r) {
		int m = q - p + 1;
		int n = r - q;
		int[] left = new int[m];
		int[] right = new int[n];

		for (int i = p, k = 0; i <= q; i++, k++) {
			left[k] = nums[i];
		}
		for (int i = q + 1, k = 0; i <= r; i++, k++) {
			right[k] = nums[i];
		}
		int i = 0;
		int j = 0;
		int k = p;
		while (i < m && j < n) {
			if (isBigger(left[i], right[j])) {
				nums[k++] = left[i++];
			} else {
				nums[k++] = right[j++];
			}
		}
		while (i < m) {
			nums[k++] = left[i++];
		}
		while (j < n) {
			nums[k++] = right[j++];
		}

	}

	private static boolean isBigger(int num1, int num2) {
		String num1num2 = num1 + "" + num2;
		String num2num1 = num2 + "" + num1;
		return (num1num2.compareTo(num2num1) > 0);
	}
}