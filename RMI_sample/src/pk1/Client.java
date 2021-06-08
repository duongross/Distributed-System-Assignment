package pk1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;


public class Client {

	public static void addBook(ServerService stub, Scanner scanner) throws RemoteException {
		System.out.println("Input name: \n");
		String name = scanner.nextLine();
		System.out.println("Input author: \n");
		String author = scanner.nextLine();
		stub.createBook(name, author);		
	}
	public static void addNewspaper(ServerService stub, Scanner scanner) throws RemoteException {
		System.out.println("Input name: \n");
		String name = scanner.nextLine();
		System.out.println("Input author: \n");
		String author = scanner.nextLine();
		stub.createNewspaper(name, author);		
	}
	public static void getBook(ServerService stub) throws RemoteException {
		List<Book> booklist = new ArrayList<>();
		booklist=stub.getBookList();
		for(int i=0;i<booklist.size();i++) {
			System.out.println(booklist.get(i).toString());
		}
	}
	public static void getNewspaper(ServerService stub) throws RemoteException {
		List<Newspaper> newspaperlist = new ArrayList<>();
		newspaperlist=stub.getNewspaperList();
		for(int i=0;i<newspaperlist.size();i++) {
			System.out.println(newspaperlist.get(i).toString());
		}
	}
	
	public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            ServerService stub = (ServerService) registry.lookup("ServerService"); // same name when server register to RMI serve
            Scanner scanner = new Scanner(System.in);
            String option="";
            while (!option.equals("end")) {
           	 option=scanner.nextLine();
				 if (option.equals("1")) {
					 System.out.println("Input book\n");
					 addBook(stub, scanner);
				 }
				 else if (option.equals("2")) {
					 System.out.println("Input newsapper\n");
					 addNewspaper(stub, scanner);
				 }
				 else if (option.equals("3")) {
					 getBook(stub);
				 }
				 else if (option.equals("4")) {
					 getNewspaper(stub);
				 }
				 else {
					 System.out.println("Wrong option!\n");
					 
           }
       } 
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
