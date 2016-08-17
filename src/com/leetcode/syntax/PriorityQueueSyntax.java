package com.leetcode.syntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueSyntax {
	public static void main(String[] args) {
		// reverse comparator
		Comparator<Integer> comp = new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return i2 - i1;
			}
		};
		
		PriorityQueue<Integer> q = new PriorityQueue<>(comp);
		q.add(3); q.add(1); q.add(5); q.add(7);
		
		Integer[] array1 = q.toArray(new Integer[0]);
		System.out.println("Array: " + Arrays.toString(array1));
		Integer[] array2 = q.toArray(new Integer[q.size()]);
		System.out.println("Array: " + Arrays.toString(array2));
		
		List<Integer> sorted = new ArrayList<>();
		while (!q.isEmpty()) {
			sorted.add(q.poll());
		}
		System.out.println("Queue: " + sorted);
		
		
	}
}
