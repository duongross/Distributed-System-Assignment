package pk1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Server {
	private static Student convertXmlString2Document(String xml) throws ParserConfigurationException, SAXException, IOException {
		Student s = null;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xml)));
		
        Element rootElement = (Element) doc.getDocumentElement();
        NodeList students = rootElement.getChildNodes();
        
        Element student = (Element) students.item(0);
    
		int id = Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent());
		String name = doc.getElementsByTagName("name").item(0).getTextContent();
		double grade = Double.parseDouble(doc.getElementsByTagName("grade").item(0).getTextContent());
		
		s = new Student(id, name ,grade);

		return s;
	}
	public static void main(String[] args) {
		int port=9999;
		ServerSocket socketServer = null;
		try {
			socketServer = new ServerSocket(port);
			System.out.println("I'm a server. I'm listening on the port 9999.");
			while (true) {
				Socket client = socketServer.accept();
				
				ObjectInputStream in = new ObjectInputStream(client.getInputStream()); //get object from client
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream()); // pass object to client
						
				// Get student from server and insert to DB
				Student s = (Student) in.readObject();
				Connection databaseConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ds?user=" + "root" + "&password=" +"1234");
				String querry="INSERT INTO student  VALUE (?,?,?)";
				try {
					PreparedStatement st = databaseConnect.prepareStatement(querry);
					st.setString(1, s.getId()+"");
					st.setString(2, s.getName());
					st.setString(3, s.getGrade()+"");
					st.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// END
				client.close();
				in.close();
				out.close();
			}
	    } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socketServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}    