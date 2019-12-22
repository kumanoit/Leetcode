package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 * 
 * Algorithm: Run preorder recursively on all childs of root.
 * 
 * @author kumanoit 22-Dec-2019 8:45:59 PM
 */
public class NAryTreePreorderTraversal {

	public static void main(String[] args) {
		test(new Integer[] { 1, null, 3, 2, 4, null, 5, 6 });
	}

	private static void test(final Integer[] nums) {
		NaryTreeNode root = TreeUtils.createNaryTreeNode(nums);
		preorder(root).forEach(item -> System.out.print(item + ", "));
	}

	public static List<Integer> preorder(NaryTreeNode root) {
		List<Integer> solution = new ArrayList<Integer>();
		if (root == null) {
			return solution;
		}
		preorder(root, solution);
		return solution;
	}

	private static void preorder(NaryTreeNode root, List<Integer> solution) {
		if (root == null) {
			return;
		}
		solution.add(root.val);
		if (root.children != null) {
			for (NaryTreeNode child : root.children) {
				preorder(child, solution);
			}
		}
	}
}
