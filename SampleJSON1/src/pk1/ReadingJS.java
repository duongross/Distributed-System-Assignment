package pk1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ReadingJS {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		FileReader fr = new FileReader("customer.json");
		JSONObject jo = (JSONObject) new JSONParser().parse(fr);
		
		long id = (long) jo.get("id");
		long age = (long) jo.get("age");
		System.out.println(id);
		System.out.println(age);
		
		JSONArray janame =(JSONArray) jo.get("names");
		Iterator nameItr = janame.iterator();
		while(nameItr.hasNext()) {
			String itemname = (String) nameItr.next();
			System.out.println(itemname);
		}
		
		JSONArray jaadd =(JSONArray) jo.get("addresses");
		Iterator addItr = jaadd.iterator();
		while(addItr.hasNext()) {
			String itemadd = (String) addItr.next();
			System.out.println(itemadd);
		}
		
	}
}
