package com.kumanoit.array;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 * 
 * @author kumanoit
 * 20-Dec-2019 10:09:01 PM
 */
public class ProductArrayExceptSelf {

	public static void main(String[] args) {
		int[] nums = { 2, 4, 6, 3 };
		printArray(productExceptSelf(nums));
		printArray(productExceptSelfBruteForce(nums));
		printArray(productExceptUsing2Arrays(nums));
	}

	private static void printArray(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}

	/**
	 * This approach has constant space (excluding the returned array)
	 * Space: O(1)
	 * Time: O(n)
	 * @param nums
	 * @return
	 */
	public static int[] productExceptSelf(int[] nums) {
		if (nums.length == 0) {
			return nums;
		}
		int[] solution = new int[nums.length];
		solution[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			solution[i] = solution[i - 1] * nums[i - 1];
		}
		int temp = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			solution[i] = solution[i] * temp;
			temp = temp * nums[i];
		}
		return solution;
	}

	/**
	 * This approach uses a single array (excluding the returned array)
	 * Space: O(n)
	 * Time: O(n)
	 * @param nums
	 * @return
	 */
	public static int[] productExceptUsing2Arrays(int[] nums) {
		if (nums.length == 0) {
			return nums;
		}
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];
		left[0] = 1;
		right[nums.length - 1] = 1;
		for (int i = 1; i < nums.length; i++) {
			left[i] = left[i - 1] * nums[i - 1];
		}
		for (int i = nums.length - 2; i >= 0; i--) {
			right[i] = right[i + 1] * nums[i + 1];
		}
		for (int i = 0; i < nums.length; i++) {
			right[i] *= left[i];
		}
		return right;
	}

	/**
	 * This approach uses three arrays (excluding the returned array)
	 * Space: O(n)
	 * Time: O(n)
	 * @param nums
	 * @return
	 */
	public static int[] productExceptSelfBruteForce(int[] nums) {
		int[] left = new int[nums.length];
		left[0] = 1;
		int[] right = new int[nums.length];
		right[nums.length - 1] = 1;
		for (int i = 1; i < left.length; i++) {
			left[i] = left[i - 1] * nums[i - 1];
		}

		for (int i = right.length - 2; i >= 0; i--) {
			right[i] = right[i + 1] * nums[i + 1];
		}

		int[] product = new int[nums.length];
		for (int i = 0; i < product.length; i++) {
			product[i] = left[i] * right[i];
		}
		return product;
	}
}
