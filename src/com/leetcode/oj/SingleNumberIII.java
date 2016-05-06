package com.leetcode.oj;

import java.util.Arrays;

public class SingleNumberIII {
	public int[] singleNumber(int[] nums) {
        int xor = nums[0];
        for (int i = 1; i < nums.length; i++)
            xor ^= nums[i];
        int[] results = new int[2];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int x = num ^ xor;
            if ((x ^ xor) == num) {
                results[index++] = num;
                if (index == 2)
                    break;
            }
        }
        return results;
    }
	
	public static void main(String[] args) {
		SingleNumberIII instance = new SingleNumberIII();
		int[] nums;
		
		nums = new int[]{0, 1};
		
		int[] results = instance.singleNumber(nums);
		System.out.println("results=" + Arrays.toString(results));
	}
}
