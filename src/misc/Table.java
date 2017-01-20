package misc;

/**
 * Class representing a Table data structure. Essentially a Linked List
 * for holding variables (id is variable name, value is what is assigned
 * to it)
 * @author Dom Parfitt
 *
 */
public class Table {
	
	public String id;
	public int value;
	public Table tail;
	
	public Table(String id, int value, Table tail) {
		this.id = id;
		this.value = value;
		this.tail = tail;
	}
	
	public Table() {}
	
	/**
	 * Method to look up an id in the table and return its value.
	 * Returns the first match it finds
	 * @param key the id to look up
	 * @return the value of that id
	 */
	public int lookup(String key) {
		//TODO: Catch error if key not present
		if (this.id == key) {
			return this.value;
		} else {
			return this.tail.lookup(key);
		}
	}
}
