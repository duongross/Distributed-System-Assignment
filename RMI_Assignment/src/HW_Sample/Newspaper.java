package HW_Sample;

public class Newspaper extends Media{
	private String type;
	
	public Newspaper(String name, String type) {
		super(name);
		this.type=type;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return super.getName() + " " + this.type;
	}
}
