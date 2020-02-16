package com.kumanoit.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 
 * Approach: Naive Solution: Create root node from first value present in
 * preorder traversal. Look for the index of root node's value in inorder
 * traversal. That will tell total nodes present in left subtree and right
 * subtree. Based on that index create left and right subtree. 
 * Complexity: 
 * Time: O(n^2) for each node there is iteration to find index in inorder array 
 * Space: O(1) no extra space is taken
 * 
 * Optimized Solution: Instead of iterating over inorder array to find index of
 * root value, create a hashmap and find out the index of root value.
 * Complexity:
 * Time: O(n) hashmap reduced iteration to find index in inorder array 
 * Space: O(n) space taken by hashmap
 * 
 * @author kumanoit
 * 16-Feb-2020 4:59:12 PM
 */
public class CreateBinaryTreeFromInorderPreorder {

	public static void main(String[] args) {
		test(new Integer[] {}, new Integer[] {}); // empty tree
		test(new Integer[] { 1 }, new Integer[] { 1 }); // single node tree
		test(new Integer[] { 1, 2, 3, 4 }, new Integer[] { 1, 2, 3, 4 }); // right skewed tree
		test(new Integer[] { 1, 2, 3, 4 }, new Integer[] { 4, 3, 2, 1 }); // left skewed tree
		test(new Integer[] { 3, 9, 20, 15, 7 }, new Integer[] { 9, 3, 15, 20, 7 }); // normal tree
	}

	private static void test(final Integer[] preorder, final Integer[] inorder) {
		System.out.println("====================================================");
		System.out.println("\nNaive Solution...");
		TreeUtils.printTree(createTree(preorder, inorder, 0, 0, inorder.length));
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		System.out.println("\nOptimized solution...");
		TreeUtils.printTree(createTreeOptimized(preorder, inorder, 0, 0, inorder.length, map));
	}

	private static TreeNode createTree(final Integer[] preorder, final Integer[] inorder,
			final int preStart, final int inStart, final int size) {
		if (size == 0) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preStart]);
		int i = inStart;
		while (preorder[preStart] != inorder[i]) {
			i++;
		}
		int leftTreeNodesCount = i - inStart;
		int rightTreeNodesCount = size - leftTreeNodesCount - 1;
		root.left = createTree(preorder, inorder, preStart + 1, inStart, leftTreeNodesCount);
		root.right = createTree(preorder, inorder, preStart + leftTreeNodesCount + 1, i + 1,
				rightTreeNodesCount);
		return root;

	}

	private static TreeNode createTreeOptimized(final Integer[] preorder, final Integer[] inorder,
			final int preStart, final int inStart, final int size,
			final Map<Integer, Integer> inorderMap) {
		if (size == 0) {
			return null;
		}

		TreeNode root = new TreeNode(preorder[preStart]);
		int i = inorderMap.get(preorder[preStart]);
		int leftTreeNodesCount = i - inStart;
		int rightTreeNodesCount = size - leftTreeNodesCount - 1;
		root.left = createTreeOptimized(preorder, inorder, preStart + 1, inStart,
				leftTreeNodesCount, inorderMap);
		root.right = createTreeOptimized(preorder, inorder, preStart + leftTreeNodesCount + 1,
				i + 1, rightTreeNodesCount, inorderMap);
		return root;
	}

}