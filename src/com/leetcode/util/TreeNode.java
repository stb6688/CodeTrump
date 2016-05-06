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
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof TreeNode)
			return ((TreeNode)o).val == this.val;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return this.val;
	} 
}
