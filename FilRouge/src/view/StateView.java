package view;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import blockworld.BWVariables;
import bwmodel.BWState;
import bwmodel.BWStateBuilder;
import bwui.BWComponent;
import bwui.BWIntegerGUI;
import planning.Action;
import representation.variable.Variable;

/**
 * Class for realising the view representing
 * the state of the block world.
 * Make use of library blocksworld
 */
public class StateView {

  private BWVariables bwV;
  private Map<Variable, Object> state;
  private String title;

  /**
   * Constructor
   * 
   * @param bwV   : reference to block world variables
   * @param state : the actual state
   * @param title : frame title
   */
  public StateView(BWVariables bwV, Map<Variable, Object> state, String title) {
    this.bwV = bwV;
    this.state = state;
    this.title = title;
  }

  /**
   * Second constructor
   * 
   * @param bwV   : reference to block world variables
   * @param state : the actual state
   */
  public StateView(BWVariables bwV, Map<Variable, Object> state) {
    this(bwV, state, "State View");
  }

  public BWState<Integer> makeBwState() {
    int numberOfOnb = bwV.getOnbVariables().size();
    BWStateBuilder<Integer> builder = BWStateBuilder.makeBuilder(numberOfOnb);
    for (Variable var : bwV.getOnbVariables()) {
      int index = bwV.getMapVar2Index().get(var);
      int under = (int) state.get(var);
      if (under >= 0)
        builder.setOn(index, under);
    }
    BWState<Integer> bwState = builder.getState();
    return bwState;
  }

  /**
   * Method displaying a given state
   */
  public void display(int x, int y) {
    int numberOfOnb = bwV.getOnbVariables().size();
    BWState<Integer> bwState = makeBwState();
    BWIntegerGUI gui = new BWIntegerGUI(numberOfOnb);
    JFrame frame = new JFrame(title);
    frame.setLocation(x, y);
    frame.setPreferredSize(new Dimension(500, 500));
    frame.add(gui.getComponent(bwState));
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Method the displaying the execution of a plan
   * 
   * @param actions : the plan
   */
  public void displayPlan(List<Action> actions) {
    int numberOfOnb = bwV.getOnbVariables().size();
    BWIntegerGUI gui = new BWIntegerGUI(numberOfOnb);
    JFrame frame = new JFrame("Plan View");
    frame.setPreferredSize(new Dimension(500, 500));
    BWState<Integer> bwState = makeBwState();
    BWComponent<Integer> component = gui.getComponent(bwState);
    frame.add(component);
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    for (Action action : actions) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
      state = action.successor(state);
      component.setState(makeBwState());
    }
    System.out.println("Simulation_of_plan: done");
  }

}
