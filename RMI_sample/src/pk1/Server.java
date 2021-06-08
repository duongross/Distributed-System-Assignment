package pk1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Server implements ServerService{
	Connection databaseConnect;
	//######################################################################################
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
	//######################################################################################
	@Override
	 public void createBook(String name, String author) throws RemoteException{
		 String querry="INSERT INTO book VALUES (?,?)";
		 PreparedStatement st;
		try {
			st = databaseConnect.prepareStatement(querry);
			 st.setString(1,name);
			 st.setString(2,author);
			 st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	 }
	@Override
	public void createNewspaper(String name, String author) throws RemoteException {
			String querry="INSERT INTO newspaper VALUES (?,?)";
			PreparedStatement st;
			try {
				st = databaseConnect.prepareStatement(querry);
				 st.setString(1,name);
				 st.setString(2,author);
				 st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	}
	@Override
	public List<Book> getBookList() throws RemoteException {
		List<Book> booklist = new ArrayList<>();
		String querry="SELECT * FROM book;";
		Statement st;
		try {
			st = databaseConnect.createStatement();
			ResultSet result = st.executeQuery(querry);
			while (result.next()) {
				booklist.add(new Book(result.getString("name"), result.getString("author")));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return booklist;
	}
	@Override
	public List<Newspaper> getNewspaperList() throws RemoteException {
		List<Newspaper> newspaperlist = new ArrayList<>();
		String querry="SELECT * FROM newspaper;";
		Statement st;
		try {
			st = databaseConnect.createStatement();
			ResultSet result = st.executeQuery(querry);
			while (result.next()) {
				newspaperlist.add(new Newspaper(result.getString("name"), result.getString("author")));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return newspaperlist;
	}
	  public static void main(String[] args) throws Exception{
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
