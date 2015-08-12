package ludum.vita.reward;

import java.text.ParseException;
import java.util.Date;

import ludum.vita.beans.MissionBean;
import ludum.vita.enums.Priority;

public class PointsManager {
	
	private static final int MISSION_COMPLETE_POINTS = 1;
	private static final int HIGH_POINTS = 4;
	private static final int NORMAL_POINTS = 2;
	private static final int LOW_POINTS = 1;
	private static final int DAY_POINTS = 1; 
	private static final int WEEK_POINTS = 2; 
	private static final int MONTH_POINTS = 3; 
	private static final int YEAR_POINTS = 5; 
	private static final long DAY = 24*60*60*60*1000;
	private static final long WEEK = 7 * DAY;
	private static final long MONTH = 4 * WEEK;
	private static final long YEAR = 12 * MONTH;

	private PointsManager(){}
	
	public static int calculateMissionPoints(MissionBean missionBean) throws ParseException, Exception{
		return missionLength(missionBean.getStartDate(), missionBean.getEndDate())+
				missionComplete() + missionPriority(missionBean.getPriority());
	}

	private static int missionLength(Date start, Date end){
		long length = end.getTime() - start.getTime();
		if (length < WEEK && length >= DAY){
			return DAY_POINTS;
		} else if (length < MONTH && length >= WEEK){
			return WEEK_POINTS;
		} else if (length < YEAR && length >= MONTH){
			return MONTH_POINTS;
		} else if (length >= YEAR) {
			return YEAR_POINTS;
		} else {
			return 0;
		}
	}
	
	private static int missionPriority(Priority p) throws Exception{
		if(Priority.HIGH == p){
			return HIGH_POINTS;
		} else if(Priority.NORMAL == p) {
			return NORMAL_POINTS;
		} else if(Priority.LOW == p) {
			return LOW_POINTS;
		}
		throw new Exception("Can't process priority" + p);
	}
	
	private static int missionComplete(){
		return MISSION_COMPLETE_POINTS;
	}
}
