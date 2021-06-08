package pk1;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Server1 {
	public static Connection connectToDatabase(String user, String password) {
		 String databaseUrl = "jdbc:mysql://localhost:3306/student?user=" + user + "&password=" + password;
		 Connection conn = null;
	     try {
	    	 conn = DriverManager.getConnection(databaseUrl);
	     } catch (SQLException e1) {
	    	 e1.printStackTrace();
	     }
	     return conn;
	 }
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
	public static String convertObject2Doc(List<Student> studentlist) throws ParserConfigurationException  {
		String xmlString = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		// create a document object
		Document doc = builder.newDocument();

		// build a tree
		Element root = doc.createElement("students");
		doc.appendChild(root);
		for(int i=0;i<studentlist.size();i++) {
			Element studentElement = doc.createElement("student");
			
			Element idElement = doc.createElement("id");
			idElement.appendChild(doc.createTextNode(studentlist.get(i).getId()+""));
		
			Element nameElement = doc.createElement("name");
			nameElement.appendChild(doc.createTextNode(studentlist.get(i).getName()));
		
			Element gradeElement = doc.createElement("grade");
			gradeElement.appendChild(doc.createTextNode(studentlist.get(i).getGrade()+""));
		
			// for the user element
			studentElement.appendChild(idElement);
			studentElement.appendChild(nameElement);
			studentElement.appendChild(gradeElement);
		
			// for the root element
			root.appendChild(studentElement);
		}
		// test
		xmlString = convertDoc2XmlString(doc);
		return xmlString;
	}
	private static List<Student> convertXmlString2Document(String xml) throws ParserConfigurationException, SAXException, IOException {
		List<Student> studentlist = new ArrayList<Student>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xml)));
		
        Element rootElement = (Element) doc.getDocumentElement();
        NodeList students = rootElement.getChildNodes();
        for (int i = 0; i < students.getLength(); i++) {
            Element e = (Element) students.item(i);
            if (e.getNodeName().contains("student")) {
            	String id = e.getElementsByTagName("id").item(0).getTextContent();
            	String name = e.getElementsByTagName("name").item(0).getTextContent();
            	String grade = e.getElementsByTagName("grade").item(0).getTextContent();
            	Student s = new Student(Integer.parseInt(id),name,Double.parseDouble(grade));
            	studentlist.add(s);
            }          	
        }
		return studentlist;
	}
	public static void main(String[] args) {
		 try {
			 Connection databaseConnect = connectToDatabase("root","1234");
			 int port = 9999;
			 List <Student> studentlist = new ArrayList<Student>();
				
			 ServerSocket socket = new ServerSocket(port);
			 System.out.println("Server run on port " + port); 
			 Socket client = socket.accept();
			 //Stream
			 DataInputStream in = new DataInputStream(client.getInputStream());
			 DataOutputStream out = new DataOutputStream(client.getOutputStream());
			 
			 String option = in.readUTF();
			 if (option.equals("1")) {
				 //-----------------------------------------------------------------------
				 Statement st;
				 String querry = "SELECT * FROM student1";
				 try {
					 st = databaseConnect.createStatement();
					 ResultSet result = st.executeQuery(querry);
					 while (result.next()) {
						 studentlist.add(new Student(result.getInt("id"), result.getString("name"), result.getDouble("grade")));
					 }
				 } catch (Exception e) {
		           e.printStackTrace();
				 }
			
				 System.out.println("Pass student to client");
				 out.writeUTF(convertObject2Doc(studentlist));
				 //-----------------------------------------------------------------------
				 System.out.println("Get student from client");
				 String XML = in.readUTF();
		    	 studentlist=convertXmlString2Document(XML);
		    	 System.out.println("Insert student from server to client database");
		    	 for(int i=0;i<studentlist.size();i++) {
					 querry = "INSERT INTO student1 VALUES (?,?,?)";
					 String checkquerry="SELECT * FROM student1 WHERE id=?";
					 try {
						 PreparedStatement prestcheck = databaseConnect.prepareStatement(checkquerry);
						 prestcheck.setString(1,studentlist.get(i).getId()+"");
						 ResultSet re = prestcheck.executeQuery();
						 if(!re.next()) {
							 PreparedStatement prest = databaseConnect.prepareStatement(querry);
							 prest.setString(1, studentlist.get(i).getId()+"");
							 prest.setString(2, studentlist.get(i).getName());
							 prest.setString(3, studentlist.get(i).getGrade()+"");
							 prest.executeUpdate();
						 }
					 } catch (Exception e) {
			           e.printStackTrace();
					 }
		    	 }
			 }
			//-----------------------------------------------------------------------
			 System.out.println("Success");
			 out.close();
	    	 in.close();
	    	 client.close();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}
}
