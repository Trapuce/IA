package planning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import representation.variable.Variable;

/**
 * Class representing a planner that make
 * use of the A-Star algorithm in order
 * to get to the goal
 */
public class AStarPlanner implements Planner {

  private Map<Variable, Object> initialState;
  private Set<Action> actions;
  private Goal goal;
  private Heuristic heuristic;
  private BFSPlanner bfs = new BFSPlanner(initialState, actions, goal);
  private int numberOfVisistedStates;

  /**
   * Constructor
   * @param initialState : starting state
   * @param actions : set of all possible actions
   * @param goal : final state
   * @param heuristic : heuristic/estimation of coast
   */
  public AStarPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal, Heuristic heuristic) {
    this.initialState = initialState;
    this.actions = actions;
    this.goal = goal;
    this.heuristic = heuristic;
    this.numberOfVisistedStates = 0;
  }

  /**
   * Implementation of aStar algorithm
   * @param father : map<state,state>, key=actual state, value=next state
   * @param plan : map<state, action>, affect each state the action that gets us to the next state
   * @param distance map<State, Integer> affect each state with the distance of actual the state from the starting state
   * @param value map<State, Float>, affect each state with the distance of actual the state from the starting state plus the heuristic
   * @return a list of actions
   */
  public List<Action> aStar(Map<Map<Variable, Object>, Map<Variable, Object>> father,
      Map<Map<Variable, Object>, Action> plan, Map<Map<Variable, Object>, Integer> distance,
      Map<Map<Variable, Object>, Float> value) {

    PriorityQueue<Map<Variable, Object>> open = new PriorityQueue<>(1, new StateComparator(value));
    father.put(initialState, null);
    distance.put(initialState, 0);
    value.put(initialState, heuristic.estimate(initialState));
    open.add(initialState);
    while (!open.isEmpty()) {
      Map<Variable, Object> currentState = open.poll();
      if (goal.isSatisfiedBy(currentState)) {
        return bfs.getBfsPlan(father, plan, currentState);
      }

      for (Action action : actions) {
        if (action.isApplicable(currentState)) {
          Map<Variable, Object> nextState = action.successor(currentState);
          if (!distance.containsKey(nextState)) {
            distance.put(nextState, Integer.MAX_VALUE);
          }
          if (distance.get(nextState) > distance.get(currentState) + action.getCost()) {
            distance.put(nextState, distance.get(currentState) + action.getCost());
            value.put(nextState, distance.get(nextState) + heuristic.estimate(nextState));
            father.put(nextState, currentState);
            plan.put(nextState, action);
            open.add(nextState);
            numberOfVisistedStates++;
          }
        }
      }
    }
    return null;
  }

  @Override
  /**
   * return aStar method
   */
  public List<Action> plan() {
    return aStar(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());
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

  public int getNumberOfVisistedStates(){
    return numberOfVisistedStates;
  }
}
