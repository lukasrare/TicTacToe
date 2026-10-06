
package dk.easv.tictactoe.gui;

// Java imports
import dk.easv.tictactoe.gui.controller.IntroController;
import dk.easv.tictactoe.gui.controller.TicTacViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author EASV
 */
public class TicTacToe extends Application
{
    private static Stage primaryStage;
    /**
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/views/Menu.fxml"));
        Parent scene = fxmlLoader.load();
        stage.setScene(new Scene(scene));
        stage.setResizable(false);
        stage.setTitle("Tic Tac Toe");
        stage.centerOnScreen();
        stage.show();
        primaryStage = stage;
    }



    public static void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("/views/TicTacView.fxml"));
        Parent scene = fxmlLoader.load();
        TicTacViewController controller = fxmlLoader.getController();
        Stage stage = primaryStage;
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Tic Tac Toe");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Entry point of the application
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
