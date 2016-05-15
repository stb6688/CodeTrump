package com.leetcode.oj;

public class HouseRobberII {
	
	private int max = 0;
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        boolean[] states = new boolean[nums.length];
        dp(nums, states, 0, 0, nums.length);
        return max;
    }
    
    private void dp(int[] nums, boolean[] states, int idx, int total, int numRemains) {
        int next = (idx + 1) % nums.length, prev = (nums.length + idx - 1) % nums.length;
        if (states[idx] || states[next] || states[prev]) {
            numRemains--;
            // termination
            if (numRemains == 0)
                return;
            else
                dp(nums, states, next, total, numRemains);
        } else { // this house is rob-able
            // not rob
            dp(nums, states, next, total, numRemains);
            // rob
            total += nums[idx];
            states[idx] = true;
            numRemains--;
            max = Math.max(max, total);
            dp(nums, states, next, total, numRemains);
            // restore
            states[idx] = false;
        }
    }
    
    public static void main(String[] args) {
    	HouseRobberII instance = new HouseRobberII();
    	int[] nums;
    	
    	nums = new int[]{0};
    	
    	int result = instance.rob(nums);
    	System.out.println("result=" + result);
	}

}
