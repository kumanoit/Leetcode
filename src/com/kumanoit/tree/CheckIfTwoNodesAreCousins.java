package com.kumanoit.tree;

/**
 * https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3322/
 * 
 * Cousins in Binary Tree
 * 
 * Algorithm: Search for both nodes in tree in bottom-up manner. Return 1 when
 * you find any of the nodes. Find the height of both nodes and check if 
 * 1. height is same 
 * 2. parent is not same : height > 1
 * 
 * Complexity:
 * Time: O(n)
 * Space: O(1)
 * 
 * @author kumanoit May 7, 2020 9:35:55 PM
 *
 */
public class CheckIfTwoNodesAreCousins {

	private static boolean areCousins;

	public static void main(String[] args) {
		test(new Integer[] { 1, 2, 4, 3, 19, 10, 5, 15, 8, null, null, 13, 14, null, 6, null, 17, null, null, null,
		        null, 18, null, 7, 11, null, null, null, null, null, 9, 16, 12, null, null, 20 }, 11, 17);
		test(new Integer[] { 1, 2, 3, null, 4 }, 2, 3);
		test(new Integer[] { 1, 2, 3, 4 }, 4, 3);
		test(new Integer[] { 1, 2, 3, null, 4, null, 5 }, 5, 4);
		test(new Integer[] { 1, 2, 3, null, 4 }, 2, 3);
	}

	private static void test(final Integer[] nodes, final int x, final int y) {
		TreeNode root = TreeUtils.createTree(nodes);
		TreeUtils.printTree(root);
		areCousins = false;
		checkIfCousins(root, x, y);
		System.out.println("Are cousins : " + areCousins);
	}

	public static boolean checkIfCousins(TreeNode root, int x, int y) {
		if (root == null) {
			return false;
		}
		areCousins = false;
		getHeight(root, x, y);
		return areCousins;
	}

	public static int getHeight(TreeNode root, int x, int y) {
		if (root == null) {
			return -1;
		}
		if (x == root.val) {
			return 1;
		}
		if (y == root.val) {
			return 1;
		}
		int xLevel = getHeight(root.left, x, y);
		int yLevel = getHeight(root.right, x, y);
		if (xLevel != -1 && yLevel != -1 && xLevel > 1) {
			areCousins = xLevel == yLevel;
		}
		if (xLevel != -1) {
			return xLevel + 1;
		}
		if (yLevel != -1) {
			return yLevel + 1;
		}
		return -1;
	}
}
