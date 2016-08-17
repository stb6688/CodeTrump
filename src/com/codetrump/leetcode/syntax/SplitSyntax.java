package com.codetrump.leetcode.syntax;

import java.util.Arrays;

public class SplitSyntax {
	
	public static void main(String[] args) {
		String s = "[[[a,b,c]]]";
		String[] splits = s.split("[,\\[\\]]");
		System.out.println(Arrays.toString(splits));
	}

}
