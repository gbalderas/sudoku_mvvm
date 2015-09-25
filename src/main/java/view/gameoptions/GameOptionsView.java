package view.gameoptions;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleButton;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

public class GameOptionsView implements FxmlView<GameOptionsViewModel>, Initializable {

	@FXML
	private Button buttonPauseGame;

	@FXML
	private ToggleButton toggleShowHint;

	@FXML
	private ToggleButton toggleShowSolution;

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
		viewModel.changeDifficultyString(event);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelTimer.textProperty().bindBidirectional(viewModel.timerString);
		labelDifficulty.textProperty().bindBidirectional(viewModel.difficultyString);
		buttonPauseGame.textProperty().bindBidirectional(GameOptionsViewModel.getInstance().timer.pauseString);
		toggleShowHint.setVisible(false);
		toggleShowSolution.setVisible(false);
	}

}
