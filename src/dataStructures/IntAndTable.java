package dataStructures;

/**
 * Wrapper class to hold an integer and a table. Used for represent side effects
 * from expressions
 * @author Dom Parfitt
 *
 */
public class IntAndTable {
	
	public int i ;
	public Table t;
	
	public IntAndTable(int i, Table t) {
		this.i = i;
		this.t = t;
	}
}
