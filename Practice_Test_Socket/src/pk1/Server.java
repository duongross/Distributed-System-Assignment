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
		try {
			int port = 9999;
			ServerSocket socket = new ServerSocket(port);
            ServerImp obj = new ServerImp();
            while (true) {
                Socket client = socket.accept();
                Thread players = new Thread(new ServerThread(obj, client));
                players.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

