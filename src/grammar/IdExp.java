package grammar;

/**
 * Class for an ID Expression (i.e. a variable declaration)
 * @author Dom Parfitt
 *
 */
public class IdExp extends Exp {

	public String id;
	
	public IdExp(String id) {
		this.id = id;
	}
}
