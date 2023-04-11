package representation.constraint;
import java.util.Map;
import java.util.Set;

import representation.variable.Variable;

/**
 * Interface Constraint
 * Represent a constraint
 * A Constraint have a scope and method returning
 * if the constraint is satisfied or not
 */
public interface Constraint {

	/**
	 * Method that return a set of Variables
	 * @return
	 */
	Set<Variable> getScope();


	/**
	 * Method that checks if a state satisfies a constraints
	 * @param state
	 * @return True or False
	 */
	boolean isSatisfiedBy(Map<Variable, Object> state);

}