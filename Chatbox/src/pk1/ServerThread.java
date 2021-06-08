package pk1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
public class ServerThread implements Runnable{
	Socket client = null;
	ArrayList<Socket> clientList;
	
	public ServerThread(Socket client,ArrayList<Socket> clientList) {
		this.client=client;
		this.clientList=clientList;
	}
	
	@Override
	public void run() {
		System.out.println("Connect to server");
		try {
			DataInputStream in = new DataInputStream(client.getInputStream());
			String mess="";
			while(!mess.equals("bye")) {
				mess=in.readUTF();
				System.out.println("Message from client to server: " + mess + "\n");
				for (Socket clients:clientList) {
					if(clients != this.client) {
						DataOutputStream out = new DataOutputStream(clients.getOutputStream());
						out.writeUTF("Message from user to other user: " + mess + "\n");
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
