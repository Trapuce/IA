package planning;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import representation.variable.Variable;

/**
 * Class representing an heuristic estimating the number of blocks wrong placed
 * If a block is on top of correct block but not in correct pile it is taken
 * into account
 * Implements Heuristic
 */
public class HeuristicReelNumberOfBlocksOnWrongPosition implements Heuristic {

  private Map<Variable, Object> finalState;
  private Set<Variable> onbVariables = new HashSet<>();
  private Set<Variable> added;
  private Map<Variable, Variable> mapBlockOnTop;
  private final int WEIGHT = 10;

  /**
   * Constructor
   *
   * @param finalState : the final state
   */
  public HeuristicReelNumberOfBlocksOnWrongPosition(Map<Variable, Object> finalState) {
    this.finalState = finalState;
    getOnbVariables();
  }

  @Override
  public float estimate(Map<Variable, Object> state) {
    float estimation = 0;
    added = new HashSet<>();
    mapBlockOnTop = new HashMap<>();
    blockMap(state);
    for (Variable onb : onbVariables) {
      boolean isWrongPlaced = finalState.get(onb) != state.get(onb) && (int) state.get(onb) < 0;
      float n = numberOfBlocksOnTopOfBlock1(state, onb);
      estimation += isWrongPlaced ? n * WEIGHT : n;
    }
    return estimation;
  }

  /**
   * Method returning the on variables
   *
   * @return void
   */
  private void getOnbVariables() {
    for (Variable var : finalState.keySet()) {
      if (var.getName().contains("on")) {
        onbVariables.add(var);
      }
    }
  }

  /**
   * Function computing the number of blocks that are on
   * top of a given block
   *
   * @param state : the actual state
   * @param onb   : the actual block
   * @return the count of block on top of current block
   */
  private float numberOfBlocksOnTopOfBlock1(Map<Variable, Object> state, Variable onb) {
    // if we have already counted the block we can just skip it
    if (added.contains(onb))
      return 0;
    // set count to one to take into account current block
    float count = 1;
    added.add(onb);
    Variable top = mapBlockOnTop.get(onb);
    while (top != null && !added.contains(top)) {
      count++;
      added.add(top);
      onb = top;
      top = mapBlockOnTop.get(onb);
    }
    return count;
  }

  /**
   * Helper Function mapping each block to the block that is directely on top
   *
   * @param state map: blockUnder -> blockOnTop
   */
  private void blockMap(Map<Variable, Object> state) {
    for (Variable onb : onbVariables) {
      mapBlockOnTop.put(onb, null);
    }
    for (Variable top : onbVariables) {
      for (Variable under : onbVariables) {
        int i = under.getIndex();
        if ((int) state.get(top) == i) {
          mapBlockOnTop.put(under, top);
          break;
        }
      }
    }
  }

}
