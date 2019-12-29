package com.kumanoit.array;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/
 * 
 * @author kumanoit
 * 29-Dec-2019 11:54:06 AM
 */
public class MaximumCandies {

	public static void main(String[] args) {
		test(	
				new int[] {1,0,1,0},
				new int[] {7,5,4,100},
				new int[][] {
					{},
					{},
					{1},
					{}
				},
				new int[][] {
					{1,2},
					{3},
					{},
					{}
				},
				new int[] {0}
			); // 16

		test(new int[] {1, 0, 0, 0, 0, 0},
				new int[] {1, 1, 1, 1, 1, 1},
				new int[][] {
					{1, 2, 3, 4, 5}, 
					{},
					{},
					{},
					{},
					{}
				}, 
				new int[][] {
					{1, 2, 3, 4, 5},
					{},
					{},
					{},
					{},
					{}
				}, 
				new int[] {0}
			); // 6

		test(new int[] {1, 1, 1},
				new int[] {100, 1, 100},
				new int[][] {
					{},
					{0, 2},
					{}},
				new int[][] {
					{},
					{},
					{}
				},
				new int[] {1}
			); // 1

		test(new int[] {1}, 
				new int[] {100}, 
				new int[][] {
					{}
				}, 
				new int[][] {
					{}
				}, 
				new int[] {}
			); // 0

		test(new int[] {1, 1, 1}, 
				new int[] {2, 3, 2}, 
				new int[][] {
					{},
					{},
					{}
				}, 
				new int[][] {
					{},
					{},
					{}
				}, 
				new int[] {2, 1, 0}
			); // 7
	}

	private static void test(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
		System.out.println(maxCandies(status, candies, keys, containedBoxes, initialBoxes));
	}

	public static int maxCandies(int[] status, 
			int[] candies, 
			int[][] keys, 
			int[][] containedBoxes, 
			int[] initialBoxes) {
		Set<Integer> availableKeys = new HashSet<Integer>();
		Queue<Integer> boxQueue = new LinkedList<Integer>();
		for (int i = 0; i < initialBoxes.length; i++) {
			boxQueue.add(initialBoxes[i]);
		}
		Set<Integer> availableLockedBoxes = new HashSet<Integer>();
		int totalCandies = 0;
		while (!boxQueue.isEmpty()) {
			int box = boxQueue.remove();
			totalCandies += candies[box];
			int[] obtainedKeys = keys[box];
			for (int key : obtainedKeys) {
				availableKeys.add(key);
				if (availableLockedBoxes.contains(key)) {
					boxQueue.add(key);
					availableLockedBoxes.remove(key);
				}
			}
			int[] childBoxes = containedBoxes[box];
			for (int cb : childBoxes) {
				if (availableKeys.contains(cb) || status[cb] == 1) {
					boxQueue.add(cb);
				} else {
					availableLockedBoxes.add(cb);
				}
			}
		}
		return totalCandies;
	}
}
