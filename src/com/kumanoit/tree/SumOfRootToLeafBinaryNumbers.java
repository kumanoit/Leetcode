package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
 * 
 * @author kumanoit 25-Dec-2019 11:56:47 AM
 */
public class SumOfRootToLeafBinaryNumbers {

	public static void main(String[] args) {
		test(new Integer[] { 0 });
		test(new Integer[] { 1 });
		test(new Integer[] {});
		test(new Integer[] { 1, 0 });
		test(new Integer[] { 1, 0, 0 });
		test(new Integer[] { 1, 0, 1 });
		test(new Integer[] { 1, 0, 1, 0, 1, 0, 1 });
	}

	private static void test(final Integer[] nodes) {
		TreeNode root = TreeUtils.createTree(nodes);
		TreeUtils.printTree(root);
		System.out.println(sumRootToLeaf(root));
	}

	public static int sumRootToLeaf(TreeNode root) {
		int[] totalSum = new int[1];
		findSum(root, totalSum, 0);
		return totalSum[0];
	}

	private static void findSum(TreeNode root, int[] totalSum, int sum) {
		if (root == null) {
			return;
		}
		sum = sum * 2 + root.val;
		if (root.left == null && root.right == null) {
			totalSum[0] += sum;
		}
		findSum(root.left, totalSum, sum);
		findSum(root.right, totalSum, sum);
	}
}
