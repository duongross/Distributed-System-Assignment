package HW_Sample;

public class Book extends Media{
	private String author;
	
	public Book(String name, String author) {
		super(name);
		this.author=author;
	}
	
	public String getAuthor() {
		return author;
	}
	
	@Override
	public String toString() {
		 return super.getName() + " " + this.author;
	}
}
