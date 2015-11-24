package model;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.grids.TextFieldViewModel;

public class NewGame {

	private static int difficulty = 0;

	public static SimpleStringProperty difficultyString = new SimpleStringProperty();

	public static void startNewGame() {
		changeDifficulty();
		SudokuGenerator.newSudokuGenerator(difficulty);
		CurrentBoard.loadNewBoard();
		generateFields();
		GameInfo.enableGrid();
		Timer.resetTime();
	}

	public static void generateFields() {
		for (int i = 0; i < 81; i++) {
			TextField tempTF = GameInfo.listOfFields.get(i);
			Label tempL = GameInfo.listOfLabels.get(i);
			TextFieldViewModel vm = GameInfo.listOfViewModels.get(i);
			tempTF.textProperty().removeListener(TextFieldViewModel.getListener());
			tempTF.setStyle(null);
			if (CurrentBoard.CURRENT.get(i) != 0) {
				tempTF.setDisable(true);
				tempTF.setFont(Font.font(null, FontWeight.BOLD, 18));
				tempTF.setStyle("-fx-control-inner-background:#DCDEE2");
				tempTF.setText(CurrentBoard.CURRENT.get(i).toString());
				tempL.setVisible(false);
			} else {
				tempTF.setDisable(false);
				tempTF.setFont(Font.font(null, FontWeight.NORMAL, 18));
				tempTF.setText(null);
				tempTF.setStyle("-fx-control-inner-background:#FFFFFF");
				tempL.setVisible(true);
			}
			tempTF.textProperty().addListener(TextFieldViewModel.getListener());
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
		difficulty = num;
	}
	
	public static int getDifficulty() {
		return difficulty;
	}
}
