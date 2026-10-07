package dk.easv.tictactoe.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dk.easv.tictactoe.bll.AIGameBoard;
import dk.easv.tictactoe.gui.TicTacToe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class IntroController {

    @FXML
    private void startSingleplayer(ActionEvent actionEvent) throws IOException {
        AIGameBoard game = new AIGameBoard();
        TicTacToe.changeScene(game);
    }


    @FXML
    private void startMultiplayer(ActionEvent actionEvent) throws IOException {
        GameBoard game = new GameBoard();
        TicTacToe.changeScene(game);
    }


}

