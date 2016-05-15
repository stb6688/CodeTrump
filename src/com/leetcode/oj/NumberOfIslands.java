package com.leetcode.oj;

import java.util.LinkedList;
import java.util.List;

import com.leetcode.util.ArrayUtil;

public class NumberOfIslands {
	private static final int[][] moves = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        if (rows == 0)
            return 0;
        int cols = grid[0].length;
        if (cols == 0)
            return 0;
            
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    List<Integer> list = new LinkedList<>();
                    list.add(cols*r + c);
                    while (!list.isEmpty()) {
                        List<Integer> nextList = new LinkedList<>();
                        for (Integer e : list) {
                            int r1 = e / cols;
                            int c1 = e % cols;
//                            grid[r1][c1] = '0';
                            for (int[] move : moves) {
                                int r2 = r1 + move[0];
                                int c2 = c1 + move[1];
                                if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && grid[r2][c2] == '1') {
                                	grid[r2][c2] = '0';
                                    nextList.add(cols*r2 + c2);
                                }
                            }
                        }
                        list = nextList;
                    }
                }
            }
        }
        
        return count;
    }
	
    
	// Solution I: Accepted
	/*
    private static final int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public int numIslands(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        if (rows == 0)
            return 0;
        int cols = grid[0].length;
        if (cols == 0)
            return 0;
            
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    List<Integer> list = new LinkedList<>();
                    list.add(cols*r + c);
                    update(grid, rows, cols, list);
                }
            }
        }
        
        return count;
    }
    
    private void update(char[][] grid, int rows, int cols, List<Integer> list) {
        while (!list.isEmpty()) {
            List<Integer> nextList = new LinkedList<>();
            for (Integer e : list) {
                int r = e / cols;
                int c = e % cols;
                for (int[] move : moves) {
                    int r2 = r + move[0];
                    int c2 = c + move[1];
                    if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && grid[r2][c2] == '1') {
                        grid[r2][c2] = '0';
                        nextList.add(cols*r2 + c2);
                    }
                }
            }
            list = nextList;
        }
    }
    */
    
    public static void main(String[] args) {
    	NumberOfIslands instance = new NumberOfIslands();
    	char[][] grid;
    	
    	// 1
    	grid = ArrayUtil.strArrayTo2DCharArray(new String[]{"11110", "11110", "11110", "00000"});
    	
    	// 3
//    	grid = ArrayUtil.strArrayTo2DCharArray(new String[]{"11000", "11000", "00100", "00011"});
    	
    	// 1
    	String[] sArray = {"11111011111111101011","01111111111110111110","10111001101111111111","11110111111111111111","10011111111111111111","10111111011101110111","01111111111101101111","11111111111101111011","11111111110111111111","11111111111111111111","01111111011111111111","11111111111111111111","11111111111111111111","11111011111110111111","10111110111011110111","11111111111101111110","11111111111110111100","11111111111111111111","11111111111111111111","11111111111111111111"};
    	grid = ArrayUtil.strArrayTo2DCharArray(sArray);
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.numIslands(grid);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
}
