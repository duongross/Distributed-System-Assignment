package connectStudentpk1;

import java.io.*;
import java.net.*;
import java.sql.*;

public class Server {
	public static Connection connectToDatabase(String user, String password) {
		 String databaseUrl = "jdbc:mysql://localhost:3306/student?user=" + user + "&password=" + password;
		 Connection conn = null;
	     try {
	    	 conn = DriverManager.getConnection(databaseUrl);
	     } catch (SQLException e1) {
	    	 e1.printStackTrace();
	     }
	     return conn;
	 }
	
	 public static void main(String[] args) {
		 try {
			 Connection databaseConnect = connectToDatabase("root","1234");
			 int port = 9999;
				
			 ServerSocket socket = new ServerSocket(port);
			 System.out.println("Server run on port " + port);
				
			 Socket client = socket.accept();
			 DataInputStream in = new DataInputStream(client.getInputStream());
			 DataOutputStream out = new DataOutputStream(client.getOutputStream());
		    	
			 String name = in.readUTF();
			 String age = in.readUTF();	
		    	
			 try {
				 PreparedStatement st = databaseConnect.prepareStatement("INSERT INTO student VALUES (?,?)");
				 st.setString(1,name);
				 st.setNString(2, age);
				 st.executeUpdate();						
			 } catch (Exception e) {
				 e.printStackTrace();
			 }    
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}
}
