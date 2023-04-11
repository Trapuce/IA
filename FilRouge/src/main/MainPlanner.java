package main;

import java.util.List;
import java.util.Map;
import java.util.Set;

import blockworld.BWActions;
import blockworld.BWVariables;
import planning.AStarPlanner;
import planning.Action;
import planning.BFSPlanner;
import planning.BasicGoal;
import planning.DFSPlanner;
import planning.DijkstraPlanner;
import planning.Goal;
import planning.HeuristicNumberOfBlocksOnWrongPosition;
import planning.HeuristicNumberOfBottomBlocksOnWrongPosition;
import planning.HeuristicReelNumberOfBlocksOnWrongPosition;
import representation.variable.Variable;
import view.StateView;

public class MainPlanner {

  public static void main(String[] args) {

    long T = 1_000_000_000;
    BWActions bwA = new BWActions(5, 3);
    BWVariables bwV = bwA.getBWvariables();
    Set<Action> actions = bwA.getAllActions();

    // Initial State
    int[][] ls = {
        { 0, 2, 4 },
        {},
        { 1, 3 }
    };
    Map<Variable, Object> state = bwV.getState(ls);

    // Goal State
    int[][] ls2 = {
        { 4, 2, 0 },
        { 3, 1 },
        {}
    };
    Map<Variable, Object> goalState = bwV.getState(ls2);

    // Instantiation of different planners
    Goal goal = new BasicGoal(goalState);
    DFSPlanner dfs = new DFSPlanner(state, actions, goal);
    BFSPlanner bfs = new BFSPlanner(state, actions, goal);
    DijkstraPlanner djk = new DijkstraPlanner(state, actions, goal);
    HeuristicNumberOfBlocksOnWrongPosition hr = new HeuristicNumberOfBlocksOnWrongPosition(goalState);
    HeuristicReelNumberOfBlocksOnWrongPosition hr2 = new HeuristicReelNumberOfBlocksOnWrongPosition(goalState);
    HeuristicNumberOfBottomBlocksOnWrongPosition hr3 = new HeuristicNumberOfBottomBlocksOnWrongPosition(goalState);
    AStarPlanner astar = new AStarPlanner(state, actions, goal, hr);
    AStarPlanner astar2 = new AStarPlanner(state, actions, goal, hr2);
    AStarPlanner astar3 = new AStarPlanner(state, actions, goal, hr3);

    // Setting timers and launching planners
    long t1 = System.nanoTime();
    List<Action> dfsplan = dfs.plan();
    long t2 = System.nanoTime();
    List<Action> bfsplan = bfs.plan();
    long t3 = System.nanoTime();
    List<Action> djkplan = djk.plan();
    long t4 = System.nanoTime();
    List<Action> astarplan = astar.plan();
    long t5 = System.nanoTime();
    List<Action> astarplan2 = astar2.plan();
    long t6 = System.nanoTime();
    List<Action> astarplan3 = astar3.plan();
    long t7 = System.nanoTime();
    System.out.println("**********************  Planner TEST  ****************************");
    System.out.println("Number of Visited nodes for DFS: " + dfs.getNumberOfVisistedStates() + ", Lenght of plan: "
        + dfsplan.size() + ", Computing Time: " + (float) (t2 - t1) / T + " Seconds");
    System.out.println();
    System.out.println("Number of Visited nodes for BFS: " + bfs.getNumberOfVisistedStates() + ", Lenght of plan: "
        + bfsplan.size() + ", Computing Time: " + (float) (t3 - t2) / T + " Seconds");
    System.out.println();
    System.out.println("Number of Visited nodes for Djikstra: " + djk.getNumberOfVisistedStates() + ", Lenght of plan: "
        + djkplan.size() + ", Computing Time: " + (float) (t4 - t3) / T + " Seconds");
    System.out.println();
    System.out.println("*****************************************************************");
    System.out.println("ASTAR with heuristic computing the number of blocks on wrong positions");
    System.out
        .println("Number of Visited nodes: "
            + astar.getNumberOfVisistedStates() + ", Lenght of plan: "
            + astarplan.size() + ", Computing Time: " + (float) (t5 - t4) / T + " Seconds");
    System.out.println();
    System.out.println("*****************************************************************");
    System.out.println("ASTAR2 with heuristic computing the real number of blocks on wrong positions");
    System.out
        .println(
            "Number of Visited nodes: "
                + astar2.getNumberOfVisistedStates() + ", Lenght of plan: "
                + astarplan2.size() + ", Computing Time: " + (float) (t6 - t5) / T + " Seconds");
    System.out.println();
    System.out.println("*****************************************************************");
    System.out.println("ASTAR3 with heurisctic privileging placing the bottom blocks first");
    System.out
        .println("Number of Visited nodes for : "
            + astar3.getNumberOfVisistedStates() + ", Lenght of plan: "
            + astarplan3.size() + ", Computing Time: " + (float) (t7 - t6) / T + " Seconds");

    // Display of the plan
    StateView stateView = new StateView(bwV, state);
    stateView.displayPlan(bfsplan);

  }
}
