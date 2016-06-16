package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PermutationsII {
	public abstract List<List<Integer>> permuteUnique(int[] nums);
	public static void main(String[] args) {
		PermutationsII instance = new SolutionI();
		int[] nums;
		List<List<Integer>> results;
		
		nums = new int[]{1,1,2};
		
		results = instance.permuteUnique(nums);
		System.out.println("results=" + results);
	}
	
	
	// Solution I: Logic Error
	static class SolutionI extends PermutationsII {
		public List<List<Integer>> permuteUnique(int[] nums) {
	        if (nums == null || nums.length == 0)
	            return Collections.emptyList();
	        List<List<Integer>> lists = new ArrayList<>();
	        List<Integer> list0 = new ArrayList<>();
	        list0.add(nums[0]);
	        lists.add(list0);
	        for (int i = 1; i < nums.length; i++) {
	            List<List<Integer>> nextLists = new ArrayList<>();
	            for (List<Integer> list : lists) {
	                for (int j = 0; j <= list.size(); j++) {
	                    if (j-1 < 0 || list.get(j-1) == nums[i])
	                        continue;
	                    else {
	                        List<Integer> nextList = new ArrayList<>(list);
	                        nextList.add(j, nums[i]);
	                        nextLists.add(nextList);
	                    }
	                }
	            }
	            lists = nextLists;
	        }
	        return lists;
	    }
	}
}
