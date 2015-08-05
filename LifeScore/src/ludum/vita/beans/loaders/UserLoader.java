/**
 * 
 */
package ludum.vita.beans.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ludum.vita.beans.UserBean;
import ludum.vita.security.PasswordManager;

/**
 * @author Owner
 *
 */
public class UserLoader implements BeanLoader<UserBean> {

	private PasswordManager p = PasswordManager.getPasswordConfiguration();
	
	@Override
	public UserBean loadSingle(ResultSet rs) throws SQLException {
		UserBean uBean = 
				new UserBean(rs.getString("LSUID"), 
						rs.getString("firstName"), 
						rs.getString("userName"), 
						p.restorePassword(rs.getString("password")));
		uBean.setLastName(rs.getString("lastName"));
		uBean.setEmail(rs.getString("email"));
		return uBean;
	}

	@Override
	public List<UserBean> loadList(ResultSet rs) throws SQLException {
		List<UserBean> users = new LinkedList<UserBean>();
		while (rs.next()) {
			users.add(loadSingle(rs));
		}
		return users;
	}
	
	@Override
	public PreparedStatement loadParameters(PreparedStatement ps, UserBean bean) {
		return null;
	}

}
