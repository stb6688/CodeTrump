package com.leetcode.oj;

public class AdditiveNumber {
	public boolean isAdditiveNumber(String num) {
        for (int i = 1; i < num.length(); i++) {
            for (int j = i+1; j < num.length(); j++) {
                String s1 = num.substring(0, i);
                if (s1.length() > 1 && s1.charAt(0) == '0')
                	continue;
                String s2 = num.substring(i, j);
                if (s2.length() > 1 && s2.charAt(0) == '0')
                	continue;
                if (check(s1, s2, Math.max(s1.length(), s2.length()), j, num))
                    return true;
            }
        }
        return false;
    }
    
    private boolean check(String s1, String s2, int len, int index, String num) {
        for (int index2 = index + len; index2 <= num.length() && index2 <= index + len + 1; index2++) {
            int len2 = index2 - index;
            if (len2 > 1 && num.charAt(index) == '0')
                continue;
            String s3 = num.substring(index, index2);
            if (add(s1, s2).equals(s3)) {
                if (index2 == num.length()) {
                	System.out.println(String.format("s1=%s, s2=%s, s3=%s", s1, s2, s3));
                    return true;
                }
                else if (check(s2, s3, len2, index2, num))
                    return true;
            }
        }
        return false;
    }
    
    private String add(String s1, String s2) {
        int i1 = s1.length() - 1, i2 = s2.length() - 1;
        int carry = 0;
        StringBuilder sum = new StringBuilder();
        while (i1 >= 0 || i2 >= 0) {
        	int n1 = 0, n2 = 0;
            if (i1 >= 0)
                n1 = s1.charAt(i1) - '0';
            if (i2 >= 0)
                n2 = s2.charAt(i2) - '0';
            int sumD = n1 + n2 + carry;
            sum.insert(0, sumD % 10);
            carry = sumD / 10;
            i1--;
            i2--;
        }
        if (carry == 1)
            sum.insert(0, 1);
        return sum.toString();
    }

    public static void main(String[] args) {
    	AdditiveNumber instance = new AdditiveNumber();
    	String num;
    	
//    	num = "112358";	// true
    	
//    	num = "199100199";	// true
    	
//    	num = "1203";	// false
    	
//    	num = "19910011992";	// false
    	
    	num = "011235";	// true
    	
    	boolean result = instance.isAdditiveNumber(num);
    	System.out.println("result=" + result);
	}
}
