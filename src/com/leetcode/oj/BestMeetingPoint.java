package com.leetcode.oj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leetcode.util.ArrayUtil;

public class BestMeetingPoint {
	
	private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int rows = grid.length, cols = grid[0].length;
        List<List<Integer>> psList = new ArrayList<>(rows*cols);
        List<Integer> ids = new ArrayList<>();
        List<Integer> dists = new ArrayList<>(rows*cols);
        List<Integer> counts = new ArrayList<>(rows*cols);
        Set<Integer> ones = new HashSet<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int p = cols*r + c;
                List<Integer> ps = new ArrayList<>();
                ps.add(p);
                psList.add(ps);
                ids.add(p);
                dists.add(0);
                if (grid[r][c] == 1) {
                    counts.add(1);
                    ones.add(cols*r + c);
                } else {
                    counts.add(0);
                }
            }
        }
        
        int h = 1;
        while (!psList.isEmpty()) {
            for (int i = 0; i < psList.size(); i++) {
                List<Integer> ps = psList.get(i);
                int id = ids.get(i);
                int dist = dists.get(i);
                int count = counts.get(i);
                if (count == ones.size())
                    return dist; // greedy
                List<Integer> nextPs = new ArrayList<>();
                for (int p : ps) {
                    int r = p/cols, c = p%cols;
                    for (int[] move : moves) {
                        int r1 = r + move[0];
                        int c1 = c + move[1];
                        int p1 = cols*r1 + c1;
                        if (r1 >= 0 && r1 < rows && c1 >= 0 && c1 < cols) {
                            nextPs.add(p1);
                            if (grid[r1][c1] == 1) {
                                count++;
                                dist += h;
                            }
                        }
                    }
                }
                psList.set(i, nextPs);
                dists.set(i, dist);
                counts.set(i, count);
            }
            h++;
        }
        
        // unreachable
        return -1;
    }
    
    public static void main(String[] args) {
    	BestMeetingPoint instance = new BestMeetingPoint();
    	int[][] grid;
    	
//    	grid = new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}; // 6
    	
    	grid = ArrayUtil.str2int2DArray("[[0,0,0,0,0,0,1,0,0,0,1,1,0,1,0,0],[0,0,0,1,0,0,1,0,0,0,0,0,0,1,0,0],[0,0,1,1,0,1,0,0,0,0,1,0,0,1,0,0],[0,0,0,0,1,1,0,0,1,0,0,0,0,0,1,0],[1,0,0,0,0,1,0,0,1,0,1,0,0,1,0,0],[0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,0],[0,0,1,0,0,1,0,1,1,1,1,0,0,1,1,1],[0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0],[1,0,0,0,0,1,0,0,1,1,0,1,0,1,0,0],[0,0,1,0,1,1,0,0,0,0,0,0,1,0,0,0],[1,0,1,1,0,0,0,0,0,1,0,0,0,1,0,0],[0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0],[1,0,1,0,1,0,0,0,1,1,0,0,1,0,0,0],[0,1,1,0,0,0,0,1,0,0,0,1,0,1,0,0],[0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0],[1,1,0,0,1,0,0,0,0,0,0,1,0,1,0,0],[0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0],[1,0,0,0,0,1,1,1,0,0,0,1,0,0,0,0],[1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0],[0,0,1,1,0,1,1,0,0,0,1,0,0,0,0,0],[0,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0],[1,0,0,1,0,0,0,1,0,1,1,0,1,0,0,0],[1,0,0,1,0,1,0,0,0,0,0,0,0,1,0,1],[0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0],[1,0,1,0,0,0,1,1,0,1,1,0,1,0,0,1],[1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,1,1,0,1,1,0,1,0,0,0,1,0,1,1],[0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0],[0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0],[0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1],[0,0,0,1,0,1,1,0,0,0,0,1,1,0,1,0],[0,0,1,0,1,0,1,0,0,0,0,1,1,0,1,0],[0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0],[0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,1]]");
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.minTotalDistance(grid);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}

}
