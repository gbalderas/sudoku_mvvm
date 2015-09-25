package view.menu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

public class ProgressView implements FxmlView<ProgressViewModel>, Initializable {

	@FXML
	private Label labelFieldsLeft;

	@FXML
	private ProgressIndicator progressIndicator;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private ToggleButton toggleButtonShowProgress;
	@FXML
	private HBox hbox;

	@InjectViewModel
	ProgressViewModel viewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelFieldsLeft.textProperty().bindBidirectional(viewModel.fieldsLeft);
		progressBar.progressProperty().bind(viewModel.progress);
		progressIndicator.progressProperty().bind(viewModel.progress);
		hbox.visibleProperty().bind(viewModel.visible);
		progressBar.visibleProperty().bind(viewModel.visible);
		progressIndicator.visibleProperty().bind(viewModel.visible);
		toggleButtonShowProgress.selectedProperty().bindBidirectional(viewModel.pressedProperty);
	}

}
