package com.leetcode.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	
	/**
	 * null node can be represented by "null" or "#".
	 * @param s
	 * @return
	 */
	public static TreeNode deserialize (String s) {
		s = s.trim();
		if (s.charAt(0) == '[') { // remove square parenthesis, if any
			s = s.substring(1, s.length()-1);
		}
		if (s.isEmpty() || s.equals("null"))
			return null;
		String[] splits = s.split(",");
		List<TreeNode> prev = new ArrayList<>();
		TreeNode root = new TreeNode(Integer.valueOf(splits[0].trim()));
		prev.add(root);
		int idx = 1;
		List<TreeNode> curr = new ArrayList<>();
		boolean left = true;
		while (idx < splits.length) {
			TreeNode child = null;
			String val = splits[idx++].trim();
			if (!val.equals("null") && !val.equals("#"))
				child = new TreeNode(Integer.valueOf(val));
			curr.add(child);
			if (left) {
				prev.get(0).left = child;
			} else {
				prev.get(0).right = child;
				prev.remove(0);
				if (prev.isEmpty()) {
					prev = curr;
					curr = new ArrayList<>();
				}
			}
			left ^= true;
		}
		return root;
	}
	
	public static String serialize(TreeNode root) {
		if (root == null)
			return "";
		StringBuilder builder = new StringBuilder();
		List<TreeNode> list = new LinkedList<>();
		list.add(root);
		TreeNode last = root;
		boolean stop = false;
		while (!list.isEmpty()) {
			List<TreeNode> nextList = new LinkedList<>();
			for (TreeNode node : list) {
				if (node == null) {
					builder.append("null,");
					nextList.add(null);
					nextList.add(null);
				} else {
					builder.append(node.val).append(",");
					nextList.add(node.left);
					nextList.add(node.right);
					
					if (node.right != null)
						last = node.right;
					else if (node.left != null)
						last = node.left;
					
					if (node == last) {
						stop = true;
						break;
					}
				}
			}
			if (stop)
				break;
			list = nextList;
		}
		builder.delete(builder.length()-1, builder.length());
		return builder.toString();
	}
	
	public static void main(String[] args) {
		String s = "5,1,5,5,5,null,5";
		TreeNode root = TreeNode.deserialize(s);
		String s2 = TreeNode.serialize(root);
		System.out.println(s2);
		System.out.println(s2.equals(s));
	}
}
