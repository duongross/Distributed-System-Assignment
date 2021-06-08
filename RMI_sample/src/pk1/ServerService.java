package pk1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ServerService extends Remote {
	public void createBook(String name, String author) throws RemoteException;
	public void createNewspaper(String name, String author) throws RemoteException;
	public List<Book> getBookList() throws RemoteException;
	public List<Newspaper> getNewspaperList() throws RemoteException;
}
