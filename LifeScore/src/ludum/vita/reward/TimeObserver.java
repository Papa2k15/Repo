package ludum.vita.reward;

import java.util.Calendar;

import ludum.vita.beans.PointsBean;
import ludum.vita.dao.PointsDAO;

public class TimeObserver extends Thread{

	private static final long CHECK = 10000;
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
			System.out.println("DAY CHECK");
			try {
				if(c.get(Calendar.HOUR_OF_DAY) == 0 && !pointsBean.isdReset()){
					pointsBean.setDaily(0);
					pointsBean.setdReset(true);
				} else {
					pointsBean.setdReset(false);
				}
				System.out.println("WEEK CHECK");
				if(c.get(Calendar.DAY_OF_WEEK) == 1 && !pointsBean.iswReset()){
					pointsBean.setWeekly(0);
					pointsBean.setwReset(true);
				} else {
					pointsBean.setwReset(false);
				}
				System.out.println("MONTH CHECK");
				if(c.get(Calendar.WEEK_OF_MONTH) == 1 && !pointsBean.ismReset()){
					pointsBean.setMonthly(0);
					pointsBean.setmReset(true);
				} else {
					pointsBean.setmReset(false);
				}
				System.out.println("YEAR CHECK");
				if(c.get(Calendar.DAY_OF_YEAR) == 1 && !pointsBean.isyReset()){
					pointsBean.setYearly(0);
					pointsBean.setyReset(true);
				} else {
					pointsBean.setyReset(false);
				}
				pointsHandler.updateUserPoints(pointsBean);
			} catch (Exception e){
				System.out.println("ERROR WITH UPDATING");
			}
			try {
				Thread.sleep(CHECK);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
