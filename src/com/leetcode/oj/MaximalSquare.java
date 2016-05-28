package com.leetcode.oj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leetcode.util.ArrayUtil;

public class MaximalSquare {
	public int maximalSquare(char[][] matrix) {
        int max = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int rows = matrix.length, cols = matrix[0].length;
        List<List<Integer>> rects = new ArrayList<>();
        Set<List<Integer>> visited = new HashSet<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == '1') {
                    List<Integer> rect = new ArrayList<>(2);
                    rect.add(cols*r + c);
                    rect.add(cols*r + c);
                    rects.add(rect);
                    visited.add(rect);
                }
            }
        }
        while (!rects.isEmpty()) {
            List<List<Integer>> nextRects = new ArrayList<>();
            for (List<Integer> rect : rects) {
                int r0 = rect.get(0) / cols, c0 = rect.get(0) % cols;
                int r1 = rect.get(1) / cols, c1 = rect.get(1) % cols;
                max = Math.max(max, (r1-r0+1)*(c1-c0+1));
                // try expand
                if (++r1 < rows && ++c1 < cols) {
                    boolean allOnes = true;
                    for (int r = r0; r <= r1; r++) {
                        if (matrix[r][c1] != '1') {
                            allOnes = false;
                            break;
                        }
                    }
                    if (allOnes) {
                        for (int c = c0; c <= c1; c++) {
                            if (matrix[r1][c] != '1') {
                                allOnes = false;
                                break;
                            }
                        }
                    }
                    if (allOnes) {
                        List<Integer> nextRect = new ArrayList<>(rect);
                        nextRect.set(1, cols*r1 + c1);
                        if (visited.add(nextRect))
                            nextRects.add(nextRect);
                    }
                }

            }
            rects = nextRects;
        }
        
        return max;
    }
	
	public static void main(String[] args) {
		MaximalSquare instance = new MaximalSquare();
		char[][] matrix;
		
		matrix = new char[][]{
			"10100".toCharArray(), 
			"10111".toCharArray(), 
			"11111".toCharArray(), 
			"10010".toCharArray()};
			
		int result = instance.maximalSquare(matrix);
		System.out.println("result=" + result);
	}
}
