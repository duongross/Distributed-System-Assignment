package pk1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Staff3 {
	public static void main(String[] args) {
		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File("staff3.xml"));
			NodeList nl = doc.getElementsByTagName("staff");
			System.out.println("Number of staff: " +nl.getLength());

			for (int i = 0; i < nl.getLength(); i++) {
		            Element e = (Element) nl.item(i);
		            String  id = e.getAttribute("id");
		            if (e.getNodeName().contains("staff")) {
		            	String firstname = e.getElementsByTagName("firstname").item(0).getTextContent();
		            	String lastname = e.getElementsByTagName("lastname").item(0).getTextContent();
		            	String nickname = e.getElementsByTagName("nickname").item(0).getTextContent();
		            	String salary = e.getElementsByTagName("salary").item(0).getTextContent();
		            	String currency = ((Element) e.getElementsByTagName("salary").item(0)).getAttribute("currency");
		            	Staff s = new Staff(Integer.parseInt(id),firstname,lastname,nickname,Double.parseDouble(salary),currency);
		            	System.out.println(s.toString());
		            }          	
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
