package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/submissions/
 * 
 * Approach: Keep a track of grandparent and parent node. Pass info to child.
 * 
 * @author kumanoit 21-Jan-2020 9:28:52 PM
 */
public class SumOfNodesWithEvenValuedGrandParents {

	public static void main(String[] args) {
		test(new Integer[] { 1 }); // 0
		test(new Integer[] { 1, 2 }); // 0
		test(new Integer[] { 1, null, 2 }); // 0
		test(new Integer[] { 1, 2, 3 }); // 0
		test(new Integer[] { 1, 2, null, 3, null, 4, null, 5, null, 6, null }); // 10
		test(new Integer[] { 1, null, 2, null, 3, null, 4, null, 5, null, 6, null }); // 10
		test(new Integer[] { 6, 7, 8, 2, 7, 1, 3, 9, null, 1, 4, null, null, null, 5 }); // 18
	}

	private static void test(final Integer[] array) {
		TreeNode root = TreeUtils.createTree(array);
		System.out.println(sumEvenGrandparent(root));
	}

	private static int sumEvenGrandparent(TreeNode root) {
		int[] sum = new int[1];
		getSum(root, false, false, sum);
		return sum[0];
	}

	private static void getSum(TreeNode root, boolean isParentEven, boolean isGrandParentEven, int[] sum) {
		if (root == null) {
			return;
		}
		if (isGrandParentEven) {
			sum[0] += root.val;
		}
		boolean isCurrentNodeEven = (root.val & 1) == 0;
		getSum(root.left, isCurrentNodeEven, isParentEven, sum);
		getSum(root.right, isCurrentNodeEven, isParentEven, sum);
	}
}
