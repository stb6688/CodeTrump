package com.leetcode.oj;

import com.leetcode.util.ListNode;
import com.leetcode.util.annotations.Leetcode;
import com.leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date="2016-06-09", tags={Tags.LIST}, url="https://leetcode.com/problems/swap-nodes-in-pairs/")
public abstract class SwapNodesInPairs {
	public abstract ListNode swapPairs(ListNode head);
	public static void main(String[] args) {
		SwapNodesInPairs instance = new SolutionV();
		ListNode head;
		
		head = ListNode.arrayToListNode(new int[]{1, 2, 3, 4});
		head = instance.swapPairs(head);
		System.out.print("result=");
		head.print();
	}
	
	// Solution V: Accepted
	// recursive solution
	static class SolutionV extends SwapNodesInPairs {
		public ListNode swapPairs(ListNode head) {
	        if (head == null || head.next == null)
	            return head;
	        ListNode next = head.next, next2 = next.next;
	        next.next = head;
	        head.next = swapPairs(next2);
	        return next;
	    }
	}
	
	// Solution III: Accepted
	// use only 1 dummy node.
	// swap 2 nodes, append swapped tuplet to dummy list. 
	static class SolutionIV extends SwapNodesInPairs {
		public ListNode swapPairs(ListNode head) {
	        ListNode dd = new ListNode(0), tail = dd;;
	        ListNode curr = head;
	        while (curr != null) {
	            ListNode next = curr.next;
	            if (next == null) {
	                tail.next = curr;
	                break;
	            } else {
	                ListNode next2 = next.next;
	                next.next = curr;
	                curr.next = null;
	                tail.next = next; // next becomes head after swap
	                tail = curr;
	                curr = next2;
	            }
	        }
	        return dd.next;
	    }
	}
	
		
	// Solution II: Accepted
	// put even & odd nodes into 2 lists;
	// then merge 2 lists into 1, but picking odd first then even.
	// this example uses the very standard steps (some of them can be simplified) 
	// for dummy-based list operation:
	// 1. move node from 1 list to another.
	// 2. merge 2 lists into 1.
	static class SolutionIII extends SwapNodesInPairs {
		public ListNode swapPairs(ListNode head) {
	        ListNode dd1 = new ListNode(0), tail1 = dd1;
	        ListNode dd2 = new ListNode(0), tail2 = dd2;
	        int i = 0;
	        ListNode curr = head;
	        while (curr != null) {
	            ListNode next = curr.next;
	            curr.next = null;
	            if (i%2 == 0) {
	                tail1.next = curr;
	                tail1 = curr;
	            } else {
	                tail2.next = curr;
	                tail2 = curr;
	            }
	            curr = next;
	            i++;
	        }
	        ListNode curr1 = dd1.next, curr2 = dd2.next;
	        ListNode dd = new ListNode(0), tail = dd;
	        while (curr1 != null) {
	            if (curr2 != null) {
	                ListNode next2 = curr2.next;
	                curr2.next = null;
	                tail.next = curr2;
	                tail = curr2;
	                curr2 = next2;
	            }
	            ListNode next1 = curr1.next;
	            curr1.next = null;
	            tail.next = curr1;
	            tail = curr1;
	            curr1 = next1;
	        }
	        return dd.next;
	    }
	}
	
	
	// Solution I: Accepted
	// maneuver without using dummy nodes; easy to make mistake with edge cases.
	static class SolutionII extends SwapNodesInPairs {
		public ListNode swapPairs(ListNode head) {
	        ListNode result = null;
	        ListNode prevTail = null;
	        ListNode curr = head;
	        while (curr != null) {
	            ListNode next = curr.next;
	            if (next == null) {
	                if (result == null)
	                    return curr;
	                else
	                    return result;
	            } else {
	                if (result == null) {
	                    result = next;
	                }
	            }
	            ListNode nextNext = next.next;
	            // reverse
	            if (prevTail != null) {
	                prevTail.next = next;
	            }
	            next.next = curr;
	            curr.next = nextNext;
	            prevTail = curr;
	            curr = nextNext;
	        }
	        return result;
	    }
	}
}
