package com.leetcode.oj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leetcode.util.ArrayUtil;
import com.leetcode.util.LeetcodeUtils;

public class BestTimeToBuyAndSellStockWithCooldown {
	
	private Map<List<Integer>, Integer> cache = new HashMap<>();
    public int maxProfit(int[] prices) {
        return help(prices, 0, 0, 0);
    }
    
    // act: previous action:
    // 0 - cool, we can buy/ cool
    // 1 - buy, we can sell/ cool
    // 2 - sell, we can cool
    private int help(int[] prices, int idx, int act, int profit) {
        if (idx > prices.length - 1)
            return profit;
        List<Integer> key = new ArrayList<>(2);
        key.add(idx);
        key.add(act);
        Integer max = cache.get(key);
        if (max != null)
            return max;
        int p1, p2;
        switch (act) {
            case 0: // we can buy or cool
                p1 = help(prices, idx+1, 1, profit - prices[idx]);
                p2 = help(prices, idx+1, 0, profit);
                max = Math.max(p1, p2);
                break;
            case 1:
                p1 = help(prices, idx+1, 2, profit + prices[idx]);
                p2 = help(prices, idx+1, 0, profit);
                max = Math.max(p1, p2);
                break;
            default:
                max = help(prices, idx+1, 0, profit);
                break;
        }
        cache.put(key, max);
        return max;
    }
    
    public static void main(String[] args) {
    	BestTimeToBuyAndSellStockWithCooldown instance = new BestTimeToBuyAndSellStockWithCooldown();
    	int[] prices;
    	
    	// 3
//    	prices = new int[] {1, 2, 3, 0, 2};
    	
    	String s = LeetcodeUtils.readText(instance);
    	prices = ArrayUtil.str2intArray(s);
    	
    	int result = instance.maxProfit(prices);
    	System.out.println("result=" + result);
	}

}
