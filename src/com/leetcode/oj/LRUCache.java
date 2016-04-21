package com.leetcode.oj;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	
	static class DNode {
        int key;
        int value;
        DNode prev;
        DNode next;
        DNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
        // insert curr node in front
        // of node
        void insert(DNode node) {
            DNode prev = node.prev;
            prev.next = this;
            this.prev = prev;
            this.next = node;
            node.prev = this;
        }
        
        DNode delete() {
            DNode prev = this.prev;
            DNode next = this.next;
            prev.next = next;
            next.prev = prev;
            return this;
        }
    }
    
    private Map<Integer, DNode> keyNode = new HashMap<Integer, DNode>();
    private DNode dummy = new DNode(-1, -1);
    private int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.next = dummy;
        dummy.prev = dummy;
    }
    
    public int get(int key) {
        DNode node = keyNode.get(key);
        if (node == null)
            return -1;
        node.delete().insert(dummy.next);
        return node.value;
    }
    
    public void set(int key, int value) {
        DNode node = keyNode.get(key);
        if (node == null) {
            // new node in list, new entry in 2 maps
            node = new DNode(key, value);
            node.insert(dummy.next);
            keyNode.put(key, node);
            // if over-capacity, delete last on list (oldest)
            if (keyNode.size() > capacity) {
                DNode last = dummy.prev;
                last.delete();
                keyNode.remove(last.key);
            }
        } else {
            // promote this node to head of list
            node.delete().insert(dummy.next);
            // update value
            node.value = value;
        }
    }
	
/*	private int index = 0;
    private final int capacity;
    private final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    private final Map<Integer, Integer> keyIndexMap = new HashMap<Integer, Integer>();
    private final Map<Integer, Integer> indexKeyMap = new HashMap<Integer, Integer>();
    TreeSet<Integer> indices = new TreeSet<Integer>();
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Integer value = map.get(key);
        if (value == null)
            return -1;
        else {
            updateIndex(key);
            return value;
        }
    }
    
    public void set(int key, int value) {
        updateIndex(key);
        map.put(key, value);
        if (map.size() > capacity) {
            // CATCH: must check if tree is empty!
            // if empty, instead of returning null, it will throw.
            Integer oldestIndex = indices.first();
            Integer oldestKey = indexKeyMap.get(oldestIndex);
            
            map.remove(oldestKey);
            indices.remove(oldestIndex);
            indexKeyMap.remove(oldestIndex);
            keyIndexMap.remove(oldestKey);
        }
    }
    
    private void updateIndex(int key) {
        Integer prevIndex = keyIndexMap.get(key);
        if (prevIndex != null) {
            indices.remove(prevIndex);
            indexKeyMap.remove(prevIndex);
        }
        keyIndexMap.put(key, ++index);
    }*/
    
    
    public static void main(String[] args) {
		LRUCache instance = new LRUCache(1);
		
		instance.set(2,1);
		instance.get(2);
		instance.set(3,2);
		instance.get(2);
		instance.get(3);
		
		
	}

}
