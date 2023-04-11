package csp;

import java.util.List;
import java.util.Set;

import representation.variable.Variable;

/**
 * Interface ValueHeuristic
 * Declare a method ordering returning an
 * ordered list of the best values that the
 * variable can take.
 */
public interface ValueHeuristic {

  /**
   *
   * @param var : the variable
   * @param varDomain : the domain of the variable
   * @return ordered list of best values
   */
  public List<Object> ordering(Variable var, Set<Object> varDomain);

}
