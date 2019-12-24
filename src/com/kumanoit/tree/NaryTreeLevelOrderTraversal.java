package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 * 
 * Algorithm: Keep all nodes at a level in a queue. Insert null once one level
 * node has all been covered. This will help to keep track of nodes at one level.
 * 
 * Complexity: 
 * Time: O(n) : all nodes are covered once
 * Space: O(n): keeping nodes in queue.
 * 
 * @author kumanoit 25-Dec-2019 3:50:14 AM
 */
public class NaryTreeLevelOrderTraversal {

	public static void main(String[] args) {
		test(new Integer[] { 1, null, 3, 2, 4, null, 5, 6 });
	}

	private static void test(final Integer[] nums) {
		NaryTreeNode root = TreeUtils.createNaryTreeNode(nums);
		levelOrder(root).forEach(list -> {
			list.forEach(item -> System.out.print(item + ", "));
			System.out.println();
		});
	}

	public static List<List<Integer>> levelOrder(NaryTreeNode root) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		if (root == null) {
			return solution;
		}
		Queue<NaryTreeNode> queue = new LinkedList<NaryTreeNode>();
		queue.add(root);
		queue.add(null);
		List<Integer> localSolution = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			NaryTreeNode temp = queue.remove();
			if (temp == null) {
				solution.add(localSolution);
				if (queue.isEmpty()) {
					break;
				}
				localSolution = new ArrayList<Integer>();
				queue.add(null);
			} else {
				localSolution.add(temp.val);
				if (temp.children != null) {
					for (NaryTreeNode child : temp.children) {
						queue.add(child);
					}
				}
			}
		}
		return solution;
	}
}
