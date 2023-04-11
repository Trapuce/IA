package datamining;

import java.util.Set;

/**
 * Interface AssocationRuleMiner
 */
public interface AssociationRuleMiner {

  /**
   * Getter
   *
   * @return database
   */
  public BooleanDatabase getDatabase();

  /**
   * Return the rules with a frequency and confidence greater than minimum
   *
   * @param frequency : minimum frequency
   * @param confiance : minimum confidence
   * @return a set of rules
   */
  public Set<AssociationRule> extract(float frequency, float confiance);

}
