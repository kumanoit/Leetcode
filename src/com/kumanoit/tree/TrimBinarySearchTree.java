package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/trim-a-binary-search-tree/
 * 
 * Algorithm: Create tree recursively. If you found a node with value lesser
 * than lower limit, go for the right subtree because that will be greater than
 * lower limit as it is a binary search tree. Similarly, if value of root is
 * greater than higher limit, go for left subtree
 * 
 * @author kumanoit 22-Dec-2019 8:21:03 PM
 */
public class TrimBinarySearchTree {

	public static void main(String[] args) {
		test(new Integer[] { 1, 0, 2 }, 1, 2);
		test(new Integer[] { 1, 0, 2 }, 0, 1);
		test(new Integer[] { 1, 0, 2 }, 0, 2);
		test(new Integer[] { 1, 0, 2 }, 1, 1);
		test(new Integer[] { 3, 4, 5 }, 1, 2);
	}

	private static void test(Integer[] nums, int min, int max) {
		System.out.println("================================================");
		TreeNode root = TreeUtils.createTree(nums);
		System.out.println("\nTree before trimming");
		TreeUtils.printTree(root);
		System.out.println("\nTree after trimming");
		TreeUtils.printTree(trimBST(root, min, max));
	}

	private static TreeNode trimBST(TreeNode root, int min, int max) {
		if (root == null) {
			return null;
		}
		if (root.val < min) {
			return trimBST(root.right, min, max);
		}
		if (root.val > max) {
			return trimBST(root.left, min, max);
		}
		root.left = trimBST(root.left, min, max);
		root.right = trimBST(root.right, min, max);
		return root;
	}
}
