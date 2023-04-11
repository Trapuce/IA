package planning;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import representation.variable.Variable;

/**
 * Class representing a planner that make
 * use of the Breath First Search algorithm in order
 * to get to the goal
 */
public class BFSPlanner implements Planner {

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
  public BFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
    this.initialState = initialState;
    this.actions = actions;
    this.goal = goal;
    this.numberOfVisistedStates = 0;
  }

  /**
   * Implementation of the DFS algorithm
   *
   * @param father map of <state, state>, key = actual state, value = state
   *               from wich the actual state have been generated
   * @param open   list of states to visit
   * @param plan   map of <state,action>, associate each state with an action
   * @param closed set of state tha have been visited during the search
   * @return a list of actions
   */
  public List<Action> BFS(Map<Map<Variable, Object>, Map<Variable, Object>> father,
      Map<Map<Variable, Object>, Action> plan, Set<Map<Variable, Object>> closed,
      LinkedList<Map<Variable, Object>> open) {

    closed.add(initialState);
    open.add(initialState);
    father.put(initialState, null);

    if (goal.isSatisfiedBy(initialState))
      return new LinkedList<>();
    while (!open.isEmpty()) {
      Map<Variable, Object> currentState = open.poll();
      closed.add(currentState);
      for (Action action : actions) {
        if (action.isApplicable(currentState)) {
          Map<Variable, Object> nextState = action.successor(currentState);
          if (!closed.contains(nextState) && !open.contains(nextState)) {
            father.put(nextState, currentState);
            plan.put(nextState, action);
            numberOfVisistedStates++;
            if (goal.isSatisfiedBy(nextState)) {
              return getBfsPlan(father, plan, nextState);
            } else {
              open.add(nextState);
            }
          }
        }
      }
    }

    return null;
  }

  /**
   * Method that reconstituate the path from start to goal
   *
   * @param father map of <state, state>
   * @param plan   map <state, action>
   * @param goal   final state
   * @return list of actions
   */
  public List<Action> getBfsPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father,
      Map<Map<Variable, Object>, Action> plan, Map<Variable, Object> goal) {
    LinkedList<Action> bfsPlan = new LinkedList<>();
    while (goal != null) {
      Action nextAction = plan.get(goal);
      if (nextAction == null)
        break;
      bfsPlan.addFirst(nextAction);
      goal = father.get(goal);
    }
    return bfsPlan;
  }

  // Takes a linkedlist as a parameter and returns a reversed linkedlist
  public List<Action> reverseLinkedList(List<Action> llist) {
    for (int i = 0; i < llist.size() / 2; i++) {
      Action temp = llist.get(i);
      llist.set(i, llist.get(llist.size() - i - 1));
      llist.set(llist.size() - i - 1, temp);
    }

    // Return the reversed arraylist
    return llist;
  }

  @Override
  /**
   * return bfs method
   */
  public List<Action> plan() {
    return BFS(new HashMap<>(), new HashMap<>(), new HashSet<>(), new LinkedList<>());
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
