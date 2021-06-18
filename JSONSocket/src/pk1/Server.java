package pk1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server {
	
	public static Book JSON2Object(String json) throws ParseException {
		Book book = null;
		
		JSONObject jo = (JSONObject) new JSONParser().parse(json);
		
		String title = (String) jo.get("title");
		String publisher = (String) jo.get("publisher");
		
		JSONObject authorobj = (JSONObject) jo.get("author");
		
		String name = (String) authorobj.get("name");
		Long age = (long) authorobj.get("age");
		
		Author author = new Author(name,age.intValue());
		book = new Book(title, publisher, author);
		return book;
	}

	public static void main(String[] args) {
		int port=9999;
		ServerSocket socketServer = null;
		try {
			socketServer = new ServerSocket(port);
			System.out.println("I'm a server. I'm listening on the port 9999.");
			Socket client = socketServer.accept();

			DataInputStream in = new DataInputStream(client.getInputStream()); //get object from client
			
			String jsonstr = in.readUTF();
			Book book = JSON2Object(jsonstr);
			System.out.println(book.toString());
			client.close();
	    } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socketServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
