package csp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import representation.variable.Variable;

/**
 * Class representing the implementation of an heuristic
 * Depending of our preference we can either choose to first
 * consider variable the biggest domain or not
 * Implements VariableHeuristic
 */
public class DomainSizeVariableHeuristic implements VariableHeuristic {

  private boolean preference;

  /**
   * Constructor
   *
   * @param preference : boolean indicating if we want to first consider varaibles
   *                   with biggest domain or not
   */
  public DomainSizeVariableHeuristic(boolean preference) {
    this.preference = preference;
  }

  /**
   * Method returning an array of varibles containing the variable with smallest
   * domain at index O
   * and varialbe with biggest domain at index 1
   *
   * @param setOfVariables : set of all variables
   * @param setDomain      : map<var, varDomain>
   * @return array of size two
   */

  @Override
  public Variable best(Set<Variable> setOfVariables, Map<Variable, Set<Object>> setDomain) {

    if (setOfVariables.isEmpty())
      return null;
    List<Variable> variables = new ArrayList<>(setOfVariables);
    variables.sort(Comparator.comparingInt(var -> setDomain.get(var).size()));
    return preference ? variables.get(variables.size() - 1) : variables.get(0);

  }

  /**
   * Getter
   *
   * @return preference
   */
  public boolean isPreference() {
    return preference;
  }

  /**
   * Setter
   *
   * @param preference : value to set
   */
  public void setPreference(boolean preference) {
    this.preference = preference;
  }

}
