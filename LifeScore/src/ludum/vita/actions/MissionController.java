package ludum.vita.actions;

import java.util.LinkedList;
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

	public List<String> addMission(MissionBean mission) throws Exception{
		List<String> problems = new LinkedList<String>();
		if(mission.getTitle().length() <= 0){
			problems.add("Mission title length needs to be greater than 0.");
		}
		if(mission.getDescription().length() <= 0){
			problems.add("Mission description length needs to be greater than 0.");
		}
		if(mission.getLSUID().length() <= 0){
			problems.add("Valid user is not logged in.");
		}
		if(mission.getTrackerGoal() <= 0){
			problems.add("Goal need sto have a positive value.");
		}
		if(mission.getUnits().length() <= 0){
			problems.add("Mission title length needs to be greater than 0.");
		}
		if(problems.size() <= 0){
			missionHandler.addMission(mission);
		} 
		return problems;
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
