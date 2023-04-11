package planning;

import java.util.Map;

import representation.variable.Variable;

/**
 * Interface Goal
 * The goal is the sate that satisfies all
 * the constraints and instanciate all the vavraibles
 */

public interface Goal {

  public boolean isSatisfiedBy(Map<Variable, Object> state);
}
