package com.leetcode.oj;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leetcode.util.ArrayUtil;

public abstract class CourseSchedule {
	public abstract boolean canFinish(int numCourses, int[][] prerequisites);
	public static void main(String[] args) {
		CourseSchedule instance = new SolutionI();
		int numCourses; int[][] prerequisites;
		boolean res;
		
		numCourses = 10;
		prerequisites = ArrayUtil.str2int2DArray("[[5,8],[3,5],[1,9],[4,5],[0,2],[1,9],[7,8],[4,9]]");
		
		res = instance.canFinish(numCourses, prerequisites);
		System.out.println("result=" + res);
	}
	
	
	static class SolutionI extends CourseSchedule {
		public boolean canFinish(int numCourses, int[][] prerequisites) {
	        Map<Integer, Set<Integer>> cPres = new HashMap<>();
	        Map<Integer, Set<Integer>> preCs = new HashMap<>();
	        for (int c = 0; c < numCourses; c++) {
	            cPres.put(c, new HashSet<>());
	            preCs.put(c, new HashSet<>());
	        }
	        for (int[] p : prerequisites) {
	            int c = p[0], pre = p[1];
	            cPres.get(c).add(pre);
	            preCs.get(pre).add(c);
	        }
	        List<Integer> leaves = new LinkedList<>();
	        Iterator<Map.Entry<Integer, Set<Integer>>> itr = cPres.entrySet().iterator();
	        while (itr.hasNext()) {
	            Map.Entry<Integer, Set<Integer>> entry = itr.next();
	            if (entry.getValue().isEmpty()) {
	                leaves.add(entry.getKey());
	                itr.remove();
	            }
	        }

	        while (!cPres.isEmpty() && !leaves.isEmpty()) {
	            List<Integer> nextLeaves = new LinkedList<>();
	            for (Integer leaf : leaves) {
	                Set<Integer> cs = preCs.get(leaf);
	                for (Integer c : cs) {
	                    cPres.get(c).remove(leaf);
	                    if (cPres.get(c).isEmpty()) {
	                        nextLeaves.add(c);
	                        cPres.remove(c);
	                    }
	                }
	            }
	            leaves = nextLeaves;
	        }
	        
	        return cPres.isEmpty();
	    }
	}
}
