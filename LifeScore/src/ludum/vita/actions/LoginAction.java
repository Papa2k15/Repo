package ludum.vita.actions;

import ludum.vita.beans.UserBean;
import ludum.vita.dao.DatabaseFactory;
import ludum.vita.dao.UsersDAO;
import ludum.vita.security.PasswordManager;

public class LoginAction {

	private UsersDAO users;
	
	private String loggedLSUID;
	
	private static final PasswordManager p = PasswordManager.getPasswordConfiguration();
	
	public LoginAction(DatabaseFactory factory){
		users = factory.getUsersDAO();
	}
	
	public boolean login(String userName, String password) throws Exception{
		UserBean currentUser = users.getUser(userName);
		if(currentUser != null){
			if(password.equalsIgnoreCase(p.restorePassword(currentUser.getPassword()))){
				loggedLSUID = currentUser.getLSUID();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public String getCurrentLoggedLSUID(){
		return loggedLSUID;
	}
}
