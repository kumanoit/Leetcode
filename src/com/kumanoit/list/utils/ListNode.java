package com.kumanoit.list.utils;

public class ListNode {

	public Integer val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}
}
