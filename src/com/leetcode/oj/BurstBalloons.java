package com.leetcode.oj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leetcode.util.ArrayUtil;

public abstract class BurstBalloons {
	public abstract int maxCoins(int[] nums);
    public static void main(String[] args) {
    	BurstBalloons instance = new SolutionIV();
    	int[] nums;
    	
    	// 167
//    	nums = ArrayUtil.str2intArray("[3, 1, 5, 8]");
    	
//    	nums = new int[]{7,9,8,0,7,1,3,5,5,2,3,3};
    	
    	nums = new int[]{8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2,5};
    	
    	// 32
//    	nums = ArrayUtil.str2intArray("[8, 3]");
    	
//    	nums = ArrayUtil.str2intArray("[8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2]");
    	
//    	nums = ArrayUtil.str2intArray("[1,6,8,3,4,6,4,7,9,8,0,6,2,8]");
    	
    	long t1 = System.currentTimeMillis();
    	int result = instance.maxCoins(nums);
    	long t2 = System.currentTimeMillis();
    	System.out.println("result=" + result);
    	System.out.println(String.format("total time=%,dms", (t2 - t1)));
	}
    
    
    static class SolutionIV extends BurstBalloons {
    	public class DNode {
            DNode prev, next;
            int val;
            public DNode(int val) {
                this.val = val;
            }
            
            public void append(DNode node) {
                DNode next = this.next;
                this.next = node;
                node.prev = this;
                node.next = next;
                next.prev = node;
            }
            
            public void remove() {
                this.prev.next = this.next;
                this.next.prev = this.prev;
            }
        }
        
        public int maxCoins(int[] nums) {
            DNode dd = new DNode(1), n = dd;
            dd.next = dd;
            dd.prev = dd;
            for (int num : nums) {
                n.append(new DNode(num));
                n = n.next;
            }
            return bt(dd);
        }
        
        private Map<List<Integer>, Integer> cache = new HashMap<>();
        private int bt(DNode dd) {
            List<Integer> key = new ArrayList<>();
            DNode curr = dd.next;
            while (curr != dd) {
                key.add(curr.val);
                curr = curr.next;
            }
            Integer max = cache.get(key);
            if (max != null)
                return max;
            max = 0;
            
            curr = dd.next;
            while (curr != dd) {
                curr.remove(); // modify
                int val = curr.val*curr.prev.val*curr.next.val + bt(dd); // recursion
                max = Math.max(max, val);
                curr.prev.append(curr); // restore
                curr = curr.next;
            }
            cache.put(key, max);
            return max;
        }
    }
}
