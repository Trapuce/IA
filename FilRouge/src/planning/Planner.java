package planning;

import java.util.List;
import java.util.Map;
import java.util.Set;

import representation.variable.Variable;

/**
 * Planner interface
 * Given a problem the planner will
 * return to set the sequence of actions needed
 * in order to get to the goal
 */

public interface Planner {

  /**
   * Method that return the list of actions needed to get to the goal
   *
   * @return a list of actions
   */
  public List<Action> plan();

  /**
   * Getter
   *
   * @return the initial state
   */
  public Map<Variable, Object> getInitialState();

  /**
   * Getter
   *
   * @return the set of actions made
   */
  public Set<Action> getActions();

  /**
   * Getter
   *
   * @return the Goal
   */
  public Goal getGoal();
}
