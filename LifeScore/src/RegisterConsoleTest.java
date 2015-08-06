import java.util.Scanner;

import ludum.vita.actions.RegisterAction;
import ludum.vita.dao.DatabaseFactory;


public class RegisterConsoleTest {

	public static void main(String[] args){
		DatabaseFactory d = DatabaseFactory.getProductionInstance();
		RegisterAction register = new RegisterAction(d);
		System.out.println("Registration:");
		Scanner user = new Scanner(System.in);
		System.out.print("Enter Username: ");
		String u = user.nextLine();
		System.out.print("Enter Firstname: ");
		String f = user.nextLine();
		System.out.print("Enter Password: ");
		String p = user.nextLine();
		System.out.println("Registering...");
		try {
			register.register(f, u, p);
			System.out.println("Success");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		user.close();
	}
}
