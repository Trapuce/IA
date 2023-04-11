package csp;

import java.util.Map;
import java.util.Set;

import representation.constraint.Constraint;
import representation.variable.Variable;

/**
 * Class NbConstraintsVariableHeuristic
 * Have an attribute preference to indiquate if
 * the variables have to appear in the maximum possible
 * number of constraints or not
 */
public class NbConstraintsVariableHeuristic implements VariableHeuristic {

  private Set<Constraint> setOfConstraints;
  private boolean preference;

  /**
   * Constructor
   *
   * @param setOfConstraints the set of constraints
   * @param preference       boolean indiquating if the variables have to appear
   *                         in the most number of constraints
   */
  public NbConstraintsVariableHeuristic(Set<Constraint> setOfConstraints, boolean preference) {
    this.setOfConstraints = setOfConstraints;
    this.preference = preference;
  }

  /**
   * @return setOfConstraints
   */
  public Set<Constraint> getSetofConstraints() {
    return setOfConstraints;
  }

  /**
   * @return preference
   */
  public boolean isPreference() {
    return preference;
  }

  /**
   * @return a list containing the varaibles appearing in the most constraints
   *         and the variable appearing in the least constraints
   */

  @Override
  public Variable best(Set<Variable> setOfVariables, Map<Variable, Set<Object>> setDomain) {
    if (setOfVariables.isEmpty()) {
      return null;
    }
    Variable best = null;
    int best_nbconstraint = preference ? Integer.MIN_VALUE : Integer.MAX_VALUE;

    for (Variable var : setOfVariables) {
      int nbConstraints = 0;
      for (Constraint constraint : setOfConstraints) {
        if (constraint.getScope().contains(var)) {
          nbConstraints += 1;
        }
      }
      if (preference) {
        if (nbConstraints > best_nbconstraint) {
          best_nbconstraint = nbConstraints;
          best = var;
        }
      } else {
        if (nbConstraints < best_nbconstraint) {
          best_nbconstraint = nbConstraints;
          best = var;
        }
      }
    }
    return best;
  }

  public Set<Constraint> getSetOfConstraints() {
    return setOfConstraints;
  }

}
