package pk1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WritetingJS {
	public static void main(String[] args) throws FileNotFoundException {
		JSONObject jo = new JSONObject();
		jo.put("id",123);
		jo.put("age", 21);
		
		JSONArray janame = new JSONArray();
		janame.add("Smith");
		janame.add("John");
		jo.put("names",janame);
		
		JSONArray jaadd= new JSONArray();
		jaadd.add("BD");
		jaadd.add("TPHCM");
		jo.put("addresses",jaadd);
		
		String jsonString = jo.toJSONString();
		System.out.println(jsonString);
	}
}
