package com.leetcode.oj;

public class FindMinimumInRotatedSortedArray {
	
	
	public int findMinX(int[] nums) {
        // edge case if only 1 element or if cut at 0 which doesn't change array
        if (nums[0] <= nums[nums.length-1])
            return nums[0];
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = i + (j-i)/2;
System.out.println("i = " + i + ", j=" + j + ", m=" + m);
            // Error: forget i==j; forget nums[m-1] == nums[i]
            if (nums[m] <= nums[i] && m-1 >= 0 && nums[m-1] >= nums[i])
                return nums[m];
            else if (nums[m] < nums[i])
                j = m - 1;
            else
                i = m + 1;
        }
        return -1; // unrechable
    }
	
	
	
	/// Solution I (Passed): improved O(logn) solution. ///
    /**
     * Keep dividing into an subarray where left > right,
     * stop when subarray size == 2, return second element.
     * NOTE: refer to https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array/solution/
     * for the diagram, which makes the problem very clear.
     */
    public int findMin(int[] num) {
        // edge case
        if (num == null || num.length == 0)
            return Integer.MIN_VALUE; // or whatever default value
        if (num.length == 1)
            return num[0];
        
        // pivot is the last element, rotation has no impact
        if (num[0] < num[num.length - 1])
            return num[0];
        
        // divide into sub-arrays and keep the one whose left > right,
        // keep dividing until sub-array size == 2.
        int left = 0, right = num.length - 1;
        while (right - left > 1) { // stop when right - left == 1, i.e. sub-array size = 2
            int mid = (left + right)/2;
            if (num[left] > num[mid]) {
                right = mid;
            } else { // num[mid] > num[right]
                left = mid;
            }
        }
        
        return num[right];
    }
    
    
    /// Solution II (Passed): O(logn) ///
    /**
     * if (num[left] < num[right])
     *  return num[left] --> rotated at end, no impact
     * 1. pick a mid point,
     * 2. divide into [left, mid], [mid, right].
     * 3. skip if num[left] < num[mid].
     * 4. repeat the same for the other subinterval.
     * 5. stop when interval size == 2, return the latter.
     */
    public int findMin1(int[] num) {
        // edge case
        if (num == null || num.length == 0)
            return Integer.MIN_VALUE;
        if (num.length == 1)
            return num[0];
            
        // rotated at last element, no impact
        if (num[0] < num[num.length - 1])
            return num[0];
            
        int index = binaryFindMin(num, 0, num.length - 1);
        return num[index];
    }
    
	
	/**
     * Solution III (Passed), O(n).
     * This is NOT the best solution; this is cheating.
     */
    public int findMin2(int[] num) {
        if (num == null || num.length == 0)
            return Integer.MIN_VALUE;
        if (num.length == 1)
            return num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] < num[i - 1])
                return num[i];
        }
        
        return num[0];
    }
    
    private int binaryFindMin(int[] num, int start, int end) {
        int left = num[start];
        int right = num[end];
        if (left < right)
            return -1;
        else { // left > right, this interval contains rotated point
            if (end - start == 1)
                return end;
            int mid = (start + end)/2;
            int index1 = binaryFindMin(num, start, mid);
            if (index1 >= 0)
                return index1;
            return binaryFindMin(num, mid, end);
        }
    }
    
    public static void main(String[] args) {
    	FindMinimumInRotatedSortedArray instance = new FindMinimumInRotatedSortedArray();
    	int[] nums;
    	
//    	nums = new int[]{3,4,5,1,2};
//    	System.out.println("result=" + instance.findMinX(nums));
    	
//    	nums = new int[]{2, 1};
//    	System.out.println("result=" + instance.findMinX(nums));
    	
    	nums = new int[]{4,5,6,1,2,3};
    	System.out.println("result=" + instance.findMinX(nums));
	}

}
