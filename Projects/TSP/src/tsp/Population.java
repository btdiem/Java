package tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Chromosome;
import model.IGraph;

/**
 * @author btdiem
 *
 */

public class Population implements IPopulation{

	private IGraph graph;
	private int dimension;
	private List<Chromosome> listNode = Collections.synchronizedList(new ArrayList<Chromosome>());
	//number of population
	
	public Population(IGraph graph){
		this.graph = graph;
		this.dimension = graph.getDimension();
	}
	/*
	 * (non-Javadoc)
	 * @see tsp.IPopulation#initialize()
	 */
	public void initialize(){
		
		while (listNode.size() < Parameter.populationSize){
			
			Chromosome oneChromosome = new Chromosome(dimension);
			//System.out.println("1- " + oneChromosome.toString());
			randomize(oneChromosome);
			oneChromosome.calculateFitness(graph);
			//System.out.println("3- " + oneChromosome.toString() + " " + oneChromosome.getDistance());
			listNode.add(oneChromosome);
			
		}//while
		System.out.println("SIZE: " + listNode.size());
				
	}
	/*
	 * (non-Javadoc)
	 * @see tsp.IPopulation#order()
	 */
   public void order() {
      Collections.sort(listNode, new Comparator<Chromosome>() {
         public int compare(Chromosome o1, Chromosome o2) {
            long cost1 = o1.getDistance();
            long cost2 = o2.getDistance();
            return (cost1 > cost2 ? -1 : (cost1 < cost2 ? 1 : 0));
         }
      });
      
   }   

   /*
    * (non-Javadoc)
    * @see tsp.IPopulation#getPopulation()
    */
	public List<Chromosome> getPopulation(){
		return this.listNode;
	}

	/**
	 * This method can be overrided with another implementation
	 * Every elements in this chromosome will be swapped at least once by randomizing
	 * The randomization is done twice to make sure to have good mutations
	 * @param chromosome
	 */
	protected void randomize(Chromosome chromosome) {
		
	      final int length=chromosome.getLength();
	      //make sure that each element is swapped at least once
	      //else there could be created lots of similar nodes
	      for(int i=0; i<length; i++) {
	         int pos1=i;
	         int pos2=TSPUtility.getRandInt(length);
	         if(pos2==pos1) {
	            if(pos2>0) { pos2--; } else { pos2++; }
	         }
	         swap(chromosome, pos1, pos2);
	         
	      }
	      //randomize all the set more
	      int randomizerSteps=10*length;
	      //do the randomization of all elements inside this chromosome
	      for(int n=0; n<randomizerSteps; n++) {
	         int pos1=TSPUtility.getRandInt(length);
	         int pos2=TSPUtility.getRandInt(length);
	         if(pos1!=pos2) {
	        	 	swap(chromosome, pos1, pos2);
	         }
	      }
	}
	/**
	 * Exchanging the value of 2 elements in a chromosome
	 * @param chromosome
	 * @param pos1 : index of 1st element
	 * @param pos2 : index of 2nd element
	 */
	private void swap(Chromosome chromosome, int pos1, int pos2){
		
        int temp = chromosome.getValue(pos1);
        chromosome.setPosition(pos1, chromosome.getValue(pos2));
        chromosome.setPosition(pos2, temp);

	}
	/*
	 * After ordering, the last chromosome is the best one in each generation.
	 * It has the highest fitness value or smallest distance.
	 * (non-Javadoc) 
	 * @see tsp.IPopulation#getBestFit()
	 */
	public Chromosome getBestFit(){
		return listNode.get(listNode.size() - 1);
	}
	/*
	 * 	(non-Javadoc)
	 * @see tsp.IPopulation#removeBadNodes()
	 */
	public void removeBadChromosomes() {
		
		while(listNode.size() > Parameter.populationSize){
			listNode.remove(0);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see tsp.IPopulation#addChromosomes(java.util.List)
	 */
	public void addChromosomes(List<Chromosome> lstChild) {
		listNode.addAll(lstChild);
	}
	/*
	 * (non-Javadoc)
	 * @see tsp.IPopulation#getSize()
	 */
	public int getSize(){
		return this.listNode.size();
	}
	
	public IGraph getIGraph(){
		return this.graph;
	}
	
	public Chromosome getChromosome(int pos) throws IllegalArgumentException{
		
		if(pos >=0 && pos <= listNode.size()){
			return listNode.get(pos);
		}
		throw new IllegalArgumentException("pos parameter is invalid.");
	}
	
	
}
