/**
 * 
 */
package ludum.vita.beans;

import java.util.List;

/**
 * A mission is simply a goal that a player plans to complete via
 * the objectives they set forth. 
 * @author Gregory Daniels
 * @version 1.0
 */
public class MissionBean {

	private String LSMID;
	
	private String LSUID;
	
	private String title;
	
	private List<ObjectiveBean> objectives;
	
	private String startDate;
	
	private String endDate;
	
	private boolean missionComplete;
	
	/**
	 * @param LSUID
	 * @param title
	 * @param objectives
	 */
	public MissionBean(String LSUID, String title, List<ObjectiveBean> objectives){
		this.setLSUID(LSUID);
		this.setTitle(title);
		this.setObjectives(objectives);
	}

	/**
	 * @param LSMID
	 * @param LSUID
	 * @param title
	 * @param objectives
	 */
	public MissionBean(String LSMID, String LSUID, String title, List<ObjectiveBean> objectives){
		this(LSUID,title,objectives);
		this.setLSMID(LSMID);
	}

	/**
	 * @return the lSUID
	 */
	public String getLSUID() {
		return LSUID;
	}

	/**
	 * @param lSUID the lSUID to set
	 */
	public void setLSUID(String lSUID) {
		LSUID = lSUID;
	}

	public String getLSMID() {
		return LSMID;
	}

	public void setLSMID(String lSMID) {
		LSMID = lSMID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ObjectiveBean> getObjectives() {
		return objectives;
	}

	public void setObjectives(List<ObjectiveBean> objectives) {
		this.objectives = objectives;
	}

	/**
	 * @return the missionComplete
	 */
	public boolean isMissionComplete() {
		return missionComplete;
	}

	/**
	 * @param missionComplete the missionComplete to set
	 */
	public void setMissionComplete(boolean missionComplete) {
		this.missionComplete = missionComplete;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
