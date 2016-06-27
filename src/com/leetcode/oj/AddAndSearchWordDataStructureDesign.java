package com.leetcode.oj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
		System.out.println(instance.search(".an.")); // false
		System.out.println(instance.search(".an"));  // true
		
	}
	
	
	public static class WordDictionary {
		class TrieNode {
	        boolean isLeaf = false;
	        // Map<Character, TrieNode> map = new HashMap<>();
	        TrieNode[] children = new TrieNode[26]; // instead of map, use array with 26 letters
	    }
	    
	    private TrieNode root = new TrieNode();
	    public void addWord(String word) {
	        TrieNode curr = root;
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            int idx = ch - 'a';
	            TrieNode child;
	            if ((child = curr.children[idx]) == null) {
	                child = new TrieNode();
	                curr.children[idx] = child;
	            }
	            curr = child;
	        }
	        curr.isLeaf = true;
	    }
	    
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        List<TrieNode> nodes = new ArrayList<>();
	        nodes.add(root);
	        for (char ch : word.toCharArray()) {
	            List<TrieNode> nextNodes = new ArrayList<>();
	            for (TrieNode node : nodes) {
	                if (ch == '.') {
	                    for (TrieNode child : node.children) {
	                    	if (child != null)
	                    		nextNodes.add(child);
	                    }
	                } else {
	                    TrieNode next = node.children[ch-'a'];
	                    if (next != null)
	                        nextNodes.add(next);
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
