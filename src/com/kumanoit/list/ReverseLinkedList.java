package com.kumanoit.list;

import com.kumanoit.list.utils.ListNode;
import com.kumanoit.list.utils.ListUtils;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * 
 * @author kumanoit
 * 29-Dec-2019 4:19:24 PM
 */
public class ReverseLinkedList {

	public static void main(String[] args) {
		test(new Integer[] {});
		test(new Integer[] { 1 });
		test(new Integer[] { 1, 2 });
		test(new Integer[] { 1, 2, 3 });
		test(new Integer[] { 1, 2, 3, 4, 5 });
	}

	private static void test(final Integer[] numbers) {
		ListNode head = ListUtils.createList(numbers);
		System.out.println("Original linked list: ");
		ListUtils.printList(head);
		System.out.println("Reversed list: ");
		ListUtils.printList(reverseList(head));
		System.out.println();
	}

	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode start = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return start;
	}
}
