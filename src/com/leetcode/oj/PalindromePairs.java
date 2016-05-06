package com.leetcode.oj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PalindromePairs {
	public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> wordIndex = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordIndex.put(words[i], i);
        }
        
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            Set<String> leftPairs = leftPairs(words[i]);
//System.out.println("w=" + words[i] + ", left=" + leftPairs);
            for (String pair : leftPairs) {
                Integer index = wordIndex.get(pair);
                if (index != null && index != i) {
                    List<Integer> result = new ArrayList<>(2);
                    result.add(index);
                    result.add(i);
                    set.add(result);
                }
            }
            
            Set<String> rightPairs = rightPairs(words[i]);
            for (String pair : rightPairs) {
                Integer index = wordIndex.get(pair);
                if (index != null && index != i) {
                    List<Integer> result = new ArrayList<>(2);
                    result.add(i);
                    result.add(index);
                    set.add(result);
                }
            }
        }
        List<List<Integer>> results = new ArrayList<>();
        for (List<Integer> result : set)
        		results.add(result);
        return results;
    }
    
    private Set<String> leftPairs(String word) {
        Set<String> results = new HashSet<>();
        for (int i = 1; i < word.length(); i++) {
            String left = word.substring(0, i);
            if (isPalindrome(left)) {
                String right = word.substring(i, word.length());
                results.add(reverse(right));
            } else
                break;
        }
        results.add(reverse(word));
        return results;
    }
    
    private Set<String> rightPairs(String word) {
    	Set<String> results = new HashSet<>();
    	for (int i = word.length()-1; i >= 1; i--) {
            String right = word.substring(i, word.length());
            if (isPalindrome(right)) {
                String left = word.substring(0, i);
                results.add(reverse(left));
            } else
                break;
        }
    	results.add(reverse(word));
        return results;
    }
    
    
    
    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
    
    private String reverse(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length()/2; i++) {
            int j = s.length() - 1 - i;
            chars[i] = (char)(chars[i] ^ chars[j]);
            chars[j] = (char)(chars[i] ^ chars[j]);
            chars[i] = (char)(chars[i] ^ chars[j]);
        }
        return new String(chars);
    }
    
    public static void main(String[] args) {
    	PalindromePairs instance = new PalindromePairs();
    	String[] words;
    	
//    	[[0, 1], [1, 0]]
//    	words = new String[]{"bat", "tab", "cat"};
    	
//    	[[0,1],[1,0]]
    	words = new String[]{"a", ""};
    	
    	// [[0, 1], [1, 0], [3, 2], [2, 4]]
//    	words = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
    	
    	List<List<Integer>> results = instance.palindromePairs(words);
    	System.out.println("results=" + results);
	}

}
