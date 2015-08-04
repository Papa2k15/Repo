/**
 * 
 */
package ludum.vita.beans.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ludum.vita.beans.UserBean;

/**
 * @author Owner
 *
 */
public class UserLoader implements BeanLoader<UserBean> {

	@Override
	public UserBean loadSingle(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement loadParameters(PreparedStatement ps, UserBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserBean> loadList(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
