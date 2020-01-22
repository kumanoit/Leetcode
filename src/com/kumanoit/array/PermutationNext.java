package com.kumanoit.array;

import java.util.Arrays;

import com.kumanoit.utils.ArrayUtils;

/**
 * https://leetcode.com/problems/next-permutation/
 * 
 * Approach: Traverse from last index and look for an index such that its
 * previous value is smaller than its value. If such value doesn't exist then it
 * means that the given number is the biggest possible permutation hence, no
 * greater permutation exists. In case, if any value exists such that
 * array[index - 1] < array[index], this means that next permutation exists.
 * Now, we need to find out which number will take place of array[index - 1]? If
 * we think array like dictionary, we know that the next permutation will have
 * ceil value of array[index - 1] from index to length of array. Thus, we need
 * to find index location of ceil and swap it with [index - 1]. Once, we do that
 * we also need to sort number from index + 1....length of array to get final
 * solution.
 * 
 * @author kumanoit 22-Jan-2020 10:04:00 PM
 */
public class PermutationNext {

	public static void main(String[] args) {
		test(new Integer[] { 1 }); // 1
		test(new Integer[] { 1, 2 }); // 2, 1
		test(new Integer[] { 2, 1 }); // 1, 2
		test(new Integer[] { 1, 2, 3 }); // 1, 3, 2
		test(new Integer[] { 3, 2, 1 }); // 1, 2, 3
		test(new Integer[] { 1, 2, 2, 2, 2, 2, 3 }); // 1,2,2,2,2,3,2
		test(new Integer[] { 1, 4, 7, 7, 7, 7, 6, 6, 6, 5, 4, 3, 2, 1 }); // 1,5,1,2,3,4,4,6,6,6,7,7,7,7
	}

	private static void test(final Integer[] nums) {
		ArrayUtils.print(nums);
		nextPermutation(nums);
		ArrayUtils.print(nums);
		System.out.println();
	}

	public static void nextPermutation(final Integer[] nums) {
		if (nums.length < 2) {
			return;
		}
		int end = nums.length - 1;
		while (end > 0 && nums[end] <= nums[end - 1]) {
			end--;
		}
		if (end == 0) {
			Arrays.sort(nums);
			return;
		}
		int ceilIndex = end;
		int swapIndex = end - 1;
		for (int i = end + 1; i < nums.length; i++) {
			if (nums[swapIndex] < nums[i] && nums[ceilIndex] > nums[i]) {
				ceilIndex = i;
			}
		}
		int temp = nums[ceilIndex];
		nums[ceilIndex] = nums[swapIndex];
		nums[swapIndex] = temp;
		Arrays.sort(nums, swapIndex + 1, nums.length);
	}
}
