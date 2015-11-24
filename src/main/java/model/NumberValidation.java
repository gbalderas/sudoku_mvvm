package model;

import java.util.ArrayList;

public class NumberValidation {
	private static int ROW;
	private static int COLUMN;

	public static Boolean validateNumber(int numberToValidate) {
		COLUMN = GameInfo.column.get();
		ROW = GameInfo.row.get();
		int id = GameInfo.id.get();
		if (CurrentBoard.CURRENT.get(id) == numberToValidate)
			return true;
		if (!isRegionValid(numberToValidate))
			return false;
		if (!areRowsAndColumnsValid(numberToValidate))
			return false;
		System.out.println(numberToValidate + " is VALID!");
		return true;
	}

	public static boolean isNumberValid(int numberToValidate) {
		return validateNumber(numberToValidate);
	}

	private static Boolean areRowsAndColumnsValid(int numberToValidate) {

		for (int i = 0; i < 9; i++) {
			if (CurrentBoard.CURRENT.get(ROW * 9 + i) == numberToValidate) {
				System.out.println("NUMBER IS NOT VALID: (row) " + i);
				return false;
			}
			if (CurrentBoard.CURRENT.get(COLUMN + 9 * i) == numberToValidate) {
				System.out.println("NUMBER IS NOT VALID: (column) " + i);
				return false;
			}
		}
		return true;
	}

	private static Boolean isRegionValid(int numberToValidate) {
		int middle = 0;
		int i = 0;
		int j = 0;
		if (COLUMN > 2)
			if (COLUMN < 6)
				i = 1;
			else
				i = 2;
		if (ROW > 2)
			if (ROW < 6)
				j = 1;
			else
				j = 2;
		int n = j * 10 + i;
		switch (n) {
			case 0:
				middle = 10;
				break;
			case 1:
				middle = 13;
				break;
			case 2:
				middle = 16;
				break;
			case 10:
				middle = 37;
				break;
			case 11:
				middle = 40;
				break;
			case 12:
				middle = 43;
				break;
			case 20:
				middle = 64;
				break;
			case 21:
				middle = 67;
				break;
			case 22:
				middle = 70;
				break;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(CurrentBoard.CURRENT.get(middle).intValue());
		list.add(CurrentBoard.CURRENT.get(middle - 10).intValue());
		list.add(CurrentBoard.CURRENT.get(middle - 9).intValue());
		list.add(CurrentBoard.CURRENT.get(middle - 8).intValue());
		list.add(CurrentBoard.CURRENT.get(middle - 1).intValue());
		list.add(CurrentBoard.CURRENT.get(middle + 1).intValue());
		list.add(CurrentBoard.CURRENT.get(middle + 8).intValue());
		list.add(CurrentBoard.CURRENT.get(middle + 9).intValue());
		list.add(CurrentBoard.CURRENT.get(middle + 10).intValue());

		if (list.contains(numberToValidate)) {
			System.out.println("NUMBER IS NOT VALID: (region)");
			return false;
		}
		return true;
	}

}
