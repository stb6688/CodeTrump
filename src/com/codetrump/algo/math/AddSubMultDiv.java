package com.codetrump.algo.math;

public class AddSubMultDiv {
	
	/**
	 * s1 & s2 are both non-empty string representing arbitrarily large numbers,
	 * and they could have a negative sign.
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String add(String s1, String s2) {
		char head1 = s1.charAt(0), head2 = s2.charAt(0);
		boolean revert = false;
		if (head1 == '-' && head2 == '-') {
			s1 = s1.substring(1, s1.length());
			s2 = s2.substring(1, s2.length());
			revert = true;
		} else if (head1 == '-') {
			return sub(s2, s1.substring(1, s1.length()));
		} else if (head2 == '-') {
			return sub(s1, s2.substring(1, s2.length()));
		}
		StringBuilder builder = new StringBuilder();
		int carry = 0;
		int i = s1.length() - 1, j = s2.length() - 1;
		while (i >= 0 || j >= 0) {
			int n1 = 0, n2 = 0;
			if (i >= 0)
				n1 = s1.charAt(i) - '0';
			if (j >= 0)
				n2 = s2.charAt(j) - '0';
			int sum = n1 + n2 + carry;
			builder.insert(0, sum % 10);
			carry = sum / 10;
			i--;
			j--;
		}
		if (carry == 1)
			builder.insert(0, 1);
		if (revert)
			builder.insert(0, '-');
		return builder.toString();
	}
	
	public static String sub(String s1, String s2) {
		char head1 = s1.charAt(0), head2 = s2.charAt(0);
		if (head1 == '-' && head2 == '-')
			return sub(s2.substring(1, s2.length()), s1.substring(1, s1.length()));
		else if (head1 == '-')
			return "-" + add(s1.substring(1, s1.length()), s2);
		else if (head2 == '-')
			return add(s1, s2.substring(1, s2.length()));
		if (less(s1, s2))
			return "-" + sub(s2, s1);
		StringBuilder builder = new StringBuilder();
		int i = s1.length() - 1, j = s2.length() - 1, borrow = 0;
		while (i >= 0) {
			int n1 = s1.charAt(i) - '0';
			int n2 = 0;
			if (j >= 0)
				n2 = s2.charAt(j) - '0';
			int diff = n1 - borrow - n2;
			if (diff < 0) {
				borrow = 1;
				diff += 10;
			} else
				borrow = 0;
			builder.insert(0, diff);
			i--;
			j--;
		}
		while (builder.length() > 1 && builder.charAt(0) == '0')
			builder.deleteCharAt(0);
		return builder.toString();
	}
	
	public static String mult(String s1, String s2) {
		return null;
	}
	
	public static String div(String s1, String s2) {
		return null;
	}
	
	public static String module(String s1, String s2) {
		return null;
	}
	
	public static String pow(String s1, String s2) {
		return null;
	}
	
	// TODO: s1 & s2 can be negative as well
	public static boolean less(String s1, String s2) {
		if (s1.length() > s2.length())
			return false;
		else if (s1.length() < s2.length())
			return true;
		int i = 0, j = 0;
		while (i < s1.length()) {
			if (s1.charAt(i++) < s2.charAt(j++))
				return true;
		}
		// equal
		return false;
	}
	
	
	
	
	public static void main(String[] args) {
		// add
//		System.out.println(add("17", "28"));
//		System.out.println(add("105635", "2222222222222222222222222222222222222222222222222222"));
//		System.out.println(add("99", "99"));
//		System.out.println(add("99", "0"));
//		System.out.println(add("0", "0"));
		
		// sub
		System.out.println(sub("17", "28"));
		System.out.println(sub("105635", "2222222222222222222222222222222222222222222222222222"));
		System.out.println(sub("99", "99"));
		System.out.println(sub("0", "99"));
		System.out.println(sub("0", "0"));
	}
}
