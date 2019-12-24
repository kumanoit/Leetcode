package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree
 * 
 * @author kumanoit
 * 25-Dec-2019 2:41:52 AM
 */
public class FindElements {

	public static void main(String[] args) {
		test(new Integer[] { 0, 0, 0, 0, null, 0, null }, new int[] { 3 });
		test(new Integer[] { -1, null, -1 }, new int[] { 1, 2 });
	}

	private static void test(final Integer[] nodes, int[] target) {
		TreeNode root = TreeUtils.createTree(nodes);
		TreeUtils.printTree(root);
		FindElements object = new FindElements(root);
		TreeUtils.printTree(object.root);
		for (int x : target) {
			System.out.println("Is " + x + " found? " + object.find(x));
		}
	}

	TreeNode root;

	public FindElements(TreeNode root) {
		root.val = 0;
		this.root = recover(root);
	}

	private TreeNode recover(TreeNode root) {
		if (root == null) {
			return null;
		}
		if (root.left != null) {
			root.left.val = 2 * root.val + 1;
			recover(root.left);
		}
		if (root.right != null) {
			root.right.val = 2 * root.val + 2;
			recover(root.right);
		}
		return root;
	}

	public boolean find(int target) {
		return find(this.root, target);
	}

	private boolean find(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		if (root.val == target) {
			return true;
		}
		return find(root.left, target) || find(root.right, target);
	}
}
