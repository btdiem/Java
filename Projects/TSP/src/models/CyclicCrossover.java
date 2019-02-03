
package operators;

import java.util.ArrayList;
import java.util.List;

import data.Chromosome;

import interfaces.ICrossover;
/**
 * @author btdiem
 *
 */
public class CyclicCrossover implements ICrossover {

	@Override
	public Chromosome crossover(Chromosome parent1, Chromosome parent2) {
		
		//find the cycle point
		List<Integer> newRoute = findCyclePoint(parent1, parent2);
		Chromosome child = new Chromosome(newRoute);
		for(int i=0; i < parent2.getLength() ; i++){
			int valueP2 = parent2.getPosition(i);
				if (!child.hasValue(valueP2)){
					child.setPosition(valueP2-1, valueP2);
				}//if
		}//for
		return child;
		
	}
	
	private List<Integer> findCyclePoint(Chromosome parent1, Chromosome parent2){

		/**
		 *  // determine the starting index
        int idx = randomStart ? GeneticAlgorithm.getRandomGenerator().nextInt(length) : 0;
        int cycle = 1;

        while (visitedIndices.size() < length) {
            indices.add(idx);

            T item = parent2Rep.get(idx);
            idx = parent1Rep.indexOf(item);

            while (idx != indices.get(0)) {
                // add that index to the cycle indices
                indices.add(idx);
                // get the item in the second parent at that index
                item = parent2Rep.get(idx);
                // get the index of that item in the first parent
                idx = parent1Rep.indexOf(item);
            }
		 */
		List<Integer> Indexes = new ArrayList<Integer>();
		//generate a number that values from 0 to n-1. Avoiding out of index
		int valueP1 = generator.nextInt(parent1.getLength());
		if (valueP1 == parent1.getLength()){
			valueP1--;
		}else if (valueP1 == 0){
			valueP1++;
		}
		
		Indexes.add(valueP1);
		int valueP2 = parent2.getPosition(valueP1 -1);
		//from 0 to n-1
		valueP1 = parent1.getPosition(valueP2 -1 );
//		System.out.println();
//		System.out.println("Parent 1 - " + parent1.toString());
//		System.out.println("Parent 2 - " + parent2.toString());
		
		while(!Indexes.get(0).equals(valueP1)){
			
			Indexes.add(valueP1);
			valueP2 = parent2.getPosition(valueP1 -1);
			valueP1 = parent1.getPosition(valueP2 -1);
			
		}
		
		List<Integer> cyclePoints = new ArrayList<Integer>();
		while(cyclePoints.size() < parent1.getLength()){
			cyclePoints.add(0);
		}
		
		for(Integer index : Indexes){
			cyclePoints.set(index-1, index);
		}
		
		return cyclePoints;
		
	}
	

}
