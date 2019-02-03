package operators;

import java.util.ArrayList;
import java.util.List;
import model.Chromosome;

public class CrossoverOperators {

	/**
     * Creates one child from two parents applying the CrossOver algorithm
     * for genetic mating of chromosomes.</b>
     * The detailed argorithm is found at {@link http://www-public.int-evry.fr/~gibson/Teaching/CSC4504/L5-GeneticAlgorithms.pdf}
	 * @param parent1
	 * @param parent2
	 * @return a new mating chromosome
	 */
	public static Chromosome cyclic(Chromosome parent1, Chromosome parent2){

		int p1ToIndex = parent1.getValue(0); 
		
		List<Integer> indices = new ArrayList<Integer>();
		indices.add(p1ToIndex -1);
		int p2ToValue = parent2.getValue(p1ToIndex-1);//to avoid exception: if p1ToIndex = n or =0
		p1ToIndex = parent1.getRoute().indexOf(p2ToValue);
		
		while(!indices.contains(p1ToIndex)){
			
			indices.add(p1ToIndex);
			p2ToValue = parent2.getValue(p1ToIndex);
			p1ToIndex = parent1.getRoute().indexOf(p2ToValue);//value is 1, index should be n-1 
			
		}
		
		List<Integer> child = new ArrayList<>(parent2.getLength());
		child.addAll(parent2.getRoute());

		for (int i : indices){
			child.set(i, parent1.getValue(i));
		}
		
		return new Chromosome(child);
		
	}

	
//	public static void main(String [] args){
//		
//		Chromosome s3 = cyclic(new Chromosome(Arrays.asList(17, 7, 4, 2, 11, 8, 5, 12, 13, 15, 6, 16, 3, 9, 14, 1, 10)),
//				new Chromosome(Arrays.asList(5, 7, 14, 11, 6, 2, 3, 16, 10, 15, 4, 1, 13, 17, 8, 12, 9)));
//
//		Chromosome s4 = cyclic(new Chromosome(Arrays.asList(1,2,3,4,5,6,7,8,9)),
//				new Chromosome(Arrays.asList(5,4,6,9,2,3,7,8,1)));
//
//		//s3.calculateFitness();
//		System.out.println("s3: "+ s3.toString());
//		System.out.println("s4: "+ s4.toString());
//		
//	}

}
