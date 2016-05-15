package com.leetcode.oj;

import java.util.Arrays;

import com.leetcode.util.TreeNode;

public class BalancedBinaryTree {
	public boolean isBalanced(TreeNode root) {
        return recur(root, new int[2]);
    }
    
    private boolean recur(TreeNode node, int[] minMax) {
        if (node == null) {
            return true;
        } else {
            int[] lminMax = new int[2], rminMax = new int[2];
            if (recur(node.left, lminMax) && recur(node.right, rminMax)) {
                minMax[0] = Math.min(lminMax[0], rminMax[0]) + 1;
                minMax[1] = Math.max(lminMax[1], rminMax[1]) + 1;
System.out.println("node=" + node.val + ", left=" + Arrays.toString(lminMax) + ", right=" + Arrays.toString(rminMax));
System.out.println("node=" + node.val + ", range=" + Arrays.toString(minMax));
                return minMax[1] - minMax[0] <= 1;
            } else {
                return false;
            }
        }
    }
    
    public static void main(String[] args) {
    	BalancedBinaryTree instance = new BalancedBinaryTree();
    	TreeNode root;
    	
    	TreeNode node1 = new TreeNode(1);
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node3 = new TreeNode(3);
    	node1.right = node2;
    	node2.right = node3;
    	root = node1;
    	
    	boolean result = instance.isBalanced(root);
    	System.out.println("result=" + result);
	}
}
