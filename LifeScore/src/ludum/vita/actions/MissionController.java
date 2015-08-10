package ludum.vita.actions;

import java.util.List;

import ludum.vita.beans.MissionBean;
import ludum.vita.dao.DatabaseFactory;
import ludum.vita.dao.MissionDAO;

public class MissionController {

	private String loggedLSUID;

	private MissionDAO missionHandler;

	public MissionController(String loggedLSUID, DatabaseFactory factory){
		this.setLoggedLSUID(loggedLSUID);
		missionHandler = factory.getMissionsDAO();
	}

	public void addMission(MissionBean mission) throws Exception{
		missionHandler.addMission(mission);
	}
	
	public void removeMission(String LSMID) throws Exception{
		MissionBean removeMission = missionHandler.getMission(LSMID);
		missionHandler.removeMission(removeMission);
	}
	
	public List<MissionBean> getAllMissionsForUser() throws Exception{
		return missionHandler.getAllMissionsForUser(loggedLSUID);
	}

	/**
	 * @return the loggedLSUID
	 */
	public String getLoggedLSUID() {
		return loggedLSUID;
	}

	/**
	 * @param loggedLSUID the loggedLSUID to set
	 */
	private void setLoggedLSUID(String loggedLSUID) {
		this.loggedLSUID = loggedLSUID;
	}
}
