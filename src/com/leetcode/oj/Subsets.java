package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subsets {
	private Map<Integer, List<List<Integer>>> cache = new HashMap<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = recur(0, nums);
        results.add(Collections.emptyList());
        return results;
    }
    
    private List<List<Integer>> recur(int index, int[] nums) {
        List<List<Integer>> results = cache.get(index);
        if (results != null)
            return results;
        results = new ArrayList<>();
        for (int i = index; i < nums.length; i++) {
            if (index == nums.length - 1) {
                List<Integer> result = new ArrayList<>();
                result.add(i);
                results.add(result);
            } else {
                List<List<Integer>> subs = recur(index+1, nums);
System.out.println("index=" + index + ", subs=" + subs);
                results.addAll(subs);
                for (List<Integer> sub : subs) {
                    List<Integer> result = new ArrayList<>();
                    result.add(i);
                    result.addAll(sub);
                    results.add(result);
                }
            }
        }
        
        cache.put(index, results);
        return results;
    }
    
    public static void main(String[] args) {
    	Subsets instance = new Subsets();
    	int[] nums;
    	
    	nums = new int[]{1,2,3};
//    	nums = new int[]{[1,2,3,4,5,6,7,8,10,0]};
    	
    	List<List<Integer>> results = instance.subsets(nums);
    	System.out.println(results);
	}
}
