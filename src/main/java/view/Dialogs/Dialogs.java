package view.Dialogs;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

import app.SudokuMain;
import model.NewGame;
import model.Timer;

public class Dialogs {

	public static void playAgainDialog() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("PUZZLE SOLVED!");
		alert.setHeaderText(
		        "Your time was: " + Timer.timeCounter.get() + "\n on Difficulty:" + NewGame.difficultyString.get());
		alert.setContentText("Play Again?");

		ButtonType buttonTypePlayAgain = new ButtonType("Play Again?");
		ButtonType buttonTypeCancel = new ButtonType("close", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypePlayAgain, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == buttonTypePlayAgain)
			NewGame.startNewGame();
		else
			System.out.println("cancel");
	}

	public static void showAboutDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(SudokuMain.title);
		alert.setHeaderText(SudokuMain.title);
		alert.setContentText("A Sudoku game with implementation of Saxonia Systems mvvmFX for JavaFX");

		alert.showAndWait();
	}

	public static void showHowToPlayDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		String title = "How to play";
		String rules = "\t\t\t RULES of Sudoku: " + "\n Fill each box with a number between 1 9 (1-9)"
		        + "\n A number can appear only ONCE on each ROW " + "\n A number can appear only ONCE on each COLUMN"
		        + "\n A number can appear only ONCE on each REGION (3x3)";

		String input = "\n\t\t\t Using Sudoku mvvmFX \n \nType a Number between 1-9"
		        + "\nRegistered fields are marked with a GREEN Background."
		        + " The registered number may or may not be the correct one."
		        + "\nIf Number is already on Row or Column, the background will change RED."
		        + " If the background is RED, the number won't be registered"
		        + "\nMovement with ArrowKeys within Fields is possible"
		        + "\nTo Mark numbers, right click on the field and select a number";
		alert.setTitle(title);
		alert.setHeaderText(rules);
		alert.setContentText(input);

		alert.showAndWait();
	}

}
