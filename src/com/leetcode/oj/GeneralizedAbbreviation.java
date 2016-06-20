package com.leetcode.oj;

import java.util.ArrayList;
import java.util.List;

public abstract class GeneralizedAbbreviation {
	public abstract List<String> generateAbbreviations(String word);
	public static void main(String[] args) {
		GeneralizedAbbreviation instance = new SolutionII();
		String word;
		List<String> results;
		
		word = "word";
		
		results = instance.generateAbbreviations(word);
		System.out.println("results=" + results);
	}
	
	
	static class SolutionII extends GeneralizedAbbreviation {
		public List<String> generateAbbreviations(String word) {
	        List<String> list = new ArrayList<>();
	        list.add("");
	        for (int i = 0; i < word.length(); i++) {
	            List<String> nextList = new ArrayList<>();
	            char ch = word.charAt(i);
	            for (String abbr : list) {
	                nextList.add(abbr + ch);
	                if (!abbr.isEmpty() && Character.isDigit(abbr.charAt(abbr.length()-1))) {
	                    int j = abbr.length()-1;
	                    while (j >= 0 && Character.isDigit(abbr.charAt(j)))
	                        j--;
	                    String head = abbr.substring(0, j+1);
	                    nextList.add(head + (1 + Integer.valueOf(abbr.substring(j+1, abbr.length()))));
	                } else
	                    nextList.add(abbr + "1");
	            }
	            list = nextList;
	        }
	        return list;
	    }
	}
}
