package mergeSort.computation;

import java.util.Arrays;

public class SingleThreadMergeSort {

	public static long mergeSort(int[] input) {
		
		long startTime = System.currentTimeMillis();		
		Arrays.sort(input);
		long endTime = System.currentTimeMillis();
		long deltaTime = endTime - startTime;
		return deltaTime;
	}
}
