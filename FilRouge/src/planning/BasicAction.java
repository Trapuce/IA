package planning;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import representation.variable.Variable;

/**
 * Class representing a basic action
 * A basic action must satisfy a precondition,
 * have an effect and return a cost
 * Implements Action interface
 */
public class BasicAction implements Action {

  private Map<Variable, Object> precondition;
  private Map<Variable, Object> effect;
  private int cost;

  /**
   * Constructor
   *
   * @param precondition map containing values the must be satisfied by thee
   *                     variables
   * @param effect       the result of the action
   * @param cost         : the cost of the action
   */
  public BasicAction(Map<Variable, Object> precondition, Map<Variable, Object> effect, int cost) {
    this.precondition = precondition;
    this.effect = effect;
    this.cost = cost;
  }

  @Override
  public boolean isApplicable(Map<Variable, Object> state) {
    // return false if state does not contains variable
    // value of the variable in the precondition is different

    for (Map.Entry<Variable, Object> entry : precondition.entrySet()) {
      Variable var = entry.getKey();
      Object val = entry.getValue();
      if (!state.containsKey(var) || !val.equals(state.get(var))) {
        return false;
      }
    }

    return true;
  }

  @Override
  public Map<Variable, Object> successor(Map<Variable, Object> state) {
    if (!isApplicable(state))
      return null;

    // Updates every pair in the state that are present in effect
    Map<Variable, Object> nextState = new HashMap<Variable, Object>(state);
    for (Map.Entry<Variable, Object> entry : effect.entrySet()) {
      Variable var = entry.getKey();
      Object val = entry.getValue();
      nextState.put(var, val);
    }
    return nextState;
  }

  @Override
  public int getCost() {
    return cost;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof BasicAction)) {
      return false;
    }
    BasicAction basicAction = (BasicAction) o;
    return Objects.equals(precondition, basicAction.precondition) && Objects.equals(effect, basicAction.effect)
        && cost == basicAction.cost;
  }

  @Override
  public int hashCode() {
    return Objects.hash(precondition, effect, cost);
  }

  // Getters
  public Map<Variable, Object> getPrecondition() {
    return this.precondition;
  }

  public Map<Variable, Object> getEffect() {
    return this.effect;
  }

  @Override
  public String toString() {
    return "BasicAction [precondition=" + precondition + ", effect=" + effect + ", cost=" + cost + "]\n";
  }

}
