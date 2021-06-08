package HW_Sample;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.*;

public class Client {
	public static void addMedia(ServerService stub, Scanner scanner) throws RemoteException {
        String option = scanner.nextLine();
        if (option.equals("book")) {
            System.out.print("Input name: \n");
            String name = scanner.nextLine();
            System.out.print("Input author: \n");
            String author = scanner.nextLine();
            stub.createMedia("book", name, author);
            
        } else if (option.equals("newspaper")){
            System.out.print("Input name: \n");
            String name = scanner.nextLine();
            System.out.print("Input type : \n");
            String type = scanner.nextLine();
            stub.createMedia("newspaper", name, type);
        }
    }
	public static void getMedia(ServerService stub, Scanner scanner) throws RemoteException {
		String option = scanner.nextLine();
		List<Media> medialist = new ArrayList<>();
		
		if (option.equals("book")) {
			medialist=stub.getMedia("book");
        } else if (option.equals("newspaper")){
        	medialist=stub.getMedia("newspaper");
        }
		
		for(int i=0;i<medialist.size();i++) {
			System.out.println(medialist.get(i).toString());
		}
	}
	 public static void main(String[] args) {
		 try {
			 Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
			 ServerService stub = (ServerService) registry.lookup("ServerService"); // same name when server register to RMI server	 
			 
			 Scanner scanner = new Scanner(System.in);
			 String option="";
			 System.out.println("Enter 1 to input, enter 2 to use service, enter end to exit\n");
			 while (option!="end") {
				 option=scanner.nextLine();
				 if (option.equals("1")) {
					 System.out.println("Input media\n");
					 System.out.println("Enter book to input book, newspaper is newspaper\n");
					 addMedia(stub, scanner);
				 }
				 else if (option.equals("2")) {
					 System.out.println("User service\n");
					 System.out.println("Enter book to see book, newspaper is newspaper\n");
					 getMedia(stub, scanner);
				 }
				 else 
					 System.out.println("Wrong option!\n");
			 }
			 scanner.close();
			 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}
}
