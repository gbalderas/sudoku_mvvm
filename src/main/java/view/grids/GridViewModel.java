package view.grids;

import java.util.ArrayList;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.GameInfo;

public class GridViewModel implements ViewModel {

	private static GridViewModel INSTANCE;
	private static ArrayList<Parent> listOfViews = new ArrayList<>();

	public ReadOnlyBooleanWrapper disabledGrid = new ReadOnlyBooleanWrapper();

	public GridViewModel() {
		INSTANCE = this;
		for (int i = 0; i < 81; i++) {
			ViewTuple<TextFieldView, TextFieldViewModel> vt = FluentViewLoader.fxmlView(TextFieldView.class).load();
			GameInfo.listOfFields.add((TextField) vt.getView().getChildrenUnmodifiable().get(0));
			GameInfo.listOfLabels.add((Label) vt.getView().getChildrenUnmodifiable().get(1));
			GameInfo.listOfViewModels.add(vt.getViewModel());
			addToListOfViews(vt.getView());
			
		}
		GameInfo.disabledGrid.set(true);
		disabledGrid.bindBidirectional(GameInfo.disabledGrid);
	}

	public static GridViewModel getInstance() {
		return INSTANCE;
	}

	public static void loadGrids(GridPane gridPane) {
		int n = 0;
		for (int x = 0; x < 9; x++)
			for (int y = 0; y < 9; y++) {
				gridPane.add(listOfViews.get(n), y, x);
				n++;
			}
	}
	
	private void addToListOfViews(Parent view){
		listOfViews.add(view);
	}

}
