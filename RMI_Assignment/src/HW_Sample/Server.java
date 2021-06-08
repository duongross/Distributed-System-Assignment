package HW_Sample;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.*;

public class Server implements ServerService {
	// Connect database
	 Connection databaseConnect;
	 public Server() throws Exception {
		 this.databaseConnect = connectToDatabase("root", "1234");
		 if (databaseConnect != null) {
			 System.out.println("Connected to the database");
		 } else {
			 throw new Exception("Cannot connect to database");
	     }
	 }
	 
	 public static Connection connectToDatabase(String user, String password) {
		 String databaseUrl = "jdbc:mysql://localhost:3306/dsmedia?user=" + user + "&password=" + password;
		 Connection conn = null;
	     try {
	    	 conn = DriverManager.getConnection(databaseUrl);
	     } catch (SQLException e1) {
	    	 e1.printStackTrace();
	     }
	     return conn;
	 }
	 
	public List<Media> getMedia(String type) throws RemoteException{
		List<Media> medialist = new ArrayList<Media>();
		String querry="";
		if (type.equals("newspaper")) {
			querry="SELECT * FROM newspaper;";
		} else if (type.equals("book")) {
			querry="SELECT * FROM book;";
		}
		Statement st;
		try {
			st = databaseConnect.createStatement();
			ResultSet result = st.executeQuery(querry);
			while (result.next()) {
				if (type.equals("newspaper"))
					medialist.add(new Newspaper(result.getString("name"), result.getString("type")));
				else if (type.equals("book"))
					medialist.add(new Book(result.getString("name"), result.getString("author")));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		return medialist;
	}
	
	public void createMedia(String type, String name, String attribute) throws RemoteException {
		String querry="";
		if (querry != null) {
			if(type.equals("newspaper")) {
				querry="INSERT INTO newspaper (name, type) VALUE ('" + name + "', '" + attribute + "')";
				try {
					PreparedStatement st = databaseConnect.prepareStatement(querry);
					st.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (type.equals("book")){
				querry="INSERT INTO book (name, author) VALUE ('" + name + "', '" + attribute + "')";
				try {
					PreparedStatement st = databaseConnect.prepareStatement(querry);
					st.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
        // create an Instance of Server Service implementation
        Server obj = new Server();
        // Create an stub to store in RMI server
        try {
            ServerService stub = (ServerService) UnicastRemoteObject.exportObject(obj, 1099);

            //  Register stub to RMI server
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("ServerService", stub); // ServerSevice will be the name in RMI server when client will lookup
            
            System.out.println("Sever ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
