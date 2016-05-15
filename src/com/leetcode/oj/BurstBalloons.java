package com.leetcode.oj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leetcode.util.ArrayUtil;

public class BurstBalloons {
	private Map<List<Integer>, Integer> listMax = new HashMap<>();
    public int maxCoins(int[] nums) {
        if (nums.length == 0)
            return 0;
        List<Integer> list = new ArrayList<>();
        for (int num : nums)
            list.add(num);
        return help(list);
    }
    
    private int help(List<Integer> list) {
        if (list.size() == 1)
            return list.get(0);
        else if (list.size() == 2) {
            int i0 = list.get(0), i1 = list.get(1);
            return i0*i1 + Math.max(i0, i1);
        }
        Integer max = listMax.get(list);
        if (max != null)
            return max;
        max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int curr = list.get(i);
            int left = 1;
            if (i-1 >= 0)
                left = list.get(i-1);
            int right = 1;
            if (i+1 < list.size())
                right = list.get(i+1);
            list.remove(i);
            int coins = left*curr*right + help(list);
            // List doesn't have insert(), only add().
            list.add(i, curr);
            max = Math.max(max, coins);
        }
        listMax.put(new ArrayList<Integer>(list), max);
//        listMax.put(list, max);
        return max;
    }
    
    public static void main(String[] args) {
    	BurstBalloons instance = new BurstBalloons();
    	int[] nums;
    	
    	// 167
//    	nums = ArrayUtil.str2intArray("[3, 1, 5, 8]");
    	
    	nums = ArrayUtil.str2intArray("[8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2]");
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.maxCoins(nums);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
}
