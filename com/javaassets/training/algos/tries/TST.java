package com.javaassets.training.algos.tries;

import java.util.LinkedList;
import java.util.Queue;

public class TST <T> {
	
	private Node root;
	
	private class Node {
		char c;
		Node left, right, mid;
		T val;
	}
	
	public T get(String key){
		Node x = get(root, key, 0);
		if(null == x) return null;
		
		return x.val;
	}
	
	private TST<T>.Node get(TST<T>.Node root, String key, int d) {
		if(null == root)
			return null;
		char c = key.charAt(d);
		if(c < root.c)
			return get(root.left, key, d);
		else if (c > root.c)
			return get(root.right, key, d);
		else if(d < key.length() - 1)
			return get(root.mid, key, d+1);
		else
			return root;
	}

	public void put(String key, T value){
		root = put(root, key, value, 0);
	}
	
	

	private TST<T>.Node put(TST<T>.Node root, String key, T value, int d) {
		char c = key.charAt(d);
		
		if(null == root) {
			
			root = new Node(); root.c = c;
		}
		
		if(c < root.c)
			root.left = put(root.left, key, value, d);
		else if (c > root.c)
			root.right = put(root.right, key, value, d);
		else if(d< key.length() - 1)
			root.mid = put(root.mid, key, value, d+1);
		else 
			root.val = value;
		
		return root;
	}
	
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}


	private Iterable<String> keysWithPrefix(String prefix) {
		
		Queue<String> queue = new LinkedList<String>();
		collect(root, prefix, queue);
		
		return queue;
	}
	
	private void collect(Node root, String prefix, Queue<String> queue){
		if(null == root)
			return ;
		if(null != root.val)
			queue.offer(prefix);
	
		System.out.println(root.c);
		
		collect(root.mid, prefix+root.c, queue);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TST<Integer> trie = new TST<Integer>();
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
		
		System.out.println("Printing keys: ");
		for(String key: trie.keys()){
			System.out.println(key);
		}
		
		System.out.println("------------------------");
		
		/*for(String key : trie.keysWithPrefix("sh")){
			System.out.println(key);
		}
		
		System.out.println("------------------------");
		
		for(String key : trie.keysWithPrefix("s")){
			System.out.println(key);
		}*/

	}

}
