package view.gameoptions;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import de.saxsys.mvvmfx.ViewModel;

import model.CurrentBoard;
import model.SudokuGenerator;
import model.Timer;
import view.grids.GridViewModel;
import view.grids.TextFieldViewModel;

public class GameOptionsViewModel implements ViewModel {

	// TODO? show hints?

	public int DIFFICULTY = 50;
	public SudokuGenerator sg;
	public Timer timer = new Timer();
	public SimpleStringProperty timerString = new SimpleStringProperty();
	public SimpleStringProperty difficultyString = new SimpleStringProperty();

	private static GameOptionsViewModel INSTANCE;

	public GameOptionsViewModel() {
		INSTANCE = this;
		difficultyString.set("Normal");
		timerString.bind(timer.timeCounter);
		timer.pauseString.set("Pause Game");
	}

	public static GameOptionsViewModel getInstance() {
		return INSTANCE;
	}

	public void startNewGame() {
		changeDifficulty();
		sg = new SudokuGenerator(DIFFICULTY);
		System.out.println(DIFFICULTY);
		new CurrentBoard();
		GridViewModel.getInstance().disabledGrid.set(false);
		generateFields();
		startTimer();
	}

	public void startTimer() {
		timer.resetTime();
		timer.startTime();
	}

	public void stopTimer() {
		timer.stopTime();
	}

	public void generateFields() {
		TextField tempTF = null;
		Label tempL = null;

		for (int i = 0; i < 81; i++) {
			tempTF = GridViewModel.getInstance().listOfFields.get(i);
			tempL = GridViewModel.getInstance().listOfLabels.get(i);
			TextFieldViewModel vm = GridViewModel.getInstance().listOfViewTuples.get(i).getViewModel();
			tempTF.textProperty().removeListener(vm.listener);
			if (CurrentBoard.CURRENT.get(i) != 0) {
				tempTF.setDisable(true);
				tempTF.setFont(Font.font(null, FontWeight.BOLD, 18));
				tempTF.setStyle("-fx-control-inner-background:#DCDEE2; -fx-opacity:1.0");
				tempTF.setText(CurrentBoard.CURRENT.get(i) + "");
				tempL.setVisible(false);
			} else {
				tempTF.setDisable(false);
				tempTF.setFont(Font.font(null, FontWeight.NORMAL, 18));
				tempTF.setText("");
				tempTF.setStyle("-fx-control-inner-background:#FFFFFF");
				tempL.setVisible(true);
			}
			tempTF.textProperty().addListener(vm.listener);
			vm.clearListOfMarkedNumbers();
		}
	}

	public void changeDifficultyString(ActionEvent event) {
		MenuItem item = (MenuItem) event.getSource();
		difficultyString.set(item.getText());
		changeDifficulty();
		startNewGame();
	}

	private void changeDifficulty() {
		Random r = new Random();
		int num = 50;
		switch (difficultyString.get()) {
			case "Easy":
				num = r.nextInt(49 - 40 + 1) + 40;
				break;
			default:
			case "Normal":
				num = r.nextInt(54 - 50 + 1) + 50;
				break;
			case "Hard":
				num = r.nextInt(58 - 54 + 1) + 54;
				break;
		}
		DIFFICULTY = num;
	}

	public SimpleStringProperty timerStringProperty() {
		return this.timerString;
	}

	public java.lang.String getTimerString() {
		return this.timerStringProperty().get();
	}

	public SimpleStringProperty difficultyStringProperty() {
		return this.difficultyString;
	}

	public java.lang.String getDifficultyString() {
		return this.difficultyStringProperty().get();
	}

}
