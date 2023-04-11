package main;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import blockworld.BWConstraints;
import blockworld.BWRegularyConstraints;
import csp.BacktrackSolver;
import csp.DomainSizeVariableHeuristic;
import csp.HeuristicMACSolver;
import csp.MACSolver;
import csp.RandomValueHeuristic;
import csp.Solver;
import csp.ValueHeuristic;
import csp.VariableHeuristic;
import representation.constraint.Constraint;
import representation.variable.Variable;
import view.StateView;

public class MainCSP {

  public static void main(String[] args) {

    long T = 1_000_000_000;
    BWConstraints bwC = new BWRegularyConstraints(11, 5);
    Set<Constraint> constraints = bwC.getConstraints();
    Set<Variable> variables = bwC.getBWvariables().getVariables();
    // Instantiate solvers
    Solver solver = new BacktrackSolver(variables, constraints);
    Solver solver2 = new MACSolver(variables, constraints);
    VariableHeuristic heuristicDomain = new DomainSizeVariableHeuristic(true);
    ValueHeuristic heuristicValue = new RandomValueHeuristic(new Random());
    Solver solver3 = new HeuristicMACSolver(variables, constraints, heuristicDomain, heuristicValue);
    // Set timers
    long t1 = System.nanoTime();
    Map<Variable, Object> finalState = solver.solve();
    long t2 = System.nanoTime();
    Map<Variable, Object> finalState2 = solver2.solve();
    long t3 = System.nanoTime();
    Map<Variable, Object> finalState3 = solver3.solve();
    long t4 = System.nanoTime();
    // Print results
    System.out.println("Computing Time of Backtrack solver: " + (float) (t2 - t1) / T + " seconds");
    System.out.println("Computing Time of MAC solver: " + (float) (t3 - t2) / T + " seconds");
    System.out.println("Computing Time of HeuristicMAC solver: " + (float) (t4 - t3) / T + " seconds");
    // Display Views
    StateView stateView = new StateView(bwC.getBWvariables(), finalState, "Backtrack");
    StateView stateView2 = new StateView(bwC.getBWvariables(), finalState2, "MAC");
    StateView stateView3 = new StateView(bwC.getBWvariables(), finalState3, "HeuristicMAC");
    stateView.display(0, 0);
    stateView2.display(600, 0);
    stateView3.display(1200, 0);
  }
}
