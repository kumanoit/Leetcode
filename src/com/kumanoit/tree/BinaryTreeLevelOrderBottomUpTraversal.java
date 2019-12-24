package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/submissions/
 * 
 * Algorithm: Keep all nodes at a level in a queue. Insert null once one level
 * node has all been covered. This will help to keep track of nodes at one level.
 * 
 * Complexity: 
 * Time: O(n) : all nodes are covered once
 * Space: O(n): keeping nodes in queue.
 * 
 * @author kumanoit
 * 25-Dec-2019 4:05:26 AM
 */
public class BinaryTreeLevelOrderBottomUpTraversal {

	public static void main(String[] args) {
		test(new Integer[] {});
		test(new Integer[] { 1, null, 3, 2, 4, null, 5, 6 });
	}

	private static void test(final Integer[] nums) {
		TreeNode root = TreeUtils.createTree(nums);
		levelOrderBottomUp(root).forEach(list -> {
			list.forEach(item -> System.out.print(item + ", "));
			System.out.println();
		});
		System.out.println();
	}

	private static List<List<Integer>> levelOrderBottomUp(TreeNode root) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		if (root == null) {
			return solution;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		queue.add(null);
		List<Integer> localSolution = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			TreeNode temp = queue.remove();
			if (temp == null) {
				solution.add(localSolution);
				if (queue.isEmpty()) {
					break;
				}
				localSolution = new ArrayList<Integer>();
				queue.add(null);
			} else {
				localSolution.add(temp.val);
				if (temp.left != null) {
					queue.add(temp.left);
				}
				if (temp.right != null) {
					queue.add(temp.right);
				}
			}
		}
		Collections.reverse(solution);
		return solution;
	}

}
