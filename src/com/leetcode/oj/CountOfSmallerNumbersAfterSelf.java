package com.leetcode.oj;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import com.leetcode.util.ArrayUtil;
import com.leetcode.util.LeetcodeUtils;

public class CountOfSmallerNumbersAfterSelf {
	
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

	public static void main(String[] args) {
		CountOfSmallerNumbersAfterSelf instance = new CountOfSmallerNumbersAfterSelf();
		int[] nums;
		
		// 2,1,1,0
		nums = new int[]{5, 2, 6, 1};
		
		String s = LeetcodeUtils.readText(instance);
		nums = ArrayUtil.str2intArray(s);
		
		long t1 = System.currentTimeMillis();
		List<Integer> results = instance.countSmaller(nums);
		long t2 = System.currentTimeMillis();
		System.out.println("results=" + results);
		System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
}
