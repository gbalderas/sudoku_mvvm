package view.menu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

import de.saxsys.mvvmfx.ViewModel;

import model.NewGame;
import model.Timer;
import view.Dialogs.Dialogs;
import view.gameoptions.GameOptionsViewModel;

public class MenuViewModel implements ViewModel {

	public BooleanProperty selected = new SimpleBooleanProperty();

	public MenuViewModel() {
		selected.set(false);
	}

	public void newGame() {
		NewGame.startNewGame();
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
		MenuItem item = (MenuItem) event.getSource();
		GameOptionsViewModel.getInstance().setDifficulty(item.getText());
	}

	public void pauseGame() {
		Timer.stopTime();
	}

	public void showHowToPlay() {
		Dialogs.showHowToPlayDialog();
	}

}
