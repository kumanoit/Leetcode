package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/convert-bst-to-greater-tree/ 
 * 
 * Complexity: Time: O(n) 
 * Space: O(1) extra variable used which is constant
 * 
 * @author kumanoit 22-Dec-2019 5:53:36 PM
 */
public class ConvertBSTToGreaterTree {

	public static void main(String[] args) {
		test(new Integer[] { 1 });
		test(new Integer[] { 2, 1 });
		test(new Integer[] { 2, 1, 3 });
		test(new Integer[] { 1, null, 2, null, 3 });
	}

	private static void test(Integer[] nums) {
		TreeNode root = TreeUtils.createTree(nums);
		TreeUtils.printTree(root);
		TreeUtils.printTree(convertBST(root));
	}

	public static TreeNode convertBST(TreeNode root) {
		convert(root, new int[] { 0 });
		return root;
	}

	private static void convert(TreeNode root, int[] maxValue) {
		if (root == null) {
			return;
		}
		convert(root.right, maxValue);
		root.val += maxValue[0];
		maxValue[0] = root.val;
		convert(root.left, maxValue);
	}
}
