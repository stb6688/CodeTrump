package com.leetcode.oj;

import java.util.Stack;

import com.leetcode.util.TreeNode;

public class CountCompleteTreeNodes {
	
	public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int maxH = 0;
        TreeNode node = root;
        while (node != null) {
            maxH++;
            node = node.left;
        }
        
        int h = 0, missings = 0;
        node = root;
        Stack<TreeNode> nodes = new Stack<>();
        boolean stop = false;
        while (!stop && (node != null || !nodes.isEmpty())) {
            while (node != null) {
                if (++h == maxH - 1) {
                    if (node.right != null) {
                        stop = true;
                        break;
                    } else {
                        missings++;
                        if (node.left != null) {
                            stop = true;
                            break;
                        } else
                            missings++;
                    }
                }
                nodes.push(node);
                node = node.right;
            }
            if (stop)
                break;
            while (!nodes.isEmpty()) {
            	h--;
                node = nodes.pop().left;
                if (node != null) {
                	h++;
                	break;
                }
            }
        }
        
        return (int)Math.pow(2, maxH) - 1 - missings;
    }
    
    public static void main(String[] args) {
    	CountCompleteTreeNodes instance = new CountCompleteTreeNodes();
    	TreeNode root;
    	
//    	root = TreeNode.deserialize("1,2,3,4,5,6,7"); // 7
//    	root = TreeNode.deserialize("1,2,3,4"); // 4
//    	root = TreeNode.deserialize("1,2,3,4,5"); // 5
//    	root = TreeNode.deserialize("1,2,3,4,5,6"); // 6
    	
    	root = TreeNode.deserialize("1");
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.countNodes(root);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
}
