package grammar;

/**
 * Class for a List of Expressions with a head and a tail
 * @author Dom Parfitt
 *
 */
public class PairExpList extends ExpList {

	public Exp head;
	public ExpList tail;
	
	public PairExpList(Exp head, ExpList tail) {
		this.head = head;
		this.tail = tail;
	}
}
