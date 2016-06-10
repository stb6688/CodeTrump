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
		BST,
		PREORDER,
		POSTORDER,
		INORDER,
		TABLE,
		TRIE,
		SERIALIZE,
		
		REMEMBER,	// just remember this problem, maybe applied in other cases
	};
	
	// date of completion
	String date();
	
	// array of tags; default is empty
	Tags[] tags() default {};
	
	// url
	String url() default "";
}
