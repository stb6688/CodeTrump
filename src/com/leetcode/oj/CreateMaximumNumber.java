package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leetcode.util.ArrayUtil;

public abstract class CreateMaximumNumber {
	public abstract int[] maxNumber(int[] nums1, int[] nums2, int k);
	public static void main(String[] args) {
		CreateMaximumNumber instance = new SolutionI();
		int[] nums1, nums2; int k;
		int[] result;
		long t1, t2;
		
		nums1 = ArrayUtil.str2intArray("[3, 4, 6, 5]");
		nums2 = ArrayUtil.str2intArray("[9, 1, 2, 5, 8, 3]");
		k = 5;
		
		t1 = System.currentTimeMillis();
		result = instance.maxNumber(nums1, nums2, k);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%s, total time=%,dms", Arrays.toString(result), (t2 - t1)));
	}
	
	static class SolutionI extends CreateMaximumNumber {
		// nums1.length-i+nums2.length-i >= k
	    // i+j <= nums1.length + nums2.length - k 
	    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        int maxSum = nums1.length + nums2.length - k;
	        return help(nums1, 0, nums2, 0, k, maxSum);
	    }

	    private Map<List<Integer>, int[]> cache = new HashMap<>();    
	    private int[] help(int[] nums1, int i, int[] nums2, int j, int k, int maxSum) {
	        List<Integer> key = new ArrayList<>(3);
	        key.add(i);
	        key.add(j);
	        key.add(k);
	        int[] result = cache.get(key);
	        if (result != null)
	            return result;
	        result = new int[k];
	        int maxDigit = Integer.MIN_VALUE;
	        List<List<Integer>> maxRanges = new ArrayList<>();
	        for (int x = i; x < nums1.length && x + j <= maxSum; x++) {
	            if (nums1[x] > maxDigit) {
	                maxDigit = nums1[x];
	                maxRanges.clear();
	                addRange(maxRanges, x+1, j);
	            } else if (nums1[x] == maxDigit) {
	                addRange(maxRanges, x+1, j);
	            }
	        }
	        for (int y = j; y < nums2.length && i + y <= maxSum; y++) {
	            if (nums2[y] > maxDigit) {
	                maxDigit = nums2[y];
	                maxRanges.clear();
	                addRange(maxRanges, i, y+1);
	            } else if (nums2[y] == maxDigit) {
	                addRange(maxRanges, i, y+1);
	            }
	        }
	        result[0] = maxDigit;
if (maxDigit == Integer.MIN_VALUE)
	System.out.println();
	        // termination & recursion
	        if (k > 1) {
	            int[] maxSub = new int[k-1];
	            Arrays.fill(maxSub, Integer.MIN_VALUE);
	            for (List<Integer> maxRange : maxRanges) {
	            	if (maxRange.get(0) + maxRange.get(1) <= maxSum)
	            		maxSub = max(maxSub, help(nums1, maxRange.get(0), nums2, maxRange.get(1), k-1, maxSum));
	            }
	            for (int x = 1; x < k; x++)
	                result[x] = maxSub[x-1];
	        }
	        cache.put(key, result);
	        return result;
	    }
	    
	    private void addRange(List<List<Integer>> ranges, int i, int j) {
	        List<Integer> range = new ArrayList<>(2);
	        range.add(i);
	        range.add(j);
	        ranges.add(range);
	    }
	    
	    private int[] max(int[] array1, int[] array2) {
	        for (int i = 0; i < array1.length; i++) {
	            if (array1[i] > array2[i])
	                return array1;
	            else if (array1[i] < array2[i])
	                return array2;
	        }
	        return array1;  // equal
	    }
	}
}
