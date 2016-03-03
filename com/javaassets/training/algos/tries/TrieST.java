package com.javaassets.training.algos.tries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TrieST<T> {
	
	private static int R = 256;
	
	private Node root;
	
	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
		
	}
	
	
	public void put(String key, T value){
		root = put(root, key, value, 0);
	}
	
	

	private Node put(Node root, String key, T value, int d) {
		
		if(null == root) 
			root = new Node();
		
		if(d == key.length()) {
			root.val = value;
			return root;
		}
			
		char c = key.charAt(d);
		root.next[c] = put(root.next[c], key, value, d+1);
		
		
		return root;
	}

	public T get(String key) {
		Node node = get(root, key, 0);
		
		if(null == node) 
			return null;
		return (T) node.val;
	}

	private Node get(Node root, String key, int d) {
		
		if(null == root)
			return null;
		if(d == key.length())
			return root;
		char c = key.charAt(d);
		
		return get(root.next[c], key, d+1);
	}

	public Iterable<String> keys() {
		return keysWithPrefix("");
	}


	private Iterable<String> keysWithPrefix(String prefix) {
		
		Queue<String> queue = new LinkedList<String>();
		
		collect(get(root, prefix, 0), prefix, queue);
		
		return queue;
	}
	
	private void collect(Node root, String prefix, Queue<String> queue){
		if(null == root)
			return ;
		if(null != root.val)
			queue.offer(prefix);
		for(char c = 0; c < R; c++){
			collect(root.next[c], prefix+c, queue);
		}
	}



	public static void main(String[] args) {
		
		TrieST<Integer> trie = new TrieST<Integer>();
		
		String phrase = "she sells sea shells by the sea shore";
		
		trie.put("she", 0);
		trie.put("sells", 1);
		trie.put("sea", 2);
		trie.put("shells", 3);
		trie.put("by", 4);
		trie.put("the", 5);
		trie.put("sea", 6);
		trie.put("shore", 7);
		
		System.out.println(trie.get("shells"));
		System.out.println(trie.get("shells"));
		System.out.println(trie.get("she"));
		System.out.println(trie.get("sea"));
		
		for(String key: trie.keys()){
			System.out.println(key);
		}
		
		System.out.println("------------------------");
		
		for(String key : trie.keysWithPrefix("sh")){
			System.out.println(key);
		}
		
		System.out.println("------------------------");
		
		for(String key : trie.keysWithPrefix("s")){
			System.out.println(key);
		}

	}

}
