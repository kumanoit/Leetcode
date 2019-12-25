package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 * 
 * Algorithm: Whenever there is a node with a child node call the same flatten
 * method on the child. Return the last node from flatten method and modify
 * pointers accordingly.
 * 
 * @author kumanoit 25-Dec-2019 11:38:46 PM
 */
public class FlattenMultilevlDoublyLinkedList {

	public static void main(String[] args) {

	}

	public DoublyLinkedListNode flatten(DoublyLinkedListNode head) {
		if (head == null) {
			return null;
		}
		flattenHelper(head);
		return head;
	}

	private DoublyLinkedListNode flattenHelper(DoublyLinkedListNode head) {
		DoublyLinkedListNode ptr = head;
		DoublyLinkedListNode prev = null;
		while (ptr != null) {
			if (ptr.child != null) {
				DoublyLinkedListNode next = flattenHelper(ptr.child);
				if (ptr.next != null) {
					ptr.next.prev = next;
				}
				next.next = ptr.next;
				ptr.next = ptr.child;
				ptr.child.prev = ptr;
				ptr.child = null;
			}
			prev = ptr;
			ptr = ptr.next;
		}
		return prev;
	}
}

class DoublyLinkedListNode {
	public int val;
	public DoublyLinkedListNode prev;
	public DoublyLinkedListNode next;
	public DoublyLinkedListNode child;
};
