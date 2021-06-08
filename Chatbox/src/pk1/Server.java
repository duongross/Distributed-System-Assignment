package pk1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.*;
public class Server {
	private int port = 9999;
	ServerSocket socket = null;
	 
	public Server() throws Exception {
		this.socket = new ServerSocket(port);
		System.out.println("Connect to: " + port);
	}
	 
	public static void main(String[] args) {
		ArrayList<Socket> clientList = new ArrayList<Socket>();
		try {
			int port = 9999;
			ServerSocket socket = new ServerSocket(port);
			System.out.println("Server run on port " + port);
			while (true) {
				Socket client = socket.accept(); 
				clientList.add(client);
				Thread worker = new Thread(new ServerThread(client,clientList));
				worker.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
