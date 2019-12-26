package com.kumanoit.list.utils;

/**
 * https://leetcode.com/problems/rotate-list/ 
 * 
 * Time Complexity: O(n)
 * 
 * @author kumanoit 26-Dec-2019 11:52:51 PM
 */
public class RotateList {

	public static void main(String[] args) {
		test(new Integer[] { 1, 2, 3, 4, 5 }, 1);
		test(new Integer[] { 1, 2, 3, 4, 5 }, 2);
		test(new Integer[] { 1, 2, 3, 4, 5 }, 3);
		test(new Integer[] { 1, 2, 3, 4, 5 }, 4);
		test(new Integer[] { 1, 2, 3, 4, 5 }, 5);
		test(new Integer[] { 1, 2, 3, 4, 5 }, 6);
	}

	private static void test(final Integer[] nodes, int k) {
		ListNode head = ListUtils.createList(nodes);
		System.out.println("Original list: ");
		ListUtils.printList(head);
		System.out.println("After rotating by " + k + " : ");
		ListUtils.printList(rotateRight(head, k));
		System.out.println();
	}

	public static ListNode rotateRight(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		ListNode ptr = head;
		ListNode kptr = head;
		int count = 0;
		while (ptr != null) {
			count++;
			ptr = ptr.next;
		}
		k = k % count;
		while (k > 0) {
			kptr = kptr.next;
			k--;
		}
		ptr = head;
		while (kptr.next != null) {
			kptr = kptr.next;
			ptr = ptr.next;
		}
		kptr.next = head;
		head = ptr.next;
		ptr.next = null;
		return head;
	}
}
