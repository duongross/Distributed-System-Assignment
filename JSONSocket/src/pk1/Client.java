package pk1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.json.simple.JSONObject;

public class Client {
	public static String Object2JSON(Book book) {
		JSONObject bookobj = new JSONObject();
		
		bookobj.put("title", book.getTitle());
		bookobj.put("publisher", book.getPublisher());
		
		Author author = book.getAuthor();
		
		JSONObject authorobj = new JSONObject();
		
		authorobj.put("name",author.getName());
		authorobj.put("age",author.getAge());
		
		bookobj.put("author", authorobj);
		
		return bookobj.toJSONString();
	}

	public static void main(String[] args) {
		try {
			/* make connection to server socket */
			Socket sock = new Socket("127.0.0.1", 9999);

			// send data to server
			DataOutputStream out = new DataOutputStream(sock.getOutputStream()); //pass object to server
			
			Author author = new Author("Duong", 21);
			Book book = new Book("No way", "NYTCT", author);
			
			out.writeUTF(Object2JSON(book));

			/* close the socket connection */
			out.close();
			sock.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
