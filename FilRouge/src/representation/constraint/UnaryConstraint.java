package representation.constraint;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import representation.variable.Variable;

/**
 * A Constraint Class that represent a constraint
 * applied to a single variable
 */

public class UnaryConstraint implements Constraint {

  private Variable var;
  private Set<Object> domain;

  /**
   * Constructor
   * 
   * @param var
   * @param domain
   */
  public UnaryConstraint(Variable var, Set<Object> domain) {
    this.var = var;
    this.domain = domain;
  }

  @Override
  public Set<Variable> getScope() {
    Set<Variable> scope = new HashSet<>();
    scope.add(var);
    return scope;
  }

  @Override
  public boolean isSatisfiedBy(Map<Variable, Object> state) {
    return domain.contains(state.get(var));
  }

  // GETTERS
  /**
   * @return the variable
   */
  public Variable getVar() {
    return this.var;
  }

  /**
   * @return the domain
   */
  public Set<Object> getDomain() {
    return this.domain;
  }

  @Override
  public String toString() {
    return "UnaryConstraint {" +
        " var='" + getVar() + "'" +
        ", domain='" + getDomain() + "'" +
        "}\n";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof UnaryConstraint)) {
      return false;
    }
    UnaryConstraint unaryConstraint = (UnaryConstraint) o;
    return Objects.equals(var, unaryConstraint.var) && Objects.equals(domain, unaryConstraint.domain);
  }

  @Override
  public int hashCode() {
    return Objects.hash(var, domain);
  }

}
