package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * 
 * Approach-1: Do it like a binary tree. Check recursively 
 * 1. do a recursive search on both left and right subtrees
 * 2. if value is either equal to any: return the root
 * 3. if both left and right returned are not null, then current root is LCA 
 *    and it means that both p and q are present on both side of root
 * 4. if any of left/right is null, it means both children are present on non-null side.
 * 
 * Approach-2: Since, it is said tha tree is binary search tree, it makes sense
 * to do search smartly. Assume that p < q
 * 1. Since, nodes are present in tree (given) it means if root's
 * value is greater than p and lesser than q then, root is LCA
 * 2. if root's value is smaller than p, then it means LCA exists on left
 * 3. if root's value is greater than q, then it means LCA exists on right
 * 
 * @author kumanoit
 * 03-Jan-2020 10:56:51 PM
 */
public class LowestCommonAncestorInBinarySearchTree {

	public static void main(String[] args) {
		final Integer[] nodes = { 6, 2, 8, 0, 4, 7, 9, null, null, 3, 5 };
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				for (int j = i + 1; j < nodes.length; j++) {
					if (nodes[j] != null) {
//			o			System.out.println("[6, 2, 8, 0, 4, 7, 9, null, null, 3, 5 ] \n" + nodes[i] + "\n" + nodes[j]);
						test(nodes, nodes[i], nodes[j]);
					}
				}
			}
		}
	}

	private static void test(final Integer[] numbers, int p, int q) {
		TreeNode root = TreeUtils.createTree(numbers);
		System.out.println(lowestCommonAncestorBF(root, new TreeNode(p), new TreeNode(q)));
	}

	/**
	 * Brute Force
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public static TreeNode lowestCommonAncestorBF(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		TreeNode left = lowestCommonAncestorBF(root.left, p, q);
		TreeNode right = lowestCommonAncestorBF(root.right, p, q);
		if (root.val == p.val || root.val == q.val) {
			return root;
		}
		if (left != null && right != null) {
			return root;
		}
		return left == null ? right : left;
	}

	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (p.val < q.val) {
			return lcaBST(root, p, q);
		}
		return lcaBST(root, q, p);
	}

	/**
	 * Smart recursion making use of BST
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	private static TreeNode lcaBST(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root.val == p.val || root.val == q.val) {
			return root;
		}
		if (root.val > p.val && root.val < q.val) {
			return root;
		}
		if (root.val < p.val) {
			return lcaBST(root.right, p, q);
		}
		return lcaBST(root.left, p, q);
	}
}