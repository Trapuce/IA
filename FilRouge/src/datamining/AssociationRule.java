package datamining;

import java.util.Objects;
import java.util.Set;

import representation.variable.BooleanVariable;

/**
 * Class AssociationRule
 * Class representing a rule
 * A rule have a premise, conclusion
 * a frequency and a confidence
 */
public class AssociationRule {

  private Set<BooleanVariable> premise;
  private Set<BooleanVariable> conclusion;
  private float frequency;
  private float confidence;

  /**
   * Constructor
   *
   * @param premise    : the premise
   * @param conclusion : the conclusion
   * @param frequency  : the frequency of the rule
   * @param confidence : the confidence
   */
  public AssociationRule(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, float frequency,
      float confidence) {
    this.premise = premise;
    this.conclusion = conclusion;
    this.frequency = frequency;
    this.confidence = confidence;
  }

  /**
   * Getter
   *
   * @return the premise
   */
  public Set<BooleanVariable> getPremise() {
    return premise;
  }

  /**
   * Getter
   *
   * @return the conclusion
   */
  public Set<BooleanVariable> getConclusion() {
    return conclusion;
  }

  /**
   * Getter
   *
   * @return the frequency
   */
  public float getFrequency() {
    return frequency;
  }

  /**
   * Getter
   *
   * @return the confidence
   */
  public float getConfidence() {
    return confidence;
  }

  @Override
  public String toString() {
    return "Rule {" +
        " premise='" + getPremise() + "'" +
        ", conclusion='" + getConclusion() + "'" +
        ", frequency='" + getFrequency() + "'" +
        ", confidence='" + getConfidence() + "'" +
        "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof AssociationRule)) {
      return false;
    }
    AssociationRule associationRule = (AssociationRule) o;
    return Objects.equals(premise, associationRule.premise) && Objects.equals(conclusion, associationRule.conclusion)
        && frequency == associationRule.frequency && confidence == associationRule.confidence;
  }

  @Override
  public int hashCode() {
    return Objects.hash(premise, conclusion, frequency, confidence);
  }

}
