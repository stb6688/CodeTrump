package com.leetcode.oj;

import java.util.Arrays;

import com.leetcode.util.ArrayUtil;

public abstract class BestTimeToBuyAndSellStockWithCooldown {
	public abstract int maxProfit(int[] prices);
    public static void main(String[] args) {
    	BestTimeToBuyAndSellStockWithCooldown instance = new SolutionII();
    	int[] prices;
    	
    	// 3
    	prices = new int[] {1, 2, 3, 0, 2};
    	
    	// 3
//    	prices = new int[] {1, 2, 4};
    	
//    	String s = LeetcodeUtils.readText(instance);
//    	prices = ArrayUtil.str2intArray(s);
    	
    	int result = instance.maxProfit(prices);
    	System.out.println("result=" + result);
	}
    
    
    static class SolutionII extends BestTimeToBuyAndSellStockWithCooldown {
    	public int maxProfit(int[] prices) {
            if (prices.length <= 1)
                return 0;
            // add tail padding column; reachable only
            int rows = 4, cols = prices.length+1;
            // 0: buy
            // 1: sell
            // 2: cool, bought
            // 3: cool, sold
            int[][] dp = new int[rows][cols];
            for (int r = 0; r < rows; Arrays.fill(dp[r++], Integer.MIN_VALUE));
            dp[3][0] = 0;
            for (int c = 0; c < cols-1; c++) {
            	for (int r = 0; r < rows; r++) {
                
                    if (dp[r][c] > Integer.MIN_VALUE) {
                        switch (r) {
                            case 0: // buy -> sell or cool
                                dp[1][c+1] = Math.max(dp[1][c+1], dp[r][c] + prices[c]);
                                dp[2][c+1] = Math.max(dp[2][c+1], dp[r][c]);
                                break;
                            case 1: // sell -> cool
                                dp[3][c+1] = Math.max(dp[3][c+1], dp[r][c]);
                                break;
                            case 2: // cool bought -> cool or buy
                                dp[2][c+1] = Math.max(dp[2][c+1], dp[r][c]);
                                dp[0][c+1] = Math.max(dp[0][c+1], dp[r][c] - prices[c]);
                                break;
                            default:    // cool sold -> cool sold or buy
                                dp[3][c+1] = Math.max(dp[3][c+1], dp[r][c]);
                                dp[0][c+1] = Math.max(dp[0][c+1], dp[r][c] - prices[c]);
                                break;
                        }
                    }
                }
            }
            int max = 0;
            for (int r = 0; r < rows; r++) {
                max = Math.max(max, dp[r][cols-1]);
            }
            return max;
        }
    }

    
    static class SolutionI extends BestTimeToBuyAndSellStockWithCooldown {
    	public int maxProfit(int[] prices) {
            // curr, prev
            // buy -> sell or cool
            // sell -> cool
            // cool & bought -> cool or sell
            // cool & not bought -> cool or buy
            // state: 
            // 0: prev buy
            // 1: prev sell
            // 2: prev cool & already bought
            // 3: prev cool & not bought
            // 0 from 3
            // 1 from 0, 2
            // 2 from 0, 2
            // 3 from 1, 3
            int[] profits = new int[4];
            Arrays.fill(profits, Integer.MIN_VALUE);
            // initial state is cool & not bought, with 0 profit
            profits[3] = 0;
            for (int i = 0; i < prices.length; i++) {
                int[] nextProfits = new int[4];
                Arrays.fill(nextProfits, Integer.MIN_VALUE);
                int max;
                for (int j = 0; j < 4; j++) {
                    switch(j) {
                        case 0: // prev state + buy
                            if (profits[3] != Integer.MIN_VALUE)
                                nextProfits[j] = profits[3] - prices[i];
                            break;
                        case 1: // prev state + sell
                            max = Math.max(profits[0], profits[2]);
                            if (max != Integer.MIN_VALUE)
                                nextProfits[j] = max + prices[i];
                            break;
                        case 2: // prev state + cool
                            max = Math.max(profits[0], profits[2]);
                            if (max != Integer.MIN_VALUE)
                                nextProfits[j] = max;
                            break;
                        default: // prev state + cool
                            max = Math.max(profits[1], profits[3]);
                            if (max != Integer.MIN_VALUE)
                                nextProfits[j] = max;
                            break;
                    }
                }
                profits = nextProfits;
            }
            Integer max = Integer.MIN_VALUE;
            for (int profit : profits)
                max = Math.max(max, profit);
            return max;
        }
    }
}
