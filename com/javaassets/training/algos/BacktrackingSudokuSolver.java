package com.javaassets.training.algos;

import java.util.Arrays;

public class BacktrackingSudokuSolver {
	
	private int[][] matrix = new int[9][9];
	
	public BacktrackingSudokuSolver(int[][] matrix) {
		this.matrix = matrix;
	}

	public static void main(String[] args) {
		
		int[][] sudoku = {{5,3,0,0,7,0,0,0,0}, 
						  {6,0,0,1,9,5,0,0,0}, 
						  {0,9,8,0,0,0,0,0,0},
						  {8,0,0,0,6,0,0,0,3}, 
						  {4,0,0,8,0,3,0,0,1}, 
						  {7,0,0,0,2,0,0,0,6},
						  {0,6,0,0,0,0,2,8,0}, 
						  {0,0,0,4,1,9,0,0,5}, 
						  {0,0,0,0,8,0,0,7,9}};
		
		BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver(sudoku);
		solver.solve();
		solver.printSolution();

	}
	
	public void solve(){
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				solve(i,j, 1);
	}
	
	
	private boolean solve(int row, int col, int number){
		
		if(row ==9 && col == 9)
			return true;
		if(matrix[row][col]!=0)
			return false;
		
		matrix[row][col] = number;
		
		//check if the number is not present in the row and column
		
		
		matrix[row][col] = 0;
		return false;
	}
	
	public void printSolution(){
		for(int[] row:matrix){
			System.out.println(Arrays.toString(row));
			System.out.println();
		}
	}

}
