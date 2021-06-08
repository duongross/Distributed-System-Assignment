package pk1;

import java.io.Serializable;

public class Newspaper implements Serializable{
	private String name;
	private String author;
	
	public Newspaper(String name, String author)  {
		this.name=name;
		this.author=author;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	@Override
	public String toString() {
		return name + "|" +author;
	}
}
