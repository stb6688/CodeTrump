package com.leetcode.oj;

import java.util.HashMap;
import java.util.Map;

public class UglyNumberII {
	private Map<Integer, Boolean> cache = new HashMap<>();
    
    public int nthUglyNumber(int n) {
        int result = 1, count = 1, i = 2;
        while (count < n) {
            if (isUgly(i)) {
                result = i;
                count++;
            }
            i++;
        }
        return result;
    }
    
    private boolean isUgly(int n) {
    	Boolean result = cache.get(n);
    	if (result != null)
			return result;
        result = (n == 1 
				|| n%2 == 0 && isUgly(n/2)) 
                || (n%3 == 0 && isUgly(n/3)) 
                || (n%5 == 0 && isUgly(n/5));
        cache.put(n, result);
        return result;
    }
    
    public static void main(String[] args) {
    	UglyNumberII instance = new UglyNumberII();
    	int n;
    	
//    	n = 2; // 2
//    	n = 277; // 60000
    	n = 306;

    	long t1 = System.currentTimeMillis();
    	int result = instance.nthUglyNumber(n);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}

}
