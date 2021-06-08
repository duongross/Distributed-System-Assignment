package pk1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Socket;

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

public class Client {
	public static String convertDoc2XmlString(Document doc) {
		String output = "";
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			output = writer.getBuffer().toString();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	public static String convertObject2Doc(Employee employee) throws ParserConfigurationException  {
		String xmlString = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		// create a document object
		Document doc = builder.newDocument();

		// build a tree
		Element root = doc.createElement("employees");
		doc.appendChild(root);
		
		
		Element employeeElement = doc.createElement("employee");
		
		
		Element idElement = doc.createElement("id");
		idElement.appendChild(doc.createTextNode(employee.getId()+""));
		
		Element nameElement = doc.createElement("name");
		nameElement.appendChild(doc.createTextNode(employee.getName()));
		
		Element ageElement = doc.createElement("age");
		ageElement.appendChild(doc.createTextNode(employee.getAge()+""));
		
		// for the user element
		employeeElement.appendChild(idElement);
		employeeElement.appendChild(nameElement);
		employeeElement.appendChild(ageElement);
		
		// for the root element
		root.appendChild(employeeElement);

		// test
		xmlString = convertDoc2XmlString(doc);
		return xmlString;
	}
	public static void main(String[] args) {
		try {
			/* make connection to server socket */
			Socket sock = new Socket("127.0.0.1", 9999);

			// send data to server
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());

			Employee e = new Employee(14302, "Duong", 21);
			
			out.writeUTF(convertObject2Doc(e));

			/* close the socket connection */
			out.close();
			sock.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
