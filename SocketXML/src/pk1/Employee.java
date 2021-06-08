package pk1;

public class Employee {
	private int id;
	private String name;
	private int age;

	public Employee() {
	}

	public Employee(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Name: " + name + " - ID: " + id + " - age: " + age;
	}

	public static void main(String[] args) {
		int i1 = 1;
		String s1 = i1 + "";
		System.out.println(s1);
	}

}
