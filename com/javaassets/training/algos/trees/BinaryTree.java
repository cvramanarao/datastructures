package com.javaassets.training.algos.trees;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

	// Depth First Order -- NLR semantics
	public static void preOrderTraversal(Node root) {
		if (null != root) {
			System.out.print(root.getData() + " "); // 1. visit the root
			preOrderTraversal(root.getLeft()); // 2. Traverse the Left Tree in
												// PreOrder
			preOrderTraversal(root.getRight()); // 3. Traverse the right Tree in
												// PreOrder
		}
	}

	// LNR Semantics
	public static void inOrderTraversal(Node root) {
		if (null != root) {
			inOrderTraversal(root.getLeft()); // 1. Traverse the Left Tree in
												// InOrder
			System.out.print(root.getData() + " "); // 2. visit the root
			inOrderTraversal(root.getRight()); // 3. Traverse the Right Tree in
												// InOrder
		}
	}

	// LRN Semantics
	public static void postOrderTraversal(Node root) {
		if (null != root) {
			postOrderTraversal(root.getLeft()); // 1. Traverse the Left Tree in
												// InOrder
			postOrderTraversal(root.getRight()); // 2. Traverse the Right Tree
													// in InOrder
			System.out.print(root.getData() + " "); // 3. visit the root
		}
	}

	// Check the following implementation
	// LevelOrderTraversal using a Queue level wise printing
	public static void levelOrderTraversal(Node root) {
		if (null != root) {
			Queue<Node> queue = new LinkedList<Node>();
			queue.offer(root);
			queue.offer(null);
			while (!queue.isEmpty()) {
				Node current = queue.poll();

				if (null != current) {
					if (null != current.getLeft()) {
						queue.offer(current.getLeft());
					}

					if (null != current.getRight()) {
						queue.offer(current.getRight());
					}
					System.out.print(current + " ");
				} else {
					System.out.println("\n");
					if (!queue.isEmpty())
						queue.offer(null);
				}
			}
		}
	}

	// zigZagTraversal using a Queue level wise printing
	public static void zigZagTraversal(Node root) {
		if (null != root) {
			Queue<Node> queue = new LinkedList<Node>();
			queue.offer(root);
			queue.offer(null);
			boolean shouldReverse = false;

			List<Node> currentLevel = new ArrayList<>();
			while (!queue.isEmpty()) {
				Node current = queue.poll();

				if (null != current) {
					currentLevel.add(current);
					if (null != current.getLeft()) {
						queue.offer(current.getLeft());
					}

					if (null != current.getRight()) {
						queue.offer(current.getRight());
					}

				} else {
					if (shouldReverse) {
						Stack<Node> nodeStack = new Stack<Node>();
						nodeStack.addAll(currentLevel);
						// currentLevel.clear();

						while (!nodeStack.isEmpty()) {
							System.out.print(nodeStack.pop() + " ");
						}

					} else {

						System.out.print(currentLevel);

					}
					currentLevel.clear();
					System.out.println("\n");

					if (!queue.isEmpty()) {
						queue.offer(null);
						shouldReverse = !shouldReverse;
					}
				}
			}
		}
	}
	
	
	/*public static int DIAMETER = 0;
	
	public static int diameterOfTree(Node root){
		if(null == root)
			return 0;
		int leftDia, rightDia;
		leftDia = diameterOfTree(root.getLeft());
		rightDia = diameterOfTree(root.getRight());
		if(leftDia+rightDia > DIAMETER){
			DIAMETER = leftDia + rightDia +1;
		}
		
		return Math.max(leftDia, rightDia) +1;
		
	}*/
	
	public static int diameterOfTree(Node root) {
		if(null == root)
			return 0;
		
		//the path goes thru the root
		int height = height(root.getLeft()) + height(root.getRight()) + 1;
		
		//the path doesnt go thru the root
		
		int diameter = Math.max(diameterOfTree(root.getLeft()), diameterOfTree(root.getRight()));
		
		return Math.max(height, diameter);
		
	}

	public static int height(Node root) {
		if(null == root)
			return 0;
		
		return Math.max(height(root.getLeft()), height(root.getRight())) + 1;
	}
	
	public static Node buildBinaryTree(int[] preOrder, int[] inOrder) {
		
		if(preOrder.length == 0 || preOrder.length != inOrder.length)
			return null;
		return buildBinaryTree(preOrder, 0, preOrder.length-1, inOrder, 0, inOrder.length-1, 0);
	}

	private static Node buildBinaryTree(int[] preOrder, int preOrderStart, int preOrderEnd,
			int[] inOrder, int inOrderStart, int inOrderEnd, int level) {
		System.out.println("buildBinaryTree()-->" + " preOrderStart: "+preOrderStart+" preOrderEnd: "+preOrderEnd+" inOrderStart: "+inOrderStart+" inOrderEnd: "+inOrderEnd+" level : "+level);
		//Boundary Conditions
		if(inOrderStart >  inOrderEnd)
			return null;
		int rootData = preOrder[preOrderStart];
		System.out.println("Building tree with node : "+rootData+" preOrderStart: "+preOrderStart+" preOrderEnd: "+preOrderEnd+" inOrderStart: "+inOrderStart+" inOrderEnd: "+inOrderEnd+" level : "+level);
		Node root = new Node(rootData);
		System.out.println("inOrder : "+Arrays.toString(inOrder));
		int index = getIndexInOrder(inOrder, inOrderStart, inOrderEnd, rootData);
		System.out.println("index in inOrder : "+index);
		
		
		System.out.println("Calling left tree recursively for Node : "+rootData);
		//preOrderStart = preOrderStart+1;
		Node leftTree = buildBinaryTree(preOrder, preOrderStart+1, preOrderEnd, inOrder, inOrderStart, index-1, level+1);
		
		System.out.println("Calling right tree recursively for Node : "+rootData);
		
		if(index < 0)
			return null;
		
		Node rightTree = buildBinaryTree(preOrder, index+level, preOrderEnd, inOrder, index+1, inOrderEnd, level+1);
		
		root.setLeft(leftTree);
		root.setRight(rightTree);
		
		System.out.println("<-- buildBinaryTree()");
		
		return root;
	}

	private static int getIndexInOrder(int[] inOrder, int start, int end,  int rootData) {
		
		int i = start;
		
		while(i <= end){
			if(inOrder[i] == rootData){
				return i;
			}
			i++;
		}
		return -1;
	}

	public static void main(String[] args) {

		/*Node tree = new Node(1);
		tree.setLeft(new Node(2));
		tree.setRight(new Node(3));

		tree.getLeft().setLeft(new Node(4));
		tree.getLeft().setRight(new Node(5));

		tree.getRight().setLeft(new Node(6));
		tree.getRight().setRight(new Node(7));*/
		
		int[] preOrder = {1,2,4,5,3,6,7};
		int[] inOrder = {4,2,5,1,6,3,7};
		
		Node tree = buildBinaryTree(preOrder, inOrder);

		System.out.println("PreOrderTraversal: ");
		preOrderTraversal(tree);
		System.out.println();

		System.out.println("InOrderTraversal: ");
		inOrderTraversal(tree);
		System.out.println();

		System.out.println("postOrderTraversal: ");
		postOrderTraversal(tree);
		System.out.println();

		System.out.println("levelOrderTraversal: ");
		levelOrderTraversal(tree);
		System.out.println();

		System.out.println("zigZagTraversal: ");
		zigZagTraversal(tree);
		System.out.println();
		
		System.out.println("Diameter of the tree : "+diameterOfTree(tree));

	}

}

class Node {
	private int data;
	private Node left;
	private Node right;

	public Node(int data) {
		super();
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {

		return String.valueOf(data);
	}

}