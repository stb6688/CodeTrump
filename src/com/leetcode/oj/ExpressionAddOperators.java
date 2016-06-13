package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class ExpressionAddOperators {
	public abstract List<String> addOperators(String num, int target);
	
	public static void main(String[] args) {
		ExpressionAddOperators instance = new SolutionIII();
		String num; int target;
		List<String> results;
		
		// 2*3+2, 2+2*3
//		num = "232"; target = 8;
		
		// 1*0 + 5, 10-5
		num = "105"; target = 5;
		
		
		results = instance.addOperators(num, target);
		System.out.println("results=" + results);
	}
	
	
	static class SolutionIII extends ExpressionAddOperators {
		private static final char[] ops = {'+', '-', '*'};
	    public List<String> addOperators(String num, int target) {
	        if (num == null || num.isEmpty())
	            return Collections.emptyList();
	        List<StringBuilder> paths = build(num.toCharArray(), 0, num.length()-1);
	        List<String> results = new ArrayList<>();
	        for (StringBuilder path : paths) {
	            if (eval(path) == target)
	                results.add(path.toString());
	        }
	        return results;
	    }
	    
	    private Map<List<Integer>, List<StringBuilder>> cache = new HashMap<>();
	    private List<StringBuilder> build(char[] chars, int l, int r) {
	        List<Integer> key = new ArrayList<>(2);
	        key.add(l);
	        key.add(r);
	        List<StringBuilder> results = cache.get(key);
	        if (results != null)
	            return results;
	        results = new ArrayList<>();
	        if (chars[l] != '0' || l == r) {
		        StringBuilder result0 = new StringBuilder();
		        for (int i = l; i <= r; result0.append(chars[i++]));
		        results.add(result0);
	        }
	        for (int i = l; i < r; i++) {
	            List<StringBuilder> lsubs = build(chars, l, i);
	            List<StringBuilder> rsubs = build(chars, i+1, r);
	            for (StringBuilder lsub : lsubs) {
	                for (StringBuilder rsub : rsubs) {
	                    for (char op : ops) {
	                        StringBuilder result = new StringBuilder();
	                        results.add(result.append(lsub).append(op).append(rsub));
	                    }
	                }
	            }
	        }
	        cache.put(key, results);
	        return results;
	    }
	    
	    private int eval(StringBuilder path) {
	        Deque<Integer> nums = new LinkedList<>();
	        int[] array = getNum(path, 0);
	        nums.add(array[0]);
	        int i = array[1];
	        while (i < path.length()) {
	            char op = path.charAt(i);
	            array = getNum(path, i+1);
	            int num = array[0];
	            i = array[1];
	            if (op == '+')
	                nums.add(num);
	            else if (op == '-')
	                nums.add(-num);
	            else
	                nums.add(nums.removeLast()*num);
	        }
	        int result = 0;
	        while (!nums.isEmpty())
	            result += nums.removeLast();
	        return result;
	    }
	    
	    private int[] getNum(StringBuilder path, int i) {
	        int num = 0;
	        while (i < path.length()) {
	            char ch = path.charAt(i);
	            if (ch >= '0' && ch <= '9') {
	                num = num*10 + (ch - '0');
	                i++;
	            } else
	                break;
	        }
	        return new int[]{num, i};
	    }
	}
	
	
	// Solution II: Misunderstand Problem
    static class SolutionII extends ExpressionAddOperators {
	    private static final char[] ops = {'+', '-', '*'};
	    public List<String> addOperators(String num, int target) {
	        if (num == null || num.isEmpty())
	            return Collections.emptyList();
	        // bfs build all possible combinations of nums & operators
	        List<StringBuilder> paths = new ArrayList<>();
	        StringBuilder path0 = new StringBuilder();
	        path0.append(num.charAt(0));
	        paths.add(path0);
	        for (int i = 1; i < num.length(); i++) {
	            List<StringBuilder> nextPaths = new ArrayList<>();
	            int curr = num.charAt(i) - '0';
	            for (StringBuilder path : paths) {
	                for (char op : ops) {
	                    StringBuilder nextPath = new StringBuilder(path);
	                    nextPath.append(op).append(curr);
	                    nextPaths.add(nextPath);
	                }
	            }
	            paths = nextPaths;
	        }
	        List<String> results = new ArrayList<>();
	        for (StringBuilder path : paths) {
	            if (eval(path) == target)
	                results.add(path.toString());
	        }
	        return results;
	    }
	    
	    private int eval(StringBuilder path) {
	        Stack<Integer> stack = new Stack<>();
	        stack.push(path.charAt(0) - '0');
	        int i = 1;
	        while (i < path.length()) {
	            char op = path.charAt(i);
	            int num = path.charAt(i+1) - '0';
	            if (op == '+')
	                stack.push(num);
	            else if (op == '-')
	                stack.push(-num);
	            else
	                stack.push(stack.pop()*num);
	            i += 2;
	        }
	        int result = 0;
	        while (!stack.isEmpty())
	            result += stack.pop();
	        return result;
	    }
    }
    
    
    /*
    private static final char[] ops = {'+', '-', '*'};
    public List<String> addOperators(String num, int target) {
        if (num == null || num.isEmpty())
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
    */
	

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
