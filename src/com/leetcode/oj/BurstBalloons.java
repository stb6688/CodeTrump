package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leetcode.util.ArrayUtil;

public class BurstBalloons {
	
	private Map<Integer, Integer> cache = new HashMap<>();
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        return help(nums, 0, nums.length-1);
    }
    
    private int help(int[] nums, int l, int r) {
        // termination
        if (l == r)
            return nums[l];
        if (l > r || l < 0 || r >= nums.length)
            return 0;
        Integer key = l*nums.length + r;
        Integer max = cache.get(key);
        if (max != null)
            return max;
        max = 0;
        for (int i = l; i <= r; i++) {
            int coins = nums[i];
            if (i-1 >= l) // ERROR: i-1 >= 0
                coins *= nums[i-1];
            if (i+1 <= r) // ERROR: i+1 <= length
                coins *= nums[i+1];
            coins += help(nums, l, i-1) + help(nums, i+1, r);
            max = Math.max(max, coins);
        }
        cache.put(key, max);
        return max;
    }
    
    public static void main(String[] args) {
    	BurstBalloons instance = new BurstBalloons();
    	int[] nums;
    	
    	// 167
    	nums = ArrayUtil.str2intArray("[3, 1, 5, 8]");
    	
//    	nums = ArrayUtil.str2intArray("[8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2]");
    	
//    	nums = ArrayUtil.str2intArray("[1,6,8,3,4,6,4,7,9,8,0,6,2,8]");
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.maxCoins(nums);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
}
