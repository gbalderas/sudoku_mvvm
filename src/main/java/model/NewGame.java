package model;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.grids.TextFieldViewModel;

public class NewGame {

	public static SudokuGenerator sg;

	public static SimpleStringProperty difficultyString = new SimpleStringProperty();

	public static void startNewGame() {
		changeDifficulty();
		sg = new SudokuGenerator(GameInfo.DIFFICULTY);
		new CurrentBoard();
		generateFields();
		GameInfo.enableGrid();
		Timer.resetTime();
	}

	public static void generateFields() {
		TextField tempTF = null;
		Label tempL = null;

		for (int i = 0; i < 81; i++) {
			tempTF = GameInfo.listOfFields.get(i);
			tempL = GameInfo.listOfLabels.get(i);
			TextFieldViewModel vm = GameInfo.listOfViewTuples.get(i).getViewModel();
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
				tempTF.setStyle("-fx-control-inner-background:#FFFFFF; -fx-opacity:1.0");
				tempL.setVisible(true);
			}
			tempTF.textProperty().addListener(vm.listener);
			vm.clearListOfMarkedNumbers();
		}
	}

	public static void changeDifficulty() {
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
		GameInfo.DIFFICULTY = num;
	}
}
