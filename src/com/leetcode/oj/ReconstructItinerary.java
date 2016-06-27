package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class ReconstructItinerary {
	public abstract List<String> findItinerary(String[][] tickets);
	public static void main(String[] args) {
		ReconstructItinerary instance = new SolutionIV();
		String[][] tickets;
		List<String> results;
		
		// [JFK, MUC, LHR, SFO, SJC]
//		tickets = new String[][]{{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
		
		// ["JFK","ATL","JFK","SFO","ATL","SFO"]
		tickets = new String[][]{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		
//		tickets = new String[][]{{"EZE","AXA"},{"TIA","ANU"},{"ANU","JFK"},{"JFK","ANU"},{"ANU","EZE"},{"TIA","ANU"},{"AXA","TIA"},{"TIA","JFK"},{"ANU","TIA"},{"JFK","TIA"}};
		
		results = instance.findItinerary(tickets);
		System.out.println("results=" + results);
	}
	
	
	static class SolutionIV extends ReconstructItinerary {
		public List<String> findItinerary(String[][] tickets) {
	        Map<String, List<String>> nodeNbs = new HashMap<>();
	        for (String[] ticket : tickets) {
	            String node = ticket[0], nb = ticket[1];
	            List<String> nbs = nodeNbs.get(node);
	            if (nbs == null) {
	                nbs = new LinkedList<>();
	                nodeNbs.put(node, nbs);
	            }
	            nbs.add(nb);
	        }
	        for (List<String> nbs : nodeNbs.values()) {
	            Collections.sort(nbs);
	        }
	        List<String> result = new LinkedList<>();
	        result.add("JFK");
	        bt("JFK", nodeNbs, result);
	        return result;
	    }
	    
	    private boolean bt(String node, Map<String, List<String>> nodeNbs, List<String> result) {
	        // termination
	        if (nodeNbs.isEmpty())
	            return true;
	        List<String> nbs = nodeNbs.get(node);
	        if (nbs == null)
	            return false;
	        List<String> copy = new LinkedList<>(nbs);
	        for (String nb : copy) {
	            result.add(nb); // modify
	            nbs.remove(nb); // modify
	            if (nbs.isEmpty())
	                nodeNbs.remove(node);
	            if (bt(nb, nodeNbs, result))
	                return true;
	            result.remove(result.size()-1); // restore
	            nbs.add(nb);    // restore
	            if (nbs.size() == 1)
	                nodeNbs.put(node, nbs);
	        }
	        return false;
	    }
	}
	
	
	static class SolutionIII extends ReconstructItinerary {
		public List<String> findItinerary(String[][] tickets) {
	        List<StringBuilder> paths = new ArrayList<>();
	        Map<String, Set<String>> nodeNbs = new HashMap<>();
	        for (String[] ticket : tickets) {
	            add(nodeNbs, ticket[0], ticket[1]);
	        }
	        StringBuilder path = new StringBuilder("JFK");
	        dfs("JFK", nodeNbs, path, paths);
	        List<String> strs = new ArrayList<>();
	        for (StringBuilder p : paths)
	            strs.add(p.toString());
	        Collections.sort(strs);
	        String s = strs.get(0);
	        List<String> results = new ArrayList<>();
	        while (!s.isEmpty()) {
	            results.add(s.substring(0, 3));
	            s = s.substring(3, s.length());
	        }
	        return results;
	    }
	    
	    private void dfs(String node, Map<String, Set<String>> nodeNbs, StringBuilder path, List<StringBuilder> paths) {
//if (path.toString().equals("JFKATLJFKSFO"))
if (path.toString().equals("JFKATLJFK"))
	System.out.println();
	        // termination
	        if (nodeNbs.isEmpty())
	            paths.add(new StringBuilder(path)); // ERROR: create a copy as path is shared
	        Set<String> nbs = nodeNbs.get(node);
	        if (nbs != null) { // termination
	            Set<String> copy = new HashSet<>(nbs);
	            for (String nb : copy) {
	                nbs.remove(nb); // modify
	                if (nbs.isEmpty()) {
if (node.equals("ATL"))
	System.out.println();
	                    nodeNbs.remove(node);
	                }
	                path.append(nb); // modify
	                dfs(nb, nodeNbs, path, paths);
	                add(nodeNbs, node, nb); // restore
	                path.delete(path.length()-3, path.length()); // restore
	            }
	        }
	    }
	    
	    private void add(Map<String, Set<String>> nodeNbs, String node, String nb) {
	        Set<String> nbs = nodeNbs.get(node);
	        if (nbs == null) {
	            nbs = new HashSet<>();
	            nodeNbs.put(node, nbs);
	        }
	        nbs.add(nb);
	    }
	}
}
