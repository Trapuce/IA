package blockworld;

import java.util.Map;
import java.util.Set;

import representation.variable.Variable;

/**
 * Abstract class reprenting a block world
 * A block world is defined by its number of block and number of piles
 */
public abstract class BlockWorld {

  protected int numberOfBlocks;
  protected int numberOfPiles;
  protected BWVariables bw;

  /**
   * Constructor
   *
   * @param numberOfBlocks : the number of blocks
   * @param numberOfPiles  : the numer of piles
   */
  public BlockWorld(int numberOfBlocks, int numberOfPiles) {
    this.numberOfBlocks = numberOfBlocks;
    this.numberOfPiles = numberOfPiles;
    bw = new BWVariables(numberOfBlocks, numberOfPiles);
  }

  // Delegations methods
  public Set<Variable> getOnbVariables() {
    return bw.getOnbVariables();
  }

  public Set<Variable> getFixedbVariables() {
    return bw.getFixedbVariables();
  }

  public Set<Variable> getFreepVariables() {
    return bw.getFreepVariables();
  }

  public Set<Variable> getVariables() {
    return bw.getVariables();
  }

  public Map<Variable, Variable> getAssociations() {
    return bw.getAssociations();
  }

  public Map<Variable, Integer> getMapVar2Index() {
    return bw.getMapVar2Index();
  }

  public Set<Object> getSubDomain() {
    return bw.getSubDomain();
  }

  public Map<Integer, Variable> getMapIndex2Var() {
    return bw.getMapIndex2Var();
  }

  public Variable getVar(int index) {
    return bw.getVar(index);
  }

  public int getIndex(Variable var) {
    return bw.getIndex(var);
  }

  public Set<Object> getPileSubDomain() {
    return bw.getPileSubDomain();
  }

  /**
   * @return the number of blocks
   */
  public int getNumberOfBlocks() {
    return bw.getNumberOfBlocks();
  }

  /**
   * @return the number of piles
   */
  public int getNumberOfPiles() {
    return bw.getNumberOfPiles();
  }

  /**
   * Getter
   *
   * @return BWVariables
   */
  public BWVariables getBWvariables() {
    return this.bw;
  }

}
