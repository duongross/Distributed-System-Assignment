package pk1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	private static Employee convertXmlString2Document(String xml) throws ParserConfigurationException, SAXException, IOException {
		Employee e = null;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xml)));
		
        Element rootElement = (Element) doc.getDocumentElement();
        NodeList employees = rootElement.getChildNodes();
        
        Element employee = (Element) employees.item(0);
    
		int id = Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent());
		String name = doc.getElementsByTagName("name").item(0).getTextContent();
		int age = Integer.parseInt(doc.getElementsByTagName("age").item(0).getTextContent());
		
		e = new Employee(id, name ,age);

		return e;
	}

	public static void main(String[] args) {
		int port=9999;
		ServerSocket socketServer = null;
		try {
			socketServer = new ServerSocket(port);
			System.out.println("I'm a server. I'm listening on the port 9999.");
			
			Socket client = socketServer.accept();

			// get stream
			DataInputStream in = new DataInputStream(client.getInputStream());

			// process data
			Employee e = convertXmlString2Document(in.readUTF());
			System.out.println(e);

			client.close();
                
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
