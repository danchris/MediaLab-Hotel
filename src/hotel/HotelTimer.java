package hotel;

import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;


/*
 * @author Daniel Christodoulopoulos
 */


public class HotelTimer extends AnimationTimer{
	
	private static final long STOPPED = -1;
	private long startTime = STOPPED;
	private HotelInfoBar infoBar;
	
	public HotelTimer(HotelInfoBar i) {
		this.infoBar = i;
	}
	
	@Override
	public void handle(long timestamp) {
		if (startTime == STOPPED) {
			startTime = timestamp;
		}
		long elapsedNanos = timestamp - startTime;
		long elapsedMillis = elapsedNanos / 1_000_000;
		infoBar.getTotalTime().setText("Total Time: " + String.format("%02d : %02d",
				TimeUnit.MILLISECONDS.toHours(elapsedMillis), TimeUnit.MILLISECONDS.toMinutes(elapsedMillis)
						- TimeUnit.MINUTES.toMinutes(TimeUnit.MILLISECONDS.toHours(elapsedMillis))));		
	}
	
	@Override
	public void stop() {
		startTime = STOPPED;
		super.stop();
	}

}
