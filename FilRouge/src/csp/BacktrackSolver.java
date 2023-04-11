package csp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import representation.constraint.Constraint;
import representation.variable.Variable;

/**
 * Class implmenting the backtrack algorithm
 * Extends AbstractSolver
 */
public class BacktrackSolver extends AbstractSolver {

  /**
   * Constructor
   * @param setOfVariables : set of all the variables
   * @param setOfConstraints : set of all the constraints
   */
  public BacktrackSolver(Set<Variable> setOfVariables, Set<Constraint> setOfConstraints) {
    super(setOfVariables, setOfConstraints);
  }

  /**
   * BackTrack algorithm
   * @param state : the partially instantiated state
   * @param nonInstanciatedVariables : all variables that have not been instantiated
   * @return solution state of the CPS if it exists
   */
  private Map<Variable, Object> backtrack(Map<Variable, Object> state, LinkedList<Variable> nonInstanciatedVariables) {

    if (nonInstanciatedVariables.isEmpty()) {
      return state;
    }
    Variable var = nonInstanciatedVariables.poll();
    for (Object value : var.getDomain()) {
      Map<Variable, Object> newState = new HashMap<>(state);
      newState.put(var, value);
      if (isConsistent(newState)) {
        Map<Variable, Object> resultState = backtrack(newState, nonInstanciatedVariables);
        if (resultState != null)
          return resultState;
      }
    }
    nonInstanciatedVariables.add(var);
    return null;
  }

  @Override
  public Map<Variable, Object> solve() {
    Map<Variable, Object> state = new HashMap<>();
    return backtrack(state, new LinkedList<>(super.setOfVariables));
  }

}
