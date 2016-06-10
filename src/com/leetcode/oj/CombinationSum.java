package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CombinationSum {
	public abstract List<List<Integer>> combinationSum(int[] candidates, int target);
	public static void main(String[] args) {
		CombinationSum instance = new SolutionII();
		int[] candidates; int target;
		List<List<Integer>> results;
		
//		candidates = new int[]{2, 3, 6, 7};
//		target = 7;
//		results = instance.combinationSum(candidates, target);
//		System.out.println("results=" + results);
		
		candidates = new int[]{1, 2};
		target = 4;
		results = instance.combinationSum(candidates, target);
		System.out.println("results=" + results);
	}
	
	static class SolutionII extends CombinationSum {
		private Map<Integer, List<List<Integer>>> cache = new HashMap<>();
	    public List<List<Integer>> combinationSum(int[] candidates, int target) {
	        Arrays.sort(candidates);
	        return help(candidates, target);
	    }
	    
	    public List<List<Integer>> help(int[] candidates, int target) {
	        List<List<Integer>> results = cache.get(target);
	        if (results != null)
	            return results;
	        results = new ArrayList<>();
	        for (int i = 0; i < candidates.length; i++) {
	            int candidate = candidates[i];
	            // termination
	            if (candidate == target) {
	                List<Integer> result = new ArrayList<>();
	                result.add(candidate);
	                results.add(result);
	                continue;
	            }
	            if (i - 1 >= 0 && candidates[i] == candidates[i-1])
	                continue;
	            int val = target - candidate;
	            if (val < candidate)
	                continue;
	            List<List<Integer>> subResults = help(candidates, val);
	            for (List<Integer> subResult : subResults) {
	                if (candidate > subResult.get(0))
	                    break;
	                List<Integer> result = new ArrayList<>();
	                result.add(candidate);
	                result.addAll(subResult);
	                results.add(result);
	            }
	        }
System.out.println("target=" + target + ", results=" + results);
	        cache.put(target, results);
	        return results;
	    }
	}
	
	
	// Solution I: Logic Error
	static class SolutionI extends CombinationSum {
		private Map<Integer, List<List<Integer>>> cache = new HashMap<>();
	    public List<List<Integer>> combinationSum(int[] candidates, int target) {
	        Arrays.sort(candidates);
	        return help(candidates, target);
	    }
	    
	    public List<List<Integer>> help(int[] candidates, int target) {
	        List<List<Integer>> results = cache.get(target);
	        if (results != null)
	            return results;
	        results = new ArrayList<>();
	        for (int i = 0; i < candidates.length; i++) {
	            int candidate = candidates[i];
	            // termination
	            if (candidate == target) {
	                List<Integer> result = new ArrayList<>();
	                result.add(candidate);
	                results.add(result);
	                continue;
	            }
	            if (i - 1 >= 0 && candidates[i] == candidates[i-1])
	                continue;
	            int val = target - candidate;
	            if (val < target)
	                break;
	            List<List<Integer>> subResults = help(candidates, val);
	            for (List<Integer> subResult : subResults) {
	                if (subResult.get(0) > candidate)
	                    break;
	                List<Integer> result = new ArrayList<>();
	                result.add(candidate);
	                result.addAll(subResult);
	                results.add(result);
	            }
	        }
	        cache.put(target, results);
	        return results;
	    }
	}
}
