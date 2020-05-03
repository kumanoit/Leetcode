package com.kumanoit.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 * 
 * Approach: Do level order traversal using two queues. One queue of odd levels
 * and other for even level nodes. Keep information about indices of each node
 * of tree in another queue. Every time a node is popped from even/odd queues,
 * pop an element from index queue as well. For adding a new node into queue
 * check if left/right child exist. If it exists, then for left child push (2 *
 * currentIndex) and for right push (2 * currentIndex + 1) into index queue.
 * 
 * Complexity: 
 * Space: O(n) n for nodes and n for index queue 
 * Time: O(n): Each node is traversed only once.
 * 
 * @author kumanoit May 3, 2020 8:54:58 PM
 * 
 */
public class MaximumWidthOfBinaryTree {
	public static void main(String[] args) {
		test(new Integer[] {}); //  0
		test(new Integer[] { 1 }); // 1
		test(new Integer[] { 1, 2 }); // 1
		test(new Integer[] { 1, 2, 3 }); // 2
		test(new Integer[] { 1, 2, 3, 4, 5, 6, 7 }); // 4
		test(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }); // 8
		test(new Integer[] { 1, null, 2, null, 3, null, 4, null, 5, null, 6, null, 7 }); // 1
		test(new Integer[] { 1, 2, null, 3, null, 4, null, 5, null, 6, null, 7 }); // 1
		test(new Integer[] { 1, 2, 3, 4, 5, null, 7 }); // 4
		test(new Integer[] { 1, 2, null, 3, 4 }); // 2
		test(new Integer[] { 1, 2, 3, 4, null, null, 5, 6, null, null, 7 }); // 8
	}

	private static void test(final Integer[] nodes) {
		TreeNode root = TreeUtils.createTree(nodes);
		TreeUtils.printTree(root);
		System.out.println(getWidthOfBinaryTree(root));
	}

	private static int getWidthOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> oddLevel = new LinkedList<TreeNode>();
		Queue<TreeNode> evenLevel = new LinkedList<TreeNode>();
		Queue<Integer> index = new LinkedList<Integer>();
		int maxWidth = 1;
		oddLevel.add(root);
		index.add(1);
		while (!(oddLevel.isEmpty() && evenLevel.isEmpty())) {
			int firstIndex = index.isEmpty() ? -1 : index.peek();
			int lastIndex = -1;
			while (!oddLevel.isEmpty()) {
				TreeNode ptr = oddLevel.remove();
				lastIndex = index.remove();
				if (ptr.left != null) {
					evenLevel.add(ptr.left);
					index.add(lastIndex * 2);
				}
				if (ptr.right != null) {
					evenLevel.add(ptr.right);
					index.add(lastIndex * 2 + 1);
				}
			}
			if (firstIndex != -1) {
				maxWidth = Math.max(maxWidth, lastIndex - firstIndex + 1);
			}

			firstIndex = index.isEmpty() ? -1 : index.peek();
			while (!evenLevel.isEmpty()) {
				TreeNode ptr = evenLevel.remove();
				lastIndex = index.remove();
				if (ptr.left != null) {
					oddLevel.add(ptr.left);
					index.add(lastIndex * 2);
				}
				if (ptr.right != null) {
					oddLevel.add(ptr.right);
					index.add(lastIndex * 2 + 1);
				}
			}
			if (firstIndex != -1) {
				maxWidth = Math.max(maxWidth, lastIndex - firstIndex + 1);
			}
		}
		return maxWidth;
	}

}
