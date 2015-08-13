package ludum.vita.actions;

import java.util.Calendar;
import java.util.Date;

import ludum.vita.beans.MissionBean;
import ludum.vita.beans.PointsBean;
import ludum.vita.dao.DatabaseFactory;
import ludum.vita.dao.MissionDAO;
import ludum.vita.dao.PointsDAO;
import ludum.vita.reward.PointsManager;
import ludum.vita.reward.TimeObserver;

public class PointsAction {
	
	private PointsDAO pointsDAO;
	private MissionDAO missionDAO;
	private String loggedLSUID;
	
	public PointsAction(DatabaseFactory factory, String loggedLSUID){
		pointsDAO = factory.getPointsDAO();
		missionDAO = factory.getMissionsDAO();
		this.loggedLSUID = loggedLSUID;
	}

	public void resetDayPoints() throws Exception{
		PointsBean userPoints = pointsDAO.getUserPoints(loggedLSUID);
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		c.add(Calendar.DATE, -1);
		Date yesterday = c.getTime();
		if(TimeObserver.nextDay(today.getTime()-yesterday.getTime())){
			userPoints.setDaily(0);	
		}
		pointsDAO.updateUserPoints(userPoints);
	}
	
	public void resetWeekPoints() throws Exception {
		PointsBean userPoints = pointsDAO.getUserPoints(loggedLSUID);
		Calendar c = Calendar.getInstance();
		Date thisWeek = c.getTime();
		c.add(Calendar.WEEK_OF_MONTH, -1);
		Date lastWeek = c.getTime();
		if(TimeObserver.nextWeek(thisWeek.getTime()-lastWeek.getTime())){
			userPoints.setWeekly(0);
		}
		pointsDAO.updateUserPoints(userPoints);
	}
	
	public void resetMonthPoints() throws Exception {
		PointsBean userPoints = pointsDAO.getUserPoints(loggedLSUID);
		Calendar c = Calendar.getInstance();
		Date thisMonth = c.getTime();
		c.add(Calendar.MONTH, -1);
		Date lastMonth = c.getTime();
		if(TimeObserver.nextMonth(thisMonth.getTime()-lastMonth.getTime())){
			userPoints.setMonthly(0);
		}
		pointsDAO.updateUserPoints(userPoints);
	}
	
	public void resetYearPoints() throws Exception {
		PointsBean userPoints = pointsDAO.getUserPoints(loggedLSUID);
		Calendar c = Calendar.getInstance();
		Date thisYear = c.getTime();
		c.add(Calendar.YEAR, -1);
		Date lastYear = c.getTime();
		if(TimeObserver.nextMonth(thisYear.getTime()-lastYear.getTime())){
			userPoints.setYearly(0);
		}
		pointsDAO.updateUserPoints(userPoints);
	}
	
	public void updatePoints() throws Exception {
		int totalPoints = 0;
		for(MissionBean m : missionDAO.getAllMissionsForUser(loggedLSUID)){
			if(m.isMissionComplete()){
				totalPoints += PointsManager.calculateMissionPoints(m);
			}
		}
		PointsBean userPoints = pointsDAO.getUserPoints(loggedLSUID);
		userPoints.settotal(totalPoints);
		pointsDAO.updateUserPoints(userPoints);
	}

	public static void main(String[] args){
		String gregsLSUID = "28cf1e5615f9a36b98084f30336c6a19554baead";
		PointsAction p = new PointsAction(DatabaseFactory.getProductionInstance(), gregsLSUID);
	}
}
