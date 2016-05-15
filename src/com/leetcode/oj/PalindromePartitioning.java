package com.leetcode.oj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePartitioning {
	private Map<Integer, List<List<Integer>>> startRanges = new HashMap<>();
    public List<List<String>> partition(String s) {
        for (int i = 0; i < s.length(); i++)
            addPalindromes(s, i);
        List<List<String>> results = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();
        list.addAll(startRanges.get(0));
        while (!list.isEmpty()) {
System.out.println("list: " + list);
            List<List<Integer>> nextList = new ArrayList<>();
            for (List<Integer> range : list) {
                int end = range.get(1);
                if (end == s.length() - 1) {
                    results.add(getResult(s, range));
                } else {
                    List<List<Integer>> subRanges = startRanges.get(end+1);
                    if (subRanges != null) {
                        for (List<Integer> subRange : subRanges) {
                            List<Integer> nextRange = new ArrayList<>(range);
                            nextRange.addAll(subRange);
                            nextList.add(nextRange);
                        }
                    }
                }
            }
            list = nextList;
        }
        return results;
    }
    
    private void addPalindromes(String s, int idx) {
        // include curr idx
        int l = idx, r = idx + 1;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {addRange(l--, r++);}
        // exclude curr idx
        l = idx; r = idx;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {addRange(l--, r++);}
    }
    
    private void addRange(int start, int end) {
        List<List<Integer>> ranges = startRanges.get(start);
        if (ranges == null) {
            ranges = new ArrayList<>();
            startRanges.put(start, ranges);
        }
        List<Integer> range = new ArrayList<>(2);
        range.add(start);
        range.add(end);
        ranges.add(range);
    }
    
    private List<String> getResult(String s, List<Integer> range) {
        List<String> results = new ArrayList<>();
        int i = 0;
        while (i < range.size()) {
            results.add(s.substring(range.get(i-1), range.get(i)+1));
            i++;
        }
        return results;
    }
    
    public static void main(String[] args) {
    	PalindromePartitioning instance = new PalindromePartitioning();
    	String s;
    	
    	s = "aab";
    	
    	long t1 = System.currentTimeMillis();
    	List<List<String>> results = instance.partition(s);
    	long t2 = System.currentTimeMillis();
    	System.out.println("results=" + results);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
}
