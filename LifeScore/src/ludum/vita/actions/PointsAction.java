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
//		if(TimeObserver.nextDay(today.getTime()-yesterday.getTime())){
//			userPoints.setDaily(0);	
//		}
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
