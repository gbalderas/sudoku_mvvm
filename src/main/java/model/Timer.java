package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

//TODO use Apache Commons time.Stopwatch instead of Timeline
public class Timer {

	private static Boolean started = false;

	private static Timeline timeline;
	public static StringProperty timeCounter = new SimpleStringProperty();

	private static int minutes;
	private static int seconds;
	private static Duration time = Duration.ZERO;

	public static void startTime() {

		if (!started) {
			started = true;
			timeCounter.set("0:00");
			timeline = new Timeline(new KeyFrame(Duration.seconds(1), t -> {
				time = time.add(Duration.seconds(1));
				if (seconds == 59) {
					time = Duration.ZERO;
					minutes++;
				}
				seconds = (int) time.toSeconds();
				if (seconds > 9)
					timeCounter.set(minutes + ":" + seconds);
				else
					timeCounter.set(minutes + ":0" + seconds);
			}));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		}
	}

	public static void stopTime() {
		if (started) {
			// Pause
			GameInfo.pauseString.set("Continue?");
			started = false;
			GameInfo.disableGrid();
			timeline.stop();
		} else // Resume
			if (GameInfo.pauseString.get().equals("Continue?")) {
				GameInfo.pauseString.set("Pause Game");
				started = true;
				GameInfo.enableGrid();
				timeline.play();
			}
	}

	public static void resetTime() {
		time = Duration.ZERO;
		minutes = 0;
		seconds = 0;
		timeCounter.set(minutes + ":" + time.toMillis());
		GameInfo.pauseString.set("Pause Game");
		startTime();
	}

}
