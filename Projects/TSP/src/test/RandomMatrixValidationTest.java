package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.IGraph;
import tsp.TSP;
import tsp.TSPUtility;

/**
 * @author btdiem
 *
 */
public class RandomMatrixValidationTest {

	/**
	 * @param args
	 */
	//Key-Value: Name of matrix - The name of method that creates graph object
	static List<String> dataList = new ArrayList<String>();
	
	static{
		dataList.addAll(Arrays.asList("createMatrix5", "createMatrix26", "createMatrix42", "createMatrix17"));
	}
	
	/**
	 * Testing randomly matrix data
	 *  @see TSPUtility#getRandInt(int)
	 * @param args
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static void main(String[] args){
		
		System.out.println(DateHeader.dateString());
		
		int ranInt = TSPUtility.getRandInt(dataList.size());
		try{

			Method method = TestGraphDataFactory.class.getDeclaredMethod(dataList.get(ranInt));
			IGraph graph = (IGraph) method.invoke(TestGraphDataFactory.class);
			System.out.println(graph);
			TSP tsp = new TSP(graph);
			tsp.evolution();
			tsp.statistic();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("A new graph data has not implemented yet.");
		}
		
	}

}
