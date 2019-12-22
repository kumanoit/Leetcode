package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/maximum-binary-tree/
 * 
 * Algorithm: Find the maximum element's index. Create a root node with value of
 * maximum element. Create left subtree recursively with all indices to left of
 * that maximum value's index and right subtree with all the indices to the
 * right of maximum value's index.
 * 
 * @author kumanoit 22-Dec-2019 7:54:37 PM
 */
public class MaximumBinaryTree {

	public static void main(String[] args) {
		test(new int[] { 1 });
		test(new int[] { 1, 2, 3, 4, 5 });
		test(new int[] { 5, 4, 3, 2, 1 });
		test(new int[] { 1, 10, 2, 9, 3, 8, 4, 7 });
		test(new int[] { 3, 2, 1, 6, 0, 5 });
	}

	private static void test(int[] nums) {
		TreeUtils.printTree(constructMaximumBinaryTree(nums));
	}

	public static TreeNode constructMaximumBinaryTree(int[] nums) {
		return constructMaximumBinaryTree(nums, 0, nums.length - 1);
	}

	public static TreeNode constructMaximumBinaryTree(int[] nums, int index, int endIndex) {
		if (endIndex < index) {
			return null;
		}
		int maxIndex = index;
		for (int i = index + 1; i <= endIndex; i++) {
			if (nums[maxIndex] < nums[i]) {
				maxIndex = i;
			}
		}
		TreeNode root = new TreeNode(nums[maxIndex]);
		root.left = constructMaximumBinaryTree(nums, index, maxIndex - 1);
		root.right = constructMaximumBinaryTree(nums, maxIndex + 1, endIndex);
		return root;
	}
}