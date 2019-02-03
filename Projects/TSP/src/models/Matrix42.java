package models;

import java.io.FileNotFoundException;

import common.Utility;
import data.AbstractGraph;

public class Matrix42 extends AbstractGraph {

	private final String fileName = "/db/matrix_42";
	@Override
	public void setData() {
		try{
			long[][] distances = Utility.read2DArray(fileName);
			setMatrix(distances);
			setExpectedCost();
			//System.out.println(this.toString());
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}

	}
	@Override
	public void setExpectedCost() {
		// TODO Auto-generated method stub
		this.expectedCost = 699;
	}
}
