package com.leetcode.oj;

import java.util.Stack;


public abstract class MiniParser {
	public abstract NestedInteger deserialize(String s);
	public static void main(String[] args) {
		MiniParser instance = new SolutionI();
		String s;
		NestedInteger res;
		
		s = "[123,456,[788,799,833],[[]],10,[]]";
		res = instance.deserialize(s);
		
		System.out.println("result=");
	}
	
	static class SolutionI extends MiniParser {
		public NestedInteger deserialize(String s) {
	        if (s.isEmpty())
	            return null;
	        Stack<NestedInteger> stack = new Stack<>();
	        NestedInteger curr = null;
	        int l = 0;
	        for (int r = 0; r < s.length(); r++) {
	            char ch = s.charAt(r);
	            if (ch == '[') {
	                if (curr != null) {
	                    stack.push(curr);
	                }
	                curr = new NestedInteger();
	                l = r+1;
	            } else if (ch == ']') {
	                String num = s.substring(l, r);
	                if (!num.isEmpty())
	                    curr.add(new NestedInteger(Integer.valueOf(num)));
	                if (!stack.isEmpty()) {
	                    NestedInteger pop = stack.pop();
	                    pop.add(curr);
	                    curr = pop;
	                }
	                l = r+1;
	            } else if (ch == ',') {
	            	if (s.charAt(r-1) != ']') {
	            		String num = s.substring(l, r);
System.out.println("num=" + num);
if (num.equals("799"))
	System.out.println();
	                	curr.add(new NestedInteger(Integer.valueOf(num)));
	            	}
	                l = r+1;
	            }
	        }
	        
	        return curr;
	    }
	}
}
