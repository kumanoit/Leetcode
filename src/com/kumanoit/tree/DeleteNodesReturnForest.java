package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Do a recrusive traversal in bottom-up manner and see if the current node has
 * to be deleted. If this node has to be deleted then return false so that the
 * parent node knows that this node has be removed from its child list.
 * Time Complexity: O(nk) where n = total nodes in tree, k = length
 * of to_delete array 
 * Space complexity: O(n): In worst case all nodes are present in a
 * single path(left/right skewed tree)
 * 
 * @author kumanoit 24-Dec-2019 2:48:52 PM
 */
public class DeleteNodesReturnForest {

	public static void main(String[] args) {
		test(new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, new int[] { 1 });
		test(new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, new int[] { 2, 3 });
		test(new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, new int[] { 3, 5 });
	}

	private static void test(Integer[] array, int[] toDelete) {
		TreeNode root = TreeUtils.createTree(array);
		System.out.println("\n\nBefore deleting...");
		TreeUtils.printLevelOrder(root);
		System.out.println("After deleting...");
		delNodes(root, toDelete).forEach(node -> TreeUtils.printLevelOrder(node));
	}

	public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		List<TreeNode> solution = new ArrayList<TreeNode>();
		if (!delNodes(root, to_delete, solution)) {
			solution.add(root);
		}
		return solution;
	}

	public static boolean delNodes(TreeNode root, int[] to_delete, List<TreeNode> solution) {
		if (root == null) {
			return false;
		}
		boolean isLeftDeletable = delNodes(root.left, to_delete, solution);
		boolean isRightDeletable = delNodes(root.right, to_delete, solution);
		if (isLeftDeletable) {
			root.left = null;
		}
		if (isRightDeletable) {
			root.right = null;
		}
		for (int i = 0; i < to_delete.length; i++) {
			if (to_delete[i] == root.val) {
				if (root.left != null) {
					solution.add(root.left);
				}
				if (root.right != null) {
					solution.add(root.right);
				}
				return true;
			}
		}
		return false;
	}
}
