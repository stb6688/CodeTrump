package com.leetcode.oj;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddAndSearchWordDataStructureDesign {
	
	public static void main(String[] args) {
		WordDictionary instance = new WordDictionary();
		
		// true
//		instance.addWord("a");
//		System.out.println(instance.search("."));
		
		// false
		instance.addWord("ran");
		instance.addWord("rune");
		System.out.println(instance.search(".an."));
		
	}
	
	
	public static class WordDictionary {
		class TrieNode {
	        boolean isLeaf = false;
	        Map<Character, TrieNode> map = new HashMap<>();
	    }
	    
	    private TrieNode root = new TrieNode();
	    private Map<Integer, Map<Character, TrieNode>> idxMap = new HashMap<>();
	    public void addWord(String word) {
	        TrieNode curr = root;
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            TrieNode next = curr.map.get(ch);
	            if (next == null) {
	                next = getNode(i, ch);
	                curr.map.put(ch, next);
	            }
	            curr = next;
	        }
	        curr.isLeaf = true;
	    }
	    
	    private TrieNode getNode(int idx, char ch) {
	        Map<Character, TrieNode> charNode = idxMap.get(idx);
	        if (charNode == null) {
	            charNode = new HashMap<>();
	            idxMap.put(idx, charNode);
	        }
	        TrieNode node = charNode.get(ch);
	        if (node == null) {
	            node = new TrieNode();
	            charNode.put(ch, node);
	        }
	        return node;
	    }
	
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        Set<TrieNode> nodes = new HashSet<>();
	        nodes.add(root);
	        for (int i = 0; i < word.length(); i++) {
	            Set<TrieNode> nextNodes = new HashSet<>();
	            char ch = word.charAt(i);
	            for (TrieNode node : nodes) {
	                for (char key : node.map.keySet()) {
	                    if (ch == '.' || key == ch)
	                        nextNodes.add(node.map.get(key));
	                }
	            }
	            if (nextNodes.isEmpty())
	                return false;
	            nodes = nextNodes;
	        }
	        for (TrieNode node : nodes) {
	            if (node.isLeaf)
	                return true;
	        }
	        return false;
	    }
	}
	
	// Solution I: Wrong
	/*
	public static class WordDictionary {
	    class TrieNode {
	        boolean isLeaf = false;
	        Map<Character, TrieNode> children;
	        public TrieNode() {
	            children = new HashMap<>();
	        }
	    }

	    TrieNode root = new TrieNode();
	    
	    // Solution II: should use Trie
	    // Adds a word into the data structure.
	    public void addWord(String word) {
	        TrieNode curr = root;
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            TrieNode next = curr.children.get(ch);
	            if (next == null) {
	                next = new TrieNode();
	                curr.children.put(ch, next);
	            }
	            curr = next;
	        }
	        curr.isLeaf = true;
	    }

	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        List<TrieNode> nodes = new LinkedList<>();
	        nodes.add(root);
	        for (int i = 0; i < word.length(); i++) {
	            List<TrieNode> nextNodes = new LinkedList<>();
	            char ch = word.charAt(i);
	            for (TrieNode node : nodes) {
	                if (ch == '.')
	                    nextNodes.addAll(node.children.values());
	                else {
	                    TrieNode child = node.children.get(ch);
	                    if (child != null)
	                        nextNodes.add(child);
	                }
	            }
	            if (nextNodes.isEmpty())
	                return false;
	            nodes = nextNodes;
	        }
	        for (TrieNode node : nodes) {
	            if (node.isLeaf)
	                return true;
	        }
	        return false;
	    }
	}
	*/
}
