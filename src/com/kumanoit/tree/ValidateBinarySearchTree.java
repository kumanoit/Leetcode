package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 * 
 * Approach: Do an in-order traversal and keep checking if last visited value is
 * not greater than current value.
 * 
 * @author kumanoit 24-Dec-2019 11:39:17 AM
 */
public class ValidateBinarySearchTree {

	public static void main(String[] args) {
		test(new Integer[] { 1 }); // true
		test(new Integer[] { 1, 2 }); // false
		test(new Integer[] { 2, 1 }); // true
		test(new Integer[] { 10, 5, 15, 1, 8, 14, 20 }); // true
		test(new Integer[] { 10, 5, 15, 1, 4, 14, 20 }); // false
	}

	private static void test(Integer[] array) {
		TreeNode root = TreeUtils.createTree(array);
		TreeUtils.inorder(root);
		System.out.println(" => " + isValidBST(root));
	}

	private static TreeNode prev;

	public static boolean isValidBST(TreeNode root) {
		prev = null;
		return isValidBSTCover(root);
	}

	private static boolean isValidBSTCover(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (!isValidBSTCover(root.left)) {
			return false;
		}
		if (prev != null && prev.val >= root.val) {
			return false;
		}
		prev = root;
		return isValidBSTCover(root.right);
	}
}
