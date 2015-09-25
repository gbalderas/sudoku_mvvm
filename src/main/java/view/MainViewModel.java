package view;

import javafx.beans.property.SimpleStringProperty;

import de.saxsys.mvvmfx.ViewModel;

public class MainViewModel implements ViewModel {

	public SimpleStringProperty row = new SimpleStringProperty();
	public SimpleStringProperty column = new SimpleStringProperty();
	public SimpleStringProperty id = new SimpleStringProperty();
	private static MainViewModel INSTANCE;

	public MainViewModel() {
		INSTANCE = this;
	}

	public static MainViewModel getInstance() {
		return INSTANCE;
	}

}
