package datamining;

import java.util.Set;

/**
 * Interface IemsetMiner
 */
public interface ItemsetMiner {

	/**
	 * Getter for the database
	 *
	 * @return the database
	 */
	public BooleanDatabase getDatabase();

	/**
	 * Method returning a set of all non empty itemset with a frequency greater than
	 * the minimum frequency
	 *
	 * @param minFrequency : the minimum frequency
	 * @return Set<Itemset>
	 */
	public Set<Itemset> extract(float minFrequency);

}