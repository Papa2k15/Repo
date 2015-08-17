package ludum.vita.reward;

import java.util.Calendar;

import ludum.vita.beans.PointsBean;
import ludum.vita.dao.PointsDAO;

public class TimeObserver extends Thread{

	private static final long CHECK = 5000;
	@SuppressWarnings("unused")
	private static final long PAUSE = 1000;
	private PointsDAO pointsHandler;
	private PointsBean pointsBean;
	private Calendar c;

	public TimeObserver(PointsDAO pointsDAO, Calendar calander, String LSUID) throws Exception {
		c = calander;
		pointsHandler = pointsDAO;
		pointsBean = pointsHandler.getUserPoints(LSUID);
	}

	@Override
	public void run() {
		while(true){
			try {
				if(c.getActualMaximum(Calendar.HOUR_OF_DAY)-c.get(Calendar.HOUR_OF_DAY) == 0 && !pointsBean.isdReset()){
					pointsBean.setDaily(0);
					pointsBean.setdReset(true);
				} else {
					pointsBean.setdReset(false);
				}
				if(c.getActualMaximum(Calendar.DAY_OF_WEEK)-c.get(Calendar.DAY_OF_WEEK) == 0 && !pointsBean.iswReset()){
					pointsBean.setWeekly(0);
					pointsBean.setwReset(true);
				} else {
					pointsBean.setwReset(false);
				}
				if(c.getActualMaximum(Calendar.DAY_OF_MONTH)-c.get(Calendar.DAY_OF_MONTH) == 0 && !pointsBean.ismReset()){
					pointsBean.setMonthly(0);
					pointsBean.setmReset(true);
				} else {
					pointsBean.setmReset(false);
				}
				if(c.getActualMaximum(Calendar.DAY_OF_YEAR)-c.get(Calendar.DAY_OF_YEAR) == 0 && !pointsBean.isyReset()){
					pointsBean.setYearly(0);
					pointsBean.setyReset(true);
				} else {
					pointsBean.setyReset(false);
				}
				pointsHandler.updateUserPoints(pointsBean);
			} catch (Exception e){
				System.out.println("Error connecting to database.");
			}
			try {
				Thread.sleep(CHECK);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
