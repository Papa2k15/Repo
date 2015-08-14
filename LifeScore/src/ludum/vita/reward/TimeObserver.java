package ludum.vita.reward;

import java.util.Calendar;

public class TimeObserver extends Thread{

	private static final long MIN_CHECK = 60000;
	private long timeRemaining;

	public TimeObserver() {
		timeRemaining = 24-Calendar.HOUR_OF_DAY;
	}

	public static void main(String[] args){
		TimeObserver t = new TimeObserver();
		t.start();
		System.out.println("Daily started");
	}

	@Override
	public void run() {
		while(true){
			timeRemaining = 24-Calendar.HOUR_OF_DAY;
			if(timeRemaining == 0){

			}
			try {
				Thread.sleep(MIN_CHECK);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}

		}
	}
}
