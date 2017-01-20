package grammar;

/**
 * Class for a Compound Statement (i.e. Stm1; Stm2)
 * @author Dom Parfitt
 *
 */
public class CompoundStm extends Stm {
	
	public Stm stm1, stm2;
	
	public CompoundStm(Stm stm1, Stm stm2) {
		this.stm1 = stm1;
		this.stm2 = stm2;
	}
}
