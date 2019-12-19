package com.kumanoit.array;

import java.util.Stack;

/**
 * https://leetcode.com/problems/trapping-rain-water/submissions/
 * 
 * @author kumanoit 19-Dec-2019 10:33:04 PM
 */
public class TrappingRainWater {

	public static void main(String[] args) {
		test(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 });
		test(new int[] { 2, 0, 2 });
		test(new int[] { 3, 0, 0, 2, 0, 4 });
		test(new int[] { 1, 2, 3, 4, 5, 6 });
		test(new int[] { 6, 5, 4, 3, 2, 1 });
		test(new int[] { 1, 2, 3, 4, 3, 2, 1 });
	}

	private static void test(int[] height) {
		System.out.println("Brute Force " + trapBruteForce(height));
		System.out.println("Optimized   " + trapOptimized(height));
		System.out.println("Best        " + trapWaterBest(height));
	}

	/**
	 * This approach uses a stack and keeps a track of the highest pillar on left.
	 * So, we keep pushing element on stack as long as top of stack is higher than
	 * current index. When a new pillar is higher than top of stack it means that
	 * water can collect over the topmost pillar of stack. Hence, we pop top and
	 * compute how much water can collect at that level between the pillar which is
	 * on top after popping and the current pillar.
	 * 
	 * Time complexity : O(n); Space complexity : O(n) // stack
	 * 
	 * @param height
	 * @return
	 */
	private static int trapWaterBest(int[] height) {
		int solution = 0;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		for (int i = 1; i < height.length; i++) {
			if (stack.isEmpty() || (height[i] <= height[stack.peek()])) {
				stack.push(i);
			} else {
				while (!stack.empty() && height[stack.peek()] < height[i]) {
					int poppedIndex = stack.pop();
					if (!stack.empty()) {
						int distance = i - stack.peek() - 1;
						solution += (Math.min(height[stack.peek()], height[i]) - height[poppedIndex]) * distance;
					}
				}
				stack.push(i);
			}
		}
		return solution;
	}

	/**
	 * In this approach we try to find out how much water can collect over each
	 * pillar due to higher pillars on either side of current pillar. So, for every
	 * pillar we compute highest pillar on left and highest on right. The amount of
	 * water which can be collected on this pillar will be minimum of height of
	 * highest pillar on left and on right - current height
	 * 
	 * Time complexity : O(n^2); Space complexity : O(1)
	 * 
	 * @param height
	 * @return
	 */
	private static int trapBruteForce(int[] height) {
		int solution = 0;
		for (int i = 0; i < height.length; i++) {
			int maxLeftHeight = height[i];
			for (int j = i - 1; j >= 0; j--) {
				if (height[j] > height[i]) {
					maxLeftHeight = Math.max(maxLeftHeight, height[j]);
				}
			}
			int maxRightHeight = height[i];
			for (int j = i + 1; j < height.length; j++) {
				if (height[j] > height[i]) {
					maxRightHeight = Math.max(maxRightHeight, height[j]);
				}
			}
			solution += Math.min(maxLeftHeight, maxRightHeight) - height[i];
		}
		return solution;
	}

	/**
	 * This is optimization over above step where we don't compute highest pillar on
	 * left and right. Instead we compute and save it in an array beforehand. This
	 * is like saving solution for efficient computation later.
	 * 
	 * Time complexity : O(n); Space complexity : O(n)
	 * 
	 * @param height
	 * @return
	 */
	private static int trapOptimized(int[] height) {
		int solution = 0;
		int size = height.length;
		int[] maxLeftHeight = new int[size];
		int[] maxRightHeight = new int[size];
		maxLeftHeight[0] = height[0];
		maxRightHeight[size - 1] = height[size - 1];
		for (int i = 1; i < size; i++) {
			maxLeftHeight[i] = Math.max(maxLeftHeight[i - 1], height[i]);
			maxRightHeight[size - i - 1] = Math.max(maxRightHeight[size - i], height[size - i - 1]);
		}
		for (int i = 0; i < size; i++) {
			solution += Math.min(maxLeftHeight[i], maxRightHeight[i]) - height[i];
		}
		return solution;
	}

}
