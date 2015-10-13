package view;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import model.GameInfo;

public class MainViewModel implements ViewModel {

	SimpleStringProperty row = new SimpleStringProperty();
	SimpleStringProperty column = new SimpleStringProperty();
	SimpleStringProperty id = new SimpleStringProperty();
	private static MainViewModel INSTANCE;

	public MainViewModel() {
		INSTANCE = this;
		row.bind(GameInfo.row.asString());
		column.bind(GameInfo.column.asString());
		id.bind(GameInfo.id.asString());

	}

	public static MainViewModel getInstance() {
		return INSTANCE;
	}

}
