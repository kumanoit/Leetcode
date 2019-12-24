package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 * 
 * @author kumanoit 25-Dec-2019 3:01:26 AM
 */
public class SubtreeOfAnotherTree {

	public static void main(String[] args) {
		test(new Integer[] { 3, 4, 5, 1, 2 }, new Integer[] { 4, 1, 2 });
		test(new Integer[] { 3, 4, 5, 1, 2, null, null, null, null, 0 }, new Integer[] { 4, 1, 2 });
	}

	private static void test(final Integer[] node_s, final Integer[] node_t) {
		TreeNode root_s = TreeUtils.createTree(node_s);
		TreeNode root_t = TreeUtils.createTree(node_t);
		TreeUtils.printTree(root_s);
		TreeUtils.printTree(root_t);
		System.out.println("Is subtree? " + isSubtree(root_s, root_t));
	}

	public static boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null && t == null) {
			return true;
		}
		if (s == null || t == null) {
			return false;
		}
		if (s.val == t.val) {
			if (isSame(s, t)) {
				return true;
			}
		}
		return isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	public static boolean isSame(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null || root1.val != root2.val) {
			return false;
		}
		return isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
	}
}
