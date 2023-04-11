package planning;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import representation.variable.Variable;

/**
 * Class representing a planner that make
 * use of the dijkstra algorithm in order
 * to get to the goal
 */

public class DijkstraPlanner implements Planner {

  private Map<Variable, Object> initialState;
  private Set<Action> actions;
  private Goal goal;
  private int numberOfVisistedStates;

  /**
   * Constructor
   *
   * @param initialState
   * @param actions
   * @param goal
   */
  public DijkstraPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
    this.initialState = initialState;
    this.actions = actions;
    this.goal = goal;
    this.numberOfVisistedStates = 0;
  }

  /**
   * Implementation of the dijkstra algorithm
   *
   * @param father   : map<state,state>, key=actual state, value=next state
   * @param plan     map<state, action>, affect each state the action that gets us
   *                 to the next state
   * @param distance : map<State, Float> affect each state with the distance of
   *                 actual the state from the starting state
   * @return a list of actions
   */
  public List<Action> dijkstra(Map<Map<Variable, Object>, Map<Variable, Object>> father,
      Map<Map<Variable, Object>, Action> plan, Map<Map<Variable, Object>, Float> distance) {

    PriorityQueue<Map<Variable, Object>> goals = new PriorityQueue<>(1, new StateComparator(distance));
    PriorityQueue<Map<Variable, Object>> open = new PriorityQueue<>(1, new StateComparator(distance));
    father.put(initialState, null);
    distance.put(initialState, (float) 0);
    open.add(initialState);
    while (!open.isEmpty()) {
      Map<Variable, Object> currentState = open.poll();
      if (goal.isSatisfiedBy(currentState)) {
        goals.add(currentState);
      }

      for (Action action : actions) {
        if (action.isApplicable(currentState)) {
          Map<Variable, Object> nextState = action.successor(currentState);
          if (!distance.containsKey(nextState)) {
            distance.put(nextState, Float.MAX_VALUE);
          }
          if (distance.get(nextState) > distance.get(currentState) + action.getCost()) {
            float newDist = distance.get(currentState) + action.getCost();
            distance.put(nextState, newDist);
            father.put(nextState, currentState);
            plan.put(nextState, action);
            open.add(nextState);
            numberOfVisistedStates++;
          }
        }
      }
    }
    if (goals.isEmpty())
      return null;

    return getPlan(father, plan, goals, distance);
  }

  /**
   * Method that reconstituate the path from the start to the goal
   *
   * @param father   : map<state,state>, key=actual state, value=next state
   * @param plan     map<state, action>, affect each state the action that gets us
   *                 to the next state
   * @param goals    : list of goals
   * @param distance : map<State, Integer> affect each state with the distance of
   *                 actual the state from the starting state
   * @return a list of actions
   */
  public List<Action> getPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father,
      Map<Map<Variable, Object>, Action> plan, Queue<Map<Variable, Object>> goals,
      Map<Map<Variable, Object>, Float> distance) {

    LinkedList<Action> dijkstraPlan = new LinkedList<>();
    Map<Variable, Object> goal = goals.poll();
    while (goal != null) {
      Action nextAction = plan.get(goal);
      if (nextAction == null)
        break;
      dijkstraPlan.addFirst(nextAction);
      goal = father.get(goal);
    }
    return dijkstraPlan;
  }

  @Override
  /**
   * @return djkstra algorithm
   */
  public List<Action> plan() {
    return dijkstra(new HashMap<>(), new HashMap<>(), new HashMap<>());
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
