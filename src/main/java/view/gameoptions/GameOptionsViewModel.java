package view.gameoptions;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import de.saxsys.mvvmfx.ViewModel;

import model.NewGame;
import model.SudokuGenerator;
import model.Timer;

public class GameOptionsViewModel implements ViewModel {

	// TODO? show hints?

	public SudokuGenerator sg;
	public SimpleStringProperty timerString = new SimpleStringProperty();
	public SimpleStringProperty difficultyString = new SimpleStringProperty();

	// TODO add check to selected difficulty level
	public SimpleBooleanProperty easySelected = new SimpleBooleanProperty();
	public SimpleBooleanProperty normalSelected = new SimpleBooleanProperty();
	public SimpleBooleanProperty hardSelected = new SimpleBooleanProperty();

	private static GameOptionsViewModel INSTANCE;

	public GameOptionsViewModel() {
		INSTANCE = this;
		difficultyString.bindBidirectional(NewGame.difficultyString);
		timerString.bindBidirectional(Timer.timeCounter);
		difficultyString.set("Normal");
		Timer.pauseString.set("Pause Game");
	}

	public static GameOptionsViewModel getInstance() {
		return INSTANCE;
	}

	public void startNewGame() {
		NewGame.startNewGame();
	}

	public void startTimer() {
		NewGame.startTimer();
	}

	public void stopTimer() {
		NewGame.stopTimer();
	}

	public void setDifficulty(String text) {
		difficultyString.set(text);
		changeDifficulty();
		startNewGame();
	}

	public void setDifficultyCheck(String text) {
		difficultyString.set(text);

		changeDifficulty();
		startNewGame();
	}

	private void changeDifficulty() {
		NewGame.changeDifficulty();
	}

}
