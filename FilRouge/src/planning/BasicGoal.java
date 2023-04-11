package planning;

import java.util.Map;

import representation.variable.Variable;

/**
 * Class representing a basic goal
 * A basic goal is partially instanciated
 * goal state
 * Implement Goal interface
 */
public class BasicGoal implements Goal {

  private Map<Variable, Object> partialState;

  public BasicGoal(Map<Variable, Object> partialState) {
    this.partialState = partialState;
  }

  @Override
  public boolean isSatisfiedBy(Map<Variable, Object> state) {
    // return false if state does not contains variable or value of the key
    // in the partialState is different

    for (Map.Entry<Variable, Object> entry : partialState.entrySet()) {
      Variable var = entry.getKey();
      Object val = entry.getValue();
      if (!state.containsKey(var) || !val.equals(state.get(var))) {
        return false;
      }
    }

    return true;
  }

}
