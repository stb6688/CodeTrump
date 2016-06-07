package com.leetcode.oj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class PalindromePairs {
	
	public abstract List<List<Integer>> palindromePairs(String[] words);
    public static void main(String[] args) {
    	PalindromePairs instance = new SolutionIII();
    	String[] words;
    	List<List<Integer>> results;
    	
//    	[[0, 1], [1, 0]]
//    	words = new String[]{"bat", "tab", "cat"};
    	
//    	[[0,1],[1,0]]
    	words = new String[]{"a", ""};
    	
    	// [[0, 1], [1, 0], [3, 2], [2, 4]]
//    	words = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
//    	results = instance.palindromePairs(words);
//    	System.out.println("results=" + results);
    	
    	words = new String[]{"a", ""};
    	results = instance.palindromePairs(words);
    	System.out.println("results=" + results);
    	
    	// [0,1], [1,0]
//    	words = new String[]{"bat", "tab", "cat"};
//    	results = instance.palindromePairs(words);
//    	System.out.println("results=" + results);
	}
    
    static class SolutionIII extends PalindromePairs {
    	public List<List<Integer>> palindromePairs(String[] words) {
            Map<String, Set<Integer>> leftRights = new HashMap<>();
            Map<String, Set<Integer>> rightLefts = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                getPairs(word, i, leftRights, rightLefts);
            }
            Set<List<Integer>> results = new HashSet<>();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                Set<Integer> rights = leftRights.get(word);
                if (rights != null) {
                    for (int r : rights)
                        addResult(i, r, results);
                }
                Set<Integer> lefts = rightLefts.get(word);
                if (lefts != null) {
                    for (int l : lefts)
                        addResult(l, i, results);
                }
            }
            return new ArrayList<>(results);
        }
        
        private void getPairs(String word, int idx, Map<String, Set<Integer>> leftRights, 
            Map<String, Set<Integer>> rightLefts) {
            for (int i = 0; i <= word.length(); i++) {
                getPair(word, idx, i-1, i+1, leftRights, rightLefts);
                getPair(word, idx, i-1, i, leftRights, rightLefts);
            }
        }
        
        private void getPair(String word, int idx, int i, int j, Map<String, Set<Integer>> leftRights, 
            Map<String, Set<Integer>> rightLefts) {
            while (i >= 0 && j < word.length()) {
                if (word.charAt(i) != word.charAt(j))
                    break;
                i--;
                j++;
            }
            if (i < 0 && j <= word.length()) {
                String left = reverse(word.substring(j, word.length()));
                add(left, idx, leftRights);
            } 
            if (j == word.length() && i < word.length()) {
                String right = reverse(word.substring(0, i+1));
                add(right, idx, rightLefts);
            }
        }
        
        private String reverse(String s) {
            StringBuilder builder = new StringBuilder();
            for (char ch : s.toCharArray())
                builder.insert(0, ch);
            return builder.toString();
        }
        
        private void add(String key, int idx, Map<String, Set<Integer>> map) {
            Set<Integer> vals = map.get(key);
            if (vals == null) {
                vals = new HashSet<>();
                map.put(key, vals);
            }
            vals.add(idx);
        }
        
        private void addResult(int l, int r, Set<List<Integer>> results) {
            if (l != r) {
                List<Integer> result = new ArrayList<>(2);
                result.add(l);
                result.add(r);
                results.add(result);
            }
        }
    }
    
    // Solution II: Logic Error
    static class SolutionII extends PalindromePairs {
    	public List<List<Integer>> palindromePairs(String[] words) {
            Map<String, List<Integer>> rightLefts = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                List<String> rights = getRights(words[i]);
                for (String right : rights) {
                    List<Integer> lefts = rightLefts.get(right);
                    if (lefts == null) {
                        lefts = new ArrayList<>();
                        rightLefts.put(right, lefts);
                    }
                    lefts.add(i);
                }
            }
            List<List<Integer>> results = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                List<Integer> lefts = rightLefts.get(word);
                if (lefts != null) {
                    for (int left : lefts) {
                        if (left != i) {
                            List<Integer> result = new ArrayList<>(2);
                            result.add(left);
                            result.add(i);
                            results.add(result);
                        }
                    }
                }
            }
            return results;
        }
        
        private List<String> getRights(String s) {
            List<String> results = new ArrayList<>();
            String result;
            for (int m = (s.length() - 1)/2; m <= s.length()-1; m++) {
                if ((result = getRight(s, m-1, m+1)) != null)
                    results.add(result);
                if ((result = getRight(s, m, m+1)) != null)
                    results.add(result);
            }
System.out.println("left=" + s + ", rights=" + results);
            return results;
        }
        
        private String getRight(String s, int i, int j) {
            while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            }
            if (j == s.length()) {
                StringBuilder builder = new StringBuilder();
                while (i >= 0)
                    builder.append(s.charAt(i--));
                return builder.toString();
            } else
                return null;
        }
    }
    
	
    // Solution I: TLE
	static class SolutionI extends PalindromePairs {
		public List<List<Integer>> palindromePairs(String[] words) {
	        Map<String, Integer> wordIndex = new HashMap<>();
	        for (int i = 0; i < words.length; i++) {
	            wordIndex.put(words[i], i);
	        }
	        
	        Set<List<Integer>> set = new HashSet<>();
	        for (int i = 0; i < words.length; i++) {
	            Set<String> leftPairs = leftPairs(words[i]);
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
	}
    
}
