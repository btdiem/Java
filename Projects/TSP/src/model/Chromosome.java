package model;

/**
 * @author btdiem
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tsp.TSPUtility;

public class Chromosome {
	//this integer list stores elements of chromosome 
	private List<Integer> route = new ArrayList<Integer>();
	//the cost
	private long distance = 0;
	private IGraph graph;
	int len = 0;
	/**
	 * Initialize the list with default values is their position
	 * @param n : the length of chromosome. In other words, this is the number of elements in a chromosome
	 */
	public Chromosome(int n){
		for (int i=1; i<=n; i++){
			route.add(i);
		}
		this.len = n;
		//this.graph = graph;
	}
	
	public boolean hasValue(Integer value){
		return route.contains(value);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Integer i : route){
			sb.append(i).append(" ");
		}
		return sb.toString();
	}

	public Chromosome(List<Integer> list){
		//this(list.size());
		this.route = new ArrayList<>(Arrays.asList(list.toArray(new Integer[]{}).clone()));
		this.len = route.size();
//		this.route.clear();
//		this.route.addAll(list);
	}
	
	public int getValue(int pos){
		if (pos >=0 && pos < len){
			return route.get(pos);
		}
		throw new IllegalArgumentException("pos parameter is invalid.");
	}
	
	public List<Integer> getRoute(){
		return this.route;
	}
	/**
	 * Adding a positive value at a certain position of chromosome</br>
	 * @param pos : index of element in chromosome
	 * @param element : new element will be left in chromosome
	 */
	public void addRoute(int pos, int element) throws IllegalArgumentException{
		if (pos >= len || pos < 0){
			throw new IllegalArgumentException("pos parameter is invalid." + pos);
		}
		if (element < 0){
			throw new IllegalArgumentException("element parameter is invalid.");
		}
		route.set(pos, element);
	}
	/**
	 * 
	 * @param fromIndex
	 * @param toIndex
	 * @return a sub list of integers from fromIndex to toIndex
	 * @throws IllegalArgumentException
	 */
	public List<Integer> getSubRoute(int fromIndex, int toIndex) throws IllegalArgumentException{
		if (fromIndex < 0 || fromIndex > toIndex){
			throw new IllegalArgumentException("fromIndex parameter is invalid.");
		}
		if (toIndex < 0 || toIndex >= len){
			throw new IllegalArgumentException("toIndex parameter is invalid.");
		}
		return route.subList(fromIndex, toIndex+1);
	}
	
	/**
	 * Creating a Chromosome with randomizely integer values
	 * These values are guaranteed differently
	 * @see tsp.TSPUtility#getRandInt(int) 
	 * @param n : the length of chromosome. In other hand, there are n elements in new chromosome
	 * @return a Chromosome
	 */
	public static Chromosome createNode(int n){
		
		List<Integer> routes = new ArrayList<Integer>();
		Chromosome newNode = new Chromosome(routes);
		while (routes.size() > n){
			int rand = TSPUtility.getRandInt(n);
			if(!routes.contains(rand)){
				routes.add(rand);
			}
		}
		return newNode;
	}
	
	public int getLength(){
		//return route.size();
		return this.len;
	}
	/**
	 * Calculating the cost of a chromosome by totaling the distance between 2 continuous nodes 
	 * @see model.IGraph#getDistance(int, int)
	 * @param matrix- Is a {@link IGraph} object
	 */
	public void calculateFitness(IGraph graph){
		
		this.distance = 0;
		int i =0;
		while (i < route.size() -1){
			int pos1 = route.get(i);
			int pos2 = route.get(++i);
			this.distance += graph.getDistance(pos1-1, pos2-1);
		}
		//add the cost from last node back to home
		this.distance+= graph.getDistance(route.get(getLength() -1)-1, route.get(0)-1);
	}
	
	public long getDistance(){
		return this.distance;
	}

	public void setPosition(int pos, int element) {
		if (pos >=0 && pos <len){
			route.set(pos, element);
		}else{
			throw new IllegalArgumentException("Parameter is invalid. pos = " + pos + " element = " + element);
		}
		
		//route.
	}
	
	public long getDistance2Position(int pos1, int pos2){
//		System.out.println("1" + pos1);
//		System.out.println("2" + pos2);
		return  graph.getDistance(pos1, pos2);
	}

	/**
	 * Creating a copied version that has the same elements 
	 * @return a Chomosomr {@link Chromosome}
	 */
	public Chromosome toCopy(){
		Chromosome copy = new Chromosome(getLength());
		copy.route = Arrays.asList(route.toArray(new Integer[]{}).clone());
		return copy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (distance ^ (distance >>> 32));
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chromosome other = (Chromosome) obj;
		if (distance != other.distance)
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		return true;
	}
	
	
}

