package blockworld;

import java.util.HashSet;
import java.util.Set;

import representation.constraint.Constraint;
import representation.constraint.Implication;
import representation.variable.Variable;

/**
 * Class instanciating constraint for a
 * regular configuration of the block world
 * Extends BWConstraints
 */

public class BWRegularyConstraints extends BWConstraints {

  // Constructor
  public BWRegularyConstraints(int numberOfBlocks, int numberOfPiles) {
    super(numberOfBlocks, numberOfPiles);
    setRegularyConstraints();
  }

  /**
   * Method instantiating constraints in order to achieve a
   * regular configuration
   */
  private void setRegularyConstraints() {

    for (Variable on_i : bw.getOnbVariables()) {
      for (Variable on_j : bw.getOnbVariables()) {
        int i = getIndex(on_i);
        int j = getIndex(on_j);
        int k = j - (i - j);
        if (i != j) {
          Set<Object> subDomain_i = new HashSet<>();
          subDomain_i.add(j);
          Set<Object> subDomain_j = new HashSet<>(bw.getPileSubDomain());
          if (k >= 0 && k < numberOfBlocks)
            subDomain_j.add(k);
          Constraint constraint = new Implication(on_i, subDomain_i, on_j, subDomain_j);
          constraints.add(constraint);
        }
      }
    }
  }
}
