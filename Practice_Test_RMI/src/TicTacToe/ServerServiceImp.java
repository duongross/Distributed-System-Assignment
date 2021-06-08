package TicTacToe;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.*;


public class ServerServiceImp implements ServerService{
	private TicTacToe game;
	private int numerOfClient = 0;
	private int clientNumber = 0;
	private String[] clientSymbol = { "X", "O" };
	Connection databaseConnect;
	public ServerServiceImp() {
		game = new TicTacToe();
		this.databaseConnect = connectToDatabase("root", "1234");
	}
	
	public static Connection connectToDatabase(String user, String password) {
		 String databaseUrl = "jdbc:mysql://localhost:3306/tictactoe?user=" + user + "&password=" + password;
		 Connection conn = null;
	     try {
	    	 conn = DriverManager.getConnection(databaseUrl);
	     } catch (SQLException e1) {
	    	 e1.printStackTrace();
	     }
	     return conn;
	}
	
	public void addAccount(String username, String password) throws RemoteException{
		String querry="INSERT INTO account (username, password) VALUE ('" + username + "', '" + password + "')";
		try {
			PreparedStatement st = databaseConnect.prepareStatement(querry);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkLogin(String username, String password) throws RemoteException{
		String querry="SELECT * FROM account WHERE username = '" + username + "' AND password = '" + password + "';";
		try {
			PreparedStatement st = databaseConnect.prepareStatement(querry);
			ResultSet result = st.executeQuery();
			if (result.next()) 
				return true;
			else 
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean chooseMove(int x, int y, String player) throws RemoteException {
		return game.setMove(x, y, player);
	}
	@Override
	public String isWin() {
		return game.checkWin();
	}
	@Override
	public String display() throws RemoteException {
		return game.toString();
	}
	 @Override
	 public String getClientToken() throws RemoteException {
	    clientNumber = numerOfClient;
	    numerOfClient++;
	    return clientSymbol[clientNumber];
	    }
}
