package pk4;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Writing {
	public static void main(String[] args) {
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account(12,100000));
		accounts.add(new Account(23,200000));
		
		JSONObject jo = new JSONObject();
		jo.put("id",123);
		jo.put("age", 21);
	
		jo.put("name","Duong");
		
		JSONArray jaacc= new JSONArray();
		for (int i=0; i<accounts.size();i++) {
			JSONObject accountObj = new JSONObject();
			accountObj.put("accountID", accounts.get(i).getAccountID());
			accountObj.put("balance", accounts.get(i).getBalance());
			
			jaacc.add(accountObj);
		}
		
		jo.put("accounts", jaacc);
		
		String jsonString = jo.toJSONString();
		System.out.println(jsonString);
		
		try {
			PrintWriter printwWriter = new PrintWriter("create_customer.json");
			printwWriter.write(jo.toJSONString());
			printwWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
