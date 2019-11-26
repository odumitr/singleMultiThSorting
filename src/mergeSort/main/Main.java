package mergeSort.main;

import java.io.IOException;
import java.util.List;

import mergeSort.computation.MultiThreadMergeSort;
import mergeSort.computation.SingleThreadMergeSort;
import mergeSort.input.InputGenerator;
import mergeSort.input.InputReader;

public class Main {

	public static final int MAX_NUMBER =  200000000;	
	
	public static void main(String []args) throws IOException, InterruptedException {
		
		List<int[]> input = InputGenerator.generateInput(MAX_NUMBER, MAX_NUMBER);
		int[] inputSingle = input.get(0);
		int[] inputMulti = input.get(1);
		InputGenerator.writeInputToFile("/media/tibi/work1/algorithmsResearch/eclipseWorkspace/results/inputLarger.csv", 
										input.get(0));
		
		long startRead = System.currentTimeMillis();
//		int[] input = InputReader.readInputToFile("/media/tibi/work1/algorithmsResearch/eclipseWorkspace/results/input.csv", 
//										MAX_NUMBER);
		long endRead = System.currentTimeMillis();
		
		long runTime = SingleThreadMergeSort.mergeSort(inputSingle);
		long readTime = endRead - startRead;
		System.out.println(runTime+readTime);							
		
		runTime = MultiThreadMergeSort.mergeSort(inputMulti);
		readTime = endRead - startRead;
		System.out.println(runTime+readTime);
		
//		TestCacheSwap.cacheSwap(input);
	}
}
