package com.javaassets.training.algos.divideandconquer;

public class MaxElementInIncreasingAndDecreasingArray {
	
	

	public static void main(String[] args) {
		
		int[] array = {8,10,20,80,100,200,300,400,500,5,4,3,2,1};
		
		int maxElement = findMaxInIncreasingAndDecreasingArray(array);
		
		System.out.println("The max element is in the array is : "+maxElement);
		
		int[] array1 = {3,50,10,9,7,6};
		
		int maxElement1 = findMaxInIncreasingAndDecreasingArray(array1);
		
		System.out.println("The max element is in the array is : "+maxElement1);
		
		
		/*Corner case (No decreasing part)
		Input: arr[] = {10, 20, 30, 40, 50}
		Output: 50 */
		
		int arr[] = {10, 20, 30, 40, 50};
		
		System.out.println("The max element is in the array is : "+findMaxInIncreasingAndDecreasingArray(arr));
		/*Corner case (No increasing part)
		Input: 
		Output: 120*/
		int arr1[] = {120, 100, 80, 20, 0};
		
		System.out.println("The max element is in the array is : "+findMaxInIncreasingAndDecreasingArray(arr1));

	}

	public static int findMaxInIncreasingAndDecreasingArray(int[] array) {
		
		return findMaxInIncreasingAndDecreasingArray(array, 0, array.length-1);
	}
	
	private static int findMaxInIncreasingAndDecreasingArray(int[] array, int low, int high) {
		
		System.out.println("findMaxInIncreasingAndDecreasingArray with low : "+low+" and high: "+high);
		if(low == high)
			return array[low];
		if(high == low + 1){
			return array[low] > array[high] ? array[low] : array[high];
		}
		int mid = (low + high)/2;
		System.out.println("array[mid-1] : "+array[mid-1]+" array[mid]: "+array[mid]+" array[mid+1]: "+array[mid+1]);
		if(array[mid-1] < array[mid]) {
			if(array[mid] > array[mid+1])
				return array[mid];
			else 
				return findMaxInIncreasingAndDecreasingArray(array, mid+1, high);
		} else {
			if(array[mid] > array[mid+1] ) 
			return findMaxInIncreasingAndDecreasingArray(array, low, mid);
			else 
				return array[mid];
		}
	
		
	}

}
