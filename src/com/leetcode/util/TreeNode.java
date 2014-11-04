package com.leetcode.util;

public class TreeNode {
	public TreeNode left;
	public TreeNode right;
	public int val;
	public TreeNode(int val) {
		this.val = val;
	}
	
	@Override
	public String toString() {
		return "val=" + val;
	}
}
