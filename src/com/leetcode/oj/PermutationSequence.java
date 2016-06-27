package com.leetcode.oj;

import java.util.LinkedList;
import java.util.List;

public abstract class PermutationSequence {
	public abstract String getPermutation(int n, int k);
	public static void main(String[] args) {
		PermutationSequence instance = new SolutionI();
		int n, k;
		String result;
		long t1, t2;
		
//		n = 3; k = 1;
		
		n = 8; k = 8590;
		
		t1 = System.currentTimeMillis();
		result = instance.getPermutation(n, k);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%s, total time=%,dms", result, (t2-t1)));
	}
	
	static class SolutionI extends PermutationSequence {
		public String getPermutation(int n, int k) {
	        List<Integer> nums = new LinkedList<>();
	        int perm = 1;
	        for (int i = 1; i <= n; i++) {
	            nums.add(i);
	            perm *= i;
	        }
	        char[] chars = new char[n];
	        k--;
	        for (int i = 0; i < n; i++) {
	            perm /= nums.size();
	            int idx = 0;
	            if (perm > 0)
	                idx = k/perm;
	            k %= perm;
	            chars[i] = (char)('0' + nums.remove(idx));
	        }
	        return new String(chars);
	    }
	}
}
