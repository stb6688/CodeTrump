package com.leetcode.oj;

import com.leetcode.util.TreeNode;

public class CountUnivalueSubtrees {
	
	public int countUnivalSubtrees(TreeNode root) {
        if (root == null)
            return 0;
        int[] count = {0};
        recur(root, count);
        return count[0];
    }
    
    private Integer recur(TreeNode node, int[] count) {
        Integer val = new Integer(node.val);
System.out.println("val=" + val);
        if (node.left == null && node.right == null) {
            count[0]++;
            return node.val;
        } else {
        	boolean unique = true;
            if (node.left != null && !val.equals(recur(node.left, count)))
                unique = false;
            if (node.right != null && !val.equals(recur(node.right, count)))
                unique = false;
            if (unique) {
            	count[0]++;
            	return val;
            } else
            	return null;
        }
    }
    
    public static void main(String[] args) {
    	CountUnivalueSubtrees instance = new CountUnivalueSubtrees();
    	TreeNode root;
    	
    	root = TreeNode.deserialize("[5,1,5,5,5,null,5]");
    	
    	int result = instance.countUnivalSubtrees(root);
    	System.out.println("result=" + result);
	}

}
