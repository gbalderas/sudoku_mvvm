package view.gameoptions;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

public class GameOptionsView implements FxmlView<GameOptionsViewModel>, Initializable {

	@FXML
	private Button buttonPauseGame;

	@FXML
	private Button buttonNewGame;

	@FXML
	private Label labelTimer;
	@FXML
	private Label labelDifficulty;

	@FXML
	private SplitMenuButton splitButtonNewGame;

	@FXML
	// start timer
	private void newGame() {
		viewModel.startNewGame();
	}

	@FXML
	// stop timer
	private void pauseGame() {
		viewModel.stopTimer();
	}

	@InjectViewModel
	private GameOptionsViewModel viewModel;

	@FXML
	private void setDifficulty(ActionEvent event) {
		MenuItem item = (MenuItem) event.getSource();
		viewModel.setDifficulty(item.getText());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelTimer.textProperty().bindBidirectional(viewModel.timer);
		labelDifficulty.textProperty().bindBidirectional(viewModel.difficulty);
		buttonPauseGame.textProperty().bindBidirectional(viewModel.paused);

	}

	@FXML
	void autoMarkFields(ActionEvent event) throws InterruptedException {
		viewModel.autoMarkFields();
	}

	@FXML
	void autoFillRegion() {
		viewModel.autoFillRegion();
	}
}
