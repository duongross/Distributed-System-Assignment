package HW_Sample;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface ServerService extends Remote {
    public List<Media> getMedia(String type) throws RemoteException;
    public void createMedia(String type, String name, String attribute) throws RemoteException;
}