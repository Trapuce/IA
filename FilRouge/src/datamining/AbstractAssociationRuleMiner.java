package datamining;

import java.util.HashSet;
import java.util.Set;
import representation.variable.BooleanVariable;

/**
 * Abstract class
 * Implements AssociationRuleMiner
 */
public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {

  protected BooleanDatabase database;

  /**
   * Constructor
   *
   * @param database : the database
   */
  public AbstractAssociationRuleMiner(BooleanDatabase database) {
    this.database = database;
  }

  /**
   * Getter
   *
   * @return database
   */
  public BooleanDatabase getDatabase() {
    return database;
  }

  /**
   * Static method returning the frequency of an itemset
   *
   * @param items        : the itemset
   * @param setOfItemset : set of the frequent itemset
   * @return frequency of the itemset
   * @throws IllegalArgumentException if the itemset is not present in the set of
   *                                  itemset
   */
  public static float frequency(Set<BooleanVariable> items, Set<Itemset> setOfItemset) {

    for (Itemset itemset : setOfItemset) {
      if (itemset.getItems().equals(items))
        return itemset.getFrequency();
    }
    throw new IllegalArgumentException("Items not present in set of items");
  }

  /**
   * Static method calculating the confidence of a rule
   *
   * @param premise      : the premise
   * @param conclusion   : the conclusion
   * @param setOfItemset : set containing all the frequent itemset
   * @return the confidence of type float
   */
  public static float confidence(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion,
      Set<Itemset> setOfItemset) {
    float confidence = 0;
    Set<BooleanVariable> premiseUnionConclusion = new HashSet<>(premise);
    premiseUnionConclusion.addAll(conclusion);
    float unionFrequency = frequency(premiseUnionConclusion, setOfItemset);
    float premiceFrequency = frequency(premise, setOfItemset);
    confidence = unionFrequency / premiceFrequency;
    return confidence;
  }

}
