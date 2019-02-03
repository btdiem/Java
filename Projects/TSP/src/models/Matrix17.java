package models;

import java.io.FileNotFoundException;

import common.Utility;
import data.AbstractGraph;

public class Matrix17 extends AbstractGraph {

	private final String fileName = "/db/matrix_17";
	@Override
	public void setData() {
		try{
			long[][] distances = Utility.read2DArray(fileName);
			setMatrix(distances);
			//System.out.println(this.toString());
			setExpectedCost();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	@Override
	public void setExpectedCost() {
		// TODO Auto-generated method stub
		this.expectedCost = 2085;
	}


	
}
