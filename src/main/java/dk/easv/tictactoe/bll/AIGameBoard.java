package dk.easv.tictactoe.bll;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIGameBoard implements IGameBoard{

    

    public int getNextPlayer() {
        return 0;
    }


    public boolean play(int col, int row) {
        return false;
    }


    public boolean isGameOver() {
        return false;
    }


    public ArrayList<Integer> getWinningLine() {
        return null;
    }


    public int getWinner() {
        return 0;
    }


    public void newGame() {

    }
}
