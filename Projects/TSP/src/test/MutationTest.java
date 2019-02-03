/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import model.Chromosome;
import operators.MutationOperators;

import org.junit.Test;

import tsp.TSPUtility;

/**
 * @author btdiem
 *
 */
public class MutationTest {

	/**
	 * Test get subroute of chromosome and swap the elements in subroute</br>
	 * The argorithm is followed Mutation Inversion }
	 * @see MutationOperators#inversion(Chromosome)
	 * @see TSPUtility.swap(subRoute)
	 */
	@Test
	public void testMutation() {
		
		Chromosome chromosome = new Chromosome(Arrays.asList(1,2,3,4,5,6,7,8));
		int p1 = 3;
		int p2 = 7;
		List<Integer> subRoute = chromosome.getSubRoute(chromosome.getRoute().indexOf(p1), chromosome.getRoute().indexOf(p2));
		assertTrue(Arrays.asList(3,4,5,6,7).equals(subRoute));
		List<Integer> expectedValues = Arrays.asList(7,6,5,4,3);
		TSPUtility.swap(subRoute);
		assertTrue(expectedValues.equals(subRoute));
	}

}
