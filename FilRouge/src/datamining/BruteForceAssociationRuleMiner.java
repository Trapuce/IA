package datamining;

import java.util.HashSet;
import java.util.Set;

import representation.variable.BooleanVariable;

/**
 * Class BruteForceAssociationRuleMiner represents the algorithm
 * for extracting all the rules
 * Extends AbstractAssociationRuleMiner
 */
public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner {

  /**
   * Constructor
   *
   * @param database : the database
   */
  public BruteForceAssociationRuleMiner(BooleanDatabase database) {
    super(database);
  }

  /**
   * Method returning a set containing all the subsets of a given set expect the
   * empty set and set itself
   *
   * @param items : set of item
   * @return all set containing all the subsets
   */
  public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> items) {
    Set<Set<BooleanVariable>> res = new HashSet<>();
    PowerSet<BooleanVariable> powerSet = new PowerSet<>();
    res = powerSet.recursivePowerSet(items);
    res.removeIf(subset -> subset.size() == 0 || subset.size() == items.size());
    return res;
  }

  /**
   * Extract all the rules with frequency and a confidence greater than the
   * minimum
   *
   * @param minFrequency  : the minimum frequency
   * @param minConfidence : the minimum confidence
   * @return a set of rules
   */
  @Override
  public Set<AssociationRule> extract(float minFrequency, float minConfidence) {
    Set<AssociationRule> rules = new HashSet<>();
    Apriori apriori = new Apriori(database);
    Set<Itemset> itemsets = apriori.extract(minFrequency);
    for (Itemset itemset : itemsets) {
      Set<BooleanVariable> items = itemset.getItems(); // X
      float itemsFrequency = frequency(items, itemsets);
      Set<Set<BooleanVariable>> subSets = allCandidatePremises(items);
      for (Set<BooleanVariable> subSet : subSets) { // Y: conclusion
        Set<BooleanVariable> itemsWithoutSubSet = new HashSet<>(items); // X\Y: premise
        itemsWithoutSubSet.removeAll(subSet);
        float itemsWithoutSubSetFrequency = frequency(itemsWithoutSubSet, itemsets);
        float confidence = itemsFrequency / itemsWithoutSubSetFrequency;
        if (confidence >= minConfidence) {
          AssociationRule rule = new AssociationRule(itemsWithoutSubSet, subSet, itemsFrequency, confidence);
          rules.add(rule);
        }
      }
    }
    return rules;

  }

}
