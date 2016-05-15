package com.leetcode.oj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SparseMatrixMultiplication {
	public int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsB = B[0].length;
        int[][] results = new int[rowsA][];
        for (int r = 0; r < rowsA; r++)
            results[r] = new int[colsB];
        // for each row on A, the cols that contains non-zero value
        Map<Integer, List<Integer>> rowColsA = new HashMap<>();
        for (int r = 0; r < rowsA; r++) {
            List<Integer> cols = new LinkedList<>();
            for (int c = 0; c < A[r].length; c++)
                if (A[r][c] != 0)
                    cols.add(c);
            if (!cols.isEmpty())
                rowColsA.put(r, cols);
        }
        // for each col on B, the rows that contains non-zero value
        Map<Integer, Set<Integer>> colRowsB = new HashMap<>();
        for (int c = 0; c < B[0].length; c++) {
            Set<Integer> rows = new HashSet<>();
            for (int r = 0; r < B.length; r++) {
                if (B[r][c] != 0)
                    rows.add(r);
            }
            if (!rows.isEmpty())
                colRowsB.put(c, rows);
        }
        
        for (Map.Entry<Integer, List<Integer>> entryA : rowColsA.entrySet()) {
            int rowA = entryA.getKey();
            List<Integer> cols = entryA.getValue();
            for (Map.Entry<Integer, Set<Integer>> entryB : colRowsB.entrySet()) {
                int colB = entryB.getKey();
                Set<Integer> rows = entryB.getValue();
                int sum = 0;
                for (int col : cols) {
                    if (rows.contains(col))
                        sum += A[rowA][col] * B[col][colB];
                }
                results[rowA][colB] = sum;
            }
        }
        
        return results;
    }
	
	public static void main(String[] args) {
		SparseMatrixMultiplication instance = new SparseMatrixMultiplication();
		int[][] A, B;
		
		A = new int[][]{{1,0,0}, {-1,0,3}};
		B = new int[][]{{7,0,0}, {0,0,0}, {0,0,1}};
		
		int[][] results = instance.multiply(A, B);
		for (int[] row : results)
			System.out.println(Arrays.toString(row));
	}
}
