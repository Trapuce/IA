package csp;


import java.util.Map;
import java.util.Set;

import representation.constraint.Constraint;
import representation.variable.Variable;

/**
 * Abstract class implementing interface Solver
 * A solver is composed of a set of variables and a set of constraints
 */
public abstract class AbstractSolver implements Solver{

  protected Set<Variable> setOfVariables;
  protected Set<Constraint> setOfConstraints;

  /**
   * Constructor
   * @param setOfVariables
   * @param setOfConstraints
   */
  public AbstractSolver(Set<Variable> setOfVariables, Set<Constraint> setOfConstraints) {
    this.setOfVariables = setOfVariables;
    this.setOfConstraints = setOfConstraints;
  }

  /**
   * Method that verifie if a state satisfies all the constraints
   * @param partialState : the given partially instanciated state
   * @return True or False
   */
  public boolean isConsistent(Map<Variable, Object> partialState){

    for(Constraint constraint : setOfConstraints){
      Set<Variable> scope = constraint.getScope();
      if(partialState.keySet().containsAll(scope)){
        if(!constraint.isSatisfiedBy(partialState)){
          return false;
        }
      }
    }
    return true;
  }

}
