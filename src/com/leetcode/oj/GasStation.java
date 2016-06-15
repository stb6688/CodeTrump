package com.leetcode.oj;

public abstract class GasStation {
	public abstract int canCompleteCircuit(int[] gas, int[] cost);
	public static void main(String[] args) {
		GasStation instance = new SolutionI();
		int[] gas, cost;
		int result;
		
		// 1
//		gas = new int[]{1, 2}; cost = new int[]{2, 1};
		
		gas = new int[]{67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66};
		cost = new int[]{27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
		
		result = instance.canCompleteCircuit(gas, cost);
		System.out.println("result=" + result);
	}
	
	
	static class SolutionI extends GasStation {
		public int canCompleteCircuit(int[] gas, int[] cost) {
	        int[] deltas = new int[gas.length];
	        for (int i = 0; i < gas.length; i++)
	            deltas[i] = gas[i] - cost[i];
	        int start = 0, max = Integer.MIN_VALUE, maxStart = -1;
	        int sum = 0;
	        for (int i = 0; i < deltas.length; i++) {
	            sum += deltas[i];
	            if (sum < 0) {
	                sum = 0;
	                start = i + 1;
	            } else if (sum > max) {
	                maxStart = start;
	                max = sum;
	            }
	        }
	        if (maxStart < 0)
	            return -1;
	        int remain = 0;
	        for (int i = 0; i < deltas.length; i++) {
	            remain += deltas[(maxStart + i) % deltas.length];
	            if (remain < 0)
	                return -1;
	        }
	        return maxStart;
	    }
	}
}
