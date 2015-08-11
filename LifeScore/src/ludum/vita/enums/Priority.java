package ludum.vita.enums;

public enum Priority {
	LOW("Low"),
	NORMAL("Normal"),
	HIGH("High");
	
	private String priority;
	
	private Priority(String priority){
		this.priority = priority;
	}

	public String getPriority(){
		return priority;
	}
}
