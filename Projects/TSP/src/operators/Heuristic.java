/**
 * 
 */
package operators;


import model.Chromosome;
import model.IGraph;
/**
 * For heuristics, the 2opt mutation is used, as described at {@link http://www.gcd.org/sengoku/docs/arob98.pdf}
 * 
 * @author btdiem
 *
 */
public class Heuristic {

	   /**
	    * Creates childs as heuristics optimalizations of chromosome</br>
	    * the algorithm is described at described at {@link http://www.gcd.org/sengoku/docs/arob98.pdf} 
	    * and used at {@link http://www.zlote.jabluszko.net/tsp/} </b>
	    * The modification is made to apply for design and code in this project</br>
	    * After muting or crossing, calling heuristic to create new generation</br>
	    * @param chromosome - chromosome to be optimalized
	 	*/
	   public static void heuristics2opt(Chromosome chromosome, IGraph g) throws Exception{
	       
		   boolean done = false;
	       int count = chromosome.getLength();
	       for(int k = 0; k < count && !done; k++)
	       {
	           done = true;
	           for(int i = 0; i < count-1; i++)
	           {
	               for(int j = i + 2; j < count-2; j++){

	            	   long d1 = g.getDistance(i, (i+1)%(count-1)) 
	            	   			+ g.getDistance(j, (j + 1)%(count-1));
	            	   
	            	   long d2 = g.getDistance(i, j) 
	            	   			+ g.getDistance( (i+1)%(count-1), (j + 1)%(count-1));
	            	   
	            	   if (d1 > d2){
	            		   
	            		   int tmp = chromosome.getValue((i+1)%(count-1));
	            		   chromosome.setPosition((i+1)%(count-1), chromosome.getValue(j));
	            		   chromosome.setPosition(j, tmp);
	            		   reverse(chromosome, i + 2, j - 1);
	            		   done = false;
	            	   }
	            	   
	            	   
	               }
	           }
	       }
	   }

	   /**
	    * Part of heuristics optimalizations of chromosome
	    * taken from code at {@link http://www.zlote.jabluszko.net/tsp/}
	    * @param Chromosome - chromosome to be optimalized
	    * @param startIndex 
	    * @param stopIndex 
	 	*/
	   public static void reverse(Chromosome chromosome, int startIndex, int stopIndex)
	   {
	       if(startIndex >= stopIndex || startIndex >= chromosome.getLength() || stopIndex < 0)
	           return;
	       for(; startIndex < stopIndex; stopIndex--)
	       {
	    	   int temp = chromosome.getValue(startIndex);
	    	   chromosome.setPosition(startIndex, chromosome.getValue(stopIndex));
	    	   chromosome.setPosition(stopIndex, temp);
	           startIndex++;
	       }

	   }

//	   
//	   public static void main(String[] args) throws Exception{
//		   
//			Chromosome parent1 = new Chromosome(Arrays.asList(17, 7, 4, 2, 11, 8, 5, 12, 13, 15, 6, 16, 3, 9, 14, 1, 10));
//			Chromosome parent2 = new Chromosome(Arrays.asList(5, 7, 14, 11, 6, 2, 3, 16, 10, 15, 4, 1, 13, 17, 8, 12, 9));
//			Chromosome child = CrossoverOperators.cyclic(parent1, parent2);
//			System.out.println(child);
//			IGraph g = TestGraphDataFactory.createMatrix42();
//			Chromosome ch = new Chromosome(Arrays.asList(1, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 21, 20, 19, 18, 16, 15, 14, 13, 17, 22, 23, 11, 12, 10, 25, 24)); 
//			heuristics2opt(ch, g);
//			System.out.println(ch);
//	   }

}
