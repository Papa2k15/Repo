package ludum.vita.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import ludum.vita.beans.UserBean;
import ludum.vita.beans.loaders.UserLoader;
import ludum.vita.dbtools.DatabaseTool;
import ludum.vita.security.PasswordManager;

//TODO 
public class UsersDAO {
		
		private DatabaseFactory factory;
		private PasswordManager p;
		private UserLoader userloader = new UserLoader();

		public UsersDAO(DatabaseFactory factory) {
			this.factory = factory;
			p = PasswordManager.getPasswordConfiguration();
		}
		
		public long addUser(UserBean ubean) throws Exception {
			if(checkUserName(ubean.getUserName())){
				throw new Exception("User already exists in database");
			}
			String LSUIDGen = getLSUID(ubean);
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("INSERT INTO users (LSUID, firstName, lastName, userName, password, email) VALUES (?,?,?,?,?,?)");
				ps.setString(1, LSUIDGen);
				ps.setString(2, ubean.getFirstName());
				ps.setString(3, ubean.getLastName());
				ps.setString(4, ubean.getUserName());
				ps.setString(5, p.securePassword(ubean.getPassword()));
				ps.setString(6, ubean.getEmail());
				ps.executeUpdate();
				ps.close();
				return DatabaseTool.getLastInsert(conn);
			} catch (SQLException e) {
				throw new Exception("Error connecting to database.");
			} finally {
				DatabaseTool.closeConnection(conn, ps);
			}
		}
		
		private String getLSUID(UserBean ubean) throws NoSuchAlgorithmException {
			 MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			  String input = ubean.getFirstName() + ubean.getUserName() + ubean.getPassword() + new Random().nextInt();
		      byte[] result = mDigest.digest(input.getBytes());
		      StringBuffer sb = new StringBuffer();
		      for (int i = 0; i < result.length; i++) {
		          sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		      }
		      return sb.toString();
		}

		public void updateUser(UserBean userbean) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, userName = ?, "
						+ " password = ?, email = ? WHERE LSUID = ?");
				ps.setString(1, userbean.getFirstName());
				ps.setString(2, userbean.getLastName());
				ps.setString(3, userbean.getUserName());
				ps.setString(4, p.securePassword(userbean.getPassword()));
				ps.setString(5, userbean.getEmail());
				ps.setString(6, userbean.getLSUID());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				throw new Exception("Error connecting to database.");
			} finally {
				DatabaseTool.closeConnection(conn, ps);
			}
		}

		public UserBean getUser(String userName, String LSUID) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("SELECT * FROM users WHERE userName = ? OR LSUID = ?");
				ps.setString(1, userName);
				ps.setString(2, LSUID);
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

		public boolean checkUserName(String userName) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("SELECT * FROM users WHERE userName=?");
				ps.setString(1, userName);
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
		
		public void removeUser(String userName, String LSUID) throws Exception  {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = factory.getConnection();
				ps = conn.prepareStatement("DELETE FROM users WHERE userName = ? OR LSUID = ?");
				ps.setString(1, userName);
				ps.setString(2, LSUID);
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
