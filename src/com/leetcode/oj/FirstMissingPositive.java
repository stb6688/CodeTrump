package com.leetcode.oj;

import com.leetcode.util.ArrayUtil;

public abstract class FirstMissingPositive {
	public abstract int firstMissingPositive(int[] nums);
	public static void main(String[] args) {
		FirstMissingPositive instance = new SolutionI();
		int[] nums;
		int result;
		
		nums = ArrayUtil.str2intArray("[3,4,-1,1]");
		
		result = instance.firstMissingPositive(nums);
		System.out.println("result=" + result);
	}
	
	
	// Solution II: Accepted
    // slight improvement from solution I
	static class SolutionII extends FirstMissingPositive {
		public int firstMissingPositive(int[] nums) {
	        for (int i = 0; i < nums.length; i++) {
	            int num = nums[i];
	            if (num > 0 && i != num - 1) {
	                nums[i] = 0;
	                while (true) {
	                    int idx = num - 1;
	                    if (idx >= nums.length || nums[idx] == num)
	                        break;
	                    if (nums[idx] <= 0) {
	                        nums[idx] = num;
	                        break;
	                    } else {
	                        int next = nums[idx];
	                        nums[idx] = num;
	                        num = next;
	                    }
	                }
	            }
	        }
	        int idx = 0;
	        while (idx < nums.length && nums[idx] > 0) idx++;
	        return idx + 1;
	    }
	}

	
	// Solution I: Accepted
	static class SolutionI extends FirstMissingPositive {
		public int firstMissingPositive(int[] nums) {
	        for (int i = 0; i < nums.length; i++) {
	            int num = nums[i];
	            if (num > 0 && i != num-1) {
	                nums[i] = 0;
	                while (true) { // give num, place it to the righ idx
	                    int idx = num-1;
	                    if (idx >= nums.length || nums[idx] == num)
	                        break;
	                    else if (nums[idx] <= 0) {
	                        nums[idx] = num;
	                        break;
	                    } else {
	                        int next = nums[idx];
	                        nums[idx] = num;
	                        num = next;
	                    }
	                }
	            }
	        }
	        int idx = 0;
	        while (idx < nums.length && nums[idx] > 0)
	            idx++;
	        return idx+1;
	    }
	}
}
