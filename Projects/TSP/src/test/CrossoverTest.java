/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import model.Chromosome;
import operators.CrossoverOperators;

import org.junit.Test;

/**
 * @author btdiem
 *
 */
public class CrossoverTest {

	/**
	 * Test Crossover cyclic operator</br>
	 * After mating, the value of parents should not change</br>
	 * The result is followed the Crossover algorithm</br>
	 * {@link CrossoverOperators#cyclic(Chromosome, Chromosome)}
	 */
	@Test
	public void testCyclicCrossover() {
		
		Chromosome parent1 = new Chromosome(Arrays.asList(17, 7, 4, 2, 11, 8, 5, 12, 13, 15, 6, 16, 3, 9, 14, 1, 10));
		Chromosome parent2 = new Chromosome(Arrays.asList(5, 7, 14, 11, 6, 2, 3, 16, 10, 15, 4, 1, 13, 17, 8, 12, 9));
		Chromosome child = CrossoverOperators.cyclic(parent1, parent2); 
		
		assertTrue(Arrays.asList(17, 7, 4, 2, 11, 8, 5, 12, 13, 15, 6, 16, 3, 9, 14, 1, 10).equals(parent1.getRoute()));
		assertTrue(Arrays.asList(5, 7, 14, 11, 6, 2, 3, 16, 10, 15, 4, 1, 13, 17, 8, 12, 9).equals(parent2.getRoute()));
		assertTrue(Arrays.asList(17, 7, 14, 11, 6, 2, 5, 16, 13, 15, 4, 1, 3, 9, 8, 12, 10).equals(child.getRoute()));
				

	}

}
