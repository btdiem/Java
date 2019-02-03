package model;

import java.io.FileNotFoundException;

import tsp.TSPUtility;
/**
 * 
 * @author btdiem
 *
 */
public class MatrixToGraph  extends AbstractGraph{
	
	/**
	 * This is a specific implementation of Graph
	 * The input value is matrix loaded by a file</br>
	 * If data is invalid, an {@link IllegalArgumentException} is thrown</br>
	 * @see TSPUtility#isValidGraph(long[][])
	 * 
	 * @param fileName
	 * @param cost
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	public MatrixToGraph(String fileName, long cost) throws FileNotFoundException, 
														IllegalArgumentException{

		long[][] arrays = TSPUtility.read2DArray(fileName);;
		if (!TSPUtility.isValidGraph(arrays)){
			throw new IllegalArgumentException("Input value is invalid.");
		}
		this.distances = arrays;
		this.dimension = distances.length;
		this.expectedCost = cost;
	}

}
