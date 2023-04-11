package datamining;

import java.util.HashSet;
import java.util.Set;

/**
 * Class PowerSet
 * Generate all subsets of a given set
 */
public class PowerSet<T> {

  /**
   * Recursive function generating all the subsets
   * 
   * @param set : set from wich to generate all subsets
   * @return a set containing all subsets
   */
  public Set<Set<T>> recursivePowerSet(Set<T> set) {
    if (set.isEmpty()) {
      Set<Set<T>> res = new HashSet<>();
      res.add(set);
      return res;
    }

    T element = set.iterator().next();
    Set<T> subSetWithoutElement = getSubSetWithoutElement(set, element);
    Set<Set<T>> powerSetSubSetWithoutElement = recursivePowerSet(subSetWithoutElement);
    Set<Set<T>> powerSetSubSetWithElement = addElementToAll(powerSetSubSetWithoutElement, element);

    Set<Set<T>> powerSet = new HashSet<>();
    powerSet.addAll(powerSetSubSetWithoutElement);
    powerSet.addAll(powerSetSubSetWithElement);
    return powerSet;
  }

  /**
   * Helper method for adding element in every subsets
   * 
   * @param powerSetSubSetWithoutElement : set of subsets that does not contain
   *                                     element
   * @param element                      : element to be added
   * @return a set of subsets
   */
  private Set<Set<T>> addElementToAll(Set<Set<T>> powerSetSubSetWithoutElement, T element) {

    Set<Set<T>> powerSetSubSetWithElement = new HashSet<>();
    for (Set<T> subsetWithoutElement : powerSetSubSetWithoutElement) {
      Set<T> subsetWithElement = new HashSet<>(subsetWithoutElement);
      subsetWithElement.add(element);
      powerSetSubSetWithElement.add(subsetWithElement);
    }
    return powerSetSubSetWithElement;
  }

  /**
   * Helper function
   * @param set 
   * @param element : first element to remove
   * @return return set without first element
   */
  private Set<T> getSubSetWithoutElement(Set<T> set, T element) {
    Set<T> subsetWithoutFirstElement = new HashSet<>(set);
    subsetWithoutFirstElement.remove(element);
    return subsetWithoutFirstElement;
  }

}
