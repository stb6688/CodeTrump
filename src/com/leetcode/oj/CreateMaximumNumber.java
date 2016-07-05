package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.leetcode.util.ArrayUtil;

public abstract class CreateMaximumNumber {
	public abstract int[] maxNumber(int[] nums1, int[] nums2, int k);
	public static void main(String[] args) {
		CreateMaximumNumber instance = new SolutionIII();
		int[] nums1, nums2; int k;
		int[] result;
		long t1, t2;
		
		// 9,8,6,5,3
//		nums1 = ArrayUtil.str2intArray("[3, 4, 6, 5]");
//		nums2 = ArrayUtil.str2intArray("[9, 1, 2, 5, 8, 3]");
//		k = 5;
		
		// 6,7,6,0,4
//		nums1 = ArrayUtil.str2intArray("[6, 7]");
//		nums2 = ArrayUtil.str2intArray("[6, 0, 4]");
//		k = 5;
		
		// 9,8,9
//		nums1 = ArrayUtil.str2intArray("[3, 9]");
//		nums2 = ArrayUtil.str2intArray("[8, 9]");
//		k = 3;
		
		t1 = System.currentTimeMillis();
		result = instance.maxNumber(nums1, nums2, k);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("result=%s, total time=%,dms", Arrays.toString(result), (t2 - t1)));
	}
	
	
	static class SolutionIII extends CreateMaximumNumber {
		public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        if (k == 0)
	            return new int[0];
	        int[] result = new int[k];
	        List<int[]> pairs = new LinkedList<>();
	        int[] pair0 = {0, 0};
	        pairs.add(pair0);
	        for (int i = 0; i < k; i++) {
	            List<int[]> nextPairs = new LinkedList<>();
	            int maxDigit = -1;
	            for (int[] pair : pairs) {
	                int idx1 = pair[0], idx2 = pair[1];
	                for (int j = idx1; j < nums1.length && nums1.length-j+nums2.length-idx2 >= k-i; j++) {
	                    if (nums1[j] > maxDigit) {
	                        maxDigit = nums1[j];
	                        nextPairs.clear();
	                        nextPairs.add(new int[]{j+1, idx2});
	                    }
	                }
	                boolean found = false;
	                for (int j = idx2; j < nums2.length && nums1.length-idx1+nums2.length-j >= k-i; j++) {
	                    if (nums2[j] > maxDigit) {
	                        found = true;
	                        maxDigit = nums2[j];
	                        nextPairs.clear();
	                        nextPairs.add(new int[]{idx1, j+1});
	                    } else if (nums2[j] == maxDigit && !found) {
	                        found = true;
	                        nextPairs.add(new int[]{idx1, j+1});
	                    }
	                }
	            }
	            result[i] = maxDigit;
	            pairs = nextPairs;
	        }
	        return result;
	    }
	}
	
	
	static class SolutionII extends CreateMaximumNumber {
		public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        return dp(nums1, 0, nums2, 0, k);
	    }
	    
	    private Map<List<Integer>, int[]> cache = new HashMap<>();
	    // guarantee k > 0; guarnatee i & j are valid
	    private int[] dp(int[] nums1, int i, int[] nums2, int j, int k) {
	        List<Integer> key = new ArrayList<>();
	        key.add(i);
	        key.add(j);
	        key.add(k);
	        int[] result = cache.get(key);
	        if (result != null)
	            return result;
	        result = new int[k];
	        int maxDigit = 0;
	        List<int[]> maxMoves = new ArrayList<>();
	        maxDigit = maxDigit(nums1, i, nums2, j, k, maxDigit, maxMoves, true);
	        maxDigit = maxDigit(nums2, j, nums1, i, k, maxDigit, maxMoves, false);
	        result[0] = maxDigit;
	        if (k > 1) {
	            int[] maxSub = new int[k-1];
	            for (int[] move : maxMoves) {
	                int[] sub = dp(nums1, i+move[0], nums2, j+move[1], k-1);
	                if (bigger(sub, maxSub))
	                    maxSub = sub;
	            }
	            for (int x = 1; x < result.length; x++)
	                result[x] = maxSub[x-1];
	        }
	        
	        cache.put(key, result);
	        return result;
	    }
	    
	    private int maxDigit(int[] nums1, int i, int[] nums2, int j, int k, int maxDigit, List<int[]> maxMoves, boolean turn) {
	        int d = 0;
	        while (i+d < nums1.length && nums1.length-(i+d) + nums2.length-j >= k) {
	            int digit = nums1[i+d];
	            if (digit > maxDigit) {
	                maxDigit = digit;
	                maxMoves.clear();
	                if (turn)
	                    maxMoves.add(new int[]{d+1, 0});
	                else
	                    maxMoves.add(new int[]{0, d+1});
	            } else if (digit == maxDigit) {
	                if (turn)
	                    maxMoves.add(new int[]{d+1, 0});
	                else
	                    maxMoves.add(new int[]{0, d+1});
	            }
	            d++;
	        }
//System.out.println(maxDigit);
	        return maxDigit;
	    }
	    
	    private boolean bigger(int[] a1, int[] a2) {
	        for (int i = 0; i < a1.length; i++) {
	            if (a1[i] > a2[i])
	                return true;
	            else if (a1[i] < a2[i])
	                return false;
	        }
	        return false;
	    }
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
