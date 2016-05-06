package com.leetcode.oj;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IntegerBreak {
	
	/*
	private final Map<Integer, Integer> cache = new HashMap<>();
    public int integerBreak(int n) {
        Stack<Integer> lefts  = new Stack<>();
        Stack<Integer> rights = new Stack<>();
int count = 0;
        for (int i = 1; i <= n/2; i++) {
            lefts.push(i);
            rights.push(n - i);
            count++;
        }

        while (!lefts.isEmpty()) {
            Integer left = lefts.peek();
            Integer lmax = cache.get(left);
            if (lmax == null) {
                cache.put(left, left);
                for (int i = 1; i <= left/2; i++) {
                    lefts.push(i);
                    rights.push(left - i);
                    count++;
                }
            } else {
                Integer right = rights.peek();
                Integer rmax = cache.get(right);
                if (rmax == null) {
                    cache.put(right, right);
                    for (int i = 1; i <= right/2; i++) {
                        lefts.push(i);
                        rights.push(right - i);
                        count++;
                    }
                } else {
                    lefts.pop();
                    rights.pop();
                    Integer p = lmax * rmax;
                    int num = left + right;
                    Integer max = cache.get(num);
                    // CATCH: must check max == null which is always try with key=n. 
                    if (max == null || p > max)
                        cache.put(num, p);
                }
            }
        }
System.out.println("count=" + count);
        return cache.get(n);
    }
    */
    
    private final Map<Integer, Integer> cache = new HashMap<>();
private int count = 0;
    public int integerBreak(int n) {
        int max = Integer.MIN_VALUE;
        // for first recursion, we must break n
        for (int i = 1; i <= n/2; i++)
            max = Math.max(max, recur(i)*recur(n-i));
System.out.println("count=" + count);
        return max;
    }
    
    private int recur(int n) {
count++;
        if (n == 0)
            return 1;
        Integer max = cache.get(n);
        if (max != null)
            return max;
        // for subsequent recursions, we can either break n or not
        max = n;
        for (int i = 1; i <= n/2; i++)
            max = Math.max(max, recur(i)*recur(n-i));
        cache.put(n, max);
        return max;
    }
    
    
    public static void main(String[] args) {
    	IntegerBreak instance = new IntegerBreak();
    	int n;
    	
    	// 1
//    	n = 2;
    	// 2
//    	n = 3;
    	// 36
    	n = 10;
    	// 1
//    	n = 28;
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.integerBreak(n);
    	long t2 = System.currentTimeMillis();
    	System.out.println(result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}

}
