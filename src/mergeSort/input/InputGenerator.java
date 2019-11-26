package mergeSort.input;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class InputGenerator {

	public static List<int[]> generateInput(int high, int size) {
		
		Random randomGenerator = new Random();	
		BitSet generatedNrs = new BitSet();
		int[] array = new int[size];
		int[] cpArray = new int[size];
		int count = 0;
		for(int i=0; i<size; i++) {
			int randomInt = randomGenerator.nextInt(high) + 1;
			while(generatedNrs.get(randomInt)) {
				randomInt = randomGenerator.nextInt(high) + 1;
			}
			generatedNrs.set(randomInt);
			array[i] = randomInt;
			cpArray[i] = randomInt;
			count++;
			if(count % 10000 == 0) {
				System.out.println(count);
			}
		}		
		List<int[]> result = new ArrayList<>();
		result.add(array);
		result.add(cpArray);
		return result;
	}
	
	public static void writeInputToFile(String outFile, int[] array) 
												throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
		for(int i=0; i<array.length; i++) {
			if(i == array.length - 1) {
				writer.write(array[i]+"\n");
				break;
			}
			writer.write(array[i]+"\n");
		}			     
	    writer.close();
	}
}
