package pk1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class Client {
	public static void main(String[] args) {
		 int serverPort = 9999;
	     String host = "127.0.0.1";
	     Socket server = null;
	     
	     try {
	    	 server = new Socket(host, serverPort);
	    	 DataInputStream in = new DataInputStream(server.getInputStream());
	    	 DataOutputStream out = new DataOutputStream(server.getOutputStream()); 
	    	 
	    	 Scanner scanner = new Scanner(System.in);
	    	 Thread other = new Thread(new Runnable() {
	    		 @Override
				public void run() {
	    			 try {
						System.out.println(in.readUTF());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	 });
	    	 other.start();
	    	 String mess="";
	    	 while(!mess.equals("bye")) {
	    		 mess=scanner.nextLine();
	    		 out.writeUTF(mess);
	    	 }
	    	 in.close();
	    	 out.close();
	    	 scanner.close();
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     }
	}   
}
