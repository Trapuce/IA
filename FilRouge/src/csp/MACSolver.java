package csp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import representation.constraint.Constraint;
import representation.variable.Variable;

/**
 * Class representing the Maintaning Arc Consistency (MAC) algorithm
 * Extends AbstractSolver
 */

public class MACSolver extends AbstractSolver {

  private ArcConsistency arc;

  /**
   * Constructor
   *
   * @param setOfVariables   the set of variables
   * @param setOfConstraints the set of constraints
   */
  public MACSolver(Set<Variable> setOfVariables, Set<Constraint> setOfConstraints) {
    super(setOfVariables, setOfConstraints);
    arc = new ArcConsistency(setOfConstraints);
  }

  /**
   * The MAC algorithm
   *
   * @param partialState             the partially instantiated state
   * @param nonInstanciatedVariables : all the variables that have not been
   *                                 instantiated
   * @param mapVarDomain                : map<var, varDomain>, associates each var
   *                                 with its domain. Keeps tracks of the
   *                                 evolution
   *                                 of the varible's domain evolution
   * @return the solution state if all constraints are satisfied
   */
  public Map<Variable, Object> mac(Map<Variable, Object> partialState, LinkedList<Variable> nonInstanciatedVariables,
      Map<Variable, Set<Object>> mapVarDomain) {

    if (nonInstanciatedVariables.isEmpty())
      return partialState;

    if (!arc.ac1(mapVarDomain))
      return null;

    Variable var = nonInstanciatedVariables.poll();
    for (Object value : mapVarDomain.get(var)) {
      Map<Variable, Object> state = new HashMap<>(partialState);
      state.put(var, value);
      if (isConsistent(state)) {
        Map<Variable, Object> newState = mac(state, nonInstanciatedVariables, mapVarDomain);
        if (newState != null)
          return newState;
      }
    }
    nonInstanciatedVariables.add(var);
    return null;
  }

  /**
   * @return mac algorithm
   */
  @Override
  public Map<Variable, Object> solve() {
    Map<Variable, Set<Object>> mapVarDomain = new HashMap<>();
    for (Variable var : setOfVariables) {
      mapVarDomain.put(var, var.getDomain());
    }
    return mac(new HashMap<>(), new LinkedList<>(setOfVariables), mapVarDomain);
  }
}
