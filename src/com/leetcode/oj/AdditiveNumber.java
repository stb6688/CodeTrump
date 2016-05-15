package com.leetcode.oj;

public class AdditiveNumber {
	
	public boolean isAdditiveNumber(String num) {
        for (int e1 = 1; e1 < num.length(); e1++) {
            for (int e2 = e1+1; e2 < num.length(); e2++) {
                String s1 = num.substring(0, e1);
                String s2 = num.substring(e1, e2);
                if (leadZero(s1) || leadZero(s2))
                    continue;
                String s3 = num.substring(e2, num.length());
                if (check(s1, s2, s3))
                    return true;
            }
        }
        return false;
    }
    
    private boolean check(String s1, String s2, String s3) {
        String sum = add(s1, s2);
        if (s3.length() >= sum.length() && s3.substring(0, sum.length()).equals(sum))
            return s3.length() == sum.length() ? true : check(s2, sum, s3.substring(sum.length(), s3.length()));
        else
            return false;
    }
    
    private String add(String s1, String s2) {
        StringBuilder builder = new StringBuilder();
        int idx1 = s1.length()-1, idx2 = s2.length()-1;
        int carry = 0;
        while (idx1 >= 0 || idx2 >= 0) {
            int i1 = 0, i2 = 0;
            if (idx1 >= 0)
                i1 = s1.charAt(idx1) - '0';
            if (idx2 >= 0)
                i2 = s2.charAt(idx2) - '0';
            builder.insert(0, (i1 + i2 + carry)%10);
            carry = (i1 + i2 + carry) / 10;
            idx1--;
            idx2--;
        }
        if (carry == 1)
            builder.insert(0, '1');
        return builder.toString();
    }
    
    private boolean leadZero(String s) {
        return s.length() > 1 && s.charAt(0) == '0';
    }
	
	
	// Solution II: Accepted
	/*
	public boolean isAdditiveNumber(String num) {
        for (int d1 = 1; d1 < num.length(); d1++) {
            for (int d2 = d1+1; d2 < num.length(); d2++) {
                if (leadingZero(num, 0, d1) || leadingZero(num, d1, d2))
                    continue;
                String sum = add(num, 0, d1, d2);
                int d3 = match(num, d2, sum);
                if (d3 > 0 && check(num, d1, d2, d3))
                    return true;
            }
        }
        return false;
    }
    
    private boolean leadingZero(String num, int i, int j) {
        if (j - i > 1 && num.charAt(i) == '0')
            return true;
        return false;
    }
    
    private String add(String num, int start, int d1, int d2) {
        StringBuilder builder = new StringBuilder();
        int idx1 = d1-1, idx2 = d2-1, carry = 0;
        while (idx1 >= start || idx2 >= d1) {
            int i1 = 0, i2 = 0;
            if (idx1 >= start)
                i1 = num.charAt(idx1) - '0';
            if (idx2 >= d1)
                i2 = num.charAt(idx2) - '0';
            builder.insert(0, (i1 + i2 + carry) % 10);
            carry = (i1 + i2 + carry)/10;
            idx1--;
            idx2--;
        }
        if (carry == 1)
            builder.insert(0, '1');
        return builder.toString();
    }
    
    private int match(String num, int start, String target) {
        int end = start + target.length();
        if (end <= num.length() && target.equals(num.substring(start, end)))
        	return end;
        else
            return -1;
    }
    
    private boolean check(String num, int d0, int d1, int d2) {
        if (d2 == num.length())
            return true;
        String sum = add(num, d0, d1, d2);
        int d3 = -1;
        if ((d3 = match(num, d2, sum)) < 0)
            return false;
        else
            return check(num, d1, d2, d3);
    }
    */
      
	
	/*
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
    */

    public static void main(String[] args) {
    	AdditiveNumber instance = new AdditiveNumber();
    	String num;
    	
    	num = "123";	//true
    	
//    	num = "112358";	// true
    	
//    	num = "199100199";	// true
    	
//    	num = "1203";	// false
    	
//    	num = "19910011992";	// false
    	
//    	num = "011235";	// true
    	
    	
    	
    	boolean result = instance.isAdditiveNumber(num);
    	System.out.println("result=" + result);
	}
}
