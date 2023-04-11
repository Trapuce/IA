package csp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import representation.constraint.Constraint;
import representation.variable.Variable;

/**
 * Class representing the implementation of Arc Consistency(AC2) algorithm
 * Ensures that the CPS is arc consistent
 */
public class ArcConsistency {

  Set<Constraint> unaryConstraints;
  Set<Constraint> binaryConstraints;

  /**
   * Constructor
   *
   * @param setOfConstraints the set of all the constraints
   * @throws IllegalArgumentException if the constraints are neither unary nor
   *                                  binary
   */
  public ArcConsistency(Set<Constraint> setOfConstraints) throws IllegalArgumentException {
    init(setOfConstraints);
  }

  /**
   * init method: initialise sets of unray constraints, binary constraints
   * appearing in a unary constraint and binary constraint
   *
   * @param constraints : set of all the constraints
   * @throws IllegalArgumentException
   */
  private void init(Set<Constraint> constraints) {
    unaryConstraints = new HashSet<>();
    binaryConstraints = new HashSet<>();
    for (Constraint constraint : constraints) {
      Set<Variable> scope = constraint.getScope();
      if (scope.size() == 1) {
        unaryConstraints.add(constraint);
      } else if (scope.size() == 2) {
        binaryConstraints.add(constraint);
      } else {
        throw new IllegalArgumentException("Ni␣unaire␣ni␣binaire");
      }
    }
  }

  /**
   * The Node Consistency(NC) algorithm
   *
   * @param mapVarDomain : map<var, varDomain>, keep track of the evolution of the
   *                  domain of each variable
   * @return a boolean and remove all the variables values that are not consistent
   */
  public boolean enforceNodeConsistency(Map<Variable, Set<Object>> mapVarDomain) {
    for (Variable var : mapVarDomain.keySet()) {
      Set<Object> varDomain = mapVarDomain.get(var);
      Set<Object> valuesToRemove = new HashSet<>();
      for (Object val : varDomain) {

        for (Constraint constraint : unaryConstraints) {
          if (constraint.getScope().contains(var)) {
            Map<Variable, Object> state = new HashMap<>();
            state.put(var, val);
            if (!constraint.isSatisfiedBy(state)) {
              valuesToRemove.add(val);
            }
          }
        }
      }
      varDomain.removeAll(valuesToRemove);
    }

    for (Variable var : mapVarDomain.keySet()) {
      if (mapVarDomain.get(var).isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Implementation of Revise algorithm
   *
   * @param varOne    the first variable
   * @param domainOne the domain of the first variable
   * @param varTwo    the second variable
   * @param domainTwo the domain of the second variable
   * @return boolean indicating if value have been removed from the domain of the
   *         first variable and remove the non viable values from domainOne
   */
  public boolean revise(Variable varOne, Set<Object> domainOne, Variable varTwo, Set<Object> domainTwo) {
    boolean deleted = false;
    Set<Object> valuesToRemove = new HashSet<>();
    for (Object valOne : domainOne) {
      boolean viable = false;
      for (Object valTwo : domainTwo) {
        boolean allSatisfied = true;
        for (Constraint constraint : binaryConstraints) {
          if (constraint.getScope().contains(varOne) && constraint.getScope().contains(varTwo)) {
            Map<Variable, Object> state = new HashMap<>();
            state.put(varOne, valOne);
            state.put(varTwo, valTwo);
            if (!constraint.isSatisfiedBy(state)) {
              allSatisfied = false;
              break;
            }
          }
        }
        if (allSatisfied) {
          viable = true;
          break;
        }
      }
      if (!viable) {
        valuesToRemove.add(valOne);
        deleted = true;
      }
    }
    domainOne.removeAll(valuesToRemove);
    return deleted;
  }

  /**
   * Implementation of Arc Consistency(AC) algorithm
   *
   * @param mapVarDomain : map<var, varDomain>
   * @return boolean indicating if CPS is arc coherent
   */
  public boolean ac1(Map<Variable, Set<Object>> mapVarDomain) {
    if (!enforceNodeConsistency(mapVarDomain)) {
      return false;
    }
    boolean change = false;
    do {
      change = false;
      for (Variable varOne : mapVarDomain.keySet()) {
        for (Variable varTwo : mapVarDomain.keySet()) {
          if (!varOne.equals(varTwo)) {
            Set<Object> varOneDomain = new HashSet<>(mapVarDomain.get(varOne));
            if (revise(varOne, varOneDomain, varTwo, mapVarDomain.get(varTwo))) {
              mapVarDomain.put(varOne, varOneDomain);
              change = true;
            }
          }
        }
      }
    } while (change);

    for (Variable var : mapVarDomain.keySet()) {
      if (mapVarDomain.get(var).isEmpty())
        return false;
    }
    return true;
  }

}
