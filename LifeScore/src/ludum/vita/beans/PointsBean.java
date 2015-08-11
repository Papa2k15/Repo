package ludum.vita.beans;

public class PointsBean {
	
	private String LSUID = "";
	
	private long total = 0;
	
	private long daily = 0;
	
	private long weekly = 0;
	
	private long monthly = 0;
	
	private long yearly = 0;
	
	public PointsBean(long total, long daily, long weekly, long monthly, long yearly){
		this.settotal(total);
		this.setDaily(daily);
		this.setWeekly(weekly);
		this.setMonthly(monthly);
		this.setYearly(yearly);
	}
	
	public PointsBean(String LSUID, long total, long daily, long weekly, long monthly, long yearly){
		this(total,daily,weekly,monthly,yearly);
		this.setLSUID(LSUID);
	}

	public long gettotal() {
		return total;
	}

	public void settotal(long total) {
		this.total = total;
	}

	public long getDaily() {
		return daily;
	}

	public void setDaily(long daily) {
		this.daily = daily;
	}

	public long getWeekly() {
		return weekly;
	}

	public void setWeekly(long weekly) {
		this.weekly = weekly;
	}

	public long getMonthly() {
		return monthly;
	}

	public void setMonthly(long monthly) {
		this.monthly = monthly;
	}

	public long getYearly() {
		return yearly;
	}

	public void setYearly(long yearly) {
		this.yearly = yearly;
	}

	public String getLSUID() {
		return LSUID;
	}

	public void setLSUID(String lSUID) {
		LSUID = lSUID;
	}
}
