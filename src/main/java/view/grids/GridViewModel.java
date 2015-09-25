package view.grids;

import java.util.ArrayList;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;

public class GridViewModel implements ViewModel {

	private static GridViewModel INSTANCE;

	public ReadOnlyBooleanWrapper disabledGrid = new ReadOnlyBooleanWrapper();
	public ArrayList<TextField> listOfFields = new ArrayList<>();
	public ArrayList<Label> listOfLabels = new ArrayList<>();
	public ArrayList<ViewTuple<TextFieldView, TextFieldViewModel>> listOfViewTuples = new ArrayList<>();

	public GridViewModel() {
		disabledGrid.set(true);
		INSTANCE = this;
		for (int i = 0; i < 81; i++) {
			ViewTuple<TextFieldView, TextFieldViewModel> vt = FluentViewLoader.fxmlView(TextFieldView.class).load();
			listOfFields.add((TextField) vt.getView().getChildrenUnmodifiable().get(0));
			listOfLabels.add((Label) vt.getView().getChildrenUnmodifiable().get(1));
			listOfViewTuples.add(vt);
		}
	}

	public static GridViewModel getInstance() {
		return INSTANCE;
	}

	public void disableGrid() {
		disabledGrid.set(true);
		TextFieldViewModel vm;
		for (int i = 0; i < 81; i++) {
			vm = GridViewModel.getInstance().listOfViewTuples.get(i).getViewModel();
			vm.registeredStyle.set(vm.registeredStyle.get() + ";-fx-opacity:0.0");
		}
	}

	public void enableGrid() {
		disabledGrid.set(false);
		TextFieldViewModel vm;
		for (int i = 0; i < 81; i++) {
			vm = GridViewModel.getInstance().listOfViewTuples.get(i).getViewModel();
			vm.registeredStyle.set(vm.registeredStyle.get() + ";-fx-opacity:1.0");
		}
	}

}
