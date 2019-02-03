package operators;

import java.util.List;

import tsp.TSPUtility;
import model.Chromosome;


public class MutationOperators {

	/**
     * Creates new randomly mutated chromosome from its parent.
     * Find randomly two mutation indexes
     * Invert the values in subroute
     * This is the most simple genetic mutation algorithm.
     * The detailed argorithm can be found : {@link http://www-public.int-evry.fr/~gibson/Teaching/CSC4504/L5-GeneticAlgorithms.pdf}
	 * @param chromosome
	 * @return a new Chromosome
	 */
	public static Chromosome inversion(Chromosome chromosome) {
		
//		System.out.println("Before mutation : " + chrom.toString());
		Chromosome newChromosome = new Chromosome(chromosome.getLength());
		//Guarantee pos1 < pos2
		int randPos2 = TSPUtility.getRandomPostition(chromosome.getLength());
		int randPos1 = TSPUtility.getRandomPostition(++randPos2);
		
//		System.out.println("pos1 = " + randPos1 + ", pos2 = " + randPos2);
		
		for (int i=0; i < randPos1; i++){
			newChromosome.addRoute(i, chromosome.getValue(i));
		}
		
		for (int i=randPos2-1; i < chromosome.getLength(); i++){
			newChromosome.addRoute(i, chromosome.getValue(i));
		}
		
		List<Integer> subRoute = chromosome.getSubRoute(randPos1, randPos2-1);
		TSPUtility.swap(subRoute);

		for (int i= 0; i < subRoute.size() ; i++){
			newChromosome.addRoute(randPos1+i, subRoute.get(i));
		}
		return newChromosome;

	}
	
	
//	   /**
//	    * Creates childs as heuristics optimalizations of chromosome
//	    * the algorithm is described at described at http://www.gcd.org/sengoku/docs/arob98.pdf
//	    * and used at http://www.zlote.jabluszko.net/tsp/
//	    * @param cities - chromosome to be optimalized
//	 	*/
//	   public static void heuristics2opt(Chromosome chromosome) {
//	       
//		   boolean done = false;
//	       int count = chromosome.getLength();
//	       for(int k = 0; k < count && !done; k++)
//	       {
//	           done = true;
//	           for(int i = 0; i < count; i++)
//	           {
//	               for(int j = i + 2; j < count; j++)
//	            	   if(
//	            	   ( chromosome.getDistance2Position(i, (i+1)%count)) + chromosome.getDistance2Position(j, (j+1)%count) 
//	            	   >
//	               		 (chromosome.getDistance2Position(i, j) + chromosome.getDistance2Position((i+1)%count, (j+1)%count))
//	               		)
//	                   {
//	            		   int tmp = chromosome.getPosition((i + 1) % count);
//	            		   chromosome.setPosition((i + 1) % count, chromosome.getPosition(j));
//	            		   chromosome.setPosition(j, tmp);
//	            		   
//	            		   reverse(chromosome, i+2, j-1);
////	                       City tmp = cities[(i + 1) % count];
////	                       cities[(i + 1) % count] = cities[j];
////	                       cities[j] = tmp;
////	                       reverse(cities, i + 2, j - 1);
//	                       done = false;
//	                   }
//	           }
//	       }
//	   }
//	   
//	   /**
//	    * Part of heuristics optimalizations of chromosome
//	    * taken from code at http://www.zlote.jabluszko.net/tsp/
//	    * @param cities - chromosome to be optimalized
//	    * @param startIndex 
//	    * @param stopIndex 
//	 	*/
//	   public static void reverse(Chromosome chromosome, int startIndex, int stopIndex)
//	   {
//	       if(startIndex >= stopIndex || startIndex >= chromosome.getLength() || stopIndex < 0)
//	           return;
//	       for(; startIndex < stopIndex; stopIndex--)
//	       {
//	    	   int tmp = chromosome.getPosition(startIndex);
//	    	   chromosome.setPosition(startIndex, chromosome.getPosition(stopIndex));
//	    	   chromosome.setPosition(stopIndex, tmp);
////	           City tmp = cities[startIndex];
////	           cities[startIndex] = cities[stopIndex];
////	           cities[stopIndex] = tmp;
//	           startIndex++;
//	       }
//
//	   }
//
	
}
