package com.leetcode.oj;

import java.util.Arrays;

import com.leetcode.util.TreeNode;

public class LargestBstSubtree {
	private int largest = 0;
    public int largestBSTSubtree(TreeNode root) {
        if (root != null)
            recur(root, true);
        return largest;
    }
    
    // array [size, min, max, largest]; size=-1 indicating invalid BST
    private int[] recur(TreeNode node, boolean left) {
        int[] results = new int[3];
        int lmin = Integer.MAX_VALUE, lmax = Integer.MIN_VALUE;
        int rmin = Integer.MAX_VALUE, rmax = Integer.MIN_VALUE;
        if (node.left != null) {
            int[] lresults = recur(node.left, true);
            if (lresults[0] < 0) {
                results[0] = -1;
                return results;
            } else {
                results[0] += lresults[0];
                lmin = lresults[1];
                lmax = lresults[2];
            }
        }
        if (node.right != null) {
            int[] rresults = recur(node.right, false);
            if (rresults[0] < 0) {
                results[0] = -1;
                return results;
            } else {
                results[0] += rresults[0];
                rmin = rresults[1];
                rmax = rresults[2];
            }
        }
        if (node.val >= lmax && node.val <= rmin) {
            results[0]++;
            results[1] = Math.min(node.val, lmin);
            results[2] = Math.max(node.val, rmax);
            largest = Math.max(largest, results[0]);
        } else {
            results[0] = -1;
        }
System.out.println("node=" + node.val + ", results=" + Arrays.toString(results));
        return results;
    }
    
    public static void main(String[] args) {
    	LargestBstSubtree instance = new LargestBstSubtree();
    	TreeNode root;
    	
    	// 3
//    	TreeNode n10 = new TreeNode(10);
//    	TreeNode n5 = new TreeNode(5);
//    	TreeNode n15 = new TreeNode(15);
//    	TreeNode n1 = new TreeNode(1);
//    	TreeNode n8 = new TreeNode(9);
//    	TreeNode n7 = new TreeNode(7);
//    	n10.left = n5;
//    	n10.right = n15;
//    	n5.left = n1;
//    	n5.right = n8;
//    	n15.right = n7;
//    	root = n10;
    	
    	// 2
//    	TreeNode n3 = new TreeNode(3);
//    	TreeNode n2 = new TreeNode(2);
//    	TreeNode n4 = new TreeNode(4);
//    	TreeNode n1 = new TreeNode(1);
//    	n3.left = n2;
//    	n3.right = n4;
//    	n4.left = n1;
//    	root = n3;
    	
    	TreeNode n1 = new TreeNode(1);
    	TreeNode n3 = new TreeNode(3);
    	TreeNode n2 = new TreeNode(2);
    	TreeNode n4 = new TreeNode(4);
    	TreeNode n5 = new TreeNode(5);
    	n1.left = n3;
    	n1.right = n2;
    	n3.left = n4;
    	n2.right = n5;
    	root = n1;
    	
    	int result = instance.largestBSTSubtree(root);
    	System.out.println("result=" + result);
	}
}
