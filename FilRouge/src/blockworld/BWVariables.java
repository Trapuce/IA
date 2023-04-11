package blockworld;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import representation.variable.BooleanVariable;
import representation.variable.Variable;

/**
 * Class instantiating all the variables
 * of the blocks world
 * The world is represented by a certain number
 * of blocks and a number of piles
 */

public class BWVariables {

  private int numberOfBlocks;
  private int numberOfPiles;
  // set containing all the variables
  private Set<Variable> variables = new HashSet<>();
  // set containing all the onb variables
  private Set<Variable> onbVariables = new HashSet<>();
  // set containing all the fixed variables
  private Set<Variable> fixedbVariables = new HashSet<>();
  // set containing all the freep variables
  private Set<Variable> freepVariables = new HashSet<>();
  // Map associating each free variable to its on variable (fixed_i -> on_i)
  private Map<Variable, Variable> associations = new HashMap<>();
  // Map associating each onb varible to its index (on_i -> i)
  private Map<Variable, Integer> mapVar2Index = new HashMap<>();
  // Map associating each index to its onb variable (i -> on-i)
  private Map<Integer, Variable> mapIndex2Var = new HashMap<>();
  // Map associating the name of each variable to the variable
  private Map<String, Variable> mapStr2Var = new HashMap<>();
  // Map containing values from 0 to numberOfBlock
  private Set<Object> subDomain = new HashSet<>();
  // Map containing values from -1 to -numberOfPile
  private Set<Object> pileSubDomain = new HashSet<>();

  /**
   * Constructor
   *
   * @param numberOfBlocks the number of blocks
   * @param numberOfPiles  the number of piles
   */
  public BWVariables(int numberOfBlocks, int numberOfPiles) {
    this.numberOfBlocks = numberOfBlocks;
    this.numberOfPiles = numberOfPiles;
    setVariables();
    setAssociations();
  }

  /**
   * Method that instanciate all the variables
   * for the given configuration
   */
  private void setVariables() {
    setOnbVariables();
    setFixedbVariables();
    setFreepVariables();
    variables.addAll(onbVariables);
    variables.addAll(freepVariables);
    variables.addAll(fixedbVariables);

  }

  /**
   * Methode that set all the onB variables
   *
   * @return void
   */
  private void setOnbVariables() {
    for (int i = 0; i < numberOfBlocks; i++) {
      Set<Object> domain = new HashSet<>();
      Variable onB = new Variable("on_" + Integer.toString(i), domain);
      onB.setDomain(domain, numberOfBlocks, numberOfPiles, i);
      onbVariables.add(onB);
      mapVar2Index.put(onB, i);
      mapIndex2Var.put(i, onB);
      subDomain.add(i);
      mapStr2Var.put(onB.getName(), onB);
    }
  }

  /**
   * Private Method that set all the fixed Variables
   *
   * @return void
   */
  private void setFixedbVariables() {
    for (int i = 0; i < numberOfBlocks; i++) {
      Variable fixedB = new BooleanVariable("fixed_" + Integer.toString(i));
      fixedbVariables.add(fixedB);
      mapVar2Index.put(fixedB, i);
      mapStr2Var.put(fixedB.getName(), fixedB);
    }
  }

  /**
   * Private Methode that set all the free Variables
   *
   * @return void
   */
  private void setFreepVariables() {
    for (int i = -numberOfPiles; i < 0; i++) {
      Variable freeP = new BooleanVariable("free_" + Integer.toString(i));
      freepVariables.add(freeP);
      mapVar2Index.put(freeP, i);
      pileSubDomain.add(i);
      mapStr2Var.put(freeP.getName(), freeP);
    }
  }

