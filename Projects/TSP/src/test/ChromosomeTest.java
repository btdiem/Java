/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;

import model.Chromosome;
import model.IGraph;

import org.junit.Test;

/**
 * @author btdiem
 *
 */
public class ChromosomeTest {

	/**
	 * Test the routine and cost of a chromosome</br>
	 * @see Chromosome#getDistance()
	 * @see IGraph#getExpectedCost()
	 * @see TestGraphDataFactory#createMatrix5()
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void test() throws FileNotFoundException {
		
		IGraph graph = TestGraphDataFactory.createMatrix5();
		Chromosome p = new Chromosome(Arrays.asList(1,2,3,4,5));
		p.calculateFitness(graph);
		assertTrue(p.getDistance() >= graph.getExpectedCost());
		assertTrue(p.getDistance()>0);
	}

}
