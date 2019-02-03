package test;

import java.io.FileNotFoundException;

import model.IGraph;
import model.MatrixToGraph;

/**
 * This class is used to produce different value for testing data matrix.</br>
 * The data input value is read from file</br>
 * For every matrix, user has to define value of each node in and a expected cost that will stop searching cycle</br>
 * The data is found at : {@link http://people.sc.fsu.edu/~jburkardt/datasets/tsp/tsp.html}</br>
 * @see MatrixToGraph
 * @author btdiem
 *
 */
public class TestGraphDataFactory {

	public static IGraph createMatrix5() throws FileNotFoundException{
		return new MatrixToGraph("/db/matrix_5", 375);
	}
	
	public static IGraph createMatrix17() throws FileNotFoundException{
		return new MatrixToGraph("/db/matrix_17", 2085);
	}
	
	public static IGraph createMatrix26() throws FileNotFoundException{
		return new MatrixToGraph("/db/matrix_26", 937);
	}
	
	public static IGraph createMatrix42() throws FileNotFoundException{
		return new MatrixToGraph("/db/matrix_42", 699);
	}

}
