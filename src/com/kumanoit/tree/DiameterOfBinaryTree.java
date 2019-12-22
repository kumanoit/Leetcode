package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 * 
 * @author kumanoit 22-Dec-2019 6:30:48 PM
 */
public class DiameterOfBinaryTree {

	public static void main(String[] args) {
		test(new Integer[] { 1 }); // 0
		test(new Integer[] { 1, 2 }); // 1
		test(new Integer[] { 1, 2, 3 }); // 2
		test(new Integer[] { 1, 2, 3, 4 }); // 3
		test(new Integer[] { 1, 2, 3, 4, 5 }); // 3
		test(new Integer[] { 1, 2, 3, 4, null, null, 5, 6, null, null, 7 }); // 6
	}

	private static void test(final Integer[] nums) {
		TreeNode root = TreeUtils.createTree(nums);
//		TreeUtils.printTree(root);
		System.out.println(diameterOfBinaryTree(root));
	}

	public static int diameterOfBinaryTree(TreeNode root) {
		int[] diameter = new int[1];
		getDiameter(root, diameter);
		return diameter[0];
	}

	public static int getDiameter(TreeNode root, int[] diameter) {
		if (root == null) {
			return 0;
		}
		int left = getDiameter(root.left, diameter);
		int right = getDiameter(root.right, diameter);
		diameter[0] = Math.max(diameter[0], left + right);
		return Math.max(left, right) + 1;
	}
}
