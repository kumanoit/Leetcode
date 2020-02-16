package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 * This is a generic solution which is applicable to all sort of trees and it
 * keeps node in inorder traversal.
 * 
 * Approach: Do inorder traversal of tree and keep note of last node. Use last
 * visited node to assign pointers to left and right nodes.
 * 
 * Complexity:
 * Time: O(n): all nodes are visited once
 * Space: Stack space: O(lg(n)): height of tree
 * 
 * @author kumanoit 16-Feb-2020 9:26:12 PM
 */
public class ConvertBinaryTreeToSortedDoublyLinkedList {

	private static TreeNode lastNode = null;

	public static void main(String[] args) {
		test(new Integer[] {});
		test(new Integer[] { 1 });
		test(new Integer[] { 1, 2, 3, 4 });
		test(new Integer[] { 4, 2, 5, 1, 3 });
		test(new Integer[] { 1, null, 2, null, 3, null, 4, null, 5 });
		test(new Integer[] { 1, 2, null, 3, null, 4, null, 5 });
	}

	private static void test(final Integer[] nodes) {
		TreeNode root = TreeUtils.createTree(nodes);
		TreeUtils.printTree(root);
		lastNode = null;
		createDoublyLinkedList(root);
		if (root == null) {
			return;
		}
		System.out.println("Left to right....");
		while (root.left != null) {
			root = root.left;
		}
		lastNode = root;
		while (root != null) {
			System.out.print(root.val + ", ");
			lastNode = root;
			root = root.right;
		}
		System.out.println("\nRight to left....");
		while (lastNode != null) {
			System.out.print(lastNode.val + ", ");
			lastNode = lastNode.left;
		}
		System.out.println("\n==========================================");
	}

	private static void createDoublyLinkedList(final TreeNode root) {
		if (root == null) {
			return;
		}
		createDoublyLinkedList(root.left);
		if (lastNode != null) {
			lastNode.right = root;
			root.left = lastNode;
		}
		lastNode = root;
		createDoublyLinkedList(root.right);
	}
}
