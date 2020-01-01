package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/submissions/
 * 
 * Approach: Do a recursive bottom-up traversal and return the ndoe if its value
 * is either equal to p or q. If both left and right is not null, it means that
 * root is least common ancestor. otherwise: left or right node is the solution.
 * This happens when both p and q are in same subtree.
 * 
 * @author kumanoit 02-Jan-2020 12:08:49 AM
 */
public class LowestCommonAncestorOfBinaryTree {

	public static void main(String[] args) {
		test(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 }, 4, 3); // 3
		test(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 }, 3, 4); // 3
		test(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 }, 6, 8); // 3
		test(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 }, 4, 6); // 5
		test(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 }, 7, 0); // 3
		test(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 }, 7, 2); // 2
		test(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 }, 2, 5); // 5
		test(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 }, 0, 1); // 1
	}

	private static void test(final Integer[] numbers, final int p, final int q) {
		TreeNode root = TreeUtils.createTree(numbers);
		TreeUtils.printTree(root);
		System.out.println("LCA = " + lowestCommonAncestor(root, p, q));
	}

	public static TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
		if (root == null) {
			return null;
		}
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (root.val == p || root.val == q) {
			return root;
		}
		if (left != null && right != null) {
			return root;
		}
		return left == null ? right : left;
	}
}
