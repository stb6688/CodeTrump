package com.leetcode.oj;

import java.util.Stack;

public class VerifyPreorderSerializationOfABinaryTree {
	public boolean isValidSerialization(String preorder) {
        // edge case
        if (preorder.length() == 0)
            return true;
        
        String[] splits = preorder.split(",");
        Stack<String> nodes = new Stack<>();
        Stack<Integer> flags = new Stack<>();
        // flags: all initialized to 0
        // pop node and flag;
        // if flag is 0 or 1, continue to push new element from array to nodes
        // (if reaching end of array, return false).
        // if flag is 2 or node is "#", drop curr node and pop next node from nodes/ flags.
        // (if stack is empty, break the loop; return true if we also reached end of array; otherwise false).
        nodes.push(splits[0]);
        flags.push(0);
        int index = 0;
        while (!nodes.empty()) {
            String node = nodes.pop();
System.out.println(node);
            Integer flag = flags.pop();
            // if curr node is null or we have already found its left & right subtrees, 
            // move on to check its parent (pop at next loop).
            if (node.equals("#") || flag == 2) {
                continue;
            } else { // if 0 or 1, check its left or right subtree (pushing next split onto stack).
                // keep the node but bump its flag
                nodes.push(node);
                flags.push(flag + 1);
                // expect more elements on array as curr node should have subtree
                if (index == splits.length - 1)
                    return false;
                else {
                    nodes.push(splits[++index]);
                    flags.push(0);
                }
            }
        }
        
        // if index is the last element, that means we have checked ALL the nodes;
        // otherwise, we have unexpected redundant node, which is invalid.
        return index == splits.length - 1;
    }
	
	public static void main(String[] args) {
		VerifyPreorderSerializationOfABinaryTree instance = new VerifyPreorderSerializationOfABinaryTree();
		String preorder;
		
		preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
		
		long t1 = System.currentTimeMillis();
		boolean result = instance.isValidSerialization(preorder);
		long t2 = System.currentTimeMillis();
		
		System.out.println("result=" + result);
		System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
}
