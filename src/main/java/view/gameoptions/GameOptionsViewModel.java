package view.gameoptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import de.saxsys.mvvmfx.ViewModel;

import model.GameInfo;
import model.NewGame;
import model.SudokuGenerator;
import model.Timer;

public class GameOptionsViewModel implements ViewModel {

	// TODO? show hints?

	public SudokuGenerator sg;
	public SimpleStringProperty paused = new SimpleStringProperty();
	public SimpleStringProperty timer = new SimpleStringProperty();
	public SimpleStringProperty difficulty = new SimpleStringProperty();

	private static GameOptionsViewModel INSTANCE;

	public GameOptionsViewModel() {
		INSTANCE = this;
		difficulty.bindBidirectional(NewGame.difficultyString);
		timer.bindBidirectional(Timer.timeCounter);
		paused.bindBidirectional(GameInfo.pauseString);
		difficulty.set("Normal");
		GameInfo.pauseString.set("Pause Game");
	}

	public static GameOptionsViewModel getInstance() {
		return INSTANCE;
	}

	public void startNewGame() {
		NewGame.startNewGame();
	}

	public void startTimer() {
		Timer.startTime();
	}

	public void stopTimer() {
		Timer.stopTime();
	}

	public void setDifficulty(String text) {
		difficulty.set(text);
		changeDifficulty();
		startNewGame();
	}

	private void changeDifficulty() {
		NewGame.changeDifficulty();
	}

	public void autoMarkFields() throws InterruptedException {
		for (int i = 0; i < 81; i++) {
			TextField tf = GameInfo.listOfFields.get(i);
			if (tf.isDisabled())
				continue;
			tf.requestFocus();
			tf.getContextMenu().getItems().get(2).fire();
			tf.getContextMenu().getItems().get(6).fire();
		}
	}

	public void autoFillRegion() {
		Label l[] = new Label[9];
		l[0] = GameInfo.listOfLabels.get(10 - 10);
		l[1] = GameInfo.listOfLabels.get(10 - 9);
		l[2] = GameInfo.listOfLabels.get(10 - 8);
		l[3] = GameInfo.listOfLabels.get(10 - 1);
		l[4] = GameInfo.listOfLabels.get(10);
		l[5] = GameInfo.listOfLabels.get(10 + 1);
		l[6] = GameInfo.listOfLabels.get(10 + 8);
		l[7] = GameInfo.listOfLabels.get(10 + 9);
		l[8] = GameInfo.listOfLabels.get(10 + 10);

		List<int[]> list = new ArrayList<>();
		int[] n = null;
		for (int i = 0; i < 9; i++) {
			if (!l[i].isVisible())
				continue;
			String s = l[i].getText();
			int[] numbers = Arrays.stream(s.split("\\s+")).mapToInt(Integer::parseInt).toArray();
			n = numbers;
			list.add(numbers);
		}

		Arrays.sort(n);

		for (int j = 1; j < n.length; j++)
			if (n[j] == n[j - 1])
				System.out.println("Duplicate: " + n[j]);
	}

}
