package ludum.vita.beans;

public class ObjectiveBean {
	
	private String LSOID;
	
	private String LSMID;
	
	private String description;
	
	private String trackerValue;
	
	/**
	 * @param LSMID
	 * @param description
	 */
	public ObjectiveBean(String LSMID, String description){
		this.setLSMID(LSMID);
		this.setDescription(description);
	}
	
	/**
	 * @param LSOID
	 * @param LSMID
	 * @param description
	 */
	public ObjectiveBean(String LSOID, String LSMID, String description){
		this(LSMID, description);
		this.setLSOID(LSOID);
	}

	/**
	 * @return the lSOID
	 */
	public String getLSOID() {
		return LSOID;
	}

	/**
	 * @param lSOID the lSOID to set
	 */
	public void setLSOID(String lSOID) {
		LSOID = lSOID;
	}

	/**
	 * @return the lSMID
	 */
	public String getLSMID() {
		return LSMID;
	}

	/**
	 * @param lSMID the lSMID to set
	 */
	public void setLSMID(String lSMID) {
		LSMID = lSMID;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the trackerValue
	 */
	public String getTrackerValue() {
		return trackerValue;
	}

	/**
	 * @param trackerValue the trackerValue to set
	 */
	public void setTrackerValue(String trackerValue) {
		this.trackerValue = trackerValue;
	}
}
