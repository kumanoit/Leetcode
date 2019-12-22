package com.kumanoit.tree;

import com.kumanoit.list.utils.ListNode;
import com.kumanoit.list.utils.ListUtils;

/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * 
 * Approach: Create tree in bottom up manner. Since, we need to create a
 * balanced BST, the best way is to first create left subtree with half of size
 * of array, then create root with middle element of array and then assign left
 * pointer to left subtree. Create right subtree with rest of numbers and assign
 * it to right subtree of root.
 * 
 * @author kumanoit 22-Dec-2019 5:06:13 PM
 */
public class CreateBSTFromSortedLinkedList {

	public static void main(String[] args) {
		test(new Integer[] { 1 });
		test(new Integer[] { 1, 2 });
		test(new Integer[] { 1, 2, 3 });
		test(new Integer[] { 1, 2, 3, 4 });
		test(new Integer[] { 1, 2, 3, 4, 5 });
		test(new Integer[] { 1, 2, 3, 4, 5, 6 });
		test(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
	}

	private static void test(Integer[] nums) {
		ListNode head = ListUtils.createList(nums);
		ListUtils.printList(head);
		TreeUtils.printTree(sortedListToBST(head));
	}

	public static TreeNode sortedListToBST(ListNode head) {
		int size = 0;
		ListNode ptr = head;
		while (ptr != null) {
			size++;
			ptr = ptr.next;
		}
		top = head;
		return createTree(size);
	}

	private static ListNode top;

	private static TreeNode createTree(int size) {
		if (size == 0) {
			return null;
		}
		TreeNode left = createTree(size / 2);
		TreeNode root = new TreeNode(top.val);
		top = top.next;
		root.left = left;
		root.right = createTree(size - size / 2 - 1);
		return root;
	}
}