package ludum.vita.beans;

public class PointsBean {
	
	private String LSUID = "";
	
	private long total = 0;
	
	private long daily = 0;
	
	private long weekly = 0;
	
	private long monthly = 0;
	
	private long yearly = 0;
	
	private boolean dReset = false;
	
	private boolean wReset = false;
	
	private boolean mReset = false;
	
	private boolean yReset = false;

	public PointsBean(String LSUID, long total){
		this.setLSUID(LSUID);
		this.settotal(total);
	}

	public String getLSUID() {
		return LSUID;
	}

	public void setLSUID(String lSUID) {
		LSUID = lSUID;
	}

	public long gettotal() {
		return total;
	}

	public void settotal(long total) {
		this.total = total;
	}

	/**
	 * @return the daily
	 */
	public long getDaily() {
		return daily;
	}

	/**
	 * @param daily the daily to set
	 */
	public void setDaily(long daily) {
		this.daily = daily;
	}

	/**
	 * @return the weekly
	 */
	public long getWeekly() {
		return weekly;
	}

	/**
	 * @param weekly the weekly to set
	 */
	public void setWeekly(long weekly) {
		this.weekly = weekly;
	}

	/**
	 * @return the monthly
	 */
	public long getMonthly() {
		return monthly;
	}

	/**
	 * @param monthly the monthly to set
	 */
	public void setMonthly(long monthly) {
		this.monthly = monthly;
	}

	/**
	 * @return the yearly
	 */
	public long getYearly() {
		return yearly;
	}

	/**
	 * @param yearly the yearly to set
	 */
	public void setYearly(long yearly) {
		this.yearly = yearly;
	}

	public boolean isdReset() {
		return dReset;
	}

	public void setdReset(boolean dReset) {
		this.dReset = dReset;
	}

	public boolean iswReset() {
		return wReset;
	}

	public void setwReset(boolean wReset) {
		this.wReset = wReset;
	}

	public boolean ismReset() {
		return mReset;
	}

	public void setmReset(boolean mReset) {
		this.mReset = mReset;
	}

	public boolean isyReset() {
		return yReset;
	}

	public void setyReset(boolean yReset) {
		this.yReset = yReset;
	}
}
