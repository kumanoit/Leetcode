package com.kumanoit.list.utils;

public class ListUtils {

	/**
	 * This method will create a linked list from given array 
	 * Time complexity O(n)
	 * Space complexity O(n)
	 * 
	 * @param array
	 * @return
	 */
	public static ListNode createList(Integer[] array) {
		if (array.length == 0) {
			return null;
		}
		ListNode head = new ListNode(array[0]);
		ListNode ptr = head;
		for (int i = 1; i < array.length; i++) {
			ptr.next = new ListNode(array[i]);
			ptr = ptr.next;
		}
		return head;
	}

	/**
	 * This method will iterate over all elements present in a list and print it.
	 * @param head
	 */
	public static void printList(final ListNode head) {
		if (head == null) {
			System.out.println("Your linked list is empty.");
			return;
		}
		ListNode ptr = head;
		while (ptr != null) {
			System.out.print(ptr.val + ", ");
			ptr = ptr.next;
		}
		System.out.println();
	}
}
