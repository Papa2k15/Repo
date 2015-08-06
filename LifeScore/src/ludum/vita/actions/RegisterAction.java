package ludum.vita.actions;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ludum.vita.beans.UserBean;
import ludum.vita.dao.DatabaseFactory;
import ludum.vita.dao.UsersDAO;

public class RegisterAction {

	private UsersDAO users;

	public RegisterAction(DatabaseFactory factory){
		users = factory.getUsersDAO();
	}

	public List<String> register(String firstName, String userName, String password) throws Exception{
		UserBean newUser = new UserBean(firstName, userName, password);
		List<String> problems = validate(newUser);
		if(problems.size() <= 0){
			users.addUser(newUser);
		} 
		return problems;
	}

	private List<String> validate(UserBean newUser){
		//USERNAME: ALP
		//PASSWORD: CAN'T CONTAIN NUMBER 0, AT LEAST EIGHT CHARACTERS alphanumerica
		List<String> errors = new LinkedList<String>();
		Pattern userValidation = Pattern.compile("[a-zA-Z0-9\\S]{6,100}");
		Matcher m = userValidation.matcher(newUser.getUserName());
		Pattern passwordValidation = Pattern.compile("[a-zA-Z0-9\\S]{8,100}");
		Matcher n = passwordValidation.matcher(newUser.getPassword());
		if(newUser.getFirstName().length() <= 0){
			errors.add("First name length must be greater than 0.");
		}
		if(!m.matches()){
			errors.add("Alphanumeric characters with length between 6 to 100.");
		}
		if(!n.matches() || newUser.getPassword().contains("0")){
			errors.add("Alphanumeric characters with length between 8 to 100 and can't contain the 0 character.");
		}
		return errors;
	}
}
