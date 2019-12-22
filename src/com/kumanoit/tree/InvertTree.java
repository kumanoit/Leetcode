package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 * 
 * @author kumanoit
 * 22-Dec-2019 5:28:06 PM
 */
public class InvertTree {

	public static void main(String[] args) {
		test(new Integer[] { 1 });
		test(new Integer[] { 1, 2 });
		test(new Integer[] { 1, 2, 3 });
		test(new Integer[] { 1, 2, null, 3 });
		test(new Integer[] { 1, null, 2, 3 });
	}

	private static void test(Integer[] nums) {
		TreeNode root = TreeUtils.createTree(nums);
		TreeUtils.printTree(root);
		TreeUtils.printTree(invertTree(root));
	}

	private static TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		invertTree(root.left);
		invertTree(root.right);
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		return root;
	}
}
