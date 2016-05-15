package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FlipGameII {
	private Set<List<Object>> visited = new HashSet<>();
    public boolean canWin(String s) {
        return dfs(s.toCharArray(), true);
    }
    
    private boolean dfs(char[] chars, boolean turn) {
System.out.println("input chars=" + Arrays.toString(chars) + ", turn=" + turn);
        boolean found = false;
        List<Object> params = new ArrayList<>(2);
        params.add(new String(chars));
        params.add(turn);
//        if (!visited.add(params))
//            return false;
        for (int i = 0; i < chars.length-1; i++) {
            if (chars[i] == '+' && chars[i+1] == '+') {
                found = true;
                chars[i] = '-';
                chars[i+1] = '-';
                boolean sub = dfs(chars, turn^true);
                if (sub && turn)
                    return true;
                else if (!sub && !turn)
                    return false;
                chars[i] = '+';
                chars[i+1] = '+';
            }
        }
        // termination
        if (!found)
            return !turn;
        else
            return !turn; // unreachable
    }
    
    public static void main(String[] args) {
    	FlipGameII instance = new FlipGameII();
    	String s;
    	
    	s = "++++";
    	
    	boolean result = instance.canWin(s);
    	System.out.println("result=" + result);
	}
}
