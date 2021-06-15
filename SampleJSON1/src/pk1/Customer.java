package pk1;

import java.util.List;

public class Customer {
	private int id;
	private List<String> names;
	private int age;
	private List<String> addresses;
	
	public Customer(int id, List<String> names, int age, List<String> addresses) {
		this.id = id;
		this.names = names;
		this.age = age;
		this.addresses = addresses;
	}
	
}
