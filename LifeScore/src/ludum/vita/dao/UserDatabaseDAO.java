package ludum.vita.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ludum.vita.beans.UserBean;
import ludum.vita.beans.loaders.UserLoader;
import ludum.vita.dbtools.DatabaseTool;

import com.fourh.security.PasswordManager;
//TODO 
public class UserDatabaseDAO {
		
		private DatabaseFactory factory;
		private PasswordManager p;
		private UserLoader userloader = new UserLoader();

		public UserDatabaseDAO(DatabaseFactory factory) {
			this.factory = factory;
			p = PasswordManager.getPasswordConfiguration();
		}
		
		public long addUser(UserBean ubean) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("INSERT INTO users (fName, lName, uName, pWord) VALUES (?,?,?,?)");
				ps.setString(1, ubean.getFirstName());
				ps.setString(2, ubean.getLastName());
				ps.setString(3, ubean.getUserName());
				ps.setString(4, p.securePassword(ubean.getPassword()));
				ps.executeUpdate();
				ps.close();
				return DatabaseTool.getLastInsert(conn);
			} catch (SQLException e) {
				throw new Exception("Error connecting to database.");
			} finally {
				DatabaseTool.closeConnection(conn, ps);
			}
		}
		
		public void updateUser(UserBean userbean) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("UPDATE users SET fName = ? , lName = ?, pWord = ? WHERE uName = ?");
				ps.setString(1, userbean.getFirstName());
				ps.setString(2, userbean.getLastName());
				ps.setString(3, userbean.getPassword());
				ps.setString(4, userbean.getUserName());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				throw new Exception("Error connecting to database.");
			} finally {
				DatabaseTool.closeConnection(conn, ps);
			}
		}

		public UserBean getUser(String uName) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("SELECT * FROM users WHERE uName = ?");
				ps.setString(1, uName);
				ResultSet rs = ps.executeQuery();
				if (rs.next()){
					UserBean result = userloader.loadSingle(rs); 
					rs.close();
					ps.close();
					return result;
				}
				else{
					rs.close();
					ps.close();
					return null;
				}
			} catch (SQLException e) {
				throw new Exception("Error connecting to database.");
			} finally {
				DatabaseTool.closeConnection(conn, ps);
			}
		}

		public boolean checkUserName(String uname) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("SELECT * FROM users WHERE uName=?");
				ps.setString(1, uname);
				ResultSet rs = ps.executeQuery();
				boolean check = rs.next();
				rs.close();
				ps.close();
				return check;
			} catch (SQLException e) {
				throw new Exception("Error connecting to database.");
			} finally {
				DatabaseTool.closeConnection(conn, ps);
			}
		}

		public List<UserBean> getAllUsers() throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn
						.prepareStatement("SELECT * FROM users");
				ResultSet rs = ps.executeQuery();
				List<UserBean> loadlist = userloader.loadList(rs);
				rs.close();
				ps.close();
				return loadlist;
			} catch (SQLException e) {
				throw new Exception("Error connecting to database.");
			} finally {
				DatabaseTool.closeConnection(conn, ps);
			}
		}
		
		public void removeUser(String uName) throws Exception  {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("DELETE FROM users WHERE uName = ?");
				ps.setString(1, uName);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				throw new Exception("Error connecting to database.");
			} finally {
				DatabaseTool.closeConnection(conn, ps);
			}
		}
		
		//FOR TESTING ONLY
		@SuppressWarnings("resource")
		public void clearUserTable() throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("DELETE FROM users");
				ps.executeUpdate();
				ps = conn.prepareStatement("ALTER TABLE users AUTO_INCREMENT = 0");
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				throw new Exception("Error connecting to database.");
			} finally {
				DatabaseTool.closeConnection(conn, ps);
			}
		}

}
