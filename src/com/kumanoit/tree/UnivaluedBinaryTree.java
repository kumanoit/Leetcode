package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/univalued-binary-tree/
 * 
 * @author kumanoit
 * 24-Dec-2019 8:49:32 PM
 */
public class UnivaluedBinaryTree {

	public static void main(String[] args) {
		test(new Integer[] { 1 });
		test(new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 });
		test(new Integer[] { 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1 });
		test(new Integer[] { 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 2 });
		test(new Integer[] { 1, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1 });
		test(new Integer[] { 1, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 2 });
	}

	private static void test(final Integer[] nodes) {
		TreeNode root = TreeUtils.createTree(nodes);
		TreeUtils.inorder(root);
		System.out.println("\t" + isUnivalTree(root));
	}

	private static boolean isUnivalTree(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left != null && root.left.val != root.val) {
			return false;
		}
		if (root.right != null && root.right.val != root.val) {
			return false;
		}
		return isUnivalTree(root.left) && isUnivalTree(root.right);
	}
}
