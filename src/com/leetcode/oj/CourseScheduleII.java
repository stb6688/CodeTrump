package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseScheduleII {
	
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
System.out.println("path=" + path);
                Integer pre = path.get(path.size() - 1);
System.out.println("pre=" + pre);
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
    
    public static void main(String[] args) {
    	CourseScheduleII instance = new CourseScheduleII();
    	int numCourses;
    	int[][] prerequisites;
    	
    	numCourses = 2;
    	prerequisites = new int[][]{{1, 0}};
    	
    	int[] results = instance.findOrder(numCourses, prerequisites);
    	System.out.println("results=" + Arrays.toString(results));
	}

}
