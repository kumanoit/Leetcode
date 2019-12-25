package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * 
 * Algorithm: Morris preorder traversal
 * 
 * @author kumanoit 25-Dec-2019 11:11:34 PM
 */
public class FlattenBinaryTreeToLinkedList {

	public static void main(String[] args) {
		test(new Integer[] {});
		test(new Integer[] { 1 });
		test(new Integer[] { 1, null, 2, null, 3, null, 4 });
		test(new Integer[] { 1, 2, null, 3, null, 4, null });
		test(new Integer[] { 1, null, 2, 3, null, 4, 5, null, 6 });
		test(new Integer[] { 1, 2, 5, 3, 4, null, 6 });
	}

	private static void test(final Integer[] nodes) {
		TreeNode root = TreeUtils.createTree(nodes);
		TreeUtils.printTree(root);
		flatten(root);
		System.out.println("Tree after flattening...");
		while (root != null) {
			System.out.print(root.val + ", ");
			root = root.right;
		}
	}

	public static void flatten(TreeNode root) {
		TreeNode ptr = root;
		while (ptr != null) {
			if (ptr.left != null) {
				TreeNode inorderPredecessor = ptr.left;
				while (inorderPredecessor.right != null) {
					inorderPredecessor = inorderPredecessor.right;
				}
				inorderPredecessor.right = ptr.right;
				ptr.right = ptr.left;
				ptr.left = null;
			}
			ptr = ptr.right;
		}
	}
}
