package pk1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.net.Socket;
import java.util.Scanner;

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
	public static String convertObject2Doc(Student student) throws ParserConfigurationException  {
		String xmlString = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		// create a document object
		Document doc = builder.newDocument();

		// build a tree
		Element root = doc.createElement("students");
		doc.appendChild(root);
		
		Element studentElement = doc.createElement("student");
		
		Element idElement = doc.createElement("id");
		idElement.appendChild(doc.createTextNode(student.getId()+""));
		
		Element nameElement = doc.createElement("name");
		nameElement.appendChild(doc.createTextNode(student.getName()));
		
		Element gradeElement = doc.createElement("grade");
		gradeElement.appendChild(doc.createTextNode(student.getGrade()+""));
		
		// for the user element
		studentElement.appendChild(idElement);
		studentElement.appendChild(nameElement);
		studentElement.appendChild(gradeElement);
		
		// for the root element
		root.appendChild(studentElement);

		// test
		xmlString = convertDoc2XmlString(doc);
		return xmlString;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			/* make connection to server socket */
			Socket sock = new Socket("127.0.0.1", 9999);

			// send data to server
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream()); //pass object to server
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream()); //get object from server
			System.out.println("Client input student to server");
			System.out.println("Input id");
			int id = Integer.parseInt(scanner.nextLine());
			System.out.println("Input name");
			String name = scanner.nextLine();
			System.out.println("Input grade");
			double grade = Double.parseDouble(scanner.nextLine());
			//Create object
			Student student = new Student(id, name, grade);
			
			out.writeObject(student);

			/* close the socket connection */
			out.close();
			in.close();
			sock.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
