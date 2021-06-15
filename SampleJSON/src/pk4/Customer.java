package pk4;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id, age;
    private String name;
    List<Account> accounts = new ArrayList<>();
    public Customer(int id, int age, String name, List<Account> accounts) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.accounts = accounts;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
	@Override
	public String toString() {
		return "Customer [id=" + id + ", age=" + age + ", name=" + name + ", accounts=" + accounts.toString() + "]";
	}
    
 
}
