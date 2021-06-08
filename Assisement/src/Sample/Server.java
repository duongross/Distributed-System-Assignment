package Sample;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.*;

public class Server {
	public static void main(String[] args) {
		String url2 = "jdbc:mysql://localhost:3306/ds?user=root&password=1234";
	        Connection conn2 = null;
	        try {
	            conn2 = DriverManager.getConnection(url2);
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        if (conn2 != null) {
	            System.out.println("Connected to the database");
	        }
		int serverPort = 1999;
		ServerSocket serverSocket = null;
		ObjectOutputStream toClient = null;
		ObjectInputStream fromClient = null;
		
		try {
			serverSocket = new ServerSocket(serverPort);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Just connected to " + socket.getRemoteSocketAddress());
				
				toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				fromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Message std = (Message) fromClient.readObject();
			    String query = "INSERT INTO student (id,name,year,gender) VALUE ('" + std.getId() + "', "
			    		+ "'" + std.getName() + "', '" + std.getYear() + "', '"+ std.getGender()+"');";
                Statement statement = conn2.createStatement();
                statement.executeUpdate(query);
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	serverSocket.close();
            } catch (IOException e) {
                System.out.println("run this");
                e.printStackTrace();
            }
        }
    }
}
