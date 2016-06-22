package com.leetcode.oj;

public class RangeSumQueryMutable {
	
	public static void main(String[] args) {
		NumArray instance = new RangeSumQueryMutable(). new NumArray(new int[]{0,9,5,7,3});
//		instance.sumRange(4,4);
//		instance.sumRange(2,4);
//		instance.sumRange(3,3);
		instance.update(4,5);
		
		System.out.println(instance.sumRange(4, 4));
		
//		instance.update(1,7);
//		instance.update(0,8);
//		instance.sumRange(1,2);
//		instance.update(1,9);
//		instance.sumRange(4,4);
//		instance.update(3,4);
		
		
		
//		NumArray instance = new RangeSumQueryMutable(). new NumArray(new int[]{1,3,5});
//		System.out.println(instance.sumRange(0, 2));
//		System.out.println(instance.sumRange(1, 2));
//		System.out.println(instance.sumRange(0, 2));
	}
	
	public class NumArray {
		class SegTreeNode {
	        int lidx, ridx, sum;
	        SegTreeNode left, right;
	        public SegTreeNode(int lidx, int ridx) {
	            this.lidx = lidx;
	            this.ridx = ridx;
	        }
	        
	        @Override
	        public String toString() {
	        	return String.format("[%d,%d] sum=%d", lidx, ridx, sum);
	        }
	    }
	    
	    private int[] nums;
	    private SegTreeNode root;
	    public NumArray(int[] nums) {
	        this.nums = nums;
	        root = build(nums, 0, nums.length-1);
	    }
	    
	    private SegTreeNode build(int[] nums, int l, int r) {
	        if (l > r)
	            return null;
	        int m = l + (r - l)/2;
	        SegTreeNode root = new SegTreeNode(l, r);
	        root.sum = nums[m];
	        root.left = build(nums, l, m-1);
	        root.right = build(nums, m+1, r); // put m into right sub
	        if (root.left != null)
	            root.sum += root.left.sum;
	        if (root.right != null)
	            root.sum += root.right.sum;
	        return root;
	    }
	    
	    // O(logn)
	    void update(int i, int val) {
	    	nums[i] = val;
	        updateTree(root, i, val);
	    }
	    
	    private void updateTree(SegTreeNode root, int idx, int val) {
	        root.sum += val - nums[idx];
	        int m = (root.lidx + root.ridx)/2; 
	        if (idx < m)
	            updateTree(root.left, idx, val);
	        else if (idx > m)
	            updateTree(root.right, idx, val);
	    }

	    public int sumRange(int i, int j) {
	        return getSum(root, i, j);
	    }
	    
	    private int getSum(SegTreeNode root, int l, int r) {
	        if (root.lidx == l && root.ridx == r)
	            return root.sum;
	        if (l > r)
	        	return 0;
	        if (l == r)
	        	return nums[l];
	        int m = (root.lidx + root.ridx)/2;
	        
	        if (r < m)
	            return getSum(root.left, l, r);
	        else if (l > m)
	            return getSum(root.right, l, r);
	        else {
	        	if (l == m)
		        	return getSum(root.right, l+1, r) + nums[m];
		        else if (r == m)
		        	return getSum(root.left, l, m-1) + nums[m];
		        else
		        	return getSum(root.left, l, m-1) + getSum(root.right, m+1, r) + nums[m];
	        }
	    }
	}
}
