package tsp;

import java.util.ArrayList;
import java.util.List;

import model.Chromosome;
import model.IGraph;
import operators.CrossoverOperators;
import operators.MutationOperators;
import operators.Heuristic;

public class TSP {

	IPopulation population = null;
	IGraph graph;
	long startTime = 0;
	long finishTime = 0;
	
	public TSP(IGraph graph){
		//check isVaildGraph();
		this.graph = graph;
		population = new Population(graph);
		//System.out.println(graph.toString());
		
	}
	/**
	 * Creating a repeated process to find the best chromosome</br>
	 * This process will stop if the expected chromosome is found or the process reaches to termination time</br>
	 * @throws Exception 
	 * @see Parameter#terminationTime</br>
	 * @see Parameter#mutationRange
	 * @see Population#initialize()</br>
	 * @see Population#order()</br>
	 */
	public void evolution() throws Exception{

		population.initialize();
		population.order();
		
		int repeatedTimes = 0;
		long fitCost = population.getBestFit().getDistance();
		int populationSize = population.getSize();
		double mutationRate = populationSize * Parameter.mutationRange;

		startTime = System.currentTimeMillis();
		while (!stop()){
			System.out.println("The cost of best fit @ this generation: " + fitCost);
			System.out.println(population.getBestFit());
			/**
			 * The searching will stop 
			 * if after crossing the chromosome, the best fit chromosome is the expected one
			 */
			if (matingParents()){
				break;
			}
			mutation(mutationRate);
			/**
			 * Checking how many times if the most optimal chromosome is repeated
			 * 
			 */
			if (fitCost == population.getBestFit().getDistance() ){
				repeatedTimes ++;
			}else{
				fitCost = population.getBestFit().getDistance();
				repeatedTimes = 0;
			}
			/**
			 * If the number reaches to repeatedTimeMax, increasing double time of mutation rate
			 * with hoping to produce a better generation
			 *  
			 */
			if (repeatedTimes == Parameter.repeatedTimeMax){
				
				if (2*mutationRate < populationSize ){
					mutationRate  *= 2;
					repeatedTimes = 0;
				}
			}
		}//while //found = true;
	
		finishTime = System.currentTimeMillis();

	}//function	
	
	/**
	 * Muting elements of chromosomes of the current generation to produce a new one
	 * The number of chromosomes that are mutated depends on the value of mutationRate
	 * After mutation, re-calculating the fitness value of each chromosome.
	 * The process only keeps that best ones 
	 * @see TSPUtility#getRandInt(int)
	 * @see Chromosome#calculateFitness(IGraph)
	 * @see MutationOperators#inversion(Chromosome)
	 * @param mutationRate
	 */
	private void mutation(double mutationRate) {

		List<Chromosome> lstMutation = new  ArrayList<Chromosome>();
		while (lstMutation.size() < (int)mutationRate){
			
			int pi = TSPUtility.getRandInt(population.getSize()-1);
			Chromosome chromosome = population.getPopulation().get(pi);
			chromosome = MutationOperators.inversion(chromosome);
			chromosome.calculateFitness(graph);
			lstMutation.add(chromosome);
			
			//Using heuristic
			try {
				
				Chromosome child = chromosome.toCopy();
				Heuristic.heuristics2opt(child, graph);
				child.calculateFitness(graph);
				lstMutation.add(child);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//while for Mutation
		population.addChromosomes(lstMutation);

		bestSelection();
	}

	/**
	 * Getting randomizely two chromosomes and scrossing them</br>
	 * The fitness value of chromosomes in new generation are calculated</br>
	 * And only the best ones are kept after they are ordered.</br>
	 * @see Parameter#bestCostRate</br>
	 * @see Population#order()</br>
	 * @see Population#removeBadChromosomes()</br>
	 * @return true of the expected value is found. Otherwise, returning false</br>
	 * @throws Exception 
	 */
	public boolean matingParents(){
		
		int populationSize = population.getSize();
		final double bestCount = populationSize*Parameter.bestCostRate;
		List<Chromosome> lstChild = new ArrayList<Chromosome>();
		
		while (lstChild.size() < bestCount){

			Chromosome parent1 = population.getChromosome(TSPUtility.getRandInt(populationSize-1));
			Chromosome parent2 = population.getChromosome(TSPUtility.getRandInt(populationSize-1));
			
			Chromosome child1 = CrossoverOperators.cyclic(parent1, parent2);
			Chromosome child2 = CrossoverOperators.cyclic(parent2, parent1);
			child1.calculateFitness(graph);
			child2.calculateFitness(graph);
			
			lstChild.add(child1);
			lstChild.add(child2);
			
			//try to using heuristic to create new generation
			Chromosome child3 = new Chromosome(parent1.getRoute());//parent1.toCopy();
			Chromosome child4 = new Chromosome(parent2.getRoute());
			Chromosome child5 = new Chromosome(child1.getRoute());
			Chromosome child6 = new Chromosome(child2.getRoute());
			try{

				Heuristic.heuristics2opt(child3, graph);
				Heuristic.heuristics2opt(child4, graph);
				Heuristic.heuristics2opt(child5, graph);
				Heuristic.heuristics2opt(child6, graph);
				
				child3.calculateFitness(graph);
				child4.calculateFitness(graph);
				child5.calculateFitness(graph);
				child6.calculateFitness(graph);
				
				lstChild.add(child3);
				lstChild.add(child4);
				lstChild.add(child5);
				lstChild.add(child6);

			}catch(Exception e){
				e.printStackTrace();
				System.out.println("p1 " + parent1);
				System.out.println("p2 " + parent2);
				System.out.println("c1 " + child1);
				System.out.println("c2 " + child2);
				System.out.println("c3 " + child3);
				System.out.println("c4 " + child4);
			}
			
		}//while for Crossover
		
		population.addChromosomes(lstChild);

		bestSelection();

		return found();

	}//mating

	/**
	 * Checking the cost of best fit chromosome</br>
	 * @see Population#getBestFit()
	 * @see Chromosome#getDistance()
	 * @see IGraph#getDistance(int, int)
	 * @return true of the best chromosome of this generation is expected one</br>
	 * 
	 */
	private boolean found(){
		return population.getBestFit().getDistance() <= graph.getExpectedCost();
	}
	/**
	 * This method select the best chromosomes by ordering and removing the bad ones
	 * @see Population#order()
	 * @see Population#removeBadChromosomes();
	 */
	private void bestSelection(){
		population.order();
		population.removeBadChromosomes();
	}
	/**
	 * @see Parameter#terminationTime</br>
	 * @return true if the expected result is found and the time to search is not over</br>
	 * Otherwise, returning false</br>
	 * 
	 * 
	 */
	private boolean stop(){
		return found() || (System.currentTimeMillis() - this.startTime > Parameter.terminationTime);
	}
	
	/**
	 * Display the duration of searching process</br>
	 * Display the best chromosome found</br>
	 * 
	 */
	public void statistic(){
		System.out.println("----------------------------------------------------------------\n");
		System.out.println("The duration of algorithm : " + (finishTime - startTime) + "(ms)");
		if(found()){
			System.out.println("The best chromosome responses the expected one: ");
			System.out.println(population.getBestFit().toString());
		}else{
			System.out.println("The cost of best chromosome found: " + population.getBestFit().getDistance());
			System.out.println(population.getBestFit().toString());
			System.out.println("Meanwhile the expected chromosome : " + graph.getExpectedCost());
		}
	}
}