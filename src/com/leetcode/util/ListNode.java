package com.leetcode.util;

import java.util.HashSet;
import java.util.Set;

public class ListNode {
	public int val;
	public ListNode next;
	public ListNode(int val) {
		this.val = val;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("value=").append(val).append(",next=");
		if (next == null)
			builder.append("null");
		else
			builder.append(next.val);
		return builder.toString();
	}
	
	public static void print(ListNode head) {
		if (head == null)
			return;
		boolean first = true;
		ListNode node = head;
		while(node != null) {
			if (first) {
				System.out.print(node.val);
				first = false;
			} else {
				System.out.print(", " + node);
			}
			node = node.next;
		}
		System.out.println();
	}
	
	public static void printAndCatchLoop(ListNode head) {
		Set<ListNode> nodes = new HashSet<ListNode>();
		if (head == null)
			return;
		boolean first = true;
		ListNode node = head;
		while(node != null) {
			if (first) {
				System.out.print(node.val);
				first = false;
			} else {
				System.out.print(", " + node);
				if (nodes.contains(node))
					System.out.println("!LOOP!");
			}
			node = node.next;
		}
		System.out.println();
	}
	
	public void print() {
		ListNode node = this;
		StringBuilder builder = new StringBuilder();
		builder.append(node.val);
		node = node.next;
		while (node != null) {
			builder.append(",").append(node.val);
			node = node.next;
		}
		System.out.println(builder);
	}
	
	public static ListNode arrayToListNode(int[] array) {
		if (array == null || array.length == 0)
			return null;
		ListNode head = null;
		ListNode prev = null, node = null;
		for (int val : array) {
			node = new ListNode(val);
			if (head == null)
				head = node;
			else
				prev.next = node;
			prev = node;
		}
		
		return head;
	}
}
