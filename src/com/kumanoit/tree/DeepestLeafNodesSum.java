package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/deepest-leaves-sum/submissions/
 * 
 * Keep a track of deepest leaf node and add it to the solutoin variable.
 * 
 * @author kumanoit 02-Jan-2020 12:22:19 AM
 */
public class DeepestLeafNodesSum {

	public static void main(String[] args) {
		test(new Integer[] { 1 }); // 1
		test(new Integer[] { 1, 2, 3 }); // 5
		test(new Integer[] { 1, 2 }); // 2
		test(new Integer[] { 1, null, 2 }); // 2
		test(new Integer[] { 1, null, 2, null, 3, null, 4, null }); // 4
		test(new Integer[] { 1, 2, null, 3, null, 4, null, 5, null }); // 5
	}

	private static void test(final Integer[] numbers) {
		TreeNode root = TreeUtils.createTree(numbers);
		TreeUtils.printTree(root);
		System.out.println("Deepest Level nodes sum: " + deepestLeavesSum(root));
	}

	public static int deepestLeavesSum(TreeNode root) {
		int[] sum = new int[1];
		deepestLeavesSum(root, new int[1], 0, sum);
		return sum[0];
	}

	private static void deepestLeavesSum(TreeNode root, int[] deepestLevel, int currentLevel, int[] sum) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			if (currentLevel > deepestLevel[0]) {
				deepestLevel[0] = currentLevel;
				sum[0] = root.val;
			} else if (currentLevel == deepestLevel[0]) {
				sum[0] += root.val;
			}
			return;
		}
		deepestLeavesSum(root.left, deepestLevel, currentLevel + 1, sum);
		deepestLeavesSum(root.right, deepestLevel, currentLevel + 1, sum);
	}
}
