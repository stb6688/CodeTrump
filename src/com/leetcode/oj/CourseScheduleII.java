package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leetcode.util.ArrayUtil;

public abstract class CourseScheduleII {
	public abstract int[] findOrder(int numCourses, int[][] prerequisites);
	public static void main(String[] args) {
    	CourseScheduleII instance = new SolutionII();
    	int numCourses;
    	int[][] prerequisites;
    	
    	numCourses = 2;
    	prerequisites = ArrayUtil.str2int2DArray("[[1,0]]");
    	
    	int[] results = instance.findOrder(numCourses, prerequisites);
    	System.out.println("results=" + Arrays.toString(results));
	}
	
	
	static class SolutionII extends CourseScheduleII {
		public int[] findOrder(int numCourses, int[][] prerequisites) {
	        Map<Integer, List<Integer>> cPres = new HashMap<>();
	        for (int c = 0; c < numCourses; c++)
	            cPres.put(c, new LinkedList<>());
	        for (int[] p : prerequisites) {
	            cPres.get(p[0]).add(p[1]);
	        }
	        int[] rets = new int[numCourses];
	        int idx = 0;
	        Set<Integer> used = new HashSet<>();
	        for (int c = 0; c < numCourses; c++) {
	            if ((idx = dfs(c, cPres, new HashSet<>(), used, rets, idx)) < 0)
	                return new int[0];
	        }
	        
	        return rets;
	    }
	    
	    private int dfs(Integer c, Map<Integer, List<Integer>> cPres, Set<Integer> pending, 
	        Set<Integer> used, int[] rets, int idx) {
	        if (used.contains(c))
	            return idx;
	        if (!pending.add(c))    // loop detected
	            return -1;
	        List<Integer> pres = cPres.get(c);
	        for (Integer pre : pres) {
	            idx = dfs(pre, cPres, pending, used, rets, idx);
	            if (idx < 0)
	                return -1;
	        }
	        rets[idx] = c;
	        used.add(c);
	        pending.remove(c);
	        
	        return idx+1;
	    }
	}
	
	static class SolutionI extends CourseScheduleII{
		public int[] findOrder(int numCourses, int[][] prerequisites) {
	        Set<Integer> roots = new HashSet<>();
	        for (int i = 0; i < numCourses; roots.add(i), i++);
	        Map<Integer, Set<Integer>> coursePres = new HashMap<>();
	        Map<Integer, Set<Integer>> preCourses = new HashMap<>();
	        for (int[] pair : prerequisites) {
	            int course = pair[0];
	            int pre = pair[1];
	            roots.remove(course);
	            add(course, pre, coursePres);
	            add(pre, course, preCourses);
	        }
	        
	        int[] results = new int[numCourses];
	        int idx = 0;
	        List<List<Integer>> paths = new ArrayList<>();
	        for (int root : roots) {
	            List<Integer> path = new ArrayList<>();
	            path.add(root);
	            paths.add(path);
	        }
	        while (!paths.isEmpty()) {
	            List<List<Integer>> nextPaths = new ArrayList<>();
	            for (List<Integer> path : paths) {
	                Integer pre = path.get(path.size() - 1);
	                results[idx++] = pre;
	                Set<Integer> courses = preCourses.get(pre);
	                if (courses != null) {
	                    for (Integer course : courses) {
	                        Set<Integer> pres = coursePres.get(course);
	                        pres.remove(pre);
	                        if (pres.isEmpty()) {
	                            List<Integer> nextPath = new ArrayList<>(path);
	                            nextPath.add(course);
	                            nextPaths.add(nextPath);
	                            coursePres.remove(course);
	                        }
	                    }
	                }
	            }
	            paths = nextPaths;
	        }
	        
	        if (!coursePres.isEmpty())
	            return new int[0];
	        else
	            return results;
	    }
	    
	    private void add(Integer key, Integer val, Map<Integer, Set<Integer>> map) {
	        Set<Integer> vals = map.get(key);
	        if (vals == null) {
	            vals = new HashSet<>();
	            map.put(key, vals);
	        }
	        vals.add(val);
	    }
	}
    
}
