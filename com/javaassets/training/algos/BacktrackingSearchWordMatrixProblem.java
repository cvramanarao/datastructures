package com.javaassets.training.algos;

import java.util.Arrays;

public class BacktrackingSearchWordMatrixProblem {
	
	private int[][] solution;
	private int path = 1;
	
	public BacktrackingSearchWordMatrixProblem(int N){
		solution = new int[N][N];
	}
	
	public static void main(String[] args) {
		
		char[][] matrix = { { 't', 'z', 'x', 'c', 'd' },
				{ 'a', 'h', 'n', 'z', 'x' }, { 'h', 'w', 'o', 'i', 'o' },
				{ 'o', 'r', 'n', 'r', 'n' }, { 'a', 'b', 'r', 'i', 'n' } };
		
		BacktrackingSearchWordMatrixProblem problem = new BacktrackingSearchWordMatrixProblem(matrix.length);
		
		if(problem.searchWord(matrix, "horizon")){
			problem.printSolution();
		} else {
			System.out.println("Given word is not found.");
		}
	}
	
	
	public boolean searchWord(char[][] matrix, String word) {
		int N = matrix.length;
		for(int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if(searchWord(matrix, word, i,j,0, N)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean searchWord(char[][] matrix, String word, int row, int col, int index, int N) {
		
		if(solution[row][col] != 0 || word.charAt(index) != matrix[row][col]) {
			return false;
		}
		
		//We completed our search and word is found 
		if(index == word.length() - 1) {
			solution[row][col] = path++;
			return true;
		}
		
		
		solution[row][col] = path++;
		
		
		//Check conditions
		//1. Checking upper cell
		if(row-1 >= 0 && searchWord(matrix, word, row-1, col, index+1, N)){
			return true;
		}
		
		//2. Checking lower cell
		if(row+1 < N && searchWord(matrix, word, row+1, col, index+1, N)){
			return true;
		}
		
		//3. Checking left cell
		if(col-1 >= 0 && searchWord(matrix, word, row, col-1, index+1, N)){
			return true;
		}
		
		//4. Checking right cell
		if(col+1 < N && searchWord(matrix, word, row, col+1, index+1, N)){
			return true;
		}
		
		//5.  Checking leftUpperCorner
		if(row-1 >= 0 && col-1 >= 0 && searchWord(matrix, word, row-1, col-1, index+1, N)){
			return true;
		}
		
		
		//6. Checking right upperCorner
		if(row-1 >= 0 && col+1 < N && searchWord(matrix, word, row-1, col+1, index+1, N)){
			return true;
		}
		//7 Checking left lower corner
		
		if(row+1 < N && col-1 >= 0 &&searchWord(matrix, word, row+1, col-1, index+1, N)){
			return true;
		}
		
		//8 checking right lower corner
		if(row+1 < N && col+1 < N &&searchWord(matrix, word, row+1, col+1, index+1, N)){
			return true;
		}
		
		// if none of the option works out, BACKTRACK and return false
		solution[row][col] = 0;
		path--;
		return false;
	}
	
	public void printSolution(){
		for(int[] row:solution){
			System.out.println(Arrays.toString(row));
			System.out.println();
		}
	}

}
