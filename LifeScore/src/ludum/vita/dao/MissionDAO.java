package ludum.vita.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import ludum.vita.beans.MissionBean;
import ludum.vita.beans.loaders.MissionLoader;
import ludum.vita.dbtools.DatabaseTool;

//TODO 
public class MissionDAO {

	private DatabaseFactory factory;
	private MissionLoader missionloader = new MissionLoader();

	public MissionDAO(DatabaseFactory factory) {
		this.factory = factory;
	}

	public long addMission(MissionBean mbean) throws Exception {
		String LSMIDGen = getLSMID(mbean);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("INSERT INTO missions (LSMID, LSUID, title, startDate, endDate, complete) VALUES (?,?,?,?,?,?)");
			ps.setString(1, LSMIDGen);
			ps.setString(2, mbean.getLSUID());
			ps.setString(3, mbean.getTitle());
			ps.setDate(4, new java.sql.Date(mbean.getStartDate().getTime()));
			ps.setDate(5, new java.sql.Date(mbean.getEndDate().getTime()));
			ps.setBoolean(6, mbean.isMissionComplete());
			ps.executeUpdate();
			ps.close();
			return DatabaseTool.getLastInsert(conn);
		} catch (SQLException e) {
			throw new Exception("Error connecting to database.");
		} finally {
			DatabaseTool.closeConnection(conn, ps);
		}
	}

	private String getLSMID(MissionBean mbean) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		String input = mbean.getTitle() + mbean.getStartDateString() + mbean.getLSUID() + new Random().nextInt();
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public void updateMission(MissionBean missionbean) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("UPDATE missions SET title = ?, startDate = ?, "
					+ " endDate = ?, complete = ? WHERE LSMID = ? AND LSUID = ? ");
			ps.setString(1, missionbean.getTitle());
			ps.setDate(2, new java.sql.Date(missionbean.getStartDate().getTime()));
			ps.setDate(3, new java.sql.Date(missionbean.getEndDate().getTime()));
			ps.setBoolean(4, missionbean.isMissionComplete());
			ps.setString(5, missionbean.getLSMID());
			ps.setString(6, missionbean.getLSUID());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new Exception("Error connecting to database.");
		} finally {
			DatabaseTool.closeConnection(conn, ps);
		}
	}

	public MissionBean getMission(String LSMID) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("SELECT * FROM missions WHERE LSMID = ?");
			ps.setString(1, LSMID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				MissionBean result = missionloader.loadSingle(rs); 
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

	public List<MissionBean> getAllMissionsForUser(String LSUID) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT * FROM missions WHERE LSUID = ?");
			ps.setString(1, LSUID);
			ResultSet rs = ps.executeQuery();
			List<MissionBean> loadlist = missionloader.loadList(rs);
			rs.close();
			ps.close();
			return loadlist;
		} catch (SQLException e) {
			throw new Exception("Error connecting to database.");
		} finally {
			DatabaseTool.closeConnection(conn, ps);
		}
	}

	public void removeMission(MissionBean missionBean) throws Exception  {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("DELETE FROM missions WHERE LSMID = ? AND LSUID = ?");
			ps.setString(1, missionBean.getLSMID());
			ps.setString(2, missionBean.getLSUID());
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
	
	//FOR TESTING ONLY
	public List<MissionBean> getAllMissions() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT * FROM missions");
			ResultSet rs = ps.executeQuery();
			List<MissionBean> loadlist = missionloader.loadList(rs);
			rs.close();
			ps.close();
			return loadlist;
		} catch (SQLException e) {
			throw new Exception("Error connecting to database.");
		} finally {
			DatabaseTool.closeConnection(conn, ps);
		}
	}

}
