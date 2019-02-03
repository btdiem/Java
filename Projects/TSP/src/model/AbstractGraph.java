package model;

/**
 * @author btdiem
 *
 */
public abstract class AbstractGraph implements IGraph{

	protected long [][] distances = null;
	protected int dimension = 0;
	protected long expectedCost = 500;
	
	public void setGraph(long [][] distances){
		this.distances = distances;
		this.dimension = distances.length;
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.IGraph#getDistance(int, int)
	 */
	public long getDistance(int pos1, int pos2){
		return distances[pos1][pos2];
	}
	/*
	 * (non-Javadoc)
	 * @see model.IGraph#setDistance(long, int, int)
	 */
	public void setDistance(long value, int pos1, int pos2) throws IllegalArgumentException{
		if (value < 0)
			throw new IllegalArgumentException("Distance value : " + value + " should be a positive number.");
		if (pos1 < 0 || pos1 >= dimension || pos2 <0 || pos2 >= dimension){
			throw new IllegalArgumentException("Postition(" + pos1 + "," + pos2 + " is invalid.");
		}
		distances[pos1][pos2] = value;
		distances[pos2][pos1] = value;
	}
	/*
	 * (non-Javadoc)
	 * @see model.IGraph#getDimension()
	 */
	public int getDimension(){
		return this.dimension;
	}
	/*
	 * (non-Javadoc)
	 * @see model.IGraph#getExpectedCost()
	 */
	public long getExpectedCost(){
		return this.expectedCost;
	}
	/*
	 * (non-Javadoc)
	 * @see model.IGraph#setExpectedCost(long)
	 */
	public void setExpectedCost(long cost){
		this.expectedCost = cost;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
        for(int i=0 ; i < dimension ; i++){
            for(int j=0 ; j < dimension ; j++)
                sb.append(distances[i][j]).append("	");
            sb.append("\n");
        }
		return sb.toString();
	}
	
	
}
