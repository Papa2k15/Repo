/**
 * 
 */
package ludum.vita.beans;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A mission is simply a goal that a player plans to complete via
 * the objectives they set forth. 
 * @author Gregory Daniels
 * @version 1.0
 */
public class MissionBean {

	private String LSMID = "";
	
	private String LSUID = "";
	
	private String title = "";
		
	private String startDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
	
	private String endDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
	
	private boolean missionComplete = false;
	
	/**
	 * @param LSUID
	 * @param title
	 * @param objectives
	 */
	public MissionBean(String LSUID, String title){
		this.setLSUID(LSUID);
		this.setTitle(title);
	}

	/**
	 * @param LSMID
	 * @param LSUID
	 * @param title
	 * @param objectives
	 */
	public MissionBean(String LSMID, String LSUID, String title){
		this(LSUID,title);
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

	public String getStartDateString() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public Date getStartDate() throws ParseException {
		Date d = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
		return d;
	}
	
	public Date getEndDate() throws ParseException {
		Date d = new SimpleDateFormat("MM/dd/yyyy").parse(endDate);
		return d;
	}


	public String getEndDateString() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
