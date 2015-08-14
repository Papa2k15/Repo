package ludum.vita.reward;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

public class TimeObserver extends Thread{
	
	private long timeRemaining;
	private Date end;
	
	public TimeObserver() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		end = new D
		timeRemaining = new Date().getTime() - end.getTime();
	}

	public static void main(String[] args){
		TimeObserver t = new TimeObserver();
		t.start();
        System.out.println("Daily started");
	}

	@Override
	public void run() {
		while(true){
			timeRemaining = new Date().getTime() - end.getTime();
			System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date(timeRemaining)));
		}
	}
}
