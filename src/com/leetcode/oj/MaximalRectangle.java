package com.leetcode.oj;

import com.leetcode.util.ArrayUtil;

public abstract class MaximalRectangle {
	public abstract int maximalRectangle(char[][] matrix);
	public static void main(String[] args) {
		MaximalRectangle instance = new SolutionI();
		char[][] matrix;
		int max;
		
		matrix = ArrayUtil.strArrayTo2DCharArray(new String[]{"11111111","11111110","11111110","11111000","01111000"});
		max = instance.maximalRectangle(matrix);
		System.out.println("max=" + max);
	}
	
	static class SolutionI extends MaximalRectangle {
		public int maximalRectangle(char[][] matrix) {
	        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	            return 0;
	        int rows = matrix.length, cols = matrix[0].length;
	        int[][] counts = new int[rows][];
	        for (int r = 0; r < rows; counts[r++] = new int[cols]);
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (matrix[r][c] == '1') {
	                    if (c == 0)
	                        counts[r][c] = 1;
	                    else
	                        counts[r][c] = counts[r][c-1] + 1;
	                }
	            }
	        }
	        int max = 0;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (r-1 < 0 || counts[r][c] > counts[r-1][c]) {
	                    int w = Integer.MAX_VALUE, h = 0;
	                    int currR = r;
	                    while (currR < rows && (w = Math.min(w, counts[currR][c])) > 0) {
	                        h++;
	                        max = Math.max(max, w*h);
	                        currR++;
	                    }
	                }
	            }
	        }
	        return max;
	    }
	}
}
