package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.leetcode.util.Interval;

public abstract class MergeIntervals {
	public abstract List<Interval> merge(List<Interval> intervals);
	public static void main(String[] args) {
		MergeIntervals instance = new SolutionII();
		List<Interval> intervals = new ArrayList<>();
		List<Interval> results;
		
//		intervals.add(new Interval(133, 138));
//		intervals.add(new Interval(131, 133));
//		intervals.add(new Interval(137, 137));
		
		intervals.add(new Interval(1, 4));
		intervals.add(new Interval(4, 5));
		
		results = instance.merge(intervals);
		System.out.println("results=" + results);
	}
	
	static class SolutionII extends MergeIntervals {
		public List<Interval> merge(List<Interval> intervals) {
	        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>(){
	            @Override
	            public int compare(int[] a1, int[] a2) {
	                return a1[0] - a2[0];
	            }
	        });
	        for (Interval interval : intervals) {
	            q.add(new int[]{interval.start, 0});
	            q.add(new int[]{interval.end, 1});
	        }
	        List<Interval> rets = new LinkedList<>();
	        int count = 0, start = -1;
	        boolean gap = true;
	        while (!q.isEmpty()) {
	            int[] top = q.peek();
	            int x = top[0];
	            while (!q.isEmpty() && q.peek()[0] == x) {
	                top = q.poll();
System.out.println(Arrays.toString(top));
					int id = top[1];
	                if (id == 0)
	                    count++;
	                else
	                    count--;
	            }
System.out.println("x=" + x + ", count=" + count);
	            if (gap) {
	                start = x;
	                gap = false;
	            } else if (count == 0) { // end of an interval
	                int end = x;
	                rets.add(new Interval(start, end));
	                gap = true;
	            }
	        }
	        return rets;
	    }
	}
	
	static class SolutionI extends MergeIntervals {
		public List<Interval> merge(List<Interval> intervals) {
	        List<Interval> results = new ArrayList<>();
	        Comparator<List<Integer>> comp = new Comparator<List<Integer>>(){
	            @Override
	            public int compare(List<Integer> l1, List<Integer> l2) {
	                // Important: if we have a start & end at the same position, always put start ahead of end
	                // this logic is important to make the subsequent while loop work.
//System.out.println(l1.get(0) + " vs. " + l2.get(0));
//	                if (l1.get(0) == l2.get(0)) 
	            	if (l1.get(0) - l2.get(0) == 0)
	                    return l1.get(1) - l2.get(1);
	                else
	                    return l1.get(0) - l2.get(0);
	            }
	        };
	        PriorityQueue<List<Integer>> q = new PriorityQueue<>(comp);
	        for (Interval interval : intervals) {
	            List<Integer> l1 = new ArrayList<>(2);
	            l1.add(interval.start);
	            l1.add(0);
	            List<Integer> l2 = new ArrayList<>(2);
	            l2.add(interval.end);
	            l2.add(1);
	            q.add(l1);
	            q.add(l2);
	        }
	        
	        Integer start = null;
	        int count = 0;
	        while (!q.isEmpty()) {
	            List<Integer> list = q.poll();
System.out.println("list=" + list);
	            if (list.get(1) == 0) {
	                if (start == null)
	                    start = list.get(0);
	                count++;
	            } else {
	                if (--count == 0) {
	                    results.add(new Interval(start, list.get(0)));
	                    start = null;
	                }
	            }
	        }
	        
	        return results;
	    }
	}
}
