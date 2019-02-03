package model;

public interface IGraph {

	/**
	 * Updating an expected cost of a specific graph
	 * @param cost - the expected distance 
	 */
	public void setExpectedCost(long cost);
	/**
	 * Set the data value for graph
	 * @param distances - is a 2 dimensional array
	 */
	//public void setGraph(long [][] distances) throws IllegalArgumentException;
	/**
	 * 
	 * @param pos1
	 * @param pos2
	 * @return return value of a cell at the position(pos1,pos2)
	 */
	public long getDistance(int pos1, int pos2);
	/**
	 * Set new value for a cell at the position(pos1, pos2)</br>
	 * value should be positive number</br>
	 * the value of pos1 and pos2 have to less than the size of graph</br>
	 * @param value
	 * @param pos1
	 * @param pos2
	 * @param IllegalArgumentException
	 */
	public void setDistance(long value, int pos1, int pos2) throws IllegalArgumentException;
	/**
	 * 
	 * @return the the number of rows and colums
	 */
	public int getDimension();
	/**
	 * 
	 * @return the expected cost of graph that is set by user
	 */
	public long getExpectedCost();
	/**
	 * Filling the data for graph.
	 * This method should be implemented by a specific way.
	 */
	//public long[][] getGraphData();
	//public void fillGraphData();
	
}
