package com.codetrump.ago.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class KMP {
	
	public static List<Integer> search(String s, String p) {
		if (p.isEmpty())
			return Collections.emptyList();
		int[] table = prepare(p);
//System.out.println(Arrays.toString(table));		
		List<Integer> rets = new LinkedList<>();
		int sidx = 0, pidx = 0;
		while (sidx < s.length() && pidx < p.length()) {
			if (s.charAt(sidx) == p.charAt(pidx)) {
				sidx++;
				pidx++;
			} else {
				// move pidx to the index after 'prefix' to try again
				if (pidx != 0) {
					pidx = table[pidx-1];
				} else {
					// confirmed no match
					sidx++;
				}
			}
			if (pidx == p.length()) {
				int ret = sidx - p.length();
				rets.add(ret);
				// ???
				sidx = ret + 1;
				pidx = 0;
			}
		}
		
		return rets;
	}

	/**
	 * prepare the look-up table for pattern.
	 * if we have a mismatch at index i, and we find i-1 is the end of suffix
	 * which happens to be the prefix of 'pattern' as well. this suffx has len=n;
	 * then for next step, we can try to continue searching from the index after prefix,
	 * which is pidx=n. 
	 * we know in s, before sidx, it's a substring equals to suffix, which also matches 
	 * the prefix of pattern. so we can continue to search from current sidx while pidx =
	 * the index after prefix, which equals to length of prefix.
	 * time & space: O(n) where n is length of p.
	 * @param p
	 * @return
	 */
	private static int[] prepare(String p) {
		int[] table = new int[p.length()];
		int l = 0; // l points to the index we are trying to add to a prefix
		for (int r = 1; r < p.length();) {
			if (p.charAt(l) == p.charAt(r)) {
				table[r] = ++l;
				r++;
			} else {
				if (l != 0)
					// move l to the index after prefix and try again
					l = table[l-1]; 
				else { // confirm no such a suffix ends at r that is also the prefix of 'p'
					table[r] = 0;
					r++;
				}
			}
		}
		
		return table;
	}
	
	public static void main(String[] args) {
		String s = "aabaabaaa aabaabaaa";
//		String p = "abcdabca";
		String p = "aabaabaaa";
		
		System.out.println("result=" + search(s, p));
	}
}
