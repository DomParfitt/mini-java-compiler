package grammar;

/**
 * Class for a List of Expressions without a tail (i.e the list terminator)
 * @author Dom Parfitt
 *
 */
public class LastExpList extends ExpList {
	
	public Exp head;
	
	public LastExpList(Exp head) {
		this.head = head;
	}

}
