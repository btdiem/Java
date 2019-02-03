/**
 * 
 */
package test;


import static org.junit.Assert.*;

import java.util.Arrays;
import model.Chromosome;
import model.IGraph;
import operators.Heuristic;

import org.junit.Test;

/**
 * @author btdiem
 *
 */
public class HeuristicTest {

	/**
	 * @see Heuristic#heuristics2opt(Chromosome, IGraph)
	 * @throws Exception
	 */
	@Test
	public void testHeuristic() throws Exception {
		
		
		IGraph g = TestGraphDataFactory.createMatrix42();
		
		Chromosome chromosome = new Chromosome(Arrays.asList(1, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 21, 20, 19, 18, 16, 15, 14, 13, 17, 22, 23, 11, 12, 10, 25, 24));
		Chromosome copy = chromosome.toCopy();
		
		Heuristic.heuristics2opt(copy, g);
		copy.calculateFitness(g);
		
		System.out.println(copy.equals(chromosome));
		
		assertFalse(chromosome.equals(copy));
		assertTrue(copy.getDistance() > 0);
		

	}

}
