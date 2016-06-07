package com.leetcode.oj;

import java.util.List;
import java.util.PriorityQueue;

public abstract class IncreasingTripletSubsequence {
	public abstract boolean increasingTriplet(int[] nums);
	public static void main(String[] args) {
		IncreasingTripletSubsequence instance = new SolutionI();
		int[] nums;
		nums = new int[]{0,4,2,1,0,-1,-3};
		System.out.println("result=" + instance.increasingTriplet(nums));
	}
	
	static class SolutionI extends IncreasingTripletSubsequence {
		public boolean increasingTriplet(int[] nums) {
	        PriorityQueue<Integer> q = new PriorityQueue<>(3);
	        for (int num : nums) {
if (num == 2)
	System.out.println(num);
	            while (!q.isEmpty() && q.peek() >= num)
	                q.poll();
	            q.add(num);
	            if (q.size() == 3)
	                return true;
	        }
	        return false;
	    }
	}
}
