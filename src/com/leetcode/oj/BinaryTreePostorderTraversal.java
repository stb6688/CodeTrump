package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.leetcode.util.TreeNode;

public class BinaryTreePostorderTraversal {

	public List<Integer> postorderTraversal(TreeNode root) {
        // edge case
        if (root == null)
            return Collections.emptyList();
            
        List<Integer> results = new ArrayList<Integer>();
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode newNode = root;
        do {
            while (newNode != null) {
                queue.addLast(newNode);
                newNode = newNode.left;
            }
            TreeNode node = queue.pollLast();
            results.add(node.val);
            if (node.right != null)
                newNode = node.right;
            else
                newNode = null;
        } while (!queue.isEmpty());
        
        return results;
    }
	
	public static void main(String[] args) {
		BinaryTreePostorderTraversal instance = new BinaryTreePostorderTraversal();
		
		TreeNode root = new TreeNode(1);
		TreeNode right = new TreeNode(2);
		root.right = right;
		
		System.out.println(">>>Result: " + instance.postorderTraversal(root));
	}
	
}
