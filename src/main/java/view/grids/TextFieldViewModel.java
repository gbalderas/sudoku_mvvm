package view.grids;

import java.util.Vector;

import javax.imageio.spi.RegisterableService;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import model.CurrentBoard;
import model.GameInfo;
import model.NumberValidation;

public class TextFieldViewModel implements ViewModel {
	private Vector<Integer> listOfMarkedNumbers = new Vector<Integer>();

	public BooleanProperty markedNumbersVisibility = new SimpleBooleanProperty();
	public StringProperty markedNumbers = new SimpleStringProperty();
	public StringProperty registeredNumber = new SimpleStringProperty();
	public StringProperty registeredStyle = new SimpleStringProperty();
	public SimpleDoubleProperty opacityProperty = new SimpleDoubleProperty();
	public BooleanProperty disable = new SimpleBooleanProperty();

	private static ChangeListener<String> LISTENER;
	private static TextFieldViewModel INSTANCE;

	public TextFieldViewModel() {
		INSTANCE = this;
		LISTENER = listener;
	}

	public static TextFieldViewModel getInstance() {
		return INSTANCE;
	}
	
	public static ChangeListener<String> getListener(){
		return LISTENER;
	}

	private ChangeListener<String> listener = (observable, oldValue, newValue) -> {
		if (newValue.isEmpty() || newValue.matches("[1-9]") || newValue.equals(" ")) {
			if (!newValue.equals(oldValue))
				registerField();
		} else
			registeredNumber.set(oldValue);
	};

	// CONTEXT MENU ---------------------------------------------------------
	
	public void addNumberToMarkedList(int buttonNumber) {
		// add to marked list
		if (!this.listOfMarkedNumbers.contains(buttonNumber)) {
			this.listOfMarkedNumbers.add(buttonNumber);
			this.listOfMarkedNumbers.sort(null);
		} else // remove from marked list
			this.listOfMarkedNumbers.removeElement(buttonNumber);
		// show marked list
		if (!this.listOfMarkedNumbers.isEmpty())
			markedNumbers.set(this.listOfMarkedNumbers.toString().replaceAll("[\\[\\],]", ""));
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
				CurrentBoard.numberFieldsLeft.set(CurrentBoard.numberFieldsLeft.get() - 1);
			} else {
				// number not valid
				registeredStyle.set("-fx-background-color:#FF6666");
				markedNumbersVisibility.set(true);
			}
			if (CurrentBoard.isBoardCorrect())
				CurrentBoard.endGame();
		} catch (NumberFormatException e) {
			// entered value was not a number
			resetField();
			registeredStyle.set("-fx-background-color:#FF6666");
		}
	}

	private void resetField() {
		if (CurrentBoard.CURRENT.get(getID()) != 0)
			CurrentBoard.numberFieldsLeft.set(CurrentBoard.numberFieldsLeft.get() + 1);
		CurrentBoard.addToCurrentBoard(getID(), 0);
		registeredNumber.set("");
		registeredStyle.set("");
		markedNumbersVisibility.set(true);
	}

	// for positioning purposes
	private int getID() {
		return GameInfo.id.get();
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
			TextField textField = GameInfo.listOfFields.get(id);
			if (textField.isDisabled())
				focusOtherField(keyPressed, id);
			textField.requestFocus();
		} catch (ArrayIndexOutOfBoundsException e) {
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public void autoFillMarkedItems() {
		for (int i = 1; i < 10; i++)
			if (NumberValidation.isNumberValid(i))
				addNumberToMarkedList(i);
		System.out.println("Field checked");
	}

	public void markNumberWithKeyboard(int number) {
		if (number == 0)
			clearField();
		else
			addNumberToMarkedList(number);

	}
}
