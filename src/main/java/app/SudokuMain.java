package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.saxsys.mvvmfx.FluentViewLoader;

import view.MainView;
import view.Dialogs.Dialogs;

public class SudokuMain extends Application {
	// FIXME Create own Sudoku board generator using NumberValidation
	// TODO create Sudoku solver -> testing

	public static final String title = "Sudoku mvvmFX";

	public static void main(String a[]) {
		launch(a);
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle(title);
		Scene scene = new Scene(FluentViewLoader.fxmlView(MainView.class).load().getView());

		stage.setScene(scene);
		stage.setMinHeight(700);
		stage.setMinWidth(700);
		stage.show();

		Dialogs.showHowToPlayDialog();
	}

}
