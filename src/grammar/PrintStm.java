package grammar;

/**
 * Class for a Print Statement (i.e. print (Expression1, Expression2,..)
 * @author Dom Parfitt
 *
 */
public class PrintStm extends Stm {
	
	public ExpList exps;
	
	public PrintStm(ExpList exps) {
		this.exps = exps;
	}
}
