package com.leetcode.oj;

public class AndroidUnlockPatterns {
	private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, -1}, {1, 1}, {-1, 1}};
    private static final int[][] lmoves = {{-1,0,-1,-1}, {-1,-1,0,-1}, {0,-1,1,-1}, {1,-1,-1,0}, 
                                           {1,0,1,1}, {1,1,0,1}, {0,1,-1,1}, {-1,1,-1,0}};
    public int numberOfPatterns(int m, int n) {
        // special
        if (n == 0) return 0;
        int result = 0;
        int[][] pad = new int[3][];
        for (int i = 0; i < 3; i++)
            pad[i] = new int[3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                pad[r][c] = 1; // modify
                result += dp(r, c, m, n, 1, pad);
                pad[r][c] = 0; // restore
            }
        }
        return result;
    }
    
    // dp: select new number at (r,c); try to continue dp.
    private int dp(int r, int c, int m, int n, int len, int[][] pad) {
        int result = 0;
        if (len >= m && len <= n)
            result++;
        if (len == n) // termination
            return result;
        
        // first, try "straight" neighbor (direct or neighbor of neighbor)
        for (int[] move : moves)
            result += move(r, c, move, m, n, len, pad);
        // second, try "L" neighbors
        for (int[] lmove : lmoves)
            result += lmove(r, c, lmove, m, n, len, pad);
                
        return result;
    }
    
    private int move(int r, int c, int[] move, int m, int n, int len, int[][]pad) {
        int result = 0;
        int r1 = r + move[0], c1 = r + move[1];
        if (valid(r1, c1)) {
            if (pad[r1][c1] == 0) {
                result += take(r1, c1, len, m, n, pad);
            } else { // neighbor is taken, move cross neighbor and try neighbor's neighbor
                r1 += move[0];
                c1 += move[1];
                if (valid(r1, c1) && pad[r1][c1] == 0) {
                    result += take(r1, c1, len, m, n, pad);
                }
            }
        }
        
        return result;
    }
    
    private int lmove(int r, int c, int[] lmove, int m, int n, int len, int[][]pad) {
        int result = 0;
        if (taken(r+lmove[0], c+lmove[1], pad) && taken(r+lmove[2], c+lmove[3], pad)) {
        	int r1 = r+lmove[0]+lmove[2], c1 = c+lmove[1]+lmove[3];
        	if (valid(r1, c1))
        		result += take(r1, c1, len, m, n, pad);
        }
        return result;
    }
    
    private int take(int r, int c, int len, int m, int n, int[][] pad) {
        int result = 0;
        pad[r][c] = 1; // modify
        result = dp(r, c, len+1, m, n, pad);
        pad[r][c] = 0; // restore
        return result;
    }
    
    private boolean taken(int r, int c, int[][] pad) {
        return valid(r, c) && pad[r][c] == 1;
    }
    
    private boolean valid(int r, int c) {
        return r >= 0 && r < 3 && c >= 0 && c < 3;
    }
    
    public static void main(String[] args) {
    	AndroidUnlockPatterns instance = new AndroidUnlockPatterns();
    	int m, n;
    	
    	m = 1; n = 2;
    	
    	int result = instance.numberOfPatterns(m, n);
    	System.out.println("result=" + result);
	}
}
