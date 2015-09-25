package model;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import view.Dialogs.Dialogs;

public class CurrentBoard {

	public static IntegerProperty numberFieldsLeft = new SimpleIntegerProperty();
	public static DoubleProperty progress = new SimpleDoubleProperty();

	public static ArrayList<Integer> CURRENT = new ArrayList<Integer>();
	public static ArrayList<Integer> SOLUTION = new ArrayList<Integer>();
	public static int DIFFICULTY = 50;

	public CurrentBoard() {
		numberFieldsLeft.set(DIFFICULTY);
		createBoards();
		printBoards();
		numberFieldsLeft.addListener((obs, oldV, newV) -> {
			double value = (DIFFICULTY - newV.doubleValue()) / DIFFICULTY;
			progress.set(value);
		});
	}

	public static void compareBoards() {
		int x = 81;
		for (int i = 0; i < 81; i++)
			if (CURRENT.get(i).equals(SOLUTION.get(i)))
				x--;
		numberFieldsLeft.setValue(x);
		if (x == 0) {
			NewGame.timer.stopTime();
			System.out.println(
			        "YOU WON!\nDifficulty: " + DIFFICULTY + "\nFinished in: " + NewGame.timer.getTimeCounter());
			Dialogs.playAgainDialog();
		}
	}

	private void createBoards() {
		CURRENT.clear();
		SOLUTION.clear();
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				CURRENT.add(NewGame.sg.board[i][j]);
				SOLUTION.add(NewGame.sg.boardSolution[i][j]);
			}

	}

	public static void addToCurrentBoard(int id, int value) {
		CURRENT.set(id, value);
	}

	private void printBoards() {
		int n = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (j == 3 || j == 6)
					System.out.print("|");
				System.out.print(CURRENT.get(n) + " ");
				n++;
			}
			System.out.println();
			if (i == 2 || i == 5)
				System.out.println("------------------");
		}
		System.out.println();
		System.out.println();
		n = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (j == 3 || j == 6)
					System.out.print("|");
				System.out.print(SOLUTION.get(n) + " ");
				n++;
			}
			System.out.println();
			if (i == 2 || i == 5)
				System.out.println("------------------");
		}
	}
}