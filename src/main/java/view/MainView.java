package view;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainView implements FxmlView<MainViewModel>, Initializable {

	@FXML
	private Label labelRow, labelColumn, labelID;

	@InjectViewModel
	private MainViewModel viewModel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		labelRow.textProperty().bindBidirectional(viewModel.row);
		labelColumn.textProperty().bindBidirectional(viewModel.column);
		labelID.textProperty().bindBidirectional(viewModel.id);
	}

}
