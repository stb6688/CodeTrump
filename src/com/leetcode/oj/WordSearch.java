package com.leetcode.oj;

import java.util.HashSet;
import java.util.Set;

public class WordSearch {
	
private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        char[] chars = word.toCharArray();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (dfs(r, c, board, rows, cols, 0, chars, new HashSet<Integer>()))
                    return true;
            }
        }
        return false;
    }
    
    private boolean dfs(int r, int c, char[][] board, int rows, int cols, int i, char[] chars, Set<Integer> visited) {
        if (r < 0 || r == rows || c < 0 || c == cols)
            return false;
        if (board[r][c] == chars[i]) {
            int hash = cols*r + c;
            if (!visited.add(hash))
                return false;
System.out.println("r=" + r + ", c=" + c + ", i=" + i + ", visited=" + visited);
            if (i == chars.length - 1)
                return true;
            for (int[] move : moves) {
                if (dfs(r + move[0], c + move[1], board, rows, cols, i+1, chars, visited))
                    return true;
            }
            visited.remove(hash);
        }
        return false;
    }
	
    // Solution I: wrong logic
    /*
	public boolean exist(char[][] board, String word) {
        int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int rows = board.length;
        if (rows == 0)
            return false;
        int cols = board[0].length;
        if (cols == 0)
            return false;
            
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (bfs(board, r, c, rows, cols, word.toCharArray(), 0, moves, new HashSet<Integer>()))
                    return true;
            }
        }
        return false;
    }
    
    private boolean bfs(char[][] board, int r, int c, int rows, int cols, char[] chars, int index, int[][] moves, Set<Integer> visited) {
        // row/col out of bound
        if (r < 0 || r == rows || c < 0 || c == cols)
            return false;
        
        if (board[r][c] == chars[index]) {
        	// visited
            if (!visited.add(cols*r + c))
                return false;
            if (index == chars.length - 1)
                return true;
            for (int[] move : moves)
                if (bfs(board, r+move[0], c+move[1], rows, cols, chars, index+1, moves, visited))
                    return true;
        }
        return false;
    }
    */
    
    public static void main(String[] args) {
    	WordSearch instance = new WordSearch();
    	char[][] board;
    	String word;
    	
    	// true
//    	board = new char[][]{{'a', 'b'}, {'c', 'd'}};
//    	word = "acdb";
    	
    	board = new char[][]{{'a', 'a'}};
    	word = "aaa";
    	
    	boolean result = instance.exist(board, word);
    	System.out.println("result=" + result);
	}

}
