package com.kumanoit.dynamicprogramming;

public class JumptoLastIndexArrayMinimumSteps {

	public static void main(String[] args) {
		test(new int[] { 2, 3, 1, 1, 4 });
		test(new int[] { 1, 1, 1, 1, 1 });
		test(new int[] { 1 });
		test(new int[] { 1, 2, 3, 4, 5 });
	}

	private static void test(int[] nums) {
		long startTime = System.currentTimeMillis();
		System.out.println("Brute force m = " + getMinimumJumpsBF(nums, 0, nums.length - 1));
		long midTime = System.currentTimeMillis();
		System.out.println("Dynamic Programming   = " + getMinimumJumpsDP(nums));
		long endTime = System.currentTimeMillis();
		System.out.println("Dynamic Programming 2 = " + getMinimumJumpsDP2(nums));
		long endTime2 = System.currentTimeMillis();
		System.out.println("Time taken by BF : " + (midTime - startTime));
		System.out.println("Time taken by DP : " + (endTime - midTime));
		System.out.println("Time taken by DP2 : " + (endTime2 - endTime));
		System.out.println();
	}

	private static int getMinimumJumpsBF(int[] nums, int start, int end) {
		if (start >= end) {
			return 0;
		}

		int minJumps = Integer.MAX_VALUE;
		for (int i = 1; i <= nums[start]; i++) {
			int localMinJumps = getMinimumJumpsBF(nums, start + i, end);
			minJumps = Math.min(minJumps, localMinJumps);
		}
		return minJumps + 1;
	}

	private static int getMinimumJumpsDP(int[] nums) {
		int[] minJumps = new int[nums.length];
		for (int i = 0; i < minJumps.length; i++) {
			minJumps[i] = nums.length + 2;
		}
		minJumps[0] = 0;
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (j + nums[j] >= i) {
					minJumps[i] = Math.min(minJumps[i], minJumps[j] + 1);
				}
			}
		}
		return minJumps[nums.length - 1];
	}

	private static int getMinimumJumpsDP2(int[] nums) {
		int[] minJumps = new int[nums.length];
		for (int i = 0; i < minJumps.length; i++) {
			minJumps[i] = nums.length + 2;
		}
		minJumps[0] = 0;
		int lastIndexVisitable = 0;
		for (int i = 0; i < nums.length && lastIndexVisitable < nums.length; i++) {
			int newIndexVisitable = i + nums[i];
			for (int j = lastIndexVisitable + 1; j <= newIndexVisitable && j < nums.length; j++) {
				minJumps[j] = Math.min(minJumps[j], minJumps[i] + 1);
			}
			lastIndexVisitable = newIndexVisitable;
		}
		return minJumps[nums.length - 1];
	}

}
