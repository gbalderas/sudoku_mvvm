package view.grids;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;

import model.GameInfo;

public class GridViewModel implements ViewModel {

	private static GridViewModel INSTANCE;

	public ReadOnlyBooleanWrapper disabledGrid = new ReadOnlyBooleanWrapper();

	public GridViewModel() {
		INSTANCE = this;
		for (int i = 0; i < 81; i++) {
			ViewTuple<TextFieldView, TextFieldViewModel> vt = FluentViewLoader.fxmlView(TextFieldView.class).load();
			GameInfo.listOfFields.add((TextField) vt.getView().getChildrenUnmodifiable().get(0));
			GameInfo.listOfLabels.add((Label) vt.getView().getChildrenUnmodifiable().get(1));
			GameInfo.listOfViewTuples.add(vt);
		}
		GameInfo.disabledGrid.set(true);
		disabledGrid.bind(GameInfo.disabledGrid);
	}

	public static GridViewModel getInstance() {
		return INSTANCE;
	}

}
