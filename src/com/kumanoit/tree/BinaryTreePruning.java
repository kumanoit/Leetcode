package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/binary-tree-pruning/
 * 
 * @author kumanoit 15-Dec-2019 4:48:11 PM
 * 
 *         Algorithm: The idea is to do a bottom-up search. If a node has value
 *         0 and it is leaf then return null else return the node itself.
 */
public class BinaryTreePruning {

	public static void main(String[] args) {
		test(new Integer[] { 1, null, 0, 0, 1 });
	}

	private static void test(Integer[] array) {
		TreeNode root = TreeUtils.createTree(array);
		TreeUtils.printTree(root);
		System.out.print("\nAfter pruning: ");
		TreeUtils.printTree(pruneTree(root));
	}

	public static TreeNode pruneTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		root.left = pruneTree(root.left);
		root.right = pruneTree(root.right);
		if (root.left == null && root.right == null) {
			if (root.val == 1) {
				return root;
			} else {
				return null;
			}
		}
		return root;
	}
}
