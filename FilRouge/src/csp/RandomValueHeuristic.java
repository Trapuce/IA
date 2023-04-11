package csp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import representation.variable.Variable;

/**
 * Class representing the implementation of an heuristic
 * Where we choose to pick the value in domain of varible randomly
 * Implements ValueHeuristic
 */
public class RandomValueHeuristic implements ValueHeuristic {

  private Random rand;

  /**
   * Constructor
   *
   * @param rand : random number generator
   */
  public RandomValueHeuristic(Random rand) {
    this.rand = rand;
  }

  @Override
  /**
   * @return a shuffled domain as list of the variables
   */
  public List<Object> ordering(Variable var, Set<Object> varDomain) {
    List<Object> shuffledList = new ArrayList<>(varDomain);
    Collections.shuffle(shuffledList, rand);
    return shuffledList;
  }

}
