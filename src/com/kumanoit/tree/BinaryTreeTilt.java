package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/binary-tree-tilt/
 * 
 * @author kumanoit 13-Dec-2019 12:41:01 AM
 */
public class BinaryTreeTilt {

	public static void main(String[] args) {
		Integer[] array = { 10, 20, 30, null, 40, 60, null, null, 50 };
		TreeNode root = TreeUtils.createTree(array);
		TreeUtils.preorder(root);
		System.out.println();
		TreeUtils.inorder(root);
		System.out.println();
		TreeUtils.printLevelOrder(root);
		System.out.println("\nTilt value = " + findTilt(root));
	}

	public static int findTilt(TreeNode root) {
		int[] tilt = new int[1];
		findTilt(root, tilt);
		return tilt[0];
	}

	private static int findTilt(TreeNode root, int[] tilt) {
		if (root == null) {
			return 0;
		}
		int leftSum = findTilt(root.left, tilt);
		int rightSum = findTilt(root.right, tilt);
		tilt[0] += Math.abs(leftSum - rightSum);
		return leftSum + rightSum + root.val;
	}
}
