package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 * 
 * Algorithm: Run postorder recursively on all children of root.
 * 
 * @author kumanoit 25-Dec-2019 3:07:34 AM
 */
public class NaryTreePostOrderTraversal {

	public static void main(String[] args) {
		test(new Integer[] { 1, null, 3, 2, 4, null, 5, 6 });
	}

	private static void test(final Integer[] nums) {
		NaryTreeNode root = TreeUtils.createNaryTreeNode(nums);
		postorder(root).forEach(item -> System.out.print(item + ", "));
	}

	public static List<Integer> postorder(NaryTreeNode root) {
		List<Integer> solution = new ArrayList<Integer>();
		if (root == null) {
			return solution;
		}
		postorder(root, solution);
		return solution;
	}

	private static void postorder(NaryTreeNode root, List<Integer> solution) {
		if (root == null) {
			return;
		}
		if (root.children != null) {
			for (NaryTreeNode child : root.children) {
				postorder(child, solution);
			}
		}
		solution.add(root.val);
	}
}
