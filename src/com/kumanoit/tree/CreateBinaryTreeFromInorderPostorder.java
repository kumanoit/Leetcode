package com.kumanoit.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * 
 * Approach: Naive Solution: Create root node from last value present in
 * postorder traversal. Look for the index of root node's value in inorder
 * traversal. That will tell total nodes present in left subtree and right
 * subtree. Based on that index create left and right subtree. 
 * Complexity: 
 * Time: O(n^2) for each node there is iteration to find index in inorder array 
 * Space: Stack size = O(height) = O(lg(n))
 * 
 * Optimized Solution: Instead of iterating over inorder array to find index of
 * root value, create a hashmap and find out the index of root value.
 * Complexity:
 * Time: O(n) hashmap reduced iteration to find index in inorder array 
 * Space: O(n) space taken by hashmap
 * 
 * @author kumanoit 16-Feb-2020 4:09:30 PM
 */
public class CreateBinaryTreeFromInorderPostorder {

	public static void main(String[] args) {
		test(new Integer[] {}, new Integer[] {}); // empty tree
		test(new Integer[] { 1 }, new Integer[] { 1 }); // single node tree
		test(new Integer[] { 1, 2, 3, 4 }, new Integer[] { 4, 3, 2, 1 }); // right skewed tree
		test(new Integer[] { 4, 3, 2, 1 }, new Integer[] { 4, 3, 2, 1 }); // left skewed tree
		test(new Integer[] { 9, 3, 15, 20, 7 }, new Integer[] { 9, 15, 7, 20, 3 }); // normal tree
	}

	private static void test(final Integer[] inorder, final Integer[] postorder) {
		TreeUtils.printTree(createTree(inorder, postorder, 0, 0, inorder.length));
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		TreeUtils.printTree(createTreeOptimized(inorder, postorder, 0, 0, inorder.length, map));
	}

	private static TreeNode createTree(Integer[] inorder, Integer[] postorder, int inStart,
			int postStart, int n) {
		if (n <= 0) {
			return null;
		}
		TreeNode root = new TreeNode(postorder[postStart + n - 1]);
		int i = inStart;
		while (inorder[i] != postorder[postStart + n - 1]) {
			i++;
		}
		int leftTreeNodes = i - inStart;
		int rightTreeNodes = n - leftTreeNodes - 1;
		root.left = createTree(inorder, postorder, inStart, postStart, leftTreeNodes);
		root.right = createTree(inorder, postorder, i + 1, postStart + leftTreeNodes,
				rightTreeNodes);
		return root;
	}

	private static TreeNode createTreeOptimized(Integer[] inorder, Integer[] postorder, int inStart,
			int postStart, int n, final Map<Integer, Integer> nodeIndexMap) {
		if (n <= 0) {
			return null;
		}
		int rootValue = postorder[postStart + n - 1];
		TreeNode root = new TreeNode(rootValue);
		int i = nodeIndexMap.get(rootValue);
		int leftTreeNodes = i - inStart;
		int rightTreeNodes = n - leftTreeNodes - 1;
		root.left = createTreeOptimized(inorder, postorder, inStart, postStart, leftTreeNodes,
				nodeIndexMap);
		root.right = createTreeOptimized(inorder, postorder, i + 1, postStart + leftTreeNodes,
				rightTreeNodes, nodeIndexMap);
		return root;
	}
}
