package com.leetcode.oj;

public class ShortestPalindrome {

	public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return s;
        int num = 0;
        for (int i = (s.length()-1)/2; i >= 0; i--) {
            if ((num = match(s, 0, i-1, i+1, s.length()-1)) >= 0 
                || (num = match(s, 0, i-1, i, s.length()-1)) >= 0)
                break;
        }
System.out.println("num=" + num);
        StringBuilder builder = new StringBuilder(s);
        for (int i = 0; i < num; i++)
            builder.insert(i, s.charAt(s.length()-1-i));
        return builder.toString();
    }
    
    private int match(String s, int i1, int j1, int i2, int j2) {
        while (j1 >= i1 && i2 <= j2) {
            if (s.charAt(j1) != s.charAt(i2))
                return -1;
            j1--;
            i2++;
        }
        return j2 - i2 + 1;
    }
	
    public static void main(String[] args) {
    	ShortestPalindrome instance = new ShortestPalindrome();
    	String s;
    	
//    	s = "aba";	// aba
    	
//    	s = "ba";	// aba
    	
    	s = "abbacd";	// 
    	
//    	s = "aacecaaa"; // aaacecaaa
    	
//    	s = "abcd"; // dcbabcd
    	
    	String result = instance.shortestPalindrome(s);
    	System.out.println("result=" + result);
	}
}
