package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionAddOperators {
	private final Map<List<Integer>, List<StringBuilder>> cache = new HashMap<>();
    private static final char[] signs = {'+', '-', '*'};
    public List<String> addOperators(String num, int target) {
        // edge case
        if (num.length() == 0)
            return Collections.emptyList();
        List<StringBuilder> builders = help(num.toCharArray(), 0, target);
        List<String> results = new ArrayList<>();
        for (StringBuilder builder : builders)
            results.add(builder.toString());
        return results;
    }
    
    private List<StringBuilder> help(char[] chars, int i, int target) {
        List<Integer> key = new ArrayList<>(2);
        key.add(i);
        key.add(target);
        List<StringBuilder> results = cache.get(key);
        if (results != null)
            return results;
        results = new ArrayList<>();
        if (i == chars.length - 1) {
            if (chars[i] - '0' == target)
                results.add(new StringBuilder().append(chars[i]));
        } else {
            int curr = chars[i] - '0';
            List<StringBuilder> subs = Collections.emptyList();
            for (char sign : signs) {
                switch (sign) {
                    case '+':
                        subs = help(chars, i+1, target - curr);
                        break;
                    case '-':
                        subs = help(chars, i+1, curr - target);
                        break;
                    case '*':
                        if (curr != 0 && target % curr == 0)
                            subs = help(chars, i+1, target / curr);
                        break;
                }
                for (StringBuilder sub : subs) {
                    StringBuilder result = new StringBuilder();
                    result.append(curr).append(sign).append(sub);
                    results.add(result);
                }
            }
        }
        cache.put(key, results);
        return results;
    }
    
    public static void main(String[] args) {
    	ExpressionAddOperators instance = new ExpressionAddOperators();
    	String num;
    	int target;
    	
    	num = "232";
    	target = 8;
    	
    	List<String> results = instance.addOperators(num, target);
    	System.out.println("results=" + results);
	}

}
