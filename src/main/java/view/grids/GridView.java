package view.grids;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import model.GameInfo;

public class GridView implements FxmlView<GridViewModel>, Initializable {

	@FXML
	private GridPane gridPane;

	@InjectViewModel
	GridViewModel viewModel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gridPane.disableProperty().bind(viewModel.disabledGrid);

		int n = 0;
		for (int x = 0; x < 9; x++)
			for (int y = 0; y < 9; y++) {
				gridPane.add(GameInfo.listOfViewTuples.get(n).getView(), y, x);
				n++;
			}
	}

}
