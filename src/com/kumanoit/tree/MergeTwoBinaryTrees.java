package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/solution/
 * 
 * Algorithm: Do a Preorder traversal of both the trees simultaneously and
 * create solution tree based on root's existence and its children.
 * 
 * @author kumanoit 25-Dec-2019 7:47:23 PM
 */
public class MergeTwoBinaryTrees {

	public static void main(String[] args) {
		test(new Integer[] {}, new Integer[] {});
		test(new Integer[] { 1 }, new Integer[] {});
		test(new Integer[] {}, new Integer[] { 1 });
		test(new Integer[] { 1, 2, null, 3, null, 4, null }, new Integer[] { 0, null, 2, null, 3, null, 4, null });
		test(new Integer[] { 1, 3, 2, 5 }, new Integer[] { 2, 1, 3, null, 4, null, 7 });
	}

	private static void test(final Integer[] nodes1, final Integer[] nodes2) {
		TreeNode root1 = TreeUtils.createTree(nodes1);
		TreeNode root2 = TreeUtils.createTree(nodes2);
		System.out.println("Tree 1: ");
		TreeUtils.printTree(root1);
		System.out.println("Tree 2: ");
		TreeUtils.printTree(root2);
		System.out.println("Merged trees ");
		TreeUtils.printTree(mergeTrees(root1, root2));
	}

	public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return null;
		}
		TreeNode root = null;
		if (t1 != null && t2 != null) {
			root = new TreeNode(t1.val + t2.val);
			root.left = mergeTrees(t1.left, t2.left);
			root.right = mergeTrees(t1.right, t2.right);
		} else if (t1 != null) {
			root = new TreeNode(t1.val);
			root.left = mergeTrees(t1.left, null);
			root.right = mergeTrees(t1.right, null);
		} else {
			root = new TreeNode(t2.val);
			root.left = mergeTrees(null, t2.left);
			root.right = mergeTrees(null, t2.right);
		}
		return root;
	}
}
