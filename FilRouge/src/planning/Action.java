package planning;

import java.util.Map;

import representation.variable.Variable;

public interface Action {

  /**
   * Method verifying if an action is applicable to an given state
   * 
   * @param state: the current state
   * @return True or False
   */
  public boolean isApplicable(Map<Variable, Object> state);

  /**
   * Method that applies an action/effect on a given state
   * 
   * @param state : the current state
   * @return new state
   */
  public Map<Variable, Object> successor(Map<Variable, Object> state);

  /**
   * Method that calculates the cost of an action
   * 
   * @return cost
   */
  public int getCost();
}
