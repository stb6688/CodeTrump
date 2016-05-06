package com.leetcode.syntax;

public class StringBuilderSyntax {
	
	public static void main(String[] args) {
		StringBuilder builder1 = new StringBuilder('a');
		System.out.println("builder1=" + builder1);
		
		StringBuilder builder2 = new StringBuilder().append('a');
		System.out.println("builder2=" + builder2);
	}

}
