package pk1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import org.apache.xmlrpc.*;

public class JavaClient {
	public static void main(String[] args) {
		try {
			// XmlRpcClient
			XmlRpcClient client =  new XmlRpcClient("http://localhost:90");

			while (true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				Scanner scann = new Scanner(System.in);
				System.out.println("First number");
				int num1 = Integer.parseInt(br.readLine());
				System.out.println("Second number");
				int num2 = Integer.parseInt(br.readLine());
				
				// params
				Vector params = new Vector();
				params.addElement(num1);
				params.addElement(num2);

				// call a remote function
				System.out.println("Input 1 to sum, 2 to subtract, 3 to multiply, 4 to divide");
				String option = scann.nextLine();
				if(option.equals("1")) {
					Object result =  client.execute("JavaServer.sum", params);
					int sum = ((Integer) result).intValue();
					System.out.println("The sum is: " + sum);
				}
				else if(option.equals("2")) {
					Object result =  client.execute("JavaServer.sub", params);
					int sub = ((Integer) result).intValue();
					System.out.println("The subtraction is: " + sub);
				}
				else if(option.equals("3")) {
					Object result =  client.execute("JavaServer.mul", params);
					int mul = ((Integer) result).intValue();
					System.out.println("The multi is: " + mul);
				}
				else if(option.equals("4")) {
					Object result =  client.execute("JavaServer.div", params);
					double div = ((Double) result).doubleValue();
					System.out.println("The divide is: " + div);
				}

			}
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}
}
