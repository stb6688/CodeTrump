package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public abstract class ExpressionAddOperators {
	public abstract List<String> addOperators(String num, int target);
	
	public static void main(String[] args) {
		ExpressionAddOperators instance = new SolutionI();
		String num; int target;
		List<String> results;
		
		num = "232"; target = 8;
		results = instance.addOperators(num, target);
		System.out.println("results=" + results);
	}

	static class SolutionI extends ExpressionAddOperators {
		private static final char[] ops = {'+', '-', '*'};
	    public List<String> addOperators(String num, int target) {
	    	if (num == null || num.length() == 0)
	    		return Collections.emptyList();
	        char[] chars = num.toCharArray();
	        List<StringBuilder> builders = new ArrayList<>();
	        StringBuilder builder0 = new StringBuilder();
	        builder0.append(chars[0]);
	        builders.add(builder0);
	        int idx = 1;
	        while (idx < num.length()) {
	            List<StringBuilder> nextBuilders = new ArrayList<>();
	            char ch = chars[idx++];
	            for (StringBuilder builder : builders) {
	                for (char op : ops) {
	                    StringBuilder nextBuilder = new StringBuilder(builder);
	                    nextBuilder.append(op).append(ch);
	                    nextBuilders.add(nextBuilder);
	                }
	            }
	            builders = nextBuilders;
	        }
	        
System.out.println("builders:\n" + builders);
	        List<String> results = new ArrayList<>();
	        for (StringBuilder builder : builders) {
	            Deque<Character> q = new LinkedList<>();
	            for (int i = 0; i < builder.length(); i++) {
	                char ch = builder.charAt(i);
	                if (ch == '*')
	                    q.addLast((char)((q.pollLast() - '0')*(builder.charAt(++i) - '0') + '0'));
	                else
	                    q.addLast(ch); // SYNTAX: no add() for Deque, only addFirst() & addLast()
	            }
	            int val = q.pollFirst() - '0';
	            while (!q.isEmpty()) {
	                char op = q.pollFirst();
	                int val2 = q.pollFirst() - '0';
	                if (op == '+')
	                    val += val2;
	                else
	                    val -= val2;
	            }
	            if (val == target)
	                results.add(builder.toString());
	        }
	        
	        return results;
	    }
	}
}
