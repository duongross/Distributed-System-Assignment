package pk1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
public class ServerThread implements Runnable{
	ServerImp obj = null;
	Socket client = null;

	public ServerThread(ServerImp obj, Socket client) {
		this.obj = obj;
		this.client = client;
	}
	@Override
	public void run() {
		System.out.println("Connect to server");
		try {
			DataInputStream in = new DataInputStream(client.getInputStream());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());

			String option="";
			 String username="";
			 String password="";
			 boolean status=false;
			 int flag=0;
			 
			 while (option!="end" & flag==0) {
				 out.writeUTF("Enter 1 to sign_in, enter 2 to create account, enter  to end\n");
				 option=in.readUTF();
				 if (option.equals("1")) {
					 out.writeUTF("Input username and pass word\n");
					 username=in.readUTF();
					 password=in.readUTF();
					 if(obj.checkLogin(username, password)) {
						status=true;
						out.writeUTF("Welcome\n");
					 	flag=1;
					 }
					 else {
						 status=false;
						 out.writeUTF("Your account is incorrect\n");
					 }
				 }
				 else if (option.equals("2")) {
					 out.writeUTF("Input username and pass word\n");
					 username=in.readUTF();
					 password=in.readUTF();
					 if(obj.checkLogin(username, password)) {
						 out.writeUTF("Account is already exist. Try again\n");
					 } else {
						 obj.addAccount(username, password);
						 out.writeUTF("Your account is created\n");
					 }
				 }
				 else 
					 out.writeUTF("Wrong option!\n");
			 }
			 // Game for client
			 String clientSymbol = obj.getClientToken();
			 out.writeUTF(clientSymbol);
			 while (obj.isWin() == null & status==true) {
				 	out.writeUTF("Player " + clientSymbol +" choose your move[1 - 3]: ");
	                int x = Integer.parseInt(in.readUTF());
	                int y = Integer.parseInt(in.readUTF());
	                if (obj.chooseMove(x, y, clientSymbol)) {
	                	out.writeUTF("This move is already havs Or Wait for your friend");
	                    continue;
	                }
	                out.writeUTF(obj.display());
	            }
	            if (obj.isWin().equals("draw"))
	            	out.writeUTF("Draw");
	            else
	            	out.writeUTF("Player " + obj.isWin() + " win!" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

