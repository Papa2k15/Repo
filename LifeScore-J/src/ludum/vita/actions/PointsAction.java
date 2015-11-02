package ludum.vita.actions;

import java.util.Calendar;

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
	private TimeObserver timeObserver;
	private PointsBean userPoints;
	
	public PointsAction(DatabaseFactory factory, String loggedLSUID) throws Exception{
		pointsDAO = factory.getPointsDAO();
		userPoints = pointsDAO.getUserPoints(loggedLSUID);
		missionDAO = factory.getMissionsDAO();
		this.loggedLSUID = loggedLSUID;
		timeObserver = new TimeObserver(pointsDAO, Calendar.getInstance(), loggedLSUID);
		timeObserver.start();
	}
	
	public synchronized void checkMissionsComplete() throws Exception {
		int total = 0;
		for(MissionBean m : missionDAO.getAllMissionsForUser(loggedLSUID)){
			if(m.isMissionComplete() && !m.isPointsEarned()){
				total += PointsManager.calculateMissionPoints(m);
				m.setPointsEarned(true);
				missionDAO.updateMission(m);
			}
		}
		userPoints.settotal(userPoints.gettotal()+total);
		userPoints.setDaily(userPoints.getDaily()+total);
		userPoints.setWeekly(userPoints.getWeekly()+total);
		userPoints.setMonthly(userPoints.getMonthly()+total);
		userPoints.setYearly(userPoints.getYearly()+total);
		pointsDAO.updateUserPoints(userPoints);
	}
	
	public PointsBean getUserPointsBean(){
		return userPoints;
	}
}
