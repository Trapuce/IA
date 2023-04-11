package representation.constraint;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import representation.variable.Variable;

public class Implication implements Constraint {

	private Variable varOne;
	private Set<Object> subDomainOne;
	private Variable varTwo;
	private Set<Object> subDomainTwo;

	/**
	 * @param varOne       the first variable
	 * @param subDomainOne the sub-domain of the first variable
	 * @param varTwo       the second variable
	 * @param subDomainTwo sub-domain of the second variable
	 */
	public Implication(Variable varOne, Set<Object> subDomainOne, Variable varTwo, Set<Object> subDomainTwo) {
		super();
		this.varOne = varOne;
		this.subDomainOne = subDomainOne;
		this.varTwo = varTwo;
		this.subDomainTwo = subDomainTwo;
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

		return !subDomainOne.contains(state.get(varOne)) || subDomainTwo.contains(state.get(varTwo));
	}

	@Override
	public String toString() {
		return "ImplicationConstraint {" +
				" varOne='" + getVarOne() + "'" +
				", subDomainOne='" + getSubDomainOne() + "'" +
				", varTwo='" + getVarTwo() + "'" +
				", subDomainTwo='" + getSubDomainTwo() + "'" +
				"}\n";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Implication)) {
			return false;
		}
		Implication implication = (Implication) o;
		return Objects.equals(varOne, implication.varOne) && Objects.equals(subDomainOne, implication.subDomainOne)
				&& Objects.equals(varTwo, implication.varTwo) && Objects.equals(subDomainTwo, implication.subDomainTwo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(varOne, subDomainOne, varTwo, subDomainTwo);
	}

	// Getters

	/**
	 * @return the first variable
	 */
	public Variable getVarOne() {
		return this.varOne;
	}

	/**
	 * @return the sub-domain of the first variable
	 */
	public Set<Object> getSubDomainOne() {
		return this.subDomainOne;
	}

	/**
	 * @return the second variable
	 */
	public Variable getVarTwo() {
		return this.varTwo;
	}

	/**
	 * @return the sub-domain of the second variable
	 */
	public Set<Object> getSubDomainTwo() {
		return this.subDomainTwo;
	}

}