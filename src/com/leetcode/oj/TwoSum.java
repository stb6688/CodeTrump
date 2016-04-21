package com.leetcode.oj;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Leetcode: https://leetcode.com/problems/two-sum/
 * @author Alex
 * @date Apr 18, 2016
 */

public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		Arrays.sort(nums);
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int sum = nums[left] + nums[right];
			System.out.println(">>> " + sum);
			if (sum == target) {
				return new int[] {left, right};
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		TwoSum instance = new TwoSum();
		
		int[] nums = {-11, -8, -5, -1, 4, 10, 15, 21, 22, 30, 33, 35, 40, 50};
		int target = 40;
		int[] result = instance.twoSum(nums, target);
		
		System.out.println("target=" + target);
		System.out.println("--- table ---");
		for (int row = nums.length/2; row < nums.length; row++) {
			for (int col = 0; col < nums.length/2; col++) {
				System.out.print(nums[row] + nums[col] + ",");
			}
			System.out.println();
		}
		System.out.println("------------");
		System.out.println("result: " + Arrays.toString(result));
		if (result != null) {
			System.out.println(nums[result[0]]);
			System.out.println(nums[result[1]]);
		}
		
		TreeSet<Integer> sums = new TreeSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				sums.add(nums[i] + nums[j]);
			}
		}
		Iterator<Integer> itr = sums.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + ",");
		}
	}
}
