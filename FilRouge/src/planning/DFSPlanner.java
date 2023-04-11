package planning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import representation.variable.Variable;

/**
 * Class representing a planner that make
 * use of the Depth First Search algorithm in order
 * to get to the goal
 */
public class DFSPlanner implements Planner {

  private Map<Variable, Object> initialState;
  private Set<Action> actions;
  private Goal goal;
  private int numberOfVisistedStates;

  /**
   * Constructor
   *
   * @param initialState the starting state
   * @param actions      set of all possibles actions
   * @param goal         the final state
   */
  public DFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
    this.initialState = initialState;
    this.actions = actions;
    this.goal = goal;
    this.numberOfVisistedStates = 0;
  }

  /**
   * Implementation of the DFS algorithm
   *
   * @param initialState starting state
   * @param plan         the list of actions to return in order to get to goal
   * @param visitedState set of state tha have been visited during the search
   * @return a list of actions
   */
  public List<Action> dfs(Map<Variable, Object> initialState, List<Action> plan,
      Set<Map<Variable, Object>> visitedState) {
    Map<Variable, Object> state = initialState;
    visitedState.add(state);
    if (goal.isSatisfiedBy(state))
      return plan;
    for (Action action : actions) {
      if (action.isApplicable(state)) {
        Map<Variable, Object> nextState = action.successor(state);
        if (!visitedState.contains(nextState)) {
          plan.add(action);
          visitedState.add(nextState);
          numberOfVisistedStates++;
          List<Action> subPlan = dfs(nextState, plan, visitedState);
          if (subPlan != null) {
            return subPlan;
          } else {
            plan.remove(plan.size() - 1);
          }
        }
      }
    }
    return null;
  }

  @Override
  /**
   * return dfs
   */
  public List<Action> plan() {
    return dfs(getInitialState(), new ArrayList<Action>(), new HashSet<>());
  }

  @Override
  public Map<Variable, Object> getInitialState() {
    return initialState;
  }

  @Override
  public Set<Action> getActions() {
    return actions;
  }

  @Override
  public Goal getGoal() {
    return goal;
  }

  public int getNumberOfVisistedStates() {
    return numberOfVisistedStates;
  }

}
