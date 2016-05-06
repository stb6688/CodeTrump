package com.leetcode.oj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ContainsDuplicateIII {
	
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Comparator<Integer> reverseComp = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        };
        PriorityQueue<Integer> q1 = new PriorityQueue<>();
        PriorityQueue<Integer> q2 = new PriorityQueue<>(reverseComp);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!q1.isEmpty()) {
System.out.println("num=" + num + ", min=" + q1.peek() + ", max=" + q2.peek());
                if (Math.abs(num - q1.peek()) <= t || Math.abs(num - q2.peek()) <= t)
                    return true;
            }
            q1.add(num);
            q2.add(num);
            if (q1.size() > k) {
                q1.remove(nums[i - k]);
                q2.remove(nums[i - k]);
            }
        }
        
        return false;
    }
	
	// Solution I: TLE
	/*
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int d = 1; d <= k && i + d < nums.length; d++) {
            	int diff = nums[i + d] - nums[i];
                if (diff <= t)
                    return true;
            }
        }
        return false;
    }
    */
	
	public static void main(String[] args) {
		ContainsDuplicateIII instance = new ContainsDuplicateIII();
		int[] nums;
		int k, t;
		
		// true
		nums = new int[]{-1, -1};
		k = 1; t = 0;
		
		// false
//		nums = new int[]{-1, -1};
//		k = 1; t = -1;
		
		// false
//		nums = new int[]{4, 2};
//		k = 2; t = 1;
		
		
		long t1 = System.currentTimeMillis();
		boolean result = instance.containsNearbyAlmostDuplicate(nums, k, t);
		long t2 = System.currentTimeMillis();
		System.out.println("result=" + result);
		System.out.println(String.format("time=%,dms", (t2 - t1)));
	}
}
