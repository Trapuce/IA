package csp;

import java.util.Map;

import representation.variable.Variable;

/**
 * Solver interface
 */
public interface Solver{

  /**
   * method that use different techniques to solve the csp
   * @return
   */
  public Map<Variable, Object> solve();

}