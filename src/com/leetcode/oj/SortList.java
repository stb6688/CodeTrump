package com.leetcode.oj;

import com.leetcode.util.ListNode;

public abstract class SortList {
	public abstract ListNode sortList(ListNode head);
	public static void main(String[] args) {
		SortList instance = new SolutionI();
		ListNode head;
		ListNode res;
		
		head = ListNode.deserialize("[2,1]");
		
		res = instance.sortList(head);
		System.out.println("result=" + ListNode.serialize(res));
	}
	
	
	static class SolutionI extends SortList {
		public ListNode sortList(ListNode head) {
System.out.println("list=" + ListNode.serialize(head));
	        if (head == null || head.next == null)
	            return head;
	        ListNode slow = head, fast = head.next;
	        while (fast != null) {
	            slow = slow.next; // ERROR: infinity loop
	            if (fast.next == null)
	                break;
//	            slow = slow.next;
	            fast = fast.next.next;
	        }
System.out.println("slow=" + slow);
//	        ListNode head2 = slow.next;
			ListNode head2 = slow;
	        slow.next = null;
	        head = sortList(head);
	        head2 = sortList(head2);
	        
	        ListNode dd = new ListNode(0), n = dd;
	        while (head != null || head2 != null) {
	            if (head2 == null || (head != null && head.val < head2.val)) {
	                ListNode next = head.next;
	                head.next = n.next;
	                n.next = head;
	                n = head;
	                head = next;
	            } else {
	                ListNode next = head2.next;
	                head2.next = n.next;
	                n.next = head2;
	                n = head2;
	                head2 = next;
	            }
	        }
	        
	        return dd.next;
	    }
	}
}
