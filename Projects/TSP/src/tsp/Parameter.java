package tsp;

public class Parameter {
 
	/**
	 * The number of chromosome is generated after initialization and each generation  
	 */
	public static final int populationSize = 100000;
	/**
	 *   
    * Ratio (0..1), how much the population should undergo after each generation 
    */
	public static final double bestCostRate = 0.5;
	/**
	 * searching process will stop after 2 hours
	 */
	public static final long terminationTime = 2*3600*1000;
   /**
    * The count of generation which give the same best result after which the program should increase mutation range
    */
	public static final int repeatedTimeMax = 100;
   /**
    * Ratio (0..1), how much the population should undergo random mutation 
    */
	public static final double mutationRange = 0.05;

	
}
