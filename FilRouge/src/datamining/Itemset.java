package datamining;

import java.util.Objects;
import java.util.Set;

import representation.variable.BooleanVariable;

/**
 * Class Itemset
 * An itemset is a set of boolean variables
 * with a frequency
 */

public class Itemset {

	private Set<BooleanVariable> items;
	private float frequency;

	/**
	 * Constructor
	 *
	 * @param items     : the set of items
	 * @param frequency : the frequency
	 * @throws IllegalArgumentException if the frequency is below 0 or over 1
	 */
	public Itemset(Set<BooleanVariable> items, float frequency) throws IllegalArgumentException {
		if (frequency >= 0 && frequency <= 1) {
			this.frequency = frequency;
		} else {
			throw new IllegalArgumentException("frequency must beetwen 1 and 0");
		}
		this.items = items;
	}

	/**
	 * Getter
	 *
	 * @return the set of items
	 */
	public Set<BooleanVariable> getItems() {
		return this.items;
	}

	/**
	 * Getter
	 *
	 * @return the frequency
	 */
	public float getFrequency() {
		return this.frequency;
	}

	@Override
	public String toString() {
		return "Itemset [items=" + items + ", frequency=" + frequency + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Itemset)) {
			return false;
		}
		Itemset itemset = (Itemset) o;
		return Objects.equals(items, itemset.items) && frequency == itemset.frequency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, frequency);
	}

}