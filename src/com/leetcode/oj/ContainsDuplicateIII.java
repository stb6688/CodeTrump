package com.leetcode.oj;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class ContainsDuplicateIII {
	
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 0 || t < 0)
            return false;
        TreeMap<Long, Integer> numCount = new TreeMap<>();
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (size == k + 1) {
                Long old = (long)nums[i-1-k];
                int count = numCount.get(old) - 1;
                if (count == 0)
                    numCount.remove(old);
                else
                    numCount.put(old, count);
                size--;
            }
            
            int num = nums[i];
            if (!numCount.subMap((long)num-t, true, (long)num+t, true).isEmpty())
                return true;
            
            Integer count = numCount.get((long)num);
            if (count == null)
                count = 0;
            numCount.put((long)num, count + 1);
            size++;
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
