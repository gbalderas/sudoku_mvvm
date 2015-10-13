package view.menu;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import model.CurrentBoard;

public class ProgressViewModel implements ViewModel {

	public DoubleProperty progress = new SimpleDoubleProperty();
	public ReadOnlyBooleanWrapper visible = new ReadOnlyBooleanWrapper();
	public Property<Boolean> pressedProperty = new ReadOnlyBooleanWrapper();
	public Property<String> fieldsLeft = new SimpleStringProperty();

	private static ProgressViewModel INSTANCE;

	public ProgressViewModel() {
		progress.bindBidirectional(CurrentBoard.progress);
		fieldsLeft.bind(CurrentBoard.numberFieldsLeft.asString());
		pressedProperty.setValue(false);
		pressedProperty.addListener((obs, oldV, newV) -> {
			if (pressedProperty.getValue() == true)
				visible.set(true);
			else
				visible.set(false);
		});

		INSTANCE = this;
	}

	public static ProgressViewModel getInstance() {
		return INSTANCE;
	}

}
