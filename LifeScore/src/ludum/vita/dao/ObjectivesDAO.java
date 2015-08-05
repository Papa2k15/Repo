package ludum.vita.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import ludum.vita.beans.ObjectiveBean;
import ludum.vita.beans.loaders.ObjectiveLoader;
import ludum.vita.dbtools.DatabaseTool;

//TODO 
public class ObjectivesDAO {

	private DatabaseFactory factory;
	private ObjectiveLoader objectiveloader = new ObjectiveLoader();

	public ObjectivesDAO(DatabaseFactory factory) {
		this.factory = factory;
	}

	public long addObjective(ObjectiveBean oBean) throws Exception {
		String LSOIDGen = getLSOID(oBean);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("INSERT INTO objectives (LSOID, LSMID, description, trackerVal) VALUES (?,?,?,?)");
			ps.setString(1, LSOIDGen);
			ps.setString(2, oBean.getLSMID());
			ps.setString(3, oBean.getDescription());
			ps.setString(4, oBean.getTrackerValue());
			ps.executeUpdate();
			ps.close();
			return DatabaseTool.getLastInsert(conn);
		} catch (SQLException e) {
			throw new Exception("Error connecting to database.");
		} finally {
			DatabaseTool.closeConnection(conn, ps);
		}
	}

	private String getLSOID(ObjectiveBean obean) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		String input = obean.getDescription() + obean.getLSMID() + new Random().nextInt();
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public void updateObjective(ObjectiveBean objectiveBean) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("UPDATE objectives SET description = ?, trackerVal = ? "
					+ " WHERE LSOID = ? AND LSMID = ? ");
			ps.setString(1, objectiveBean.getDescription());
			ps.setString(2, objectiveBean.getTrackerValue());
			ps.setString(3, objectiveBean.getLSOID());
			ps.setString(4, objectiveBean.getLSMID());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new Exception("Error connecting to database.");
		} finally {
			DatabaseTool.closeConnection(conn, ps);
		}
	}

	public ObjectiveBean getObjective(String LSOID) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("SELECT * FROM objectives WHERE LSOID = ?");
			ps.setString(1, LSOID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				ObjectiveBean result = objectiveloader.loadSingle(rs); 
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

	public List<ObjectiveBean> getAllObjectivesForMission(String LSOID, String LSMID) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT * FROM objectives WHERE LSOID = ? AND LSMID = ?");
			ps.setString(1, LSOID);
			ps.setString(2, LSMID);
			ResultSet rs = ps.executeQuery();
			List<ObjectiveBean> loadlist = objectiveloader.loadList(rs);
			rs.close();
			ps.close();
			return loadlist;
		} catch (SQLException e) {
			throw new Exception("Error connecting to database.");
		} finally {
			DatabaseTool.closeConnection(conn, ps);
		}
	}

	public void removeObjective(ObjectiveBean oBean) throws Exception  {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("DELETE FROM objectives WHERE LSOID = ? AND LSMID = ?");
			ps.setString(1, oBean.getLSOID());
			ps.setString(2, oBean.getLSMID());
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
	public void clearObjectivesTable() throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("DELETE FROM objectives");
			ps.executeUpdate();
			ps = conn.prepareStatement("ALTER TABLE objectives AUTO_INCREMENT = 0");
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new Exception("Error connecting to database.");
		} finally {
			DatabaseTool.closeConnection(conn, ps);
		}
	}
}
