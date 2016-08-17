package com.codetrump.syntax;

public class IntegerSyntax {
	public static void main(String[] args) {
		Integer i = new Integer(1);
		if (--i == 0) {
			System.out.println("i was 1");
			System.out.println("i = " + i);
		}
	}
}
