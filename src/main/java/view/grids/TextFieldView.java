package view.grids;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import model.GameInfo;

public class TextFieldView implements FxmlView<TextFieldViewModel>, Initializable {

	@FXML
	private TextField textField;

	@FXML
	private MenuItem clearFieldItem;

	@FXML
	private MenuItem clearMarkedItem;

	@FXML
	private Label label;

	@InjectViewModel
	TextFieldViewModel viewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label.textProperty().bindBidirectional(viewModel.markedNumbers);
		textField.textProperty().bindBidirectional(viewModel.registeredNumber);
		textField.styleProperty().bindBidirectional(viewModel.registeredStyle);
		textField.opacityProperty().bindBidirectional(viewModel.opacityProperty);
		label.visibleProperty().bindBidirectional(viewModel.markedNumbersVisibility);

		textField.focusedProperty().addListener((ChangeListener<Boolean>) (arg0, oldV, newV) -> {
			if (newV) {
				Parent parent = textField.getParent();
				GameInfo.row.set(GridPane.getRowIndex(parent));
				GameInfo.column.set(GridPane.getColumnIndex(parent));
				int id = GridPane.getRowIndex(parent) * 9 + GridPane.getColumnIndex(parent);
				GameInfo.id.set(id);
			}
		});
		textField.disableProperty().bindBidirectional(viewModel.disable);
	}

	// TEXTFIELD ------
	@FXML
	void registerField() {
		viewModel.registerField();
	}

	@FXML
	void keyPressed(KeyEvent event) {
		KeyCode keyPressed = event.getCode();
		if (event.isControlDown() && keyPressed.isDigitKey()) {
			int number = Integer.parseInt(keyPressed.getName());
			viewModel.markNumberWithKeyboard(number);
		} else
			if (keyPressed.isArrowKey())
				viewModel.focusOtherField(keyPressed, 100);
	}

	// CONTEXT MENU -----
	@FXML
	void addNumberToMarkedList(ActionEvent event) {
		Button button = (Button) event.getSource();
		int buttonNumber = Integer.parseInt(button.getText());

		viewModel.addNumberToMarkedList(buttonNumber);
	}

	@FXML
	void clearMarkedNumbers() {
		viewModel.clearListOfMarkedNumbers();
	}

	@FXML
	void clearField() {
		viewModel.clearField();
	}

	@FXML
	void autoFillMarkedItems(ActionEvent event) {
		viewModel.autoFillMarkedItems();
	}

}
