package ludum.vita.actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ludum.vita.beans.PointsBean;
import ludum.vita.beans.UserBean;
import ludum.vita.dao.DatabaseFactory;
import ludum.vita.dao.PointsDAO;
import ludum.vita.dao.UsersDAO;

public class RegisterAction {

	private UsersDAO users;
	private PointsDAO points;

	public RegisterAction(DatabaseFactory factory){
		users = factory.getUsersDAO();
		points = factory.getPointsDAO();
	}

	public void register(String firstName, String lastName, String userName, String password, String confirm, String email) throws Exception{
		UserBean newUser = new UserBean(firstName, userName, password);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		String problems = validate(newUser);
		if(!password.equals(confirm)){
			problems += "Password Error: Passwords much match\n";
		}
		if(problems.length() <= 0){
			users.addUser(newUser);
			points.addUserPoints(new PointsBean(users.getUser(userName).getLSUID()));
		} else { 
			throw new IllegalArgumentException(problems);
		}
	}

	private String validate(UserBean newUser) throws Exception{
		//USERNAME: ALP
		//PASSWORD: CAN'T CONTAIN NUMBER 0, AT LEAST EIGHT CHARACTERS alphanumerica
		String errors = "";
		Pattern userValidation = Pattern.compile("[a-zA-Z0-9\\S]{6,100}");
		Matcher m = userValidation.matcher(newUser.getUserName());
		Pattern passwordValidation = Pattern.compile("[a-zA-Z0-9\\S]{8,100}");
		Matcher n = passwordValidation.matcher(newUser.getPassword());
		Pattern emailValidation =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher o = emailValidation.matcher(newUser.getEmail());
		if(newUser.getFirstName().length() <= 0){
			errors += "First name length must be greater than 0.\n";
		}
		if(newUser.getLastName().length() <= 0){
			errors += "Last name length must be greater than 0.\n";
		}
		if(!m.matches()){
			errors += "Username Error: Alphanumeric characters with length between 6 to 100.\n";
			if(users.checkUserName(newUser.getUserName())){
				errors += "Username Error: Username is taken\n";
			}
		}
		if(!n.matches() || newUser.getPassword().contains("0")){
			errors += "Password Error: Alphanumeric characters with length between 8 to 100 and can't contain the 0 character.\n";
		}
		if(!o.matches()){
			errors += "Email Error: Invalid email address\n";
		}
		return errors;
	}
}
