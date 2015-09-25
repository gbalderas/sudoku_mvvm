package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.saxsys.mvvmfx.FluentViewLoader;

import view.MainView;
import view.Dialogs.Dialogs;

public class SudokuMain_1_1 extends Application {

	public static final String title = "Sudoku mvvmFX";
	// TODO? Create own sudoku board generator

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
