package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/symmetric-tree/
 * 
 * @author kumanoit 22-Dec-2019 4:19:00 PM
 */
public class SymmetricTree {

	public static void main(String[] args) {
		test(new Integer[] { 1 }); // true
		test(new Integer[] { 1, 2 }); // false
		test(new Integer[] { 1, 2, 3 }); // false
		test(new Integer[] { 1, 2, 2 }); // true
		test(new Integer[] { 1, 2, 2, 3, null, null, 3 }); // true
		test(new Integer[] { 1, 2, 2, 3, null, 3, null });// false
		test(new Integer[] { 1, 2, 2, 3, 4, 4, 3 }); // true
		test(new Integer[] { 1, 2, 2, null, 3, 3, null }); // true
	}

	private static void test(Integer[] nums) {
		System.out.println(isSymmetric(TreeUtils.createTree(nums)));
	}

	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isSymmetric(root.left, root.right);
	}

	public static boolean isSymmetric(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		if (left.val != right.val) {
			return false;
		}
		return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
	}
}
