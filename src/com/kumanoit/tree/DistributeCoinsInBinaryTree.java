package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/distribute-coins-in-binary-tree/
 * 
 * Approach: Start in bottom up manner. 
 * 1) For a node: total = value at leaf  + left subtree need + right subtree need
 *     1a) If the value at leaf node is 0, it means that it needs coins. 
 *         so 1 step will be needed to get one coin. Also pass information about 
 *         coins needed upwards to parent node.
 *     1b) if total == 1; 
 *         then it means no coin is needed and no moves is required
 *     1c) if total > 1: 
 *         It means that total - 1 coins needs to be moved for which (total - 1)
 *         moves will be needed.
 *     1d) if total at leaf < 1: It means that Math.abs(total + 1) coins needs
 *         to be moved for which (total + 1) moves will be needed.
 *  
 * @author kumanoit 24-Dec-2019 5:13:50 PM
 */
public class DistributeCoinsInBinaryTree {

	public static void main(String[] args) {
		test(new Integer[] { 1 });
		test(new Integer[] { 1, 1, 1, 1 });
		test(new Integer[] { 3, 0, 0 });
		test(new Integer[] { 0, 3, 0 });
		test(new Integer[] { 1, 0, 2 });
		test(new Integer[] { 1, 0, 0, null, 3 });
		test(new Integer[] { 5, 0, 0, 0, null, null, 0 });
		test(new Integer[] { 0, 0, 0, 0, 0, 7, 0 });
	}

	private static void test(Integer[] array) {
		TreeNode root = TreeUtils.createTree(array);
		TreeUtils.printTree(root);
		System.out.println("Moves needed: " + distributeCoins(root));
	}

	public static int distributeCoins(TreeNode root) {
		int[] total = new int[1];
		distribute(root, total);
		return total[0];
	}

	private static int distribute(TreeNode root, int[] total) {
		if (root == null) {
			return 0;
		}
		int left = distribute(root.left, total);
		int right = distribute(root.right, total);
		total[0] += Math.abs(left) + Math.abs(right);
		if (root.val + left + right == 1) {
			return 0;
		}
		return root.val + left + right - 1;
	}
}
