package com.leetcode.util.annotations;

public @interface Leetcode {
	// tags
	public static enum Tags {
		CLASSIC, // worth memorizing
		BITWISE,
		LIST,
		MATH,
		RECURSION,
		RECURSION_VS_ITERATION,
		DP,
		MEMOIZATION,
		TREE,
		BFS,
		DFS,
		TRIE,
		SERIALIZE,
	};
	
	// date of completion
	String date();
	
	// array of tags; default is empty
	Tags[] tags() default {};
	
	// url
	String url() default "";
}
