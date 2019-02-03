/**
 * 
 */
package models;

import java.util.List;

import common.Utility;
import data.Chromosome;

import interfaces.IMutation;

/**
 * @author btdiem
 *
 */
public class IndexMutation implements IMutation {

	@Override
	public Chromosome mutation(Chromosome chrom) {
		
		Chromosome ret = new Chromosome(chrom.getLength());
		//guarantee pos1 < pos2
		int randPos2 = Utility.getRandomPostition(chrom.getLength());
		int randPos1 = Utility.getRandomPostition(++randPos2);
		
		for (int i=0; i < randPos1; i++){
			ret.addRoute(i, chrom.getPosition(i));
		}
		
		for (int i=randPos2-1; i < chrom.getLength(); i++){
			ret.addRoute(i, chrom.getPosition(i));
		}
		
		List<Integer> subRoute = chrom.getSubRoute(randPos1, randPos2-1);
		Utility.swap(subRoute);

		for (int i= 0; i < subRoute.size() ; i++){
			ret.addRoute(randPos1+i, subRoute.get(i));
		}
//		System.out.println("After mutation : " + ret.toString());
		return ret;

	}
	


	

}
