package mergeSort.computation;

import java.util.Random;

public class TestCacheSwap {

	//this class is intended for discovering the amount of data
	//each thread should receive
	//the right amount of data is defined by the amount that is accessed in 
	//constant time - cache memory that holds the array will not be evicted
	//in order for the other 'part' of data to be brought into cache memory
	
	public static void cacheSwap(int[] input) {
		
		Random randomGenerator = new Random();			
		for(int i=0; i<1000; i++) {
			int randIndex = randomGenerator.nextInt(input.length);
			long startTime = System.nanoTime();
			int x = input[randIndex];
			long endTime = System.nanoTime();
			long deltaTime = endTime - startTime;
			System.out.println(deltaTime);					
		}		
	}		
}
