package model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import view.dialogs.Dialogs;

public class CurrentBoard {

	public static IntegerProperty numberFieldsLeft = new SimpleIntegerProperty();

	public static ArrayList<Integer> CURRENT = new ArrayList<Integer>(81);

	public static void loadNewBoard() {
		numberFieldsLeft.set(NewGame.getDifficulty());
		loadBoard();
	}

	public static Boolean isBoardCorrect() {
		if (numberFieldsLeft.get() == 0)
			return true;
		return false;
	}

	public static void endGame() {
		Timer.stopTime();
		System.out
		        .println("YOU WON!\nDifficulty: " + NewGame.difficultyString.getValue() + "\nFinished in: " + Timer.timeCounter.get());
		Dialogs.playAgainDialog();
	}

	private static void loadBoard() {
		CURRENT.clear();
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				CURRENT.add(SudokuGenerator.board[i][j]);
			}

	}

	public static void addToCurrentBoard(int id, int value) {
		CURRENT.set(id, value);
	}
}
