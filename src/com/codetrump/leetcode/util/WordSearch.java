package com.codetrump.leetcode.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class WordSearch {
	public abstract boolean exist(char[][] board, String word);
	public static void main(String[] args) {
		WordSearch instance = new SolutionIII();
		char[][] board; String word;
		boolean result;
		
		board = new char[][] {{'a', 'b'}, {'c', 'd'}};
		word = "acdb";
		
		result = instance.exist(board, word);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionIV extends WordSearch {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public boolean exist(char[][] board, String word) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return false;
	        // word is not null or empty
	        int rows = board.length, cols = board[0].length;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (dfs(board, r, c, word))
	                    return true;
	            }
	        }
	        return false;
	    }
	    
	    private boolean dfs(char[][] board, int r, int c, String word) {
	        Set<Integer> used = new HashSet<>();
	        return recurse(board, r, c, word, 0, used);
	    }
	    
	    private boolean recurse(char[][] board, int r, int c, String word, int idx, Set<Integer> used) {
	        int rows = board.length, cols = board[0].length;
	        int p = r*cols + c;
	        if (used.add(p)) { // modify
	            if (board[r][c] == word.charAt(idx)) {
	                if (idx == word.length()-1)
	                    return true;    // termination
	                else {
	                    for (int[] dir : dirs) {
	                        int r1 = r + dir[0], c1 = c + dir[1];
	                        if (r1 >= 0 && r1 < rows && c1 >= 0 && c1 < cols) {
	                            if (recurse(board, r1, c1, word, idx+1, used))
	                                return true;
	                        }
	                    }
	                }
	            }
	            used.remove(p); // restore
	        }
	        return false;
	    }
	}
	
	static class SolutionIII extends WordSearch {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public boolean exist(char[][] board, String word) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return false;
	        // word is not null or empty
	        int rows = board.length, cols = board[0].length;
	        char ch0 = word.charAt(0);
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (ch0 == board[r][c] && bfs(board, r, c, word))
	                    return true;
	            }
	        }
	        return false;
	    }
	    
	    private boolean bfs(char[][] board, int r, int c, String word) {
	        int rows = board.length, cols = board[0].length;
	        List<Integer> ps = new ArrayList<>();
	        int p0 = cols*r + c;
	        ps.add(p0);
	        List<Set<Integer>> usedSets = new ArrayList<>();
	        Set<Integer> set0 = new HashSet<>();
	        set0.add(p0);
	        usedSets.add(set0);
	        for (int i = 1; i < word.length(); i++) {
	            List<Integer> nextPs = new ArrayList<>();
	            List<Set<Integer>> nextSets = new ArrayList<>();
	            char ch = word.charAt(i);
	            boolean found = false;
	            for (int j = 0; j < ps.size(); j++) {
	                int p = ps.get(j);
	                Set<Integer> usedSet = usedSets.get(j);
	                int r1 = p/cols, c1 = p%cols;
	                for (int[] dir : dirs) {
	                    int r2 = r1 + dir[0], c2 = c1 + dir[1];
	                    int nextP = cols*r2 + c2;
	                    if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && !usedSet.contains(nextP)) {
	                        if (board[r2][c2] == ch) {
	                            found = true;
	                            nextPs.add(nextP);
	                            Set<Integer> nextSet = new HashSet<>(usedSet);
	                            nextSet.add(nextP);
	                            nextSets.add(nextSet);
	                        }
	                    }
	                }
	            }
	            if (!found)
	                return false;
	            ps = nextPs;
	            usedSets = nextSets;
	        }
	        return true;
	    }
	}
	
	
	// Solution I: Logic Error
	static class SolutionI extends WordSearch {
		private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public boolean exist(char[][] board, String word) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return false;
	        // word is not null or empty
	        int rows = board.length, cols = board[0].length;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (bfs(board, r, c, word))
	                    return true;
	            }
	        }
	        return false;
	    }
	    
	    private boolean bfs(char[][] board, int r, int c, String word) {
	        int rows = board.length, cols = board[0].length;
	        Set<Integer> used = new HashSet<>();
	        List<Integer> ps = new ArrayList<>();
	        int p0 = cols*r + c;
	        ps.add(p0);
	        used.add(p0);
	        for (int i = 0; i < word.length(); i++) {
if (i == 3)
	System.out.println();
	            char ch = word.charAt(i);
	            List<Integer> nextPs = new ArrayList<>();
	            boolean found = false;
	            for (int p : ps) {
	                int r1 = p / cols, c1 = p % cols;
	                if (board[r1][c1] == ch) {
	                    found = true;
	                    for (int[] dir : dirs) {
	                        int r2 = r1 + dir[0], c2 = c1 + dir[1];
	                        if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols) {
	                            int nextP = cols*r2 + c2;
	                            if (used.add(nextP))
	                                nextPs.add(nextP);
	                        }
	                    }
	                }
	            }
	            if (!found)
	                return false;
	            ps = nextPs;
	        }
	        return true;
	    }
	}
}
