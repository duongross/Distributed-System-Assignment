package HW_Sample;
import java.io.Serializable;

public class Media implements Serializable{
	private String name;
	
	public Media(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
}
