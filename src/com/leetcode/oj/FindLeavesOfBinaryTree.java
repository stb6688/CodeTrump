package com.leetcode.oj;

import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leetcode.util.TreeNode;

public abstract class FindLeavesOfBinaryTree {
	public abstract List<List<Integer>> findLeaves(TreeNode root);
	public static void main(String[] args) {
		FindLeavesOfBinaryTree instance = new SolutionII();
		TreeNode root;
		List<List<Integer>> rets;
		
		root = TreeNode.deserialize("[1,2,3,4,5]");
		
		rets = instance.findLeaves(root);
		System.out.println("results=" + rets);
	}
	
	
	static class SolutionII extends FindLeavesOfBinaryTree {
		public List<List<Integer>> findLeaves(TreeNode root) {
	        if (root == null)
	            return Collections.emptyList();
	        Map<TreeNode, TreeNode> childParent = new HashMap<>();
	        childParent.put(root, null);
	        Set<TreeNode> candidates = new HashSet<>();
	        Deque<TreeNode> q = new LinkedList<>();
	        q.add(root);
	        while (!q.isEmpty()) {
	            TreeNode node = q.pollFirst();
	            if (node.left == null && node.right == null)
	                candidates.add(node);
	            else {
	                if (node.left != null) {
	                    childParent.put(node.left, node);
	                    q.add(node.left);
	                }
	                if (node.right != null) {
	                    childParent.put(node.right, node);
	                    q.add(node.right);
	                }
	            }
	        }
	        
	        List<List<Integer>> rets = new LinkedList<>();
	        while (!candidates.isEmpty()) {
	            Set<TreeNode> nextCandidates = new HashSet<>();
	            List<Integer> ret = new LinkedList<>();
	            for (TreeNode cand : candidates) {
	                if (!childParent.containsKey(cand.left) /* && !childParent.containsKey(cand.right) */) {
	                    ret.add(cand.val);
	                    TreeNode parent = childParent.remove(cand);
	                    if (parent != null && childParent.containsKey(parent))
	                        nextCandidates.add(parent);
	                }
	            }
	            rets.add(ret);
	            candidates = nextCandidates;
	        }
	        return rets;
	    }
	}
}
