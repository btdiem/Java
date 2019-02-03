package tsp;

import java.util.List;

import model.Chromosome;
import model.IGraph;

public interface IPopulation {

	/**
	 * Creating randomizely the 1st generation
	 * For each chromosome:
	 * Exchanging the position of elements in a chromosome
	 * Calculating the fitness value for it.
	 * 
	 */
	public void initialize();
	/**
	 * Orders population of chromosome is sorted according to the their costs in descending order
	 */
	public void order();
	/**
	 * @return the possible chromosome generated in each generation
	 */
	public List<Chromosome> getPopulation();
	/**
	 * @return a the most optimal cost of a chromosome at each generation
	 */
	public Chromosome getBestFit();
	/**
	 * Removing the chromosome that are less fit cost.
	 * The parameter to decide How many chromosomes are removed is defined in Parameter.java
	 * @see tsp.Parameter.bestCostRate
	 */
	public void removeBadChromosomes();
	/**
	 * Adding new chromosomes into collection
	 * @param listNode
	 */
	public void addChromosomes(List<Chromosome> listChromosome);
	/**
	 * 
	 * @return the number of chromosomes
	 */
	public int getSize();
	/**
	 * @return an IGraph object
	 */
	public IGraph getIGraph();
	/**
	 * Get a chromosome at a certain position of list
	 * @param pos : index of chromosome in the list
	 * @return a Chromosome
	 */
	public Chromosome getChromosome(int pos);
	
}
