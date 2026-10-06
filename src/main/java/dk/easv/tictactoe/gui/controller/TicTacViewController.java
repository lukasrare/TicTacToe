package dk.easv.tictactoe.gui.controller;
// Java imports
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

/**
 *
 * @author EASV
 */
public class TicTacViewController implements Initializable {
    @FXML
    private Label lblPlayer;
    @FXML
    private Button btnNewGame;
    @FXML
    private GridPane gridPane;
    private static final String TXT_PLAYER = "Player: ";
    private IGameBoard game;

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getNextPlayer();
            if (game.play(c, r))
            {
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                }
                else {setPlayer();}
                Button btn = (Button) event.getSource();
                btn.setText(player == 0 ? "X" : "O");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event) {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Set the next player
     */
    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + (game.getNextPlayer()+1));
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner) {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                setWinningLine();
                message = "Player " + (winner+1) + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    private void setWinningLine() {
        ArrayList<Integer> values = game.getWinningLine();
        System.out.println("Winning line: " + values);

        for(Integer value : values) {
            for(Node n : gridPane.getChildren()) {
                Button btn = (Button) n;
                if(String.valueOf(value + 1).equals(btn.getId().substring(3))) {
                    btn.setFont(Font.font("System", FontWeight.BOLD, 14));
                }
            }
        }
    }

    /**
     * Clears the game board in the GUI and resets the font for all the buttons.
     */
    private void clearBoard() {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
            btn.setFont(Font.font("System", FontWeight.NORMAL, 12));
        }
    }

    public void setGame(IGameBoard game) {
        this.game = game;
        setPlayer();
    }
}
