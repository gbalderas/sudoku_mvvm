package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

import view.grids.GridViewModel;

public class Timer {

	private Boolean started = false;

	private Timeline timeline;
	public StringProperty timeCounter = new SimpleStringProperty();
	public StringProperty pauseString = new SimpleStringProperty();

	private int minutes;
	private double seconds;
	private Duration time = Duration.ZERO;

	public void startTime() {

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

	public void stopTime() {
		if (started) {
			// Pause
			pauseString.set("Continue?");
			started = false;
			GridViewModel.getInstance().disableGrid();
			timeline.stop();
		} else // Resume
			if (pauseString.get().equals("Continue?")) {
				pauseString.set("Pause Game");
				started = true;
				GridViewModel.getInstance().enableGrid();
				timeline.play();
			}

	}

	public void resetTime() {
		time = Duration.ZERO;
		minutes = 0;
		timeCounter.set(minutes + ":" + time.toMillis());
		pauseString.set("Pause Game");
	}

	public StringProperty timeCounterProperty() {
		return this.timeCounter;
	}

	public String getTimeCounter() {
		return this.timeCounterProperty().get();
	}

}
