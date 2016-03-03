package com.javaassets.training.algos;

import java.util.Arrays;

/**
 * The following problem demonstrates the usage of Backtracking in eliminating wrong solutions - back track and start again
 * In chess, a queen can move as far as she pleases, hor­i­zon­tally, ver­ti­cally, or diag­o­nally. 
 * A chess board has 8 rows and 8 columns. 
 * The stan­dard 8 by 8 Queen’s prob­lem asks how to place 8 queens on an ordi­nary chess board so that none of them can hit any other in one move.
 * (Source: http://www.math.utah.edu/~alfeld/queens/queens.html)
 * Here we are solv­ing it for N queens in NxN chess board.
 * @author venkatchadaram
 *
 */
public class BackTrackingQueensProblem {
	
	private int[][] solution;
	
	public BackTrackingQueensProblem(int N){
		solution = new int[N][N];
	}

	public static void main(String[] args) {
		BackTrackingQueensProblem problem = new BackTrackingQueensProblem(8);
		problem.solve();

	}
	
	public void solve(){
		solve(solution.length);
	}
	
	private void solve(int N) {
		if(placeQueens(0, N)){
			printSolution();
		} else {
			System.out.println("There is no solution fo this problem");
		}
		
	}
	
	private boolean placeQueens(int queen, int N){
		if(queen == N)
			return true;
		
		for(int row = 0;row < N;row++){
			if(canPlace(solution, row, queen)){
				solution[row][queen] = 1;
				if(placeQueens(queen+1, N)) {
					return true;
				}
				solution[row][queen] = 0;
			}
		}

		return false;
	}
	
	private boolean canPlace(int[][] matrix, int row, int col){
		//check if no row has queen
		
		for(int i=0;i<col;i++){
			if(matrix[row][i] == 1)
				return false;
			
		}
		
		//check if upper diagonal has no queen
		for(int i=row, j=col; i>=0 && j>=0; i--, j--){
			if(matrix[i][j] == 1) {
				return false;
			}
		}
		
		//check if lower diagonal has no queen
		for(int i=row, j = col; i< matrix.length && j >=0;i++, j--) {
			if(matrix[i][j] == 1) {
				return false;
			}
		}
		
		
		
		
		return true;
	}

	public void printSolution(){
		for(int[] row:solution){
			System.out.println(Arrays.toString(row));
			System.out.println();
		}
	}

}
