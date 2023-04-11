package representation.constraint;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import representation.variable.Variable;

/**
 * A Constraint class that represent the constraint of
 * the nature that two variable onB cannot have the same
 * value
 * Implements the Constraint interface
 */
public class DifferenceConstraint implements Constraint {

	private Variable varOne;
	private Variable varTwo;

	/**
	 * @param varOne
	 * @param varTwo
	 */
	public DifferenceConstraint(Variable varOne, Variable varTwo) {
		super();
		this.varOne = varOne;
		this.varTwo = varTwo;
	}

	@Override
	public Set<Variable> getScope() {
		Set<Variable> scope = new HashSet<>();
		scope.add(varOne);
		scope.add(varTwo);
		return scope;
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, Object> state) {

		if (state.get(varOne) == null || state.get(varTwo) == null) {
			throw new IllegalArgumentException();
		}
		return state.get(varOne) != state.get(varTwo);
	}

	// GETTERS
	/**
	 * @return the first variable
	 */
	public Variable getVarOne() {
		return this.varOne;
	}

	/**
	 * @return the second variable
	 */
	public Variable getVarTwo() {
		return this.varTwo;
	}

	@Override
	public String toString() {
		return "DifferenceConstraint {" +
				" varOne='" + getVarOne() + "'" +
				", varTwo='" + getVarTwo() + "'" +
				"}\n";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof DifferenceConstraint)) {
			return false;
		}
		DifferenceConstraint differenceConstraint = (DifferenceConstraint) o;
		return Objects.equals(varOne, differenceConstraint.varOne) && Objects.equals(varTwo, differenceConstraint.varTwo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(varOne, varTwo);
	}

}