package Sample;

import java.io.Serializable;

public final class Message implements Serializable {
	private String name;
	private String id;
	private String year;
	private String gender;

	Message(String id,String name,String year,String gender) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.gender=gender;
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getYear() {
		return year;
	}
	public String getGender() {
		return gender;
	}
}