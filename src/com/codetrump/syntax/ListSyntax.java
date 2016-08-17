package com.codetrump.syntax;

import java.util.Arrays;

public class ListSyntax {
	
	public static void main(String[] args) {
		// Array to List conversion
		System.out.println(Arrays.asList(1,2,3));
		
		// NOTE: must be an array of Integer, not int!
		Integer[] array1 = {1,2,3};
		System.out.println(Arrays.asList(array1));
		
		// NOTE: this becomes a list of array of int[] type! 
		int[] array2 = {1,2,3};
		System.out.println(Arrays.asList(array2));
	}

}
