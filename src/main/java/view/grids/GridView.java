package view.grids;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class GridView implements FxmlView<GridViewModel>, Initializable {

	@FXML
	private GridPane gridPane;

	@InjectViewModel
	GridViewModel viewModel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gridPane.disableProperty().bind(viewModel.disabledGrid);
		
		GridViewModel.loadGrids(gridPane);
	}

}
