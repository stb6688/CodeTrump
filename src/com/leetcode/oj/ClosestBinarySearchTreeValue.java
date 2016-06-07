package com.leetcode.oj;

import com.leetcode.util.TreeNode;

public abstract class ClosestBinarySearchTreeValue {
	public abstract int closestValue(TreeNode root, double target);
	public static void main(String[] args) {
		ClosestBinarySearchTreeValue instance = new SolutionI();
		TreeNode root; double target;
		int result;
		
		root = TreeNode.deserialize("2147483647"); target = 0.0;
		result = instance.closestValue(root, target);
		System.out.println("result=" + result);
	}
	
	static class SolutionI extends ClosestBinarySearchTreeValue {
		public int closestValue(TreeNode root, double target) {
	        TreeNode node = root;
	        int bigger = 0, smaller = 0;
	        while (node != null) {
	            if (node.val == target) {
	                return node.val;
	            } else if (target < node.val) {
	                bigger = node.val;
	                node = node.left;
	            } else {
	                smaller = node.val;
	                node = node.right;
	            }
	        }
	        return Math.abs(target - bigger) < Math.abs(target - smaller) ? bigger : smaller;
	    }
	}
}
