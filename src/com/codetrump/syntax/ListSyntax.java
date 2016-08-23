package com.codetrump.syntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListSyntax {
	
	public static void array2list() {
		// Array to List conversion
		System.out.println(Arrays.asList(1,2,3));
		
		// NOTE: must be an array of Integer, not int!
		Integer[] array1 = {1,2,3};
		System.out.println(Arrays.asList(array1));
		
		// NOTE: this becomes a list of array of int[] type! 
		int[] array2 = {1,2,3};
		System.out.println(Arrays.asList(array2));
	}
	
	/**
	 * Fill a list using Collections.fill().
	 * Note first argument must be a List!
	 * @param val
	 */
	public static void fillList(int val) {
		List<Integer> list = new ArrayList<>(10); // won't work
		Collections.fill(list, val);
		System.out.println("list filled=" + list);
	}
	
	public static void main(String[] args) {
		
		array2list();

		fillList(1);
		
	}

}
