package com.leetcode.oj;

import java.util.HashMap;
import java.util.Map;

import com.leetcode.util.annotations.Leetcode;
import com.leetcode.util.annotations.Leetcode.Tags;

@Leetcode(date = "2016-05-26", tags={Tags.LIST}, url="https://leetcode.com/problems/lru-cache/")
public class LRUCache {
	
	// an customized doubly linked list, internal class
    static class DNode {
        int val;
        DNode prev, next;
        
        DNode(int val) {
            this.val = val;
        }
        
        // append a node after me
        void append(DNode node) {
            node.next = this.next;
            this.next.prev = node;
            node.prev = this;
            this.next = node;
        }
        
        // remove me from the list
        void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
    }
    
    // bump given node from current location to head of list
    private void bump(DNode node) {
        node.remove();
        dummy.append(node);
    }
    
    private int capacity;
    private Map<Integer, Integer> keyVal;
    private Map<Integer, DNode> keyNode;
    private DNode dummy;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyVal = new HashMap<>();
        keyNode = new HashMap<>();
        // CATCH: don't forget to initialize prev/next
        dummy = new DNode(0);
        dummy.next = dummy;
        dummy.prev = dummy;
    }
    
    public int get(int key) {
        Integer val = keyVal.get(key);
        if (val == null) {
            return -1;
        } else {
            // keyVal no change;
            // node no change, but moved to head of list
            bump(keyNode.get(key));
            return val;
        }
    }
    
    public void set(int key, int value) {
        if (keyVal.put(key, value) == null) { // newly inserted, should check capacity
            DNode node = new DNode(key);
            keyNode.put(key, node);
            dummy.append(node);
            // check capacity, remove last key if necessary
            if (keyVal.size() > capacity) {
                Integer lastKey = dummy.prev.val;
                keyVal.remove(lastKey); // remove key-val
                keyNode.remove(lastKey).remove(); // remove key-node & remove node from list
            }
        } else { // re-visit an existing key: promote old node to head of list
            bump(keyNode.get(key));
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
		System.out.println(instance.get(2));
		instance.set(3,2);
		System.out.println(instance.get(2));
		System.out.println(instance.get(3));
		
		
	}

}
