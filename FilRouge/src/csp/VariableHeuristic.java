package csp;

import java.util.Map;
import java.util.Set;

import representation.variable.Variable;

/**
 * Interface VariableHeuristic
 * Declare a best method that return the
 * best variable depending of the heuristic
 */
public interface VariableHeuristic {

  /**
   *
   * @param setOfVariables : a set of the variables
   * @param setDomain : map<var, varDomain>
   * @return best variable
   */
  public Variable best(Set<Variable> setOfVariables, Map<Variable, Set<Object>> setDomain);
}
