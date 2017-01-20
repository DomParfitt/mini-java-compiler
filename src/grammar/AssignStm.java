package grammar;

/**
 * A class for an Assignment Statement (i.e. x = Expression;)
 * @author Dom Parfitt
 *
 */
public class AssignStm extends Stm {
	
	public String id;
	public Exp exp;
	
	public AssignStm(String id, Exp exp) {
		this.id = id;
		this.exp = exp;
	}

}
