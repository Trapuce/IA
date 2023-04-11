package datamining;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import representation.variable.BooleanVariable;

/**
 * Abstract class AbstractItemsetMiner
 * Implements ItemsetMiner
 */
public abstract class AbstractItemsetMiner implements ItemsetMiner {

	protected BooleanDatabase booleanDB;

	public static final Comparator<BooleanVariable> COMPARATOR = (var1, var2) -> var1.getName().compareTo(var2.getName());

	/**
	 * Constructor
	 *
	 * @param booleanDB
	 */
	public AbstractItemsetMiner(BooleanDatabase booleanDB) {
		this.booleanDB = booleanDB;
	}

	/**
	 * Method returning the frequency of wich an itemset appears in all the
	 * transactions contained in the database
	 *
	 * @param itemSet : the itemSet
	 * @return the frequency of type float
	 */
	public float frequency(Set<BooleanVariable> itemSet) {
		List<Set<BooleanVariable>> transactions = booleanDB.getTransactions();
		if (transactions.size() == 0)
			throw new IllegalArgumentException();
		float frequency = 0;
		float count = 0;
		for (Set<BooleanVariable> transaction : transactions) {
			if (transaction.containsAll(itemSet)) {
				count++;
			}
		}
		frequency = count / (float) transactions.size();
		return frequency;
	}

	/**
	 * Getter
	 * @return the database
	 */
	public BooleanDatabase getBooleanDB() {
		return this.booleanDB;
	}

}