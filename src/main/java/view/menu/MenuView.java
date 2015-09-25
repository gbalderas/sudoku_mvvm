package view.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

import view.Dialogs.Dialogs;

public class MenuView implements FxmlView<MenuViewModel> {

	@InjectViewModel
	private MenuViewModel viewModel;

	@FXML
	void newGame() {
		viewModel.newGame();
	}

	@FXML
	void exitApplication() {
		viewModel.exit();
	}

	@FXML
	void showProgress() {
		viewModel.showProgress();
	}

	@FXML
	void changeDifficulty(ActionEvent event) {
		viewModel.changeDifficulty(event);
	}

	@FXML
	void pauseGame() {
		viewModel.pauseGame();
	}

	@FXML
	void showAboutDialog() {
		Dialogs.showAboutDialog();
	}

	@FXML
	void showHowToPlayDialog() {
		Dialogs.showHowToPlayDialog();
	}

}
