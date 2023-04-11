package planning;

import java.util.Map;

import representation.variable.Variable;

/**
 * Heuristic interface
 * An heuristic is an estimation of the 
 * coast to get to a certain goal
 */
public interface Heuristic {
  
  /**
   * Method that estimate the coast of a plan
   * @param state
   * @return
   */
  public float estimate(Map<Variable, Object> state);
}
