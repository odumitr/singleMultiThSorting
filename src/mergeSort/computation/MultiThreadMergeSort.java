package mergeSort.computation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadMergeSort {
	
	private static void sortData(int[] input) throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable() { 
            public void run() 
            { 
            	Arrays.sort(input, 0, input.length/4);            	
            } 
            
        });
		t1.start();				
		
		Thread t2 = new Thread(new Runnable() { 
            public void run() 
            { 
            	Arrays.sort(input, input.length/4, input.length/2);            
            } 
        });
		t2.start();		
		
		Thread t3 = new Thread(new Runnable() { 
            public void run() 
            { 
            	Arrays.sort(input, input.length/2, (input.length/4)*3);            	
            } 
        });
		t3.start();				
		
		Thread t4 = new Thread(new Runnable() { 
            public void run() 
            { 
            	Arrays.sort(input, (input.length/4)*3, input.length);            	
            } 
        });
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();		
	}
	
	private static List<int[]> mergeSubArrays(int[] input) throws InterruptedException {
		
		int[] resultFirstHalf = new int[input.length/2];
		Thread t1 = new Thread(new Runnable() { 
            public void run() 
            { 				
				int index = 0;
				int indexAr1 = 0;
				int indexAr2 = input.length/4;
				while(indexAr1 < input.length/4 && indexAr2 < input.length/2) {
					if(input[indexAr1] <= input[indexAr2]) {
						resultFirstHalf[index] = input[indexAr1];
						indexAr1++;
					}
					else {
						resultFirstHalf[index] = input[indexAr2];
						indexAr2++;
					}
					index++;
				}
				int restIndex = 0;
				boolean isRestIndexAr1 = false;
				if(indexAr1 == input.length/4) {
					restIndex = indexAr2;
				}
				else {
					restIndex = indexAr1;
					isRestIndexAr1 = true;
				}
				while((isRestIndexAr1 == true && restIndex < input.length/4) || (isRestIndexAr1 == false && restIndex < input.length/2)) {
					resultFirstHalf[index] = input[restIndex];
					restIndex++;
					index++;
				}			
            }
        });
		t1.start();		
		
		int[] resultSecHalf = new int[input.length/2];
		Thread t2 = new Thread(new Runnable() { 
            public void run() 
            { 				
				int index = 0;
				int indexAr1 = input.length/2;
				int indexAr2 = (input.length/4)*3;
				while(indexAr1 < (input.length/4)*3 && indexAr2 < input.length) {
					if(input[indexAr1] <= input[indexAr2]) {
						resultSecHalf[index] = input[indexAr1];
						indexAr1++;
					}
					else {
						resultSecHalf[index] = input[indexAr2];
						indexAr2++;
					}
					index++;
				}
				int restIndex = 0;
				boolean isRestIndexAr1 = false;
				if(indexAr1 == (input.length/4)*3) {
					restIndex = indexAr2;
				}
				else {
					restIndex = indexAr1;
					isRestIndexAr1 = true;
				}
				while((isRestIndexAr1 == true && restIndex < (input.length/4)*3) || (isRestIndexAr1 == false && restIndex < input.length)) {
					resultSecHalf[index] = input[restIndex];
					restIndex++;
					index++;
				}									
            }
        });
		t2.start();		
		
		t1.join();
		t2.join();
		
		List<int[]> result = new ArrayList<>();
		result.add(resultFirstHalf);
		result.add(resultSecHalf);				
		return result;
	}
	
	private static int[] finalMerge(int[] firstHalf, int[] secHalf){
		
		int[] result = new int[firstHalf.length+secHalf.length];		 			
		int index = 0;
		int indexAr1 = 0;
		int indexAr2 = 0;
		while(indexAr1 < firstHalf.length && indexAr2 < secHalf.length) {
			if(firstHalf[indexAr1] <= secHalf[indexAr2]) {
				result[index] = firstHalf[indexAr1];
				indexAr1++;
			}
			else {
				result[index] = secHalf[indexAr2];
				indexAr2++;
			}
			index++;
		}
		int restIndex = 0;
		boolean isRestIndexAr1 = false;
		if(indexAr1 == firstHalf.length) {
			restIndex = indexAr2;
		}
		else {
			restIndex = indexAr1;
			isRestIndexAr1 = true;
		}
		while((isRestIndexAr1 == true && restIndex < firstHalf.length) || (isRestIndexAr1 == false && restIndex < secHalf.length)) {
			if(isRestIndexAr1 == true && restIndex < firstHalf.length) {
				result[index] = firstHalf[restIndex];
			}
			else {
				result[index] = secHalf[restIndex];
			}
			restIndex++;
			index++;
		}		    
		return result;
	}
	
	private static void printArray(int[] input) {
		
		for(int i=0; i<input.length; i++) {
			System.out.print(input[i]+", ");
		}
		System.out.println();
	}
	
	private static boolean checkSortedArray(int[] input) {
		
		for(int i=0; i<input.length-1; i++) {
			if(input[i] > input[i+1]) {
				printArray(input);
				return false;				
			}
		}
		return true;
	}
	
	public static long mergeSort(int[] input) throws InterruptedException {
		
		long startTime = System.currentTimeMillis();
		sortData(input);										
		List<int[]> subArraysSorted = mergeSubArrays(input);				
		int[] result = finalMerge(subArraysSorted.get(0), subArraysSorted.get(1));		
		long endTime = System.currentTimeMillis();
		long deltaTime = endTime - startTime;
		//checkSortedArray(result);
		return deltaTime;
	}		
}
