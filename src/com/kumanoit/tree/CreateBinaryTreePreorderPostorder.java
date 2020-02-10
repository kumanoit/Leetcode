package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 * 
 * Approach: Use first node from preorder to create a node. Use second element
 * from preorder and use it to find out the numbers which will lie in left
 * subtree and right subtree.
 * 
 * Complexity:
 * Time: O(n^2): Since, this process repeats for n elements and
 * there is iteration in each recursive call. 
 * Space: Stack size = O(height) = O(lg(n))
 * 
 * @author kumanoit 11-Feb-2020 1:11:25 AM
 */
public class CreateBinaryTreePreorderPostorder {

	public static void main(String[] args) {
		test(new Integer[] { 1 }, new Integer[] { 1 });
		test(new Integer[] { 1, 2 }, new Integer[] { 2, 1 });
		test(new Integer[] { 1, 2, 4, 5, 3, 6, 7 }, new Integer[] { 4, 5, 2, 6, 7, 3, 1 });
	}

	private static void test(final Integer[] preorder, final Integer[] postorder) {
		TreeUtils.printTree(createTree(preorder, postorder, 0, 0, preorder.length));
	}

	private static TreeNode createTree(final Integer[] pre, final Integer[] post, int preStart, int postStart, int size) {
		if (size == 0) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preStart]);
		if (size == 1) {
			return root;
		}
		int nextPostStart = postStart;
		while (pre[preStart + 1] != post[nextPostStart]) {
			nextPostStart++;
		}
		nextPostStart++;
		int leftTreeSize = nextPostStart - postStart;
		root.left = createTree(pre, post, preStart + 1, postStart, leftTreeSize);
		root.right = createTree(pre, post, preStart + leftTreeSize + 1, nextPostStart, size - leftTreeSize - 1);
		return root;
	}
}
