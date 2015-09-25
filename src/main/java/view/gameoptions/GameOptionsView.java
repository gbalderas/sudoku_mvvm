package view.gameoptions;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleButton;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

import model.Timer;

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
	private CheckMenuItem itemHard, itemEasy, itemNormal;

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

	@FXML
	private void setDifficultyCheck(ActionEvent event) {
		CheckMenuItem item = (CheckMenuItem) event.getSource();
		// if(itemEasy.isSelected())
		viewModel.setDifficultyCheck(item.getText());
		itemEasy.selectedProperty().set(false);
		itemNormal.selectedProperty().set(false);
		itemHard.selectedProperty().set(false);
		item.selectedProperty().set(true);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelTimer.textProperty().bindBidirectional(viewModel.timerString);
		labelDifficulty.textProperty().bindBidirectional(viewModel.difficultyString);
		buttonPauseGame.textProperty().bindBidirectional(Timer.pauseString);
		itemEasy.selectedProperty().bindBidirectional(viewModel.easySelected);
		itemNormal.selectedProperty().bindBidirectional(viewModel.normalSelected);
		itemHard.selectedProperty().bindBidirectional(viewModel.hardSelected);
		toggleShowHint.setVisible(false);
		toggleShowSolution.setVisible(false);

	}

}
