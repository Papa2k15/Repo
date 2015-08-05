package ludum.vita.enums;

public enum TrackerType {
	MONETARY("money"),
	DOUBLE("Double");
	
	private String trackerType;
	
	private TrackerType(String trackerType){
		this.trackerType = trackerType;
	}
	
	public String getCanonicalType(){
		return trackerType;
	}
}