package tsp;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author btdiem
 *
 */

public class TSPUtility {

	public static final Random generator = new Random();
	
	/**
	 * The graph should be sysmatic and transitive
	 * if (i#j) graph[i,j] = graph[j,i] and graph[i,j] > 0 
	 * if (i=j) graph[i,j] = 0
	 * 
	 * @param graph
	 * @return
	 */
	public static boolean isValidGraph(long [][] graph){
		if (graph == null){
			return false;
		}
		int n = graph.length;
		if (n < 2){
			return false;
		}
		for (int i=0; i<n; i++){
			for (int j=0; j<n; j++){
				if (i==j){
					if (graph[i][j] != 0){
						return false;
					}
				}else{
					if (graph[i][j] != graph[j][i]){
						return false;
					}
				}
			}
		}
		return true;
	}
	/**
	 * Loading a file that is a dimensional direction integer array
	 * Files are located in db
	 * @param fileName
	 * @return long[][]
	 * @throws FileNotFoundException
	 */
	public static long [][] read2DArray(String fileName) throws FileNotFoundException{
		
		URL location = TSPUtility.class.getProtectionDomain().getCodeSource().getLocation();
		File file = new File(location.getPath().concat(fileName));
		Scanner input = new Scanner (file);
		// pre-read in the number of rows/columns
		int rows = 0;
		int columns = 0;
		while(input.hasNextLine()){
			input.nextLine();
		    ++rows;
		}
		columns = rows;
		input.close();
		long[][] a = new long[rows][columns];
		// read in the data
		input = new Scanner(file);
		for(int i = 0; i < rows; ++i){
		    for(int j = 0; j < columns; ++j){
		        if(input.hasNextInt()){
		            a[i][j] = input.nextInt();
		        }
		    }
		}//for
		input.close();
		return a;
	}
	/**
	 * Creating a randomly integer value</br>
	 * This value will be greater than 0 and less than length</br>
	 * @see tsp.TSPUtility.getRandInt()</br>
	 * @param length
	 * @return an integer value
	 */
	public static int getRandomPostition(int length){
		
		int randPos = 0;
		while (randPos ==0 || randPos == length){
			randPos = getRandInt(length);
		}
		return randPos;
		
	}

	/**
	 * Swapping the position among integer numbers 
	 * @param subRoute
	 */
	public static void swap(List<Integer> subRoute) {
		
		int n = subRoute.size();
		for (int i=0; i<n/2; i++){
			int temp = subRoute.get(i);
			subRoute.set(i, subRoute.get(n - 1 - i));
			subRoute.set(n - 1 - i, temp);
		}
		
	}

	public static int getRandInt(int bound){
		return generator.nextInt(bound);
	}
}
