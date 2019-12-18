package com.kumanoit.array;

/**
 * https://leetcode.com/problems/container-with-most-water/submissions/
 * 
 * @author kumanoit 19-Dec-2019 1:10:14 AM
 * 
 * Algorithm: There are two ways to solve this problem
 * 1. Brute Force where you consider each pair of pillars and calculate area accordingly.
 * 2. Smart way is to see that max base length = n - 1. This will happen if the two pillars
 *  are present at the ends. As one move inwards this base length will reduce. 
 *  MostWaterContained = baseLength * Min(ai, aj) where i begins from start and j from end
 *  We must try to maximize Min(ai, aj) which will happen only when we try to find maximum 
 *  height pillar.
 */
public class ContainerWithMostWater {

	public static void main(String[] args) {
		test(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 });
		test(new int[] { 1, 5, 4, 3 });
		test(new int[] { 3, 1, 2, 4, 5 });
	}

	private static void test(int[] array) {
		System.out.println("Brute Force = " + getSolutionBruteForce(array));
		System.out.println("Smart solution = " + getSolutionSmartSolution(array));
	}

	/**
	 * O(n^2)
	 * 
	 * @param array
	 * @return
	 */
	private static int getSolutionBruteForce(int[] array) {
		int result = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				result = Math.max(result, (j - i) * Math.min(array[i], array[j]));
			}
		}
		return result;
	}

	/**
	 * O(n)
	 * 
	 * @param array
	 * @return
	 */
	private static int getSolutionSmartSolution(int[] array) {
		int result = 0;
		int i = 0;
		int j = array.length - 1;
		while (i < j) {
			result = Math.max((j - i) * Math.min(array[i], array[j]), result);
			if (array[i] < array[j]) {
				i++;
			} else {
				j--;
			}
		}
		return result;
	}
}
