package ludum.vita.enums;

public enum Type {
	INTEGER("Integer"),
	DOUBLE("Double");
	
	private String type;
	
	private Type(String type){
		this.type = type;
	}
	
	public String getCanonicalType(){
		return type;
	}
}