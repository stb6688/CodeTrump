package com.leetcode.oj;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class MaxPointsOnALine {
	class Point {
		int x, y;
		Point() {
			x = 0;
			y = 0;
		}
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public abstract int maxPoints(Point[] points);
	
	// Solution II: Accepted
	class SolutionII extends MaxPointsOnALine {
		public int maxPoints(Point[] points) {
	        int max = 0;
	        // point, and slope already checked on this point
	        Map<Point, Set<Double>> pointSlopes = new HashMap<>();
	        for (Point p : points) {
	            int dups = 0;
	            // slopes already checked for p
	            Set<Double> slopes = pointSlopes.get(p);
	            Map<Double, Set<Point>> slopePoints = new HashMap<>();
	            for (Point p1 : points) {
	                if (p.x == p1.x && p.y == p1.y)
	                    dups++;
	                else {
	                    // SYNTAX: if we use Double, then we can't do slope=0 (cannot convert int to Double)!
	                    // we can either use double or we do slope=0.0
	                    double slope = Double.POSITIVE_INFINITY; 
	                    if (p.x != p1.x) {
	                        if (p.y == p1.y)
	                            slope = 0;
	                        else
	                            slope = 1.0*(p.y - p1.y)/(p.x - p1.x); // must use 1.0 to convert to double
	                    }
	                    if (slopes == null || !slopes.contains(slope)) {
	                        Set<Point> pset = slopePoints.get(slope);
	                        if (pset == null) {
	                            pset = new HashSet<>();
	                            slopePoints.put(slope, pset);
	                        }
	                        pset.add(p1);
	                    }
	                }
	            }
	            max = Math.max(max, dups); // check separately, as map slopePoints could be empty
	            for (Map.Entry<Double, Set<Point>> entry : slopePoints.entrySet()) {
	                Double slope = entry.getKey();
	                Set<Point> p1s = entry.getValue();
	                max = Math.max(max, p1s.size() + dups);
	                for (Point p1 : p1s) {
	                    Set<Double> slopeSet = pointSlopes.get(p1);
	                    if (slopeSet == null) {
	                        slopeSet = new HashSet<>();
	                        pointSlopes.put(p1, slopeSet);
	                    }
	                    slopeSet.add(slope);
	                }
	            }
	        }
	        
	        return max;
		}
	}
	
	
	// Solution I: Accepted
	class SolutionI extends MaxPointsOnALine {
		public int maxPoints(Point[] points) {
	        int max = 0;
	        for (Point p : points) {
	            int dups = 0;
	            Map<Double, Integer> slopeCount = new HashMap<>();
	            for (Point p1 : points) {
	                if (p.x == p1.x && p.y == p1.y)
	                    dups++;
	                else {
	                    double slope = Double.POSITIVE_INFINITY; // SYNTAX
	                    if (p.x != p1.x) {
	                        if (p.y == p1.y)
	                            slope = 0;
	                        else
	                            slope = 1.0*(p.y - p1.y)/(p.x - p1.x);
	                    }
	                    Integer count = slopeCount.get(slope);
	                    if (count == null)
	                        count = 0;
	                    slopeCount.put(slope, count+1);
	                }
	            }
	            max = Math.max(max, dups);
	            for (int count : slopeCount.values())
	                max = Math.max(max, count + dups);
	        }
	        
	        return max;
	    }
	}
}
