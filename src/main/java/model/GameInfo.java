package model;

import java.util.ArrayList;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.grids.TextFieldViewModel;

public class GameInfo {

	public static ArrayList<TextField> listOfFields = new ArrayList<>();
	public static ArrayList<Label> listOfLabels = new ArrayList<>();
	public static ArrayList<TextFieldViewModel>listOfViewModels = new ArrayList<>();

	public static SimpleIntegerProperty row = new SimpleIntegerProperty();
	public static SimpleIntegerProperty column = new SimpleIntegerProperty();
	public static SimpleIntegerProperty id = new SimpleIntegerProperty();

	public static ReadOnlyBooleanWrapper disabledGrid = new ReadOnlyBooleanWrapper();
	public static StringProperty pauseString = new SimpleStringProperty();

	public static void disableGrid() {
		disabledGrid.set(true);
		
		listOfViewModels.forEach(vm -> {
			vm.opacityProperty.set(0);
		});
	}

	public static void enableGrid() {
		disabledGrid.set(false);
		
		listOfViewModels.forEach(vm -> {
			vm.opacityProperty.set(1);
		});
	}
}
