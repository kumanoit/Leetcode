package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/smallest-string-starting-from-leaf/
 * 
 * Approach: Iterate from root to leaf in DFS manner creating a string which
 * would be formed at leaf in reverse order from leaf to root. Keep a track of
 * first lexicographic string that will form.
 * 
 * @author kumanoit 25-Dec-2019 1:52:51 AM
 */
public class SmallestStringStartingFromLeaf {

	public static void main(String[] args) {
		test(new Integer[] { 4, 0, 1, 1 });
		test(new Integer[] { 25, 1, null, 0, 0, 1, null, null, null, 0 });
	}

	private static void test(final Integer[] nodes) {
		TreeNode root = TreeUtils.createTree(nodes);
		TreeUtils.printTree(root);
		System.out.println(smallestFromLeafOptimized(root));
	}

	private static String solution = "";

	private static String smallestFromLeafOptimized(TreeNode root) {
		solution = "";
		smallestFromLeafOptimized(root, "");
		return solution;
	}

	private static void smallestFromLeafOptimized(TreeNode root, String parentString) {
		if (root == null) {
			return;
		}
		String stringSoFar = (char) (root.val + 'a') + parentString;
		if (root.left == null && root.right == null) {
			if (solution == "" || solution.compareTo(stringSoFar) > 0) {
				solution = stringSoFar;
			}
			return;
		}
		smallestFromLeafOptimized(root.left, stringSoFar);
		smallestFromLeafOptimized(root.right, stringSoFar);
	}
}
