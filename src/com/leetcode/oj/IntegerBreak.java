package com.leetcode.oj;

import java.util.HashMap;
import java.util.Map;

public class IntegerBreak {
	
	// memoization
    private Map<Integer, Integer> valMax = new HashMap<>();
    
    // recursion
    public int integerBreak(int n) {
        if (n == 0)
            return -1;
        if (n == 1)
            return 1;
        int max = n;
        for (int i = 1; i <= n/2; i++) {
            int p = integerBreak(i) * integerBreak(n - i);
            max = Math.max(max, p);
        }
        valMax.put(n, max);
        return max;
    }
    
    public static void main(String[] args) {
    	IntegerBreak instance = new IntegerBreak();
    	int n;
    	
    	// 1
//    	n = 2;
    	// 2
    	n = 3;
    	// 36
//    	n = 10;
    	// 1
//    	n = 28;
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.integerBreak(n);
    	long t2 = System.currentTimeMillis();
    	System.out.println(result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}

}
