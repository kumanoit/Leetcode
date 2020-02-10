package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * 
 * Approach: Create root node from first element. Now, as per the logic of BST,
 * all values lesser than root should be in left subtree and rest should be in
 * right subtree.
 * 
 * Complexity: 
 * Time: O(n^2): Since, this process repeats for n elements and
 * there is iteration in each recursive call. 
 * Space: Stack size = O(height) = O(lg(n))
 * 
 * @author kumanoit 11-Feb-2020 1:28:40 AM
 */
public class CreateBSTFromPreorder {

	public static void main(String[] args) {
		test(new Integer[] { 1 });
		test(new Integer[] { 8, 5, 1, 7, 10, 12 });
		test(new Integer[] { 1, 2, 3, 4, 5, 6 });
		test(new Integer[] { 6, 5, 4, 3, 2, 1 });
	}

	private static void test(final Integer[] preorder) {
		TreeUtils.printTree(construct(preorder, 0, preorder.length - 1));
	}

	private static TreeNode construct(Integer[] preorder, int start, int end) {
		if (end < start) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[start]);
		int i = start + 1;
		while (i <= end && preorder[i] < root.val) {
			i++;
		}
		root.left = construct(preorder, start + 1, i - 1);
		root.right = construct(preorder, i, end);
		return root;
	}
}
