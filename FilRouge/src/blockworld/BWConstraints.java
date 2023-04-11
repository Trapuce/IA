package blockworld;

import java.util.HashSet;
import java.util.Set;

import representation.constraint.Constraint;
import representation.constraint.DifferenceConstraint;
import representation.constraint.Implication;
import representation.variable.Variable;

/**
 * Class instantiating all the constraints
 * of the blocks world
 * The world is represented by a certain number
 * of blocks and a number of piles
 * Extends BlockWorld
 */
public class BWConstraints extends BlockWorld {

  protected Set<Constraint> constraints;

  /**
   * Constructor
   *
   * @param numberOfBlocks the number of blocks
   * @param numberOfPiles  the number of piles
   */
  public BWConstraints(int numberOfBlocks, int numberOfPiles) {
    super(numberOfBlocks, numberOfPiles);
    this.constraints = new HashSet<>();
    setDifferenceConstraints();
    setImplicationConstraints();
  }

  /**
   * @return all the constraints for the given configuration
   */
  public Set<Constraint> getConstraints() {
    return this.constraints;
  }

  /**
   * Method instanciating all the constraints of type difference
   */
  protected void setDifferenceConstraints() {
    Set<Variable> variables = bw.getOnbVariables();
    for (Variable varOne : variables) {
      for (Variable varTwo : variables) {
        if (!varOne.equals(varTwo)) {
          Constraint differenceConstraint = new DifferenceConstraint(varOne, varTwo);
          constraints.add(differenceConstraint);
        }
      }
    }
  }

  /**
   * Method instanciating all the constraints of type implication
   */
  protected void setImplicationConstraints() {
    for (Variable onB : bw.getOnbVariables()) {
      int i = getIndex(onB);
      for (Variable fixedB : bw.getFixedbVariables()) {
        int j = getIndex(fixedB);
        if (i == j)
          continue;
        Set<Object> subDomainOfOnB = new HashSet<>();
        Set<Object> subDomainOfFixedB = new HashSet<>();
        subDomainOfOnB.add(j);
        subDomainOfFixedB.add(true);
        Constraint implactionConstraint = new Implication(onB, subDomainOfOnB, fixedB, subDomainOfFixedB);
        constraints.add(implactionConstraint);
      }
      for (Variable freeP : bw.getFreepVariables()) {
        int k = getIndex(freeP);
        Set<Object> subDomainOfOnB = new HashSet<>();
        Set<Object> subDomainOfFreeP = new HashSet<>();
        subDomainOfOnB.add(k);
        subDomainOfFreeP.add(false);
        Constraint implactionConstraint = new Implication(onB, subDomainOfOnB, freeP, subDomainOfFreeP);
        constraints.add(implactionConstraint);
      }
    }
  }

  @Override
  public String toString() {
    return "{" +
        " numberOfBlocks='" + getNumberOfBlocks() + "'" +
        ", numberOfPiles='" + getNumberOfPiles() + "'" +
        ", constraints='" + getConstraints() + "'" +
        "}";
  }

}