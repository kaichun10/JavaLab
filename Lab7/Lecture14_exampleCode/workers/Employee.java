package workers;

/**
 * Represents an employee number, given name, and family name. Subclasses of
 * this should implement the getDetails() method to append any additional
 * information to the toString() method.
 * 
 * @author mefoster
 *
 */
public abstract class Employee {
	// Common fields
	private int employeeNumber;
	private String familyName;
	private String givenName;

	// Used to ensure sequential employee numbers
	private static int nextEmployeeNumber = 1;

	// Used in toString() method
	protected String employeeType;

	/**
	 * @return The list of possible employee types
	 */
	public static final String[] getEmployeeTypes() {
		return new String[] { "Salaried", "Hourly" };
	}

	/**
	 * @param familyName
	 * @param givenName
	 */
	public Employee(String familyName, String givenName) {
		this.employeeNumber = nextEmployeeNumber++;
		this.familyName = familyName;
		this.givenName = givenName;
	}

	/**
	 * Returns a string containing all information about this Employee,
	 * including any subclass-specific details.
	 * 
	 * @see java.lang.Object#toString()
	 * @see #getDetails()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(familyName).append(", ").append(givenName).append(" ");
		builder.append("(").append(employeeType).append(", ");
		builder.append("#").append(employeeNumber);
		builder.append(": ").append(getDetails());
		return builder.toString();
	}

	/**
	 * @return the employeeNumber
	 */
	public int getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName
	 *            the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName
	 *            the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * Returns any additional details about this Employee for use in toString()
	 * 
	 * @return Additional information (e.g., salary or hourly rate) as a string
	 */
	public abstract String getDetails();

}
