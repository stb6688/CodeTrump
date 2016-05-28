package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.leetcode.util.TreeNode;

public abstract class BinaryTreePostorderTraversal {

	public abstract List<Integer> postorderTraversal(TreeNode root);
	
	public static void main(String[] args) {
		BinaryTreePostorderTraversal instance = new Solution();
		TreeNode root;
		List<Integer> results;
		
		root = TreeNode.deserialize("[3,null,1,2]");
		results = instance.postorderTraversal(root);
		System.out.println("results=" + results);
	}
	
	public static class Solution extends BinaryTreePostorderTraversal {
		public List<Integer> postorderTraversal(TreeNode root) {
	        List<Integer> results = new ArrayList<>();
	        TreeNode node = root;
	        Stack<TreeNode> nodes = new Stack<>();
	        while (node != null || !nodes.isEmpty()) {
	            while (node != null) {
	                results.add(node.val);
	                nodes.push(node);
	                node = node.right;
	            }
	            while (!nodes.isEmpty() && (node = nodes.pop().left) == null);
	        }
System.out.println(results);
	        Collections.reverse(results);
	        return results;
	    }
	}
	
}
