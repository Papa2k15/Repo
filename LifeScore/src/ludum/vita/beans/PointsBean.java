package ludum.vita.beans;

public class PointsBean {
	
	private String LSUID = "";
	
	private long total = 0;
	
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
}
