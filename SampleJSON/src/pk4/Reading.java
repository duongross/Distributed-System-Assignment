package pk4;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
public class Reading {

	public static void main(String[] args) throws IOException, ParseException {
		FileReader fr = new FileReader("customer2.json");
		JSONObject jo = (JSONObject) new JSONParser().parse(fr);
		
		Long id = (long) jo.get("id");
		String name = (String) jo.get("name");
		Long age = (long) jo.get("age");		
		List<Account> accounts = new ArrayList<>();
		
		JSONArray ja = (JSONArray) jo.get("accounts");
		Iterator jaacc = ja.iterator();
		
		while(jaacc.hasNext()) {
			Map map = (Map) jaacc.next();
			Long accid = (long) map.get("accountID");
			Long balance = (long) map.get("balance");
			accounts.add(new Account(accid.intValue(), balance.intValue()));
		}
		//Create object
		Customer customer = new Customer(id.intValue(), age.intValue(), name, accounts);
		System.out.println(customer.toString());
	}

}
