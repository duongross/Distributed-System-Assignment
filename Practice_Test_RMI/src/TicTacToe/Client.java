package TicTacToe;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		 try {
			 Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
			 ServerService stub = (ServerService) registry.lookup("ServerService"); // same name when server register to RMI server
			 // Login
			 String option="";
			 String username="";
			 String password="";
			 boolean status=false;
			 int flag=0;
			 while (option!="end" & flag==0) {
				 System.out.println("Enter 1 to sign_in, enter 2 to create account, enter  to end\n");
				 option=scanner.nextLine();
				 if (option.equals("1")) {
					 System.out.println("Input username and pass word\n");
					 username=scanner.nextLine();
					 password=scanner.nextLine();
					 if(stub.checkLogin(username, password)) {
						status=true;
					 	System.out.println("Welcome\n");
					 	flag=1;
					 }
					 else {
						 status=false;
						 System.out.println("Your account is incorrect\n");
					 }
				 }
				 else if (option.equals("2")) {
					 System.out.println("Input username and pass word\n");
					 username=scanner.nextLine();
					 password=scanner.nextLine();
					 if(stub.checkLogin(username, password)) {
						 System.out.println("Account is already exist. Try again\n");
					 } else {
						 stub.addAccount(username, password);
						 System.out.println("Your account is created\n");
					 }
				 }
				 else 
					 System.out.println("Wrong option!\n");
			 }
			 // Game for client
			 String clientSymbol = stub.getClientToken();
	         System.out.println(clientSymbol);
			 while (stub.isWin() == null & status==true) {
	                System.out.print("Player " + clientSymbol +" choose your move[1 - 3]: ");
	                int x = scanner.nextInt();
	                int y = scanner.nextInt();
	                if (!stub.chooseMove(x, y, clientSymbol)) {
	                    System.out.println("This move is already havs Or Wait for your friend");
	                    continue;
	                }
	                System.out.println(stub.display());
	            }
	            if (stub.isWin().equals("draw"))
	                System.out.println("Draw");
	            else
	                System.out.println("Player " + stub.isWin() + " win!" );
	            scanner.close();

		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}
}
