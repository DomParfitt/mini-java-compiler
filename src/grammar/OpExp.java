package grammar;

/**
 * Class to represent an Expression with a binary Operator (e.g. Expr1 + Expr 2)
 * @author Dom Parfitt
 *
 */
public class OpExp extends Exp {
	
	final public static int PLUS = 1, MINUS = 2, TIMES = 3, DIVIDE = 4;
	
	public Exp left, right;
	public int op;
	
	public OpExp(Exp left, int op, Exp right) {
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
}
