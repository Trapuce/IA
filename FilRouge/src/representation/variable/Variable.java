package representation.variable;

import java.util.Set;

/**
 * Class representing a variable
 * A variable have a name and a domain
 */

public class Variable {

	protected String name;
	protected Set<Object> domain;
	protected final String SPLITTER = "_";

	/**
	 * @param name
	 * @param domain
	 */
	public Variable(String name, Set<Object> domain) {
		super();
		this.name = name;
		this.domain = domain;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the domain
	 */
	public Set<Object> getDomain() {
		return domain;
	}

	/**
	 * @param numberOfBlocks the number of blocks
	 * @param numberOfPiles  the number of piles
	 * @blockNumber the number of the block
	 * @param domain the domain to set
	 */
	public void setDomain(Set<Object> domain, int numberOfBlocks, int numberOfPiles, int blockNumber) {
		for (int i = -numberOfPiles; i < numberOfBlocks; i++) {
			domain.add(i);
		}
		domain.remove(blockNumber);
		this.domain = domain;
	}

	/**
	 * Method that return the "index" of the variable
	 *
	 * @return i for on_i or free_i or fixed_i
	 */
	public int getIndex() {
		int index = 0;
		String[] parts = name.split(SPLITTER);
		index = Integer.parseInt(parts[1]);
		return index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{" +
				"'" + getName() + "'" +
				"}";
	}

}
