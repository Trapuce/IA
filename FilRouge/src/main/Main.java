package main;

import java.util.HashMap;
import java.util.Map;

import blockworld.BWConstraints;
import blockworld.BWRegularyConstraints;
import blockworld.BWVariables;
import representation.constraint.Constraint;
import representation.variable.Variable;

public class Main {

  public static void main(String[] args) {

    BWConstraints bwC = new BWRegularyConstraints(4, 2);
    BWVariables bwV = bwC.getBWvariables();
    System.out.println("***************************  Test Regulary World ************************");
    Map<Variable, Object> state = new HashMap<>();
    Map<Variable, Object> state2 = new HashMap<>();
    int[][] ls = {
        { 0, 2 },
        { 1, 3 }
    };
    int[][] ls2 = {
        { 3, 2, 1, 0 },
        {}
    };
    state = bwV.getState(ls);
    state2 = bwV.getState(ls2);

    boolean allSatisfied = true;
    for (Constraint constraint : bwC.getConstraints()) {
      if (!constraint.isSatisfiedBy(state) || !constraint.isSatisfiedBy(state2)) {
        allSatisfied = false;
      }
    }
    System.out.println(allSatisfied ? "Constraints are satisfied" : "Constraints are not satisfied");

  }
}