  /**
   * Method intantiating a state from a list
   * of list of int that represent a block world
   * configuration
   * 
   * @param state : list of list of int
   * @return state map<var, val>
   */
  public Map<Variable, Object> getState(int[][] state) {
    Map<Variable, Object> res = new HashMap<>();
    int nbPiles = state.length;
    for (int i = 0; i < nbPiles; i++) {
      int nbBlocks = state[i].length;
      String str = "free_" + Integer.toString(-i - 1);
      Variable free = mapStr2Var.get(str);
      if (nbBlocks == 0) {
        res.put(free, true);
      } else {
        res.put(free, false);
      }
      for (int j = 0; j < nbBlocks; j++) {
        String strOn = "on_" + Integer.toString(state[i][j]);
        String strfix = "fixed_" + Integer.toString(state[i][j]);
        Variable on = mapStr2Var.get(strOn);
        Variable fixed = mapStr2Var.get(strfix);
        if (j == 0) {
          res.put(on, -i - 1);
          res.put(fixed, true);
        }
        if (nbBlocks == 1) {
          res.put(on, -i - 1);
          res.put(fixed, false);
        }
        if (j > 0 && j == nbBlocks - 1) {
          res.put(on, state[i][j - 1]);
          res.put(fixed, false);
        }
        if (j > 0 && j < nbBlocks - 1) {
          res.put(on, state[i][j - 1]);
          res.put(fixed, true);
        }

      }
    }
    return res;
  }

  @Override
  public String toString() {
    return "{" +
        " numberOfBlocks='" + getNumberOfBlocks() + "'" +
        ", numberOfPiles='" + getNumberOfPiles() + "'" +
        ", variables='" + getVariables() + "'" +
        "}";
  }

  /**
   * @return the number of blocks
   */
  public int getNumberOfBlocks() {
    return this.numberOfBlocks;
  }

  /**
   * @return the number of piles
   */
  public int getNumberOfPiles() {
    return this.numberOfPiles;
  }

  /**
   * Getter
   *
   * @return a set containing all the onb variables
   */
  public Set<Variable> getOnbVariables() {
    return onbVariables;
  }

  /**
   * Getter
   *
   * @return a set containing all the fixedb variables
   */
  public Set<Variable> getFixedbVariables() {
    return fixedbVariables;
  }

  /**
   * Getter
   *
   * @return a set containing all the freep variables
   */
  public Set<Variable> getFreepVariables() {
    return freepVariables;
  }

  /**
   * @return all the variable for a specific configuration
   *         of the block world
   */
  public Set<Variable> getVariables() {
    return this.variables;
  }

  /**
   * Getter
   *
   * @return an association map
   */
  public Map<Variable, Variable> getAssociations() {
    return associations;
  }

  /**
   * Getter
   *
   * @return map associating onb to its index
   */
  public Map<Variable, Integer> getMapVar2Index() {
    return mapVar2Index;
  }

  private void setAssociations() {
    for (Variable fixedb : getFixedbVariables()) {
      for (Variable onb : getOnbVariables()) {
        int i = mapVar2Index.get(fixedb);
        int j = mapVar2Index.get(onb);
        if (i == j) {
          associations.put(fixedb, onb);
        }
      }
    }
  }

  /**
   * Function returning a set containing the domain of the blocks
   *
   * @return set
   */
  public Set<Object> getSubDomain() {
    return subDomain;
  }

  /**
   * Function returning a map associating each index to its variable
   *
   * @return map
   */
  public Map<Integer, Variable> getMapIndex2Var() {
    return mapIndex2Var;
  }

  /**
   * Function returning the variable corresponding to
   * the index
   *
   * @param index
   * @return the variable corresponding to the index
   */
  public Variable getVar(int index) {
    return getMapIndex2Var().get(index);
  }

  /**
   * Function returning the index of the corresponding variable
   *
   * @param var : the variable
   * @return return its index
   */
  public int getIndex(Variable var) {
    return getMapVar2Index().get(var);
  }

  /**
   * Function returning a set containing the values that
   * the piles can take
   *
   * @return set
   */
  public Set<Object> getPileSubDomain() {
    return pileSubDomain;
  }

}
