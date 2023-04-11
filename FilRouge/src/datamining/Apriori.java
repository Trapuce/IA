package datamining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import representation.variable.BooleanVariable;

/**
 * Class Apriori
 * Class representing the apriori algorithm
 * Extends AbstractItemsetMiner
 */
public class Apriori extends AbstractItemsetMiner {

	/**
	 * Constructor
	 * 
	 * @param booleanDB
	 */
	public Apriori(BooleanDatabase booleanDB) {
		super(booleanDB);
	}

	@Override
	public BooleanDatabase getDatabase() {
		return booleanDB;
	}

	@Override
	/**
	 * Final part of the apriori algorithm
	 * Return a set containing all the itemset with a frequency
	 * greater than of minimum frequency
	 * 
	 * @param minFrequency : the minimum frequency
	 * @return Set<Itemset>
	 */
	public Set<Itemset> extract(float minFrequency) {
		Set<Itemset> setOfItemset = frequentSingletons(minFrequency);

		List<SortedSet<BooleanVariable>> listK = new ArrayList<>();
		for (Itemset itemset : setOfItemset) {
			Set<BooleanVariable> items = itemset.getItems();
			SortedSet<BooleanVariable> sortedSet = new TreeSet<>(COMPARATOR);
			sortedSet.addAll(items);
			listK.add(sortedSet);
		}

		while (!listK.isEmpty()) {
			List<SortedSet<BooleanVariable>> candidates = new ArrayList<>();
			for (int i = 0; i < listK.size(); i++) {
				for (int j = i + 1; j < listK.size(); j++) {
					SortedSet<BooleanVariable> candidate = combine(listK.get(i), listK.get(j));
					if (candidate != null && allSubsetsFrequent(candidate, listK)) {
						float frequency = frequency(candidate);
						if (frequency >= minFrequency) {
							Itemset itemset = new Itemset(candidate, frequency);
							setOfItemset.add(itemset);
							candidates.add(candidate);
						}
					}
				}
			}
			listK = candidates;
		}
		return setOfItemset;
	}

	/**
	 * First part of the apriori algorithm
	 * Method returning a set of all itemsets of size one with frequency greater
	 * than a given
	 * frequency
	 * 
	 * @param frequency : the minimum frequency
	 * @return Set of Itemset
	 */
	public Set<Itemset> frequentSingletons(float frequency) {
		Set<Itemset> singletons = new HashSet<>();
		for (BooleanVariable item : booleanDB.getItems()) {
			Set<BooleanVariable> items = new HashSet<>();
			items.add(item);
			float singletonFrequency = frequency(items);
			if (singletonFrequency >= frequency) {
				Itemset itemSet = new Itemset(items, singletonFrequency);
				singletons.add(itemSet);
			}
		}
		return singletons;
	}

	/**
	 * Second part of the apriori algorithm
	 * Static Method combining two set of same size k, of same first k-1 elements
	 * and
	 * having different last element
	 * 
	 * @param setOne : the first sortedSet of size k
	 * @param setTwo : the second SortedSet of size k
	 * @return sortedSet of size k+1
	 */
	public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable> setOne,
			SortedSet<BooleanVariable> setTwo) {
		if (!allValid(setOne, setTwo))
			return null;
		SortedSet<BooleanVariable> combinedSet = new TreeSet<>(COMPARATOR);
		combinedSet.addAll(setOne);
		combinedSet.add(setTwo.last());
		return combinedSet;
	}

	/**
	 * Static helper method verifying if all conditions are met
	 * 
	 * @param setOne : the first sortedSet
	 * @param setTwo : the second sortedSet
	 * @return boolean
	 */
	private static boolean allValid(SortedSet<BooleanVariable> setOne, SortedSet<BooleanVariable> setTwo) {
		if (setOne.size() == 0 || setTwo.size() == 0)
			return false;
		if (setOne.last().equals(setTwo.last()))
			return false;
		if (setOne.size() != setTwo.size())
			return false;
		if (!setOne.headSet(setOne.last()).equals(setTwo.headSet(setTwo.last())))
			return false;
		return true;
	}

	/**
	 * Method verifying if all the subsets of size k-1 of an itemset are frequent,
	 * i.e are
	 * present in the list tracking all the frequents itemsets.
	 * 
	 * @param itemsetK : the itemset of size k
	 * @param listK    : list containing all the frequents itemsets of size k-1.
	 * @return true or false
	 */
	public static boolean allSubsetsFrequent(Set<BooleanVariable> itemsetK,
			Collection<SortedSet<BooleanVariable>> listK) {
		for (BooleanVariable item : itemsetK) {
			Set<BooleanVariable> copyMinusOne = new HashSet<>(itemsetK);
			copyMinusOne.remove(item);
			if (!listK.contains(copyMinusOne)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "{" +
				"}";
	}

}