package model;

import java.util.ArrayList;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import de.saxsys.mvvmfx.ViewTuple;

import view.grids.TextFieldView;
import view.grids.TextFieldViewModel;

public class GameInfo {

	public static ArrayList<TextField> listOfFields = new ArrayList<>();
	public static ArrayList<Label> listOfLabels = new ArrayList<>();
	public static ArrayList<ViewTuple<TextFieldView, TextFieldViewModel>> listOfViewTuples = new ArrayList<>();

	public static SimpleIntegerProperty row = new SimpleIntegerProperty();
	public static SimpleIntegerProperty column = new SimpleIntegerProperty();
	public static SimpleIntegerProperty id = new SimpleIntegerProperty();

	public static ReadOnlyBooleanWrapper disabledGrid = new ReadOnlyBooleanWrapper();

	public static StringProperty pauseString = new SimpleStringProperty();

	public static int DIFFICULTY = 50;

	public static StringProperty timeCounter = new SimpleStringProperty();

	// TODO? move time counter here

	public static void disableGrid() {
		disabledGrid.set(true);
		TextFieldViewModel vm;
		for (int i = 0; i < 81; i++) {
			vm = GameInfo.listOfViewTuples.get(i).getViewModel();
			vm.registeredStyle.set(vm.registeredStyle.get() + ";-fx-opacity:0.0");
		}
	}

	public static void enableGrid() {
		disabledGrid.set(false);
		TextFieldViewModel vm;
		for (int i = 0; i < 81; i++) {
			vm = GameInfo.listOfViewTuples.get(i).getViewModel();
			String style = vm.registeredStyle.get().replaceAll(";-fx-opacity:0.0", ";-fx-opacity:1.0");
			vm.registeredStyle.set(style);
		}
	}
}
