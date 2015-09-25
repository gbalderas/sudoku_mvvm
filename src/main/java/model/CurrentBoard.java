package model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import view.Dialogs.Dialogs;
import view.gameoptions.GameOptionsViewModel;
import view.menu.ProgressViewModel;

public class CurrentBoard {

	public static IntegerProperty numberFieldsLeft = new SimpleIntegerProperty();

	public static ArrayList<Integer> CURRENT = new ArrayList<Integer>();
	public static ArrayList<Integer> SOLUTION = new ArrayList<Integer>();

	public CurrentBoard() {
		numberFieldsLeft.set(GameOptionsViewModel.getInstance().DIFFICULTY);
		createBoards();
		printBoards();
		numberFieldsLeft.addListener((obs, oldV, newV) -> {
			double value = (GameOptionsViewModel.getInstance().DIFFICULTY - newV.doubleValue())
		            / GameOptionsViewModel.getInstance().DIFFICULTY;
			ProgressViewModel.getInstance().progress.set(value);
		});
	}

	public static void compareBoards() {
		int x = 81;
		for (int i = 0; i < 81; i++)
			if (CURRENT.get(i).equals(SOLUTION.get(i)))
				x--;
		numberFieldsLeft.setValue(x);
		if (x == 0) {
			GameOptionsViewModel.getInstance().timer.stopTime();
			System.out.println("YOU WON!\nDifficulty: " + GameOptionsViewModel.getInstance().DIFFICULTY
			        + "\nFinished in: " + GameOptionsViewModel.getInstance().timer.getTimeCounter());
			Dialogs.playAgainDialog();
		}
	}

	private void createBoards() {
		CURRENT.clear();
		SOLUTION.clear();
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				CURRENT.add(GameOptionsViewModel.getInstance().sg.board[i][j]);
				SOLUTION.add(GameOptionsViewModel.getInstance().sg.boardSolution[i][j]);
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
