package view.menu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;

import de.saxsys.mvvmfx.ViewModel;

import view.Dialogs.Dialogs;
import view.gameoptions.GameOptionsViewModel;

public class MenuViewModel implements ViewModel {

	// TODO? add check to selected difficulty level

	public BooleanProperty selected = new SimpleBooleanProperty();

	public MenuViewModel() {
		selected.set(false);
	}

	public void newGame() {
		GameOptionsViewModel.getInstance().startNewGame();
	}

	public void exit() {
		System.exit(0);
	}

	public void showProgress() {
		boolean showed = ProgressViewModel.getInstance().pressedProperty.getValue();
		if (showed == false)
			ProgressViewModel.getInstance().pressedProperty.setValue(true);
		else
			ProgressViewModel.getInstance().pressedProperty.setValue(false);
	}

	public void changeDifficulty(ActionEvent event) {
		GameOptionsViewModel.getInstance().changeDifficultyString(event);
	}

	public void pauseGame() {
		GameOptionsViewModel.getInstance().stopTimer();
	}

	public void showHowToPlay() {
		Dialogs.showHowToPlayDialog();
	}

}
