package com.leetcode.oj;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import com.leetcode.util.TreeNode;

public class LowestCommonAncestorOfABinaryTree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        Stack<Integer> flags = new Stack<>();
        flags.push(0);
        Stack<Set<TreeNode>> subtrees = new Stack<>();
        subtrees.push(new HashSet<>());
        Set<TreeNode> prevSubtree = new HashSet<>();
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            Integer flag  = flags.pop();
            Set<TreeNode> subtree = subtrees.pop();
            if (flag == 0) { // check left subtree
                if (node != null) {
                    nodes.push(node);
                    flags.push(1);
                    subtrees.push(subtree);
                    // left sub
                    nodes.push(node.left);
                    flags.push(0);
                    subtrees.push(new HashSet<>());
                } else {
                    prevSubtree = subtree; // done with this subtree, it's an empty subtree
                }
            } else if (flag == 1) { // done with left, check right subtree
                nodes.push(node);
                flags.push(2);
                subtree.addAll(prevSubtree);    // add all nodes on left subtree
                subtrees.push(subtree);
                // right sub
                nodes.push(node.right);
                flags.push(0);
                subtrees.push(new HashSet<>());
            } else { // done with right subtree
                subtree.addAll(prevSubtree); // add all nodes on right subtree
                subtree.add(node);
System.out.println("node=" + node + ", tree=" + subtree);
                if (subtree.contains(p) && subtree.contains(q))
                    return node;
                prevSubtree = subtree;
            }
        }
        return null;
    }

	public static void main(String[] args) {
		LowestCommonAncestorOfABinaryTree instance = new LowestCommonAncestorOfABinaryTree();
		TreeNode root, p, q;
		
		root = new TreeNode(1);
		p = new TreeNode(2);
		q = new TreeNode(3);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		root.left = left;
		root.right = right;
		
		TreeNode result = instance.lowestCommonAncestor(root, p, q);
		System.out.println("result=" + result);
	}
}
