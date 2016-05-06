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

	public static int[][] str2int2DArray(String s) {
		s = s.substring(1, s.length()-1);
		if (s.length() == 0)
			return new int[][]{};
		String[] splits = s.split("\\[");
		int[][] results = new int[splits.length - 1][];
		int r = 0;
		for (String split : splits) {
			split = split.trim();
			if (split.isEmpty())
				continue;
			if (split.charAt(split.length()-1) == ',')
				split = split.substring(0, split.length() - 2);
			else
				split = split.substring(0, split.length() - 1);
			String[] elements = split.split(",");
			int[] row = new int[elements.length];
			for (int i = 0; i < elements.length; i++) {
				row[i] = Integer.valueOf(elements[i]);
			}
			results[r++] = row;
		}
		return results;
	}
}
