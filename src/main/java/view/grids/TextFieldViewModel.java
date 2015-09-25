package view.grids;

import java.util.List;
import java.util.Vector;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import de.saxsys.mvvmfx.ViewModel;

import model.CurrentBoard;
import model.NumberValidation;
import view.MainViewModel;

public class TextFieldViewModel implements ViewModel {
	private List<Integer> listOfMarkedNumbers = new Vector<Integer>();

	public BooleanProperty markedNumbersVisibility = new SimpleBooleanProperty();
	public StringProperty markedNumbers = new SimpleStringProperty();
	public StringProperty registeredNumber = new SimpleStringProperty();
	public StringProperty registeredStyle = new SimpleStringProperty();
	public BooleanProperty focus = new SimpleBooleanProperty();

	private static TextFieldViewModel INSTANCE;

	public TextFieldViewModel() {
		INSTANCE = this;
	}

	public static TextFieldViewModel getInstance() {
		return INSTANCE;
	}

	public ChangeListener<String> listener = (observable, oldValue, newValue) -> {
		if (newValue.isEmpty() || newValue.matches("[1-9]") || newValue.equals(" ")) {
			if (!newValue.equals(oldValue))
				registerField();
		} else
			registeredNumber.set(oldValue);
	};

	// CONTEXT MENU ---------------------------------------------------------

	public void addNumberToMarkedList(int buttonNumber) {
		// add to marked list
		if (!listOfMarkedNumbers.contains(buttonNumber)) {
			listOfMarkedNumbers.add(buttonNumber);
			listOfMarkedNumbers.sort(null);
		} else // remove from marked list
			listOfMarkedNumbers.remove(listOfMarkedNumbers.indexOf(buttonNumber));

		// show marked list
		if (!listOfMarkedNumbers.isEmpty())
			markedNumbers.set(listOfMarkedNumbers.toString());
		else // show nothing
			markedNumbers.set("");
	}

	public void clearListOfMarkedNumbers() {
		listOfMarkedNumbers.clear();
		markedNumbers.set("");
	}

	public void clearField() {
		clearListOfMarkedNumbers();
		resetField();
	}

	// TEXTFIELD -----------------------------------------------------------

	public void registerField() {
		if (registeredNumber.get().equals("") || registeredNumber.get().equals(" ")) {
			resetField();
			return;
		}
		try {
			int newValue = Integer.parseInt(registeredNumber.get());
			// check if number is valid
			if (NumberValidation.isNumberValid(newValue)) {
				if (CurrentBoard.CURRENT.get(getID()) == newValue)
					return;
				CurrentBoard.addToCurrentBoard(getID(), newValue);
				registeredStyle.set("-fx-background-color:#99CC99");
				markedNumbersVisibility.set(false);
			} else {
				// number not valid
				registeredStyle.set("-fx-background-color:#FF6666");
				markedNumbersVisibility.set(true);
			}
			CurrentBoard.compareBoards();
		} catch (NumberFormatException e) {
			// entered value was not a number
			resetField();
			registeredStyle.set("-fx-background-color:#FF6666");
		}
	}

	private void resetField() {
		CurrentBoard.addToCurrentBoard(getID(), 0);
		registeredNumber.set("");
		registeredStyle.set("");
		markedNumbersVisibility.set(true);
	}

	// for positioning purposes
	private int getID() {
		return Integer.parseInt(MainViewModel.getInstance().id.get());
	}

	// id 100 -> default placeholder for starting application
	public void focusOtherField(KeyCode keyPressed, int id) {
		if (id == 100)
			id = getID();
		if (id > 81 || id < 0)
			return;
		try {
			switch (keyPressed) {
				case UP:
					id = id - 9;
					break;
				case DOWN:
					id = id + 9;
					break;
				case LEFT:
					id = id - 1;
					break;
				case RIGHT:
					id = id + 1;
					break;
				default:
					return;
			}
			TextField textField = GridViewModel.getInstance().listOfFields.get(id);
			if (textField.isDisabled())
				focusOtherField(keyPressed, id);
			textField.requestFocus();
		} catch (ArrayIndexOutOfBoundsException e) {
		} catch (IndexOutOfBoundsException e) {
		}
	}
}
