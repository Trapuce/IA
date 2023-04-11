package datamining;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import representation.variable.BooleanVariable;

/**
 * Class BooleanDatabase
 * A boolean database contains all the items
 * and a list of all the transactions
 */
public class BooleanDatabase {

  private Set<BooleanVariable> items;
  private List<Set<BooleanVariable>> listOfTransactions;

  /**
   * Constructor
   *
   * @param items : set containing all the items
   */
  public BooleanDatabase(Set<BooleanVariable> items) {
    this.items = items;
    listOfTransactions = new LinkedList<>();
  }

  /**
   * Getter
   *
   * @return the items
   */
  public Set<BooleanVariable> getItems() {
    return items;
  }

  /**
   * Method for adding a transaction
   *
   * @param transaction : the transaction
   */
  public void add(Set<BooleanVariable> transaction) {
    listOfTransactions.add(transaction);
  }

  /**
   * Getter
   *
   * @return listOfTransactions
   */
  public List<Set<BooleanVariable>> getTransactions() {
    return listOfTransactions;
  }

}
