package mergeSort.input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import mergeSort.main.Main;

public class InputReader {	
	
	public static  int[] readInputToFile(String inFile, int size) 
												throws IOException {
		
		int[] result = new int[size];
		int counter = 0;
		BufferedReader reader = new BufferedReader(new FileReader(inFile));
		String line = null;
		while((line = reader.readLine()) != null) {								
			result[counter] = Integer.parseInt(line);			
			counter++;
		}			     
		reader.close();	
		return result;
	}
}
