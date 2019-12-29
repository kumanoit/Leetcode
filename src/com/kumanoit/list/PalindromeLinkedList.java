package com.kumanoit.list;

import com.kumanoit.list.utils.ListNode;
import com.kumanoit.list.utils.ListUtils;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 * 
 * @author kumanoit
 * 29-Dec-2019 4:24:21 PM
 */
public class PalindromeLinkedList {

	private static ListNode start;

	public static void main(String[] args) {
		test(new Integer[] {});
		test(new Integer[] { 1 });
		test(new Integer[] { 1, 2 });
		test(new Integer[] { 1, 2, 1 });
		test(new Integer[] { 1, 2, 3, 4 });
		test(new Integer[] { 1, 2, 2, 1 });
	}

	private static void test(final Integer[] numbers) {
		ListNode head = ListUtils.createList(numbers);
		System.out.println("Original linked list: ");
		ListUtils.printList(head);
		System.out.println("Is Palindrome? " + isPalindrome(head));
		System.out.println();
	}

	private static boolean isPalindrome(ListNode head) {
		if (head == null) {
			return true;
		}
		start = head;
		return isPalindromeWrapper(head);
	}

	private static boolean isPalindromeWrapper(ListNode tail) {
		if (tail == null) {
			return true;
		}
		if (!isPalindromeWrapper(tail.next) || start.val != tail.val) {
			return false;
		}
		start = start.next;
		return true;
	}
}
