package pk1;

public class Book {
	private String title;
	private String publisher;
	private Author author;
	
	public Book(String title, String publisher, Author author) {
		this.title = title;
		this.publisher = publisher;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", publisher=" + publisher + ", author=" + author.toString() + "]";
	}
	
	

}
