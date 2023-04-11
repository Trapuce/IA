package representation.variable;

import java.util.HashSet;

/**
 * Class representing a variable of
 * the type boolean
 * The variable can only have the values
 * True or False
 * Extends the Variable Class
 */

public class BooleanVariable extends Variable {

	public BooleanVariable(String name) {
		super(name, new HashSet<Object>());
		super.domain.add(true);
		super.domain.add(false);

	}

}