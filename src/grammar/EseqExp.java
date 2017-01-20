package grammar;

/**
 * Class for an Expression Sequence (i.e. Stm, Exp)
 * @author Dom Parfitt
 *
 */
public class EseqExp extends Exp {

	public Stm stm1;
	public Exp exp;
	
	public EseqExp(Stm stm1, Exp exp) {
		this.stm1 = stm1;
		this.exp = exp;
	}
}
