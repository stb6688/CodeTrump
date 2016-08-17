package com.codetrump.leetcode.oj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

import com.codetrump.leetcode.util.ArrayUtil;
import com.codetrump.leetcode.util.LeetcodeUtils;

public abstract class CountOfSmallerNumbersAfterSelf {
	public abstract List<Integer> countSmaller(int[] nums);
	public static void main(String[] args) {
		CountOfSmallerNumbersAfterSelf instance = new SolutionIII();
		int[] nums;
		
		// 2,1,1,0
//		nums = new int[]{5, 2, 6, 1};
//		long t1 = System.currentTimeMillis();
//		List<Integer> results = instance.countSmaller(nums);
//		long t2 = System.currentTimeMillis();
//		System.out.println("results=" + results);
//		System.out.println(String.format("total time=%,dms", (t2 - t1)));
		
		String s = LeetcodeUtils.readText(instance);
		nums = ArrayUtil.str2intArray(s);
		long t1 = System.currentTimeMillis();
		List<Integer> results = instance.countSmaller(nums);
		long t2 = System.currentTimeMillis();
		System.out.println("results=" + results);
		System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
	
	
	// Solution V: TLE
    // O(nlogn). sort numbers from high to low, then poll from q and add to a tree sorted by index.
    // must deal with duplicates.
	static class SolutionV extends CountOfSmallerNumbersAfterSelf {
		public List<Integer> countSmaller(int[] nums) {
	        // list: idx, val
	        Comparator<List<Integer>> comp = new Comparator<List<Integer>>(){
	            public int compare(List<Integer> l1, List<Integer> l2) {
	                return l2.get(1) == l1.get(1) ? l2.get(0) - l1.get(0) : l2.get(1) - l1.get(1);
	            }
	        };
	        PriorityQueue<List<Integer>> q = new PriorityQueue<>(comp);
	        for (int i = 0; i < nums.length; i++) {
	            List<Integer> list = new ArrayList<>(2);
	            list.add(i);
	            list.add(nums[i]);
	            q.add(list);
	        }
	        TreeSet<Integer> indices = new TreeSet<>();
	        Map<Integer, Integer> valDups = new HashMap<>();
	        List<Integer> results = new ArrayList<>();
	        for (int i = 0; i < nums.length; i++)
	            results.add(0);
	        while (!q.isEmpty()) {
	            List<Integer> list = q.poll();
	            int index = list.get(0), val = list.get(1);
	            indices.add(index);
	            Integer dups = valDups.get(val);
	            if (dups == null)
	                dups = 0;
	            valDups.put(val, dups+1);
	            int count = indices.headSet(index, false).size() - dups; // for dups, i always add higher index first
	            results.set(index, count);
	        }
	        return results;
	    }
	}
	
	
	// Solution IV: TLE
	// use array instead of list; prove the slowness is not due to overhead of list,
	// but because it takes O(n) to get size of headSet().
	// that leads to O(n^2 * logn) performance, instead of O(nlogn).
	static class SolutionIV extends CountOfSmallerNumbersAfterSelf {
		public List<Integer> countSmaller(int[] nums) {
	        Comparator<int[]> comp = new Comparator<int[]>() {
	            @Override
	            public int compare(int[] l1, int[] l2) {
	            	return l1[1] == l2[1] ? l1[0] - l2[0] : l1[1] - l2[1];
	            }
	        };
	        TreeSet<int[]> set = new TreeSet<>(comp);
	        List<Integer> results = new LinkedList<>();
	        for (int i = nums.length-1; i >= 0; i--) {
	            int[] array = {i, nums[i]};
	            results.add(0, set.headSet(array).size());
	            set.add(array);
	        }

	        return results;
	    }
	}
	
	// Solution III: TLE
	static class SolutionIII extends CountOfSmallerNumbersAfterSelf {
		public List<Integer> countSmaller(int[] nums) {
	        Comparator<List<Integer>> comp = new Comparator<List<Integer>>() {
	            @Override
	            public int compare(List<Integer> l1, List<Integer> l2) {
	                return l1.get(1) == l2.get(1) ? l1.get(0) - l2.get(0) : l1.get(1) - l2.get(1);
	            }
	        };
	        TreeSet<List<Integer>> set = new TreeSet<>(comp);
	        List<Integer> results = new LinkedList<>();
	        for (int i = nums.length-1; i >= 0; i--) {
	            List<Integer> list = new ArrayList<>(2);
	            list.add(i);
	            list.add(nums[i]);
	            results.add(0, set.headSet(list).size());
	            set.add(list);
	        }

	        return results;
	    }
	}
	
	// Solution I: Logic Error
	static class SolutionII extends CountOfSmallerNumbersAfterSelf {
		public List<Integer> countSmaller(int[] nums) {
	        Comparator<List<Integer>> comp = new Comparator<List<Integer>>() {
	            @Override
	            public int compare(List<Integer> l1, List<Integer> l2) {
	                if (l1.get(0) > l2.get(0) && l1.get(1) < l2.get(1))
	                    return -1;
	                else
	                    return 1;
	            }
	        };
	        TreeSet<List<Integer>> set = new TreeSet<>(comp);
	        for (int i = 0; i < nums.length; i++) {
	            List<Integer> list = new ArrayList<>(2);
	            list.add(i);
	            list.add(nums[i]);
	            set.add(list);
	        }
	        List<Integer> results = new ArrayList<>();
	        for (int i = 0; i < nums.length; i++) {
	            List<Integer> self = new ArrayList<>(2);
	            self.add(i);
	            self.add(nums[i]);
	            results.add(set.headSet(self, false).size());
System.out.println("idx=" + i + ", val=" + nums[i] + ": " + set.headSet(self, false));
	        }
	        return results;
	    }
	}
	
	
	// SolutionI: TLE
	static class SolutionI extends CountOfSmallerNumbersAfterSelf {
		public List<Integer> countSmaller(int[] nums) {
	        TreeMap<Integer, List<Integer>> numIndices = new TreeMap<>();
	        for (int i = 0; i < nums.length; i++) {
	            int num = nums[i];
	            List<Integer> indices = numIndices.get(num);
	            if (indices == null) {
	                indices = new LinkedList<>();
	                numIndices.put(num, indices);
	            }
	            indices.add(i);
	        }
	        List<Integer> results = new LinkedList<>();
	        for (int i = 0; i < nums.length; i++)
	            results.add(0);
	        TreeSet<Integer> sortedIndices = new TreeSet<>();
	        Iterator<Map.Entry<Integer, List<Integer>>> itr = numIndices.entrySet().iterator();
	        while (itr.hasNext()) {
	            Map.Entry<Integer, List<Integer>> entry = itr.next();
	            for (Integer index : entry.getValue()) {
	                results.set(index, sortedIndices.tailSet(index).size());
	                sortedIndices.add(index);
	            }
	        }
	        return results;
	    }
	}

}
