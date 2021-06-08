package pk1;

public class Staff {
	private int id;
	private String firstName;
	private String lastName;
	private String nickName;
	private double salary;
	private String currency;

	public Staff(int id, String firstName, String lastName, String nickName, double salary, String currency) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.salary = salary;
		this.currency = currency;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		return "Id: " + id + 
				" - First name: " + firstName + 
				" - Last name: " + lastName +
				" - Nick name: " + nickName + 
				" - Salary: " + salary + 
				" - Currency: " + currency;
	}
}
