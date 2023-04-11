package csp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import representation.constraint.Constraint;
import representation.variable.Variable;

/**
 * Class representing the Maintaning Arc Consistency (MAC) algorithm
 * But uses heuristic of variables and values to make decisions
 * Extends AbstractSolver
 */
public class HeuristicMACSolver extends AbstractSolver {

  private VariableHeuristic variableHeuristic;
  private ValueHeuristic valueHeuristic;
  private ArcConsistency arc;

  /**
   * Constructor
   *
   * @param setOfVariables    the set of all variables
   * @param setOfConstraints  the set of all constraints
   * @param variableHeuristic heuristic on the variables
   * @param valueHeuristic    heuristic on the value
   */
  public HeuristicMACSolver(Set<Variable> setOfVariables, Set<Constraint> setOfConstraints,
      VariableHeuristic variableHeuristic, ValueHeuristic valueHeuristic) {
    super(setOfVariables, setOfConstraints);
    this.variableHeuristic = variableHeuristic;
    this.valueHeuristic = valueHeuristic;
    arc = new ArcConsistency(setOfConstraints);
  }

  /**
   * The MAC algorithm
   *
   * @param partialState             the partially instantiated state
   * @param nonInstanciatedVariables : all the variables that have not been
   *                                 instantiated
   * @param mapVarDomain             : map<var, varDomain>, associates each var
   *                                 with it domain. Keeps tracks of the evolution
   *                                 of the varible's domain evolution
   * @return the solution state if all constraints are satisfied
   */
  private Map<Variable, Object> mac(Map<Variable, Object> partialState, Set<Variable> nonInstanciatedVariables,
      Map<Variable, Set<Object>> mapVarDomain) {

    if (nonInstanciatedVariables.isEmpty())
      return partialState;

    if (!arc.ac1(mapVarDomain))
      return null;

    Variable var = variableHeuristic.best(nonInstanciatedVariables, mapVarDomain);
    List<Object> domainOrder = valueHeuristic.ordering(var, mapVarDomain.get(var));
    for (Object value : domainOrder) {
      Map<Variable, Object> state = new HashMap<>(partialState);
      state.put(var, value);
      Set<Variable> newVariables = new HashSet<>(nonInstanciatedVariables);
      newVariables.remove(var);
      if (isConsistent(state)) {
        Map<Variable, Object> newState = mac(state, newVariables, mapVarDomain);
        if (newState != null)
          return newState;
      }
    }
    return null;
  }

  @Override
  /**
   * @return mac algorithm
   */
  public Map<Variable, Object> solve() {
    Map<Variable, Set<Object>> mapVarDomain = new HashMap<>();
    for (Variable var : setOfVariables) {
      mapVarDomain.put(var, var.getDomain());
    }
    return mac(new HashMap<>(), setOfVariables, mapVarDomain);
  }

  /**
   * getter
   *
   * @return variableHeuristic
   */
  public VariableHeuristic getVariableHeuristic() {
    return variableHeuristic;
  }

  /**
   * getter
   *
   * @return valueHeuristic
   */
  public ValueHeuristic getValueHeuristic() {
    return valueHeuristic;
  }

  public ArcConsistency getArc() {
    return arc;
  }

}
