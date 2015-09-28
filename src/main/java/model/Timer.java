package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

public class Timer {

	private static Boolean started = false;

	private static Timeline timeline;
	public static StringProperty timeCounter = new SimpleStringProperty();

	private static int minutes;
	private static double seconds;
	private static Duration time = Duration.ZERO;

	public static void startTime() {

		if (!started) {
			started = true;
			timeline = new Timeline(new KeyFrame(Duration.millis(100), t -> {
				Duration duration = ((KeyFrame) t.getSource()).getTime();
				time = time.add(duration);
				seconds = time.toSeconds();
				if (seconds >= 60) {
					time = Duration.ZERO;
					minutes++;
				}
				if (seconds < 10)
					timeCounter.set(minutes + ":0" + seconds);
				else
					timeCounter.set(minutes + ":" + seconds);
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
