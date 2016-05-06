package com.leetcode.oj;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UglyNumber {
	
	private Map<Integer, Boolean> cache = new HashMap<>();
    public boolean isUgly(int num) {
        if (num == 1)
            return true;
        Boolean result = cache.get(num);
        if (result != null)
            return result;
        result = true;
        boolean isPrime = true;
        for (int i = 2; i < num; i++) {
            if (num / i < i)
                break;
            if (num % i == 0) {
            	isPrime = false;
            	if (!isUgly(i) || !isUgly(num / i)) {
            		result = false;
            		break;
            	}
            }
        }
        if (isPrime)
            result = (num == 2 || num == 3 || num == 5);
        cache.put(num, result);
        return result;
    }
	
	/*
	private Map<Integer, Set<Integer>> numPrimes = new HashMap<>();
    private static final Set<Integer> factors = new HashSet<>();
    static {
        factors.add(2);
        factors.add(3);
        factors.add(5);
    }
    public boolean isUgly(int num) {
    	if (num == 1)
            return true;
        Set<Integer> primes = getPrimes(num);
        for (Integer prime : primes)
            if (!factors.contains(prime))
                return false;
        return true;
    }
    
    private Set<Integer> getPrimes(int num) {
        Set<Integer> primes = numPrimes.get(num);
        if (primes != null)
            return primes;
        primes = new HashSet<>();
        for (int i = 2; i < num; i++) {
            if (num / i < i)
                break;
            if (num % i == 0) {
                primes.addAll(getPrimes(i));
                primes.addAll(getPrimes(num/i));
            }
        }
        // num itself is prime
        if (primes.isEmpty())
            primes.add(num);
        numPrimes.put(num, primes);
        return primes;
    }
    */
    
    public static void main(String[] args) {
    	UglyNumber instance = new UglyNumber();
    	int num;
    	
    	num = 1;	// true
    	System.out.println("result=" + instance.isUgly(num));
    	
    	num = 4;	// true
    	System.out.println("result=" + instance.isUgly(num));
    	
    	num = 6;	// true
    	System.out.println("result=" + instance.isUgly(num));
    	
    	num = 8;	// true
    	System.out.println("result=" + instance.isUgly(num));
    	
    	num = 14;	// false
    	System.out.println("result=" + instance.isUgly(num));
	}
}
