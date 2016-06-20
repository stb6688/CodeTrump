package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.leetcode.util.ArrayUtil;
import com.leetcode.util.LeetcodeUtils;

public abstract class WordSearchII {
	public abstract List<String> findWords(char[][] board, String[] words);
	public static void main(String[] args) {
		WordSearchII instance = new SolutionIII();
		char[][] board; String[] words;
		List<String> results;
		long t1, t2;
		
		// acdb
//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"ab","cd"});
//		words = new String[]{"acdb"};

		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"ab","aa"});
		words = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
		
//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"oaan","etae","ihkr","iflv"});
//		words = new String[]{"oath","pea","eat","rain"};
		
//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"seenew","tmriva","obsibd","wmysen","ltsnsa","iezlgn"});
//		words = ArrayUtil.str2strArray(LeetcodeUtils.readText(instance));
		
		
		t1 = System.currentTimeMillis();
		results = instance.findWords(board, words);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("results=%s, total time=%,dms", results, (t2 - t1)));
	}
	
	
	static class SolutionIII extends WordSearchII {
		private static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	    public List<String> findWords(char[][] board, String[] words) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return Collections.emptyList();
	        Set<String> set = new HashSet<>();
	        for (String w : words) set.add(w);
	        int rows = board.length, cols = board[0].length;
	        List<String> results = new ArrayList<>();
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                Iterator<String> itr = set.iterator();
	                int[][] used = new int[rows][];
	                int id = 1;
	                while (itr.hasNext()) {
	                    String w = itr.next();
	                    if (bfs(board, r, c, w, used, id++)) {
	                        results.add(w);
	                        itr.remove();
	                    }
	                }
	                if (set.isEmpty())
	                    return results;
	            }
	        }
	        return results;
	    }
	    
	    private boolean bfs(char[][] board, int r, int c, String w, int[][] used, int id) {
	        int rows = board.length, cols = board[0].length;
	        for (int i = 0; i < rows; used[i++] = new int[cols]);
	        List<int[]> ps = new LinkedList<>();
	        ps.add(new int[]{r, c});
//	        used[r][c] = id;
	        int idx = 0;
	        while (!ps.isEmpty()) {
	            List<int[]> nextPs = new LinkedList<>();
	            char ch = w.charAt(idx);
	            for (int[] p : ps) {
	                int r1 = p[0], c1 = p[1];
	                if (board[r1][c1] == ch) {
	                	used[r1][c1] = id;
	                    if (idx == w.length()-1)
	                        return true;
	                    for (int[] dir : dirs) {
	                        int r2 = r1 + dir[0], c2 = c1 + dir[1];
	                        if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && used[r2][c2] != id) {
//	                            used[r2][c2] = id;
	                            nextPs.add(new int[]{r2, c2});
	                        }
	                    }
	                }
	            }
	            ps = nextPs;
	            idx++;
	        }
	        return false;
	    }
	}
	
	
	static class SolutionI extends WordSearchII {
		private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    public List<String> findWords(char[][] board, String[] words) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return Collections.emptyList();
	        int rows = board.length, cols = board[0].length;
	        Set<String> set = new HashSet<>();
	        int maxLen = 0;
	        for (String word : words) {
	            set.add(word);
	            maxLen = Math.max(maxLen, word.length());
	        }
	        List<String> results = new ArrayList<>();
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                bfs(board, r, c, set, maxLen, results);
	            }
	        }
	        return results;
	    }
	    
	    private void bfs(char[][] board, int r, int c, Set<String> set, int maxLen, List<String> results) {
	        int rows = board.length, cols = board[0].length;
	        boolean[][] visited = new boolean[rows][];
	        // track cells visited in bfs
	        for (int i = 0; i < rows; visited[i++] = new boolean[cols]);
	        List<int[]> ps = new ArrayList<>();
	        ps.add(new int[]{r, c});
	        List<String> words = new ArrayList<>(set);
	        int idx = 0;
	        while (!ps.isEmpty() && !words.isEmpty()) {
	            List<int[]> nextPs = new ArrayList<>();
	            List<String> nextWords = new ArrayList<>();
	            for (int i = 0; i < ps.size(); i++) {
	                int[] p = ps.get(i);
	                int r1 = p[0], c1 = p[1];
	                char ch = board[r1][c1];
	                Iterator<String> itr = words.iterator();
	                while (itr.hasNext()) {
	                    String word = itr.next();
	                    if (idx < word.length() && word.charAt(idx) == ch) {
	                        if (idx == word.length() - 1) {
	                            results.add(word);
	                            set.remove(word);
	                        } else {
	                            nextWords.add(word);
	                        }
	                        itr.remove();
	                    }
	                }
	                for (int[] move : moves) {
	                    int r2 = r1 + move[0], c2 = c1 + move[1];
	                    if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && !visited[r2][c2]) {
	                        nextPs.add(new int[]{r2, c2});
	                        visited[r2][c2] = true;
	                    }
	                }
	            }
	            ps = nextPs;
	            words = nextWords;
	            idx++;
	        }
	    }
	}
}
