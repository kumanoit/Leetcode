package com.kumanoit.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 * 
 * Approach: Use first node from preorder to create a node. Use second element
 * from preorder and use it to find out the numbers which will lie in left
 * subtree and right subtree.
 * 
 * Complexity: Time: O(n^2): Since, this process repeats for n elements and
 * there is iteration in each recursive call. 
 * Space: Stack size = O(height) = O(lg(n))
 * 
 * Optimized Solution: Instead of iterating over postorder array to find index of
 * root value, create a hashmap and find out the index of root value.
 * Complexity:
 * Time: O(n) hashmap reduced iteration to find index in inorder array 
 * Space: O(n) space taken by hashmap
 * 
 * @author kumanoit 11-Feb-2020 1:11:25 AM
 */
public class CreateBinaryTreePreorderPostorder {

	public static void main(String[] args) {
		test(new Integer[] {}, new Integer[] {}); // empty tree
		test(new Integer[] { 1 }, new Integer[] { 1 }); // single node tree
		test(new Integer[] { 1, 2, 3, 4 }, new Integer[] { 4, 3, 2, 1 }); // left/right skewed tree
		test(new Integer[] { 1, 2, 4, 5, 3, 6, 7 }, new Integer[] { 4, 5, 2, 6, 7, 3, 1 }); // regular tree
	}

	private static void test(final Integer[] preorder, final Integer[] postorder) {
		System.out.println("====================================================");
		System.out.println("\nNaive Solution...");
		TreeUtils.printTree(createTree(preorder, postorder, 0, 0, preorder.length));
		Map<Integer, Integer> postorderMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < postorder.length; i++) {
			postorderMap.put(postorder[i], i);
		}
		System.out.println("\nOptimized solution...");
		TreeUtils.printTree(
				createTreeOptimized(preorder, postorder, 0, 0, postorder.length, postorderMap));
	}

	private static TreeNode createTree(final Integer[] pre, final Integer[] post, int preStart,
			int postStart, int size) {
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
		int leftTreeSize = nextPostStart - postStart + 1;
		root.left = createTree(pre, post, preStart + 1, postStart, leftTreeSize);
		root.right = createTree(pre, post, preStart + leftTreeSize + 1, nextPostStart + 1,
				size - leftTreeSize - 1);
		return root;
	}

	private static TreeNode createTreeOptimized(final Integer[] pre, final Integer[] post,
			int preStart, int postStart, int size, final Map<Integer, Integer> postorderMap) {
		if (size == 0) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preStart]);
		if (size == 1) {
			return root;
		}
		int nextPostStart = postorderMap.get(pre[preStart + 1]);
		int leftTreeSize = nextPostStart - postStart + 1;
		root.left = createTreeOptimized(pre, post, preStart + 1, postStart, leftTreeSize,
				postorderMap);
		root.right = createTreeOptimized(pre, post, preStart + leftTreeSize + 1, nextPostStart + 1,
				size - leftTreeSize - 1, postorderMap);
		return root;
	}
}
