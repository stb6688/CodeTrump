package com.leetcode.oj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leetcode.util.ArrayUtil;
import com.leetcode.util.LeetcodeUtils;

public abstract class PalindromePairs {
	
	public abstract List<List<Integer>> palindromePairs(String[] words);
    public static void main(String[] args) {
    	PalindromePairs instance = new SolutionIII();
    	String[] words;
    	long t1, t2;
    	List<List<Integer>> results;
    	
//    	[[0, 1], [1, 0]]
//    	words = new String[]{"bat", "tab", "cat"};
    	
//    	[[0,1],[1,0]]
    	words = new String[]{"a", ""};
    	
    	// [[0, 1], [1, 0], [3, 2], [2, 4]]
//    	words = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
//    	results = instance.palindromePairs(words);
//    	System.out.println("results=" + results);
    	
//    	words = new String[]{"a", ""};
//    	results = instance.palindromePairs(words);
//    	System.out.println("results=" + results);
    	
    	// [[0,1],[1,0],[2,1],[2,3],[0,3],[3,2]]
    	words = new String[]{"ab","ba","abc","cba"};
    	results = instance.palindromePairs(words);
    	System.out.println("results=" + results);
    	
    	// [0,1], [1,0]
//    	words = new String[]{"bat", "tab", "cat"};
//    	results = instance.palindromePairs(words);
//    	System.out.println("results=" + results);
    	
    	
//    	words = ArrayUtil.str2strArray(LeetcodeUtils.readText(instance));
//    	t1 = System.currentTimeMillis();
//    	results = instance.palindromePairs(words);
//    	t2 = System.currentTimeMillis();
//    	System.out.println(String.format("results=%s, total time=%,dms", results, (t2 - t1)));
	}
    
    static class SolutionIII extends PalindromePairs {
    	public List<List<Integer>> palindromePairs(String[] words) {
            Map<String, List<Integer>> leftIndices = new HashMap<>();
            Map<String, List<Integer>> rightIndices = new HashMap<>();
            Set<String> visited = new HashSet<>();
            for (int i = 0; i < words.length; i++) {
                if (visited.add(words[i]))
                    addPairs(words[i], i, leftIndices, rightIndices);
            }
for (Map.Entry<String, List<Integer>> entry : leftIndices.entrySet()) {
	for (int i : entry.getValue())
		System.out.println("left=" + entry.getKey() + ", right=" + words[i]);
}
System.out.println("------------");
for (Map.Entry<String, List<Integer>> entry : rightIndices.entrySet()) {
	for (int i : entry.getValue())
		System.out.println("left=" + words[i] + ", right=" + entry.getKey());
}
            List<List<Integer>> results = new ArrayList<>();
            List<Integer> indices;
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if ((indices = leftIndices.get(word)) != null) {
                    for (int r : indices)
                        addResult(i, r, results);
                }
                if ((indices = rightIndices.get(word)) != null) {
                    for (int l : indices)
                        addResult(l, i, results);
                }
            }
            
            return results;
        }
        
        private void addResult(int l, int r, List<List<Integer>> results) {
            if (l != r) {
                List<Integer> result = new ArrayList<>(2);
                result.add(l);
                result.add(r);
                results.add(result);
            }
        }
        
        private void addPairs(String word, int idx, Map<String, List<Integer>> leftIndices, Map<String, List<Integer>> rightIndices) {
            // with pivot
            for (int p = 0; p < word.length(); p++)
                addPair(word, p-1, p+1, leftIndices, rightIndices, idx);
            // without pivot
            for (int i = -1; i < word.length(); i++)
                addPair(word, i, i+1, leftIndices, rightIndices, idx);
        }
        
        private void addPair(String word, int l, int r, Map<String, List<Integer>> leftIndices, 
            Map<String, List<Integer>> rightIndices, int idx) {
            StringBuilder builder = new StringBuilder();
            while (l >= 0 && r < word.length()) {
                if (word.charAt(l) != word.charAt(r))
                    break;
                l--;
                r++;
            }
            if (l < 0 && r == word.length()) {
                add(leftIndices, "", idx);
                add(rightIndices, "", idx);
            } else if (l < 0) {
                while (r < word.length())
                    builder.insert(0, word.charAt(r++));
                add(leftIndices, builder.toString(), idx);
            } else if (r == word.length()) {
                while (l >= 0)
                    builder.append(word.charAt(l--));
                add(rightIndices, builder.toString(), idx);
            }
        }
        
        private void add(Map<String, List<Integer>> map, String pair, int idx) {
            List<Integer> indices = map.get(pair);
            if (indices == null) {
                indices = new ArrayList<>();
                map.put(pair, indices);
            }
            indices.add(idx);
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
