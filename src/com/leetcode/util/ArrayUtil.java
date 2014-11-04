package com.leetcode.util;

public class ArrayUtil {
	
	/**
	 * print out elements of integer array separated by comma.
	 * @param array
	 */
	public static void printArray(int[] array) {
		boolean first = true;
		for (int i : array) {
			if (first) {
				System.out.print(i);
				first = false;
			} else {
				System.out.print(", " + i);
			}
		}
		System.out.println();
	}
	
	public static void printMatrix(int[] array) {
		
	}
	
	public static void printMatrix(int[][] array) {
		int dim1 = array.length;
		int dim2 = array[0].length;
		
		System.out.print("[");
		for (int i = 0; i < dim1; i++) {
			System.out.print("[");
			for (int j = 0; j < dim2; j++) {
				System.out.print(array[i][j]);
				if (j < dim2 - 1)
					System.out.print(",");
			}
			System.out.print("]");
			if (i < dim1 - 1)
				System.out.print(",");
		}
		System.out.print("]");
		System.out.println();
	}

}
