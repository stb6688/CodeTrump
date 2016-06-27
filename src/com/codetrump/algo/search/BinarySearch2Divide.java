package com.codetrump.algo.search;

public class BinarySearch2Divide {

	public int search(int[] nums, int target) {
		return binary2(nums, 0, nums.length-1, target);
	}
	
	// required l <= r
	private int binary2(int[] nums, int l, int r, int target) {
		int m = (l + r) >> 1;
		if (m == l) {
			
		}
		if (nums[m] == target)
			return m;
		else if (target < nums[m])
			return binary2(nums, l, m-1, target);
		else
			return binary2(nums, m, r, target);
	}
	
	public static void main(String[] args) {
		BinarySearch2Divide instance = new BinarySearch2Divide();
		int[] nums;
		int target;
		
//		nums = new int[]{1,2,2,3,4,4,4,5,9};
		nums = new int[]{1,2};
		target = 7;
		
		int idx = instance.search(nums, target);
		if (idx < 0)
			System.out.println("not found");
		else
			System.out.println(String.format("found %d at index %d", nums[idx], idx));
	}
}
