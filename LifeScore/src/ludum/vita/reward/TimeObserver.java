package ludum.vita.reward;

public class TimeObserver {
	private static final long DAY = 24*60*60*60*1000;
	private static final long WEEK = 7 * DAY;
	private static final long MONTH = 4 * WEEK;
	private static final long YEAR = 12 * MONTH;
	
	public static boolean nextDay(long length){
		if(length >= DAY)
			return true;
		else 
			return false;
	}
	
	public static boolean nextWeek(long length){
		if(length >= WEEK)
			return true;
		else
			return false;
	}
	
	public static boolean nextMonth(long length){
		if(length >= MONTH)
			return true;
		else
			return false;
	}

	public static boolean nextYear(long length){
		if(length >= YEAR)
			return true;
		else 
			return false;
	}
}
